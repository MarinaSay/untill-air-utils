# unTill AIR Utils

## Requirements
  * JDK 1.8 or higher
  * maven;

#### Example how to install on Windows
```shell
choco install jdk8 maven
```

## How to build

```shell
mvn clean compile assembly:single
```
## How to use 
### Fill location with data
Creates 2 categories,4 groups, 15 departaments, 100 articles, 4 users, 2 printers.
```shell
java -cp ./target/airutils.jar com.untillairutils.LocationLoader https://dev.air.untill.com user@email.com 123456 "My Location"
```
Specify correct URL, login and password, location name.
### Clear location  data
Deletes all users, printers, articles, departments, categories and groups.
```shell
java -cp ./target/airutils.jar com.untillairutils.LocationCleaner https://dev.air.untill.com user@email.com 123456 "My Location"
```
Specify the same as in fill location: URL, login and password, location name. 




