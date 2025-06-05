package com.tillDawn.Controller;

import com.tillDawn.Model.Enums.Ability;
import com.tillDawn.Model.Enums.HeroType;
import com.tillDawn.Model.Enums.WeaponType;
import com.tillDawn.Model.GameAssetManager;
import com.tillDawn.Model.Player;
import com.tillDawn.Model.SfxManager;
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

        worldController = new WorldController(playerController);
        weaponController = new WeaponController(new Weapon(weaponType));
        worldController.generateTrees(30);
        playerController = new PlayerController(new Player(heroType), worldController, weaponController);
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

            updateSpeedy();
            updateWeapon();
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
        SfxManager.play("win");
        com.tilldawn.Main.getMain().setScreen(new EndMenuView(new EndMenuController(playerController.getPlayer(), "You survived!", true, duration * 60),
            GameAssetManager.getGameAssetManager().getSkin()));
    }

    public void loseGame() {
        SfxManager.play("lose");
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
        SfxManager.play("lose");
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

    public void increaseHpMax() {
        playerController.getPlayer().setHpMaxIncreased(true);
    }

    public void ability1() {
        if (playerController.getPlayer().getAbilities().contains(Ability.VITALITY)) {
            playerController.getPlayer().setHpMaxIncreased(true);
            view.getErrorLabel().setText("VITALITY applied");
        }
        else {
            view.getErrorLabel().setText("VITALITY's unavailable!");
        }
    }

    public void ability2() {
        if (playerController.getPlayer().getAbilities().contains(Ability.DAMAGER)) {
            view.getErrorLabel().setText("DAMAGER applied");
        }
        else {
            view.getErrorLabel().setText("DAMAGER's unavailable!");
        }
    }

    public void ability3() {
        if (playerController.getPlayer().getAbilities().contains(Ability.PROCREASE)) {

            view.getErrorLabel().setText("PROCREASE applied");
        }
        else {
            view.getErrorLabel().setText("PROCREASE's unavailable!");
        }
    }

    public void ability4() {
        if (playerController.getPlayer().getAbilities().contains(Ability.AMOCREASE)) {
            weaponController.getWeapon().setMaxAmmo(true);
            view.getErrorLabel().setText("AMOCREASE applied");
        }
        else {
            view.getErrorLabel().setText("AMOCREASE's unavailable!");
        }
    }

    public void ability5() {
        if (playerController.getPlayer().getAbilities().contains(Ability.SPEEDY)) {
            playerController.setSpeedy(true);
            playerController.setSpeedyDeadline(Math.max(remainingTime - 10, 0));
            view.getErrorLabel().setText("SPEEDY applied");
        }
        else {
            view.getErrorLabel().setText("SPEEDY's unavailable!");
        }
    }

    public void updateSpeedy() {
        if (playerController.isSpeedy()) {
            if (remainingTime < getPlayerController().getSpeedyDeadline()) {
                playerController.setSpeedy(false);
            }
        }
    }

    public WorldController getWorldController() {
        return worldController;
    }

    public void updateWeapon() {
        if (weaponController.isReload()) {
            if (weaponController.getReloadTime() <= 0) {
                weaponController.setReloadTime(remainingTime);
            }
            else {
                if (weaponController.getReloadTime() - remainingTime >= weaponController.getWeapon().getWeaponType().getReloadTime()) {
                    weaponController.getWeapon().reload();
                    weaponController.setReload(false);
                }
            }
        }
        else {
            weaponController.setReloadTime(0f);
        }
    }
}
