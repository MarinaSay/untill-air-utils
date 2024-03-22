@echo off

rem Check if the correct number of arguments are provided
if "%~5"=="" (
    echo Usage: %0 ^<load^|clean^> ^<url^> ^<login^> ^<password^> ^<location_name^>
    exit /b 1
)

rem Set the Java classpath and execute the Java command based on the first argument
if /i "%1"=="load" (
	mvn clean compile exec:java -Dexec.mainClass=com.untillairutils.LocationLoader -Dexec.args="%2 %3 %4 %5"
) else if /i "%1"=="clean" (
	mvn clean compile exec:java -Dexec.mainClass=com.untillairutils.LocationCleaner -Dexec.args="%2 %3 %4 %5"
   ; java -cp ./target/airutils.jar com.untillairutils.LocationCleaner %2 %3 %4 %5
) else (
    echo Invalid option. Use 'load' or 'clean' as the first argument.
    exit /b 1
)