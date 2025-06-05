
package com.tillDawn.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

public class TreeEnemy {

    private Texture texture;
    private Vector2 position;
    private CollisionRect collisionRect;

    private int health;
    private boolean alive;

    private static final int WIDTH = 64;
    private static final int HEIGHT = 64;

    public TreeEnemy(Texture texture, float x, float y) {
        this.texture = texture;
        this.position = new Vector2(x, y);
        this.health = 3;
        this.alive = true;

        this.collisionRect = new CollisionRect(x, y, WIDTH, HEIGHT);
    }

    public void update() {

    }

    public void render(Batch batch) {
        if (alive)
            batch.draw(texture, position.x, position.y, WIDTH, HEIGHT);
    }

    public void takeDamage(int dmg) {
        if (!alive) return;

        health -= dmg;
        if (health <= 0) {
            alive = false;
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public CollisionRect getCollisionRect() {
        return collisionRect;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void onPlayerCollision(Player player) {
        if (alive && player.getRect().collidesWith(collisionRect)) {
            player.reduceHp(1);
        }
    }
}


