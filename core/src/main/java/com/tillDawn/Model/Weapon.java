package com.tillDawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tillDawn.Model.Enums.WeaponType;

public class Weapon {

    private WeaponType weaponType;
    private final Texture smgTexture = new Texture(GameAssetManager.getGameAssetManager().getSmg());
    private Sprite smgSprite = new Sprite(smgTexture);
    private int ammo = 30;

    public Weapon(WeaponType weaponType){
        smgSprite.setX((float) Gdx.graphics.getWidth() / 2 );
        smgSprite.setY((float) Gdx.graphics.getHeight() / 2);
        smgSprite.setSize(50,50);

        this.weaponType = weaponType;
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
}
