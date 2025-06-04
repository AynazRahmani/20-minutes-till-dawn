package com.tillDawn.Model.Enums;

public enum HeroType {
    SHANA(1),
    DIAMOND(2),
    SCARLET(3),
    LILITH(4),
    DASHER(5);

    private final int heroNumber;

    HeroType(int heroNumber) {
        this.heroNumber = heroNumber;
    }

    public int getHeroNumber() {
        return heroNumber;
    }
}
