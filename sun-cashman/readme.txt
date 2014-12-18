Cashman
 v0.11

Requirement:
 jdk1.7
 maven 3
 junit 4.11

Compile:
 mvn compile

Test:
 mvn test
 
Test result:
 Tests run: 27, Failures: 7, Errors: 0, Skipped: 0
 reason for Failures: 7: not implemented
 
Assumption/s:
# authentication pass
# balance check pass
# re-supplier done in no time
 
Todo:
# coin support is half finished, only support dollar now
# some test/s are not implemented
# combination algorithm to be improved
# test coverage to be improved

Docs can be found under folder ./doc
# brief class diagram can be found at ./doc/device-controller-cls.png
# combination sequence can be found at ./doc/combination-builder-seq.png
# device state can be found at ./doc/device-state.png
# initialization sequence can be found at ./doc/init-seq.png
# notification sequence can be found at ./doc/device-notification-seq.png

Change log:
 #0.11 coin support (not finish)
 #0.10 dollar support
 
Author:
 bill