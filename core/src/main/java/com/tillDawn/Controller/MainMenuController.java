package com.tillDawn.Controller;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tillDawn.Model.App;
import com.tillDawn.Model.GameAssetManager;
import com.tillDawn.Model.User;
import com.tillDawn.Model.UserDataManager;
import com.tillDawn.View.MainMenuView;
import com.tillDawn.View.PreGameMenuView;
import com.tillDawn.View.SignInMenuView;
import com.tillDawn.View.SignupMenuView;

public class MainMenuController {
//    private MainMenuView view;
//
//    public void setView(MainMenuView view) {
//        this.view = view;
//    }
//
//    public void handleMainMenuButtons() {
//        if (view != null) {
//            if (view.getPlayButton().isChecked() && view.getField().getText().equals("kiarash")) {
//                com.tilldawn.Main.getMain().getScreen().dispose();
//                com.tilldawn.Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
//            }
//        }
//    }

    private MainMenuView view;
    private Skin skin;


    public void setView(MainMenuView view) {
        this.view = view;
        this.skin = GameAssetManager.getGameAssetManager().getSkin();
        initListeners();
    }

    private void initListeners() {
        view.getSettingButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
//                com.tilldawn.Main.getMain().setScreen(new MainMenuView(new MainMenuController(), skin));
            }
        });

        view.getProfileButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
//                com.tilldawn.Main.getMain().setScreen(new MainMenuView(new MainMenuController(), skin));
            }
        });

        view.getPregameButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                com.tilldawn.Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController(), skin));
            }
        });

        view.getScoreboardButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
//                com.tilldawn.Main.getMain().setScreen(new SignInMenuView(new SignInMenuController(), skin));
            }
        });

        view.getExitButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                App.setCurrentUser(null);
                com.tilldawn.Main.getMain().setScreen(new SignupMenuView(new SignupMenuController(), skin));
            }
        });
    }

    public void handleButtons() {
        // handled via listeners
    }

    public Skin getSkin() {
        return skin;
    }
}
