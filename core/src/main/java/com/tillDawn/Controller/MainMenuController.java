package com.tillDawn.Controller;

import com.tillDawn.Model.GameAssetManager;
import com.tillDawn.View.MainMenuView;
import com.tillDawn.View.PreGameMenuView;

public class MainMenuController {
    private MainMenuView view;

    public void setView(MainMenuView view) {
        this.view = view;
    }

    public void handleMainMenuButtons() {
        if (view != null) {
            if (view.getPlayButton().isChecked() && view.getField().getText().equals("kiarash")) {
                com.tilldawn.Main.getMain().getScreen().dispose();
                com.tilldawn.Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }
}
