package com.tillDawn.Controller;

import com.tillDawn.Model.GameAssetManager;
import com.tillDawn.Model.PreGame;
import com.tillDawn.View.GameView;
import com.tillDawn.View.PreGameMenuView;

public class PreGameMenuController {
    private PreGameMenuView view;
    private PreGame pregame;


    public void setView(PreGameMenuView view) {
        this.view = view;
        this.pregame = new PreGame();
    }

    public void handlePreGameMenuButtons() {
        if (view != null) {
            com.tilldawn.Main.getMain().getScreen().dispose();
            com.tilldawn.Main.getMain().setScreen(new GameView(new GameController(), GameAssetManager.getGameAssetManager().getSkin()));
        }
    }

}
