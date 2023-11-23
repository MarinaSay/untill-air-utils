# unTill AIR Utils

## Requirements
  * JDK 1.8 or higher
  * maven;

### Example how to install on Windows

Windows:
```shell
choco install jdk8 maven
```

Linux:
```shell
sudo apt install openjdk-8-jdk maven
```

## How to build

```shell
mvn clean compile assembly:single
```

## Fill location with data
Create 2 categories,4 groups, 15 departaments, 100 articles, 4 users, 2 printers.

Windows:
```shell
location.bat load http://dev.air.untill.com user@email.com 123456 "My Location"
```

Linux:
```shell
./location load http://dev.air.untill.com user@email.com 123456 "My Location"
```

(Specify correct URL, login and password, location name)

## Clear location  data
Delete all users, printers, articles, departments, categories and groups.

Windows:
```shell
location.bat http://dev.air.untill.com clean user@email.com 123456 "My Location"
```

Linux:
```shell
./location clean http://dev.air.untill.com user@email.com 123456 "My Location"
```

(Specify correct URL, login and password, location name)



