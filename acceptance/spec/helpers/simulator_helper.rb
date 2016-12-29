def set_response(fixture)
  endpoint = "http://localhost:#{ENV['SIMULATOR']}/response/path/to/resource"
  assert_schema_valid(HTTParty.put(endpoint, body: get_fixture_data(fixture)), fixture)
end

def set_invalid_response(fixture, status=200)
  endpoint = "http://localhost:#{ENV['SIMULATOR']}/response/path/to/resource"
  HTTParty.put(endpoint, body: get_fixture_data(fixture, status))
end

def assert_schema_valid(response, fixture)
  contract_violations = JSON.parse(response.body)['contractViolations']

  if contract_violations.any?
    fail(<<-BODY)
Attempt to set response for #{response.request.uri} did not match the schema.

Response from spec/fixtures/#{fixture}.json had the following contract violation(s):
#{contract_violations.map{|cv| "- #{cv}"}.join("\n")}

Body:
#{JSON.parse(JSON.parse(response.request.raw_body)['response']).pretty_inspect}
    BODY
  end
end
