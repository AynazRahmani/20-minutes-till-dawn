package com.tillDawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tillDawn.Model.Enums.Ability;
import com.tillDawn.Model.Enums.HeroType;

import java.util.ArrayList;

public class Player {
    private HeroType heroType;
    private Texture playerTexture = new Texture(GameAssetManager.getGameAssetManager().getHeroPath(heroType));
    private Sprite playerSprite = new Sprite(playerTexture);
    private float playerHealth = 100;
    private CollisionRect rect;
    private float time = 0;
    private float speed;
    private boolean facingRight = true;
    private int hp;
    private int killsNumber;
    private int exp = 0;
    private int level = 0;
    private ArrayList<Ability> abilities = new ArrayList<>();

    private boolean isPlayerIdle = true;
    private boolean isPlayerRunning = false;

    public Player(HeroType heroType) {
        this.heroType = heroType;
//        playerTexture = new Texture(GameAssetManager.getGameAssetManager().getHeroPath(heroType));
        float startX = (float) Gdx.graphics.getWidth() / 2;
        float startY = (float) Gdx.graphics.getHeight() / 2;

        playerSprite.setPosition(startX, startY);
        playerSprite.setSize(playerTexture.getWidth() * 3, playerTexture.getHeight() * 3);

        rect = new CollisionRect(startX, startY, playerSprite.getWidth(), playerSprite.getHeight());
        hp = heroType.getHp();
        speed = heroType.getSpeed();

    }

    public Sprite getPlayerSprite() {
        return playerSprite;
    }

    public float getPlayerHealth() {
        return playerHealth;
    }

    public void setPlayerHealth(float playerHealth) {
        this.playerHealth = playerHealth;
    }

    public CollisionRect getRect() {
        return rect;
    }

    public boolean isPlayerIdle() {
        return isPlayerIdle;
    }

    public void setPlayerIdle(boolean playerIdle) {
        isPlayerIdle = playerIdle;
    }

    public boolean isPlayerRunning() {
        return isPlayerRunning;
    }

    public void setPlayerRunning(boolean playerRunning) {
        isPlayerRunning = playerRunning;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public float getSpeed() {
        return speed;
    }

    public boolean isFacingRight() {
        return facingRight;
    }

    public void setFacingRight(boolean facingRight) {
        this.facingRight = facingRight;
    }

    public HeroType getHeroType() {
        return heroType;
    }

    public void setHeroType(HeroType heroType) {
        this.heroType = heroType;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void addHp(int amount) {
        this.hp += amount;
    }

    public void reduceHp(int amount) {
        this.hp -= amount;
        if (hp < 0) hp = 0;
    }

    public int getKillsNumber() {
        return killsNumber;
    }

    public void addKillsNumber(int amount) {
        this.killsNumber += amount;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void addExp (int amount) {
        exp += amount;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public ArrayList<Ability> getAbilities() {
        return abilities;
    }

    public void addAbility(Ability ability) {
        abilities.add(ability);
    }
}
