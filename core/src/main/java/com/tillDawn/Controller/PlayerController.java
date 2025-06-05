package com.tillDawn.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tillDawn.Model.*;
import com.tillDawn.Model.Enums.Ability;

import java.util.ArrayList;
import java.util.Random;

public class PlayerController {
    private WorldController worldController;
    private WeaponController weaponController;
    private Player player;
    private float speedyDeadline;
    private boolean isSpeedy;

    public PlayerController(Player player, WorldController worldController, WeaponController weaponController) {
        this.player = player;
        this.worldController = worldController;
        this.weaponController = weaponController;
    }

    public void update() {
        handlePlayerInput();
        handleAbility();
        handleLevel();
        handleHp();
        idleAnimation();
        player.getPlayerSprite().draw(com.tilldawn.Main.getBatch());
        weaponController.syncWeaponWithPlayer(player.getPlayerSprite());

    }

    public void handlePlayerInput() {
        Sprite sprite = player.getPlayerSprite();
        boolean moving = false;

        float speed = player.getSpeed();

        if (isSpeedy) {
            speed = player.getSpeed() * 2;
        }

        CollisionRect newRect = new CollisionRect(player.getRect().getX(), player.getRect().getY(), player.getRect().getWidth(), player.getRect().getHeight());
        if (App.getKeyboardManager().isUp()) {
            newRect.setY(newRect.getY() + speed);
            if (!worldController.isBlocked(newRect)) {
                sprite.translateY(speed);
                moving = true;
            }
        }
        if (App.getKeyboardManager().isDown()) {
            newRect.setY(newRect.getY() - speed);
            if (!worldController.isBlocked(newRect)) {
                sprite.translateY(-speed);
                moving = true;
            }
        }
        if (App.getKeyboardManager().isRight()) {
            newRect.setY(newRect.getX() + speed);
            if (!worldController.isBlocked(newRect)) {
                if (!player.isFacingRight()) {
                    sprite.flip(true, false);
                    player.setFacingRight(true);
                }
                sprite.translateX(speed);
                moving = true;
            }

        }
        if (App.getKeyboardManager().isLeft()) {
            newRect.setY(newRect.getX() - speed);
            if (!worldController.isBlocked(newRect)) {
                if (player.isFacingRight()) {
                    sprite.flip(true, false);
                    player.setFacingRight(false);
                }
                sprite.translateX(-speed);
                moving = true;
            }
        }

        player.setPlayerIdle(!moving);
    }

    public void idleAnimation() {
        Animation<Texture> animation = GameAssetManager.getGameAssetManager().getHeroAnimation(player.getHeroType());
        Sprite sprite = player.getPlayerSprite();

        sprite.setRegion(animation.getKeyFrame(player.getTime(), true));

        player.setTime(player.getTime() + Gdx.graphics.getDeltaTime());
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void handleLevel() {
        int exp = player.getExp();

        for (int n = 0; n < 100; n++) {
            if (exp >= n * (n - 1) * 10 && exp < n * (n + 1) * 10) {
                player.setLevel(n);
                return;
            }
        }
    }

    public void handleAbility() {
        if (player.getLevel() <= player.getAbilities().size()) return;

        ArrayList<Ability> availableAbilities = new ArrayList<>();
        for (Ability a : Ability.values()) {
            if (!player.getAbilities().contains(a)) {
                availableAbilities.add(a);
            }
        }
        if (!availableAbilities.isEmpty()) {
            int num = new Random().nextInt(availableAbilities.size());
            player.addAbility(availableAbilities.get(num));
        }
    }

    public float getSpeedyDeadline() {
        return speedyDeadline;
    }

    public void setSpeedyDeadline(float speedyDeadline) {
        this.speedyDeadline = speedyDeadline;
    }

    public boolean isSpeedy() {
        return isSpeedy;
    }

    public void setSpeedy(boolean speedy) {
        isSpeedy = speedy;
    }

    public WorldController getWorldController() {
        return worldController;
    }

    public void setWorldController(WorldController worldController) {
        this.worldController = worldController;
    }

    public void handleHp() {
        if (worldController.isClose(player.getRect())) {
            player.reduceHp(1);
        }
    }
}
