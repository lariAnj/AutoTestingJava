package org.vk.utilityClasses;

public class TestBot {
    private final String login;
    private final String password;
    private String ID;

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

}
