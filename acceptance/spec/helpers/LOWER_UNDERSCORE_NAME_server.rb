require_relative './retry_for'

class APPLICATION_NAMEServer
  attr_reader :app_port

  def initialize(port, simulator_port)
    @app_port = port
    @simulator_port = simulator_port
  end

  def start
    return if app_is_running?

    unless artifact_exists?
      puts ''
      puts '<'*80
      puts ''
      puts "No artifact found, create with 'cd ~/workspace/SERVICE_GIT_PROJECT_NAME/ && ./gradlew clean assemble'"
      puts ''
      puts '>'*80
      exit!
    end

    java_opts = ENV['ACCEPTANCE_JAVA_OPTS']
    @service_offering_groups_app_pid = Process.spawn(
        "SERVER_PORT=#{app_port} SIMULATOR_PORT=#{simulator_port} java #{java_opts} -jar build/libs/SERVICE_GIT_PROJECT_NAME.jar",
        chdir: '../',
        pgroup: true,
        out: 'application.std.log',
        err: 'application.err.log'
    )

    puts "Attempting to start the application at http://localhost:#{app_port}"
    retry_for(240) {
      resp = health_check
      raise 'Application never came up. Does `gradle dev` work?' unless resp.code == 200
    }

    resp = health_check
    puts resp.body
  end

  def stop
    return unless service_offering_groups_app_pid
    puts 'Shutting down the application'
    puts "Killing pid #{service_offering_groups_app_pid}"
    Process.kill('TERM', service_offering_groups_app_pid)
  end

  private
  attr_reader :service_offering_groups_app_pid, :simulator_port

  def health_check
    HTTParty.get("http://localhost:#{app_port}/health")
  end

  def app_is_running?
    begin
      health_check.code == 200
    rescue
      false
    end
  end

  def artifact_exists?
    artifact_found = `[ -e ../build/libs/SERVICE_GIT_PROJECT_NAME.jar ] && echo true || echo false`
    return true if artifact_found == "true\n"
    false
  end
end
