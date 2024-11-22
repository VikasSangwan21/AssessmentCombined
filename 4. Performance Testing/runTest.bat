@echo off
cd C:\apache jmeter\apache-jmeter-5.6.3\bin
jmeter.bat -n -t "C:\Users\user\Desktop\SOAR\Performance Testing\SoarLoadTest.jmx" -l "C:\Users\user\Desktop\SOAR\Performance Testing\results\SoarLoadTest.jtl" -e -o "C:\Users\user\Desktop\SOAR\Performance Testing\results"