package com.tillDawn.Model.Enums;

public enum HeroType {
    SHANA(1, 4, 4),
    DIAMOND(2, 7, 1),
    SCARLET(3, 3, 5),
    LILITH(4, 5, 3),
    DASHER(5, 2, 10);

    private final int heroNumber;
    private final int hp;
    private final int speed;

    HeroType(int heroNumber, int hp, int speed) {
        this.heroNumber = heroNumber;
        this.hp = hp;
        this.speed = speed;
    }

    public int getHeroNumber() {
        return heroNumber;
    }

    public int getHp() {
        return hp;
    }

    public int getSpeed() {
        return speed;
    }
}
