def read_fixture path
  file = File.open(File.join(File.dirname(__FILE__), "../fixtures/#{path}"), 'r')
  content = file.read
  file.close

  content
end

def read_json path
  JSON.parse read_fixture path
end