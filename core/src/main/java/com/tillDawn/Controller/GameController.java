package com.tillDawn.Controller;

import com.tillDawn.Model.Enums.HeroType;
import com.tillDawn.Model.Enums.WeaponType;
import com.tillDawn.Model.GameAssetManager;
import com.tillDawn.Model.Player;
import com.tillDawn.Model.Weapon;
import com.tillDawn.View.EndMenuView;
import com.tillDawn.View.GameView;

public class GameController {
    private HeroType heroType;
    private WeaponType weaponType;
    int duration;
    private float remainingTime;
    private GameView view;
    private PlayerController playerController;
    private WorldController worldController;
    private WeaponController weaponController;
    private boolean isPaused = false;

    public GameController (HeroType heroType, WeaponType weaponType, int duration) {
        this.heroType = heroType;
        this.weaponType = weaponType;
        this.duration = duration;
        remainingTime = duration * 60;
    }

    public void setView(GameView view) {
        this.view = view;
        playerController = new PlayerController(new Player(heroType));
        worldController = new WorldController(playerController);
        weaponController = new WeaponController(new Weapon(weaponType));
    }

    public void updateGame(float delta) {
        if (view != null && !isPaused) {
            remainingTime -= delta;
            if (remainingTime <= 0) {
                remainingTime = 0;
                winGame();
            }
            if (playerController.getPlayer().getHp() <= 0) {
                playerController.getPlayer().setHp(0);
                loseGame();
            }
            view.updateTimerDisplay((int) remainingTime);


            worldController.update();
            playerController.update();
            weaponController.update();
        }
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public WeaponController getWeaponController() {
        return weaponController;
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

    public void winGame() {
        com.tilldawn.Main.getMain().setScreen(new EndMenuView(new EndMenuController(playerController.getPlayer(), "You survived!", true, duration * 60),
            GameAssetManager.getGameAssetManager().getSkin()));
    }

    public void loseGame() {
        com.tilldawn.Main.getMain().setScreen(new EndMenuView(new EndMenuController(playerController.getPlayer(), "You died!", false, duration * 60 - (int) remainingTime),
            GameAssetManager.getGameAssetManager().getSkin()));
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public void giveUp () {
        com.tilldawn.Main.getMain().setScreen(new EndMenuView(new EndMenuController(playerController.getPlayer(), "You gave up!", false, duration * 60 - (int) remainingTime),
            GameAssetManager.getGameAssetManager().getSkin()));
    }

    public void cheatReduceTime() {
        remainingTime -= 10;
    }

    public void cheatReduceHp() {
        playerController.getPlayer().reduceHp(1);
    }

    public void cheatIncreaseHp() {
        playerController.getPlayer().addHp(1);
    }

    public void cheatAddExp() {
        playerController.getPlayer().addExp(10);
    }
}
