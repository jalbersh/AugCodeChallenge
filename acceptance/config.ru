require 'simulators'

Simulators.configure do |config|
  config.contract_repo = "https://gitlab.global.dish.com/contracts/contracts.git"
  config.response_path = ENV['PATH_TO_FIXTURES'] || './spec/fixtures'
  config.schema_path = './simulatorTmp'

  # Configuration for a simulator:
  # '/uri for the service' => {
  #     method: <:get, :put, :post, :delete>,
  #     schema_file: schema file path in the schema_path directory,
  #     default_response: json OR read_response(response file path in the response_path directory),
  #     name: 'Display name for service endpoint',
  #     service: 'Service Group Name',
  # },

  config.endpoint_configurations = {
  }
end

require 'simulators/listing_app'
run Simulators::ListingApp
