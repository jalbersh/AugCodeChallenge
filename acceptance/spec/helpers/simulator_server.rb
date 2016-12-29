require_relative './retry_for'
require 'httparty'
require_relative './read_fixture'

class SimulatorServer
  attr_reader :port

  def initialize(port)
    @port = port
  end

  def start
    return if app_is_running?

    @offering_groups_simulator_pid = Process.spawn(
      "rackup config.ru -p #{port}",
      pgroup: true,
      out: 'rackup.std.log',
      err: 'rackup.err.log'
    )

    puts "Attempting to start the simulators on port #{port}"
    retry_for(20) {
      resp = health_check
      if resp.code == 200
        assert_valid_default_responses
      else
        raise 'Simulators never came up'
      end
    }

    resp = health_check
    puts resp.body
  end

  def reset
    HTTParty.delete("http://localhost:#{port}/responses")
    HTTParty.delete("http://localhost:#{port}/requests")
  end

  def assert_no_bad_requests
    bad_requests = JSON.parse(HTTParty.get("http://localhost:#{port}/bad-requests.json").body)

    if bad_requests.any?
      formatted_explanations = bad_requests.map do |request|
        <<-BODY
        Your #{request['method']} to #{request['path']}?#{request['queryString']}
        had the following contract violation(s):

        #{request['contractViolations'].map { |v| "- #{v}" }.join("\n")}

        With body:
        #{request['body']}
        BODY
      end

      fail("\n" + formatted_explanations.join("\n(╯°□°）╯︵ ┻━┻\n"))
    end
  end

  def stop
    return unless offering_groups_simulator_pid

    puts 'Shutting down the simulators'

    puts "Killing pid #{offering_groups_simulator_pid}"
    Process.kill('TERM', offering_groups_simulator_pid)
  end

  def assert_valid_default_responses
    formatted_explanations = endpoint_configuration_violations.map do |endpoint, configuration|
      <<-BODY
        Fixture (#{configuration[:default_response_file]}) for endpoint #{endpoint} was not valid. Here's why:

        #{configuration[:contract_violations].map { |violation| "- #{violation}" }.join("\n")}
      BODY
    end

    if formatted_explanations.any?
      fail("\n" + formatted_explanations.join("\n(╯°□°）╯︵ ┻━┻\n"))
    end
  end

  private
  attr_reader :offering_groups_simulator_pid

  def endpoint_configuration_violations
    JSON.parse(HTTParty.get("http://localhost:#{port}/invalid-endpoint-configurations.json"), symbolize_names: true)
  end

  def health_check
    HTTParty.get("http://localhost:#{port}/healthcheck")
  end

  def app_is_running?
    begin
      health_check.code == 200
    rescue
      false
    end
  end
end
