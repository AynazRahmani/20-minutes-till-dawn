package com.tillDawn.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tillDawn.Model.App;
import com.tillDawn.Model.Enums.Ability;
import com.tillDawn.Model.GameAssetManager;
import com.tillDawn.Model.KeyboardManager;
import com.tillDawn.Model.Player;

import java.util.ArrayList;
import java.util.Random;

public class PlayerController {
    private Player player;

    public PlayerController(Player player) {
        this.player = player;
    }

    public void update() {
        handlePlayerInput();
        idleAnimation();
        player.getPlayerSprite().draw(com.tilldawn.Main.getBatch());
        handleAbility();
        handleLevel();
    }

    public void handlePlayerInput() {
        Sprite sprite = player.getPlayerSprite();
        boolean moving = false;

        if (App.getKeyboardManager().isUp()) {
            sprite.translateY(player.getSpeed());
            moving = true;
        }
        if (App.getKeyboardManager().isDown()) {
            sprite.translateY(-player.getSpeed());
            moving = true;
        }
        if (App.getKeyboardManager().isRight()) {
            if (!player.isFacingRight()) {
                sprite.flip(true, false);
                player.setFacingRight(true);
            }
            sprite.translateX(player.getSpeed());
            moving = true;
        }
        if (App.getKeyboardManager().isLeft()) {
            if (player.isFacingRight()) {
                sprite.flip(true, false);
                player.setFacingRight(false);
            }
            sprite.translateX(-player.getSpeed());
            moving = true;
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
        int level = player.getLevel();
        int exp = player.getExp();
        if (level * (level - 1) < exp / 10) {
            player.setLevel(level + 1);
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
        int num = new Random().nextInt(availableAbilities.size());
        player.addAbility(availableAbilities.get(num));
    }
}
