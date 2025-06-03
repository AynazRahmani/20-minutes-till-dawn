package com.tillDawn.Model;

public class User {
    public String username;
    public String password;
    public int securityQuestionIndex;
    public String securityAnswer;
    public String avatarPath;

    public User(String username, String password, int securityQuestionIndex, String securityAnswer, String avatarPath) {
        this.username = username;
        this.password = password;
        this.securityQuestionIndex = securityQuestionIndex;
        this.securityAnswer = securityAnswer;
        this.avatarPath = avatarPath;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getSecurityQuestionIndex() {
        return securityQuestionIndex;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSecurityQuestionIndex(int securityQuestionIndex) {
        this.securityQuestionIndex = securityQuestionIndex;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }
}
