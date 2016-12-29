require 'rubygems'
require 'bundler/setup'
require 'pp'
require 'pry'

require_relative 'helpers/read_fixture'

RSpec.configure do |config|
  if ENV['SPRING_PROFILES_ACTIVE'] == 'ci'
    config.before(:example, :focus) { raise "Should not commit focused specs" }
  end
  config.filter_run :focus
  config.example_status_persistence_file_path = "examples.txt"
  config.run_all_when_everything_filtered = true
  config.default_formatter = 'doc'
  config.order = :random
end
