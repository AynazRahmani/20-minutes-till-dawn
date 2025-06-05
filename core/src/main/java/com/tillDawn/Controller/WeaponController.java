package com.tillDawn.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.tillDawn.Model.Bullet;
import com.tillDawn.Model.Weapon;

import java.util.ArrayList;

public class WeaponController {
    private Weapon weapon;
    private ArrayList<Bullet> bullets = new ArrayList<>();

    public WeaponController(Weapon weapon){
        this.weapon = weapon;
    }

    public void update(){
        weapon.getSmgSprite().draw(com.tilldawn.Main.getBatch());
        updateBullets();
    }

    public void handleWeaponRotation(int x, int y) {
        Sprite weaponSprite = weapon.getSmgSprite();

        float weaponCenterX = (float) Gdx.graphics.getWidth() / 2;
        float weaponCenterY = (float) Gdx.graphics.getHeight() / 2;

        float angle = (float) Math.atan2(y - weaponCenterY, x - weaponCenterX);

        weaponSprite.setRotation((float) (3.14 - angle * MathUtils.radiansToDegrees));
    }

    public void handleWeaponShoot(int mouseX, int mouseY){
        Sprite weaponSprite = weapon.getSmgSprite();

        float startX = weaponSprite.getX();
        float startY = weaponSprite.getY();

        bullets.add(new Bullet(startX, startY, mouseX, mouseY));
        weapon.setAmmo(weapon.getAmmo() - 1);
    }


    public void updateBullets() {
        for (Bullet b : bullets) {
            b.getSprite().draw(com.tilldawn.Main.getBatch());

            b.getSprite().translate(b.getDirection().x * 5, b.getDirection().y * 5);
        }
    }



    public void syncWeaponWithPlayer(Sprite playerSprite) {
        Sprite weaponSprite = weapon.getSmgSprite();

        float offsetX = playerSprite.getWidth() / 2f;
        float offsetY = playerSprite.getHeight() / 3f;

        weaponSprite.setPosition(
            playerSprite.getX() + offsetX,
            playerSprite.getY() + offsetY
        );
    }

}
