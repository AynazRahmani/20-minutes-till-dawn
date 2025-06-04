package com.tillDawn.Controller;

import com.tillDawn.Model.Enums.HeroType;
import com.tillDawn.Model.Enums.WeaponType;
import com.tillDawn.Model.GameAssetManager;
import com.tillDawn.Model.Player;
import com.tillDawn.Model.Weapon;
import com.tillDawn.View.GameView;
import com.tillDawn.View.PreGameMenuView;
import sun.jvm.hotspot.gc.g1.HeapRegion;

public class GameController {
    private HeroType heroType;
    private WeaponType weaponType;
    int duration;
    private float remainingTime;
    private GameView view;
    private PlayerController playerController;
    private WorldController worldController;
    private WeaponController weaponController;

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
        if (view != null) {
            remainingTime -= delta;
            if (remainingTime < 0) {
                remainingTime = 0;
                endGame();
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

    public void endGame() {

    }
}
