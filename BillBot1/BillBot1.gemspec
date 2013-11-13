# coding: utf-8
lib = File.expand_path('../lib', __FILE__)
$LOAD_PATH.unshift(lib) unless $LOAD_PATH.include?(lib)
require 'BillBot1/version'

Gem::Specification.new do |spec|
  spec.name          = "BillBot1"
  spec.version       = BillBot1::VERSION
  spec.authors       = ["Bill"]
  spec.email         = ["autoaim800@gmail.com"]
  spec.description   = %q{Toy Robot 5x5 code test for real-group}
  spec.summary       = %q{Toy Robot place, move and turn on 5x5 square table}
  #spec.homepage      = "na"
  #spec.license       = "na"

  spec.files         = `git ls-files`.split($/)
  spec.executables   = spec.files.grep(%r{^bin/}) { |f| File.basename(f) }
  spec.test_files    = spec.files.grep(%r{^(test|spec|features)/})
  spec.require_paths = ["lib"]

  spec.add_development_dependency "bundler", "~> 1.3"
  spec.add_development_dependency "rake"
end
