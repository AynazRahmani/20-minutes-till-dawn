package com.tillDawn.Model;

import com.tillDawn.Model.Enums.HeroType;
import com.tillDawn.Model.Enums.WeaponType;

public class PreGame {
    private HeroType heroType;
    private WeaponType weaponType;
    private int duration;

    public PreGame() {
        this.heroType = HeroType.SHANA;
        this.weaponType = WeaponType.SMG;
        this.duration = 2;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
