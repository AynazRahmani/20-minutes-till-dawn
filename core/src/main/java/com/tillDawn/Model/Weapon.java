package com.tillDawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tillDawn.Model.Enums.WeaponType;

public class Weapon {

    private WeaponType weaponType;
    private final Texture smgTexture;
    private Sprite smgSprite;
    private int ammo;
    private boolean maxAmmo = false;

    public Weapon(WeaponType weaponType){
        this.weaponType = weaponType;
        smgTexture  = new Texture(GameAssetManager.getGameAssetManager().getSmg(weaponType));
        smgSprite = new Sprite(smgTexture);
        smgSprite.setX((float) Gdx.graphics.getWidth() / 2 );
        smgSprite.setY((float) Gdx.graphics.getHeight() / 2);
        smgSprite.setSize(50,50);

        ammo = weaponType.getAmmoMax();

    }

    public Sprite getSmgSprite() {
        return smgSprite;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo){
        this.ammo = ammo;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    public void reduceAmmo(int amount) {
        ammo -= amount;
    }


    public boolean isMaxAmmo() {
        return maxAmmo;
    }

    public void setMaxAmmo(boolean maxAmmo) {
        this.maxAmmo = maxAmmo;
    }

    public void reload() {
        if (maxAmmo) {
            ammo = weaponType.getAmmoMax() + 5;
        }
        else {
            ammo = weaponType.getAmmoMax();
        }
    }
}
