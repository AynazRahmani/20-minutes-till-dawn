package com.tillDawn.Model.Enums;

public enum WeaponType {
    REVOLVER(1, 20, 1, 1, 6),
    SHOTGUN(2, 10, 4, 1, 2),
    SMG(3, 8, 1, 2, 24);

    private final int weaponId;
    private final int damage;
    private final int projectile;
    private final int reloadTime;
    private final int ammoMax;

    WeaponType(int weaponId, int damage, int projectile, int reloadTime, int ammoMax) {
        this.weaponId = weaponId;
        this.damage = damage;
        this.projectile = projectile;
        this.reloadTime = reloadTime;
        this.ammoMax = ammoMax;
    }

    public int getDamage() {
        return damage;
    }

    public int getProjectile() {
        return projectile;
    }

    public int getReloadTime() {
        return reloadTime;
    }

    public int getAmmoMax() {
        return ammoMax;
    }


    public int getWeaponId() {
        return weaponId;
    }
}
