package org.vk;


import java.time.LocalDate;

public class TestBot {
    private final String login;
    private final String password;
    private String ID;
    private String firstName;
    private String lastName;
    private int birthday;
    private int birthmonth;
    private int birthyear;

    public TestBot(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public TestBot(String login, String password, String ID) {
        this.login = login;
        this.password = password;
        this.ID = ID;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getBirthday() {
        return birthday;
    }

    public TestBot setBirthday(int birthday) {
        if (birthday < 1 || birthday > 31) {
            throw new IllegalArgumentException("Invalid day of month: " + birthday);
        }
        this.birthday = birthday;
        return this;
    }

    public int getBirthmonth() {
        return birthmonth;
    }

    public TestBot setBirthmonth(int birthmonth) {
        if (birthmonth < 1 || birthmonth > 12) {
            throw new IllegalArgumentException("Invalid month: " + birthmonth);
        }
        this.birthmonth = birthmonth;
        return this;
    }

    public int getBirthyear() {
        return birthyear;
    }

    public TestBot setBirthyear(int birthyear) {
        int currentYear = LocalDate.now().getYear();
        if (birthyear < 1900 || birthyear > currentYear) {
            throw new IllegalArgumentException("Invalid year: " + birthyear);
        }
        this.birthyear = birthyear;
        return this;
    }

}
