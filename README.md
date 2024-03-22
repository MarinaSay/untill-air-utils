# unTill AIR Web Automation

## Requirements
  * JDK 1.11
  * maven;

Windows:
```shell
choco install choco install liberica11jdk maven
```

Linux:
```shell
sudo apt install openjdk-11-jdk maven
```

## Fill Location With Data
Create 2 categories, 4 groups, 15 departaments, 100 articles, 4 users, 2 printers.

Windows:
```shell
location.bat load http://dev.air.untill.com user@email.com 123456 "My Location"
```

Linux:
```shell
./location load http://dev.air.untill.com user@email.com 123456 "My Location"
```

(Specify correct URL, login and password, location name)

## Clear Location Data
Delete all users, printers, articles, departments, categories and groups.

Windows:
```shell
location.bat http://test.air.untill.com clean user@email.com 123456 "My Location"
```

Linux:
```shell
./location clean http://test.air.untill.com user@email.com 123456 "My Location"
```

(Specify correct URL, login and password, location name)

## Backoffice Tests
### Run Tests
```shell
set UNTILLAIR_URL=http://dev.air.untill.com
set UNTILLAIR_LOGIN=username
set UNTILLAIR_PASSWORD=password
set UNTILLAIR_LOCATION="My Location"
mvn test
```

### List of Tests
- Login Page
    - Has all required elements: 
        - text boxes
        - "Sign in" and "Sign up" buttons
        - "Forgot password" button, email input box
    - Switching languages
- Home Page
    - My Profile button, which shows user name and email
    - "Welcome" box shown on login
    - Switching languages
    - Search box
- Creating and deleting entities:
    - Spaces
    - General
        - Discounts
        - POS Users
        - Equipment:
            - Printers
    - Products:
        - Categories
        - Groups
        - Departments
        - Articles




