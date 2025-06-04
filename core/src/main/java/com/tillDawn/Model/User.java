package com.tillDawn.Model;

import com.tillDawn.Model.Enums.HeroType;
import com.tillDawn.Model.Enums.WeaponType;

public class User {
    private int usrID;
    public String username;
    public String password;
    public int securityQuestionIndex;
    public String securityAnswer;
    public String avatarPath;
    private HeroType heroType;
    private WeaponType weaponType;
    private int score;
    private int killNumber;
    private int maxSurvivalTime;

    public User(String username, String password, int securityQuestionIndex, String securityAnswer, String avatarPath) {
        this.username = username;
        this.password = password;
        this.securityQuestionIndex = securityQuestionIndex;
        this.securityAnswer = securityAnswer;
        this.avatarPath = avatarPath;
        this.heroType = HeroType.SHANA;
        this.weaponType = WeaponType.SMG;
        score = 0;
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

    public HeroType getHeroType() {
        return heroType;
    }

    public void setHeroType(HeroType heroType) {
        this.heroType = heroType;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int amount) {
        score += amount;
    }

    public int getUsrID() {
        return usrID;
    }

    public void setUsrID(int usrID) {
        this.usrID = usrID;
    }

    public int getKillNumber() {
        return killNumber;
    }

    public void addKillNumber(int amount) {
        killNumber += amount;
    }

    public int getMaxSurvivalTime() {
        return maxSurvivalTime;
    }

    public void setMaxSurvivalTime(int maxSurvivalTime) {
        this.maxSurvivalTime = maxSurvivalTime;
    }
}
