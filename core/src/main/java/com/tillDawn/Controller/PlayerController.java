//package com.tillDawn.Controller;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Input;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.Animation;
//import com.tillDawn.Model.GameAssetManager;
//import com.tillDawn.Model.Player;
//
//public class PlayerController {
//    private Player player;
//
//    public PlayerController(Player player){
//        this.player = player;
//    }
//
//    public void update(){
//        player.getPlayerSprite().draw(com.tilldawn.Main.getBatch());
//
//        if(player.isPlayerIdle()){
//            idleAnimation();
//        }
//
//        handlePlayerInput();
//    }
//
//
//    public void handlePlayerInput(){
//        if (Gdx.input.isKeyPressed(Input.Keys.W)){
//            player.setPosY(player.getPosY() - player.getSpeed());
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.D)){
//            player.setPosX(player.getPosX() - player.getSpeed());
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.S)){
//            player.setPosY(player.getPosY() + player.getSpeed());
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.A)){
//            player.setPosX(player.getPosX() + player.getSpeed());
//            player.getPlayerSprite().flip(true, false);
//        }
//    }
//
//
//    public void idleAnimation(){
//        Animation<Texture> animation = GameAssetManager.getGameAssetManager().getCharacter1_idle_animation();
//
//        player.getPlayerSprite().setRegion(animation.getKeyFrame(player.getTime()));
//
//        if (!animation.isAnimationFinished(player.getTime())) {
//            player.setTime(player.getTime() + Gdx.graphics.getDeltaTime());
//        }
//        else {
//            player.setTime(0);
//        }
//
//        animation.setPlayMode(Animation.PlayMode.LOOP);
//    }
//
//    public Player getPlayer() {
//        return player;
//    }
//
//    public void setPlayer(Player player) {
//        this.player = player;
//    }
//}




package com.tillDawn.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tillDawn.Model.GameAssetManager;
import com.tillDawn.Model.Player;

public class PlayerController {
    private Player player;

    public PlayerController(Player player) {
        this.player = player;
    }

    public void update() {
        handlePlayerInput();
        idleAnimation();
        player.getPlayerSprite().draw(com.tilldawn.Main.getBatch());
    }

    public void handlePlayerInput() {
        Sprite sprite = player.getPlayerSprite();
        boolean moving = false;

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            sprite.translateY(player.getSpeed());
            moving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            sprite.translateY(-player.getSpeed());
            moving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            if (!player.isFacingRight()) {
                sprite.flip(true, false);
                player.setFacingRight(true);
            }
            sprite.translateX(player.getSpeed());
            moving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
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
}
