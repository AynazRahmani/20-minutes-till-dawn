package com.tillDawn.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
    private Texture texture = new Texture(GameAssetManager.getGameAssetManager().getBullet());
    private Sprite sprite = new Sprite(texture);
    private int damage = 5;
    private Vector2 direction;

    public Bullet(float startX, float startY, float targetX, float targetY){
        sprite.setSize(20, 20);
        sprite.setPosition(startX, startY);

        direction = new Vector2(targetX - startX, targetY - startY).nor();
    }

    public Sprite getSprite() {
        return sprite;
    }

    public int getDamage() {
        return damage;
    }

    public Vector2 getDirection() {
        return direction;
    }
}
