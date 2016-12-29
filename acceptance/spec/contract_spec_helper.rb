require 'json-schema'
require 'capybara/rspec'

def update_contracts
  `
    mkdir -p tmp/#{ENV['SIMULATOR']} && cd tmp/#{ENV['SIMULATOR']} && rm -rf *
    echo "CLONING contracts into ./tmp/#{ENV['SIMULATOR']}";
    git clone https://gitlab.global.dish.com/contracts/contracts.git;
  `
end

def remove_contracts
  `rm -rf tmp/#{ENV['SIMULATOR']}`
end

def load_schema(app, filename)
  JSON.parse(read_fixture("../../tmp/#{ENV['SIMULATOR']}/contracts/ofm/#{app}/#{filename}.json"))
end

def dereference_external_schemas
  schemas = []

  schemas.each do |schema|
    new_schema = JSON::Schema.new(load_schema(schema[:app], schema[:file]), "#{schema[:file]}.json")
    JSON::Validator.add_schema(new_schema)
  end
end

RSpec.configure do |config|
  config.include Capybara::DSL

  config.before :suite do
    update_contracts
    dereference_external_schemas
  end

  config.after :suite do
    remove_contracts
  end
end

def fully_validate(data)
  expect(JSON::Validator.fully_validate(schema, data)).to be_empty
  expect(JSON::Validator.validate(schema, data)).to eq true
end

def validate(endpoint, fixture)
  data = JSON.parse(read_fixture("#{endpoint}/#{fixture}.json"))
  fully_validate(data)
end

def validate_java_fixture(fixture)
  data = JSON.parse(read_fixture("../../../server/src/test/resources/fixtures/#{fixture}.json"))
  fully_validate(data)
end