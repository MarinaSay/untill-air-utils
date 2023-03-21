# unTill AIR Utils

## Requirements
  * maven;
  * chrome;
  * [chromedriver](https://chromedriver.chromium.org/downloads)
## How to build

```shell
mvn clean compile assembly:single
```
## How to use 
### Fill location with data
Creates 2 categories,4 groups, 15 departaments, 100 articles, 4 users.
```shell
java -cp ./target/airutils.jar com.untillairutils.LocationLoader /path/to/chromedriver https://dev.air.untill.com user@email.com 123456 "My Location"
```
Specify correct path to chromedriver, URL, login and password, location name.
### Clear location  data
Deletes all users, articles, departments, categories and groups.
```shell
java -cp ./target/airutils.jar com.untillairutils.LocationCleaner /path/to/chromedriver https://dev.air.untill.com user@email.com 123456 "My Location"
```
Specify the same as in fill location: path to chromedriver,URL, login and password, location name. 




