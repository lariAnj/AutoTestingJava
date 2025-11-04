This is a repository for works on the course "Automated testing and QA".
This project presents the main features and patterns for autotesting the OK social network web appliction.

## Usage
Clone this repo

```bash
git clone https://github.com/lariAnj/AutoTestingJava
```
Run using gradle

- to run all tests
```bash
./gradlew test
```

- to run concrete test class

  OK functions avaiable for testing:
  - Authorization (LoginPageTest class)
  - Notifications (NotificationsPageTest class)
  - Photos page (PhotosPgeTest class)
  - Main page (UserPageTest class)
```bash
./gradlew test --tests "org.vk.TestClassName"
```
