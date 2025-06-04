package com.tillDawn.Controller;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tillDawn.Model.*;
import com.tillDawn.View.*;

public class MainMenuController {

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
                SfxManager.play("click");
                com.tilldawn.Main.getMain().setScreen(new SettingMenuView(new SettingMenuController(), skin));
            }
        });

        view.getProfileButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                SfxManager.play("click");
                if (App.getCurrentUser() != null) {
                    com.tilldawn.Main.getMain().setScreen(new ProfileMenuView(new ProfileMenuController(), skin));
                }
            }
        });

        view.getPregameButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                SfxManager.play("click");
                com.tilldawn.Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController(), skin));
            }
        });

        view.getScoreboardButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                SfxManager.play("click");
                if (App.getCurrentUser() == null) return;
                com.tilldawn.Main.getMain().setScreen(new ScoreboardView(new ScoreboardController(), skin));
            }
        });

        view.getExitButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                SfxManager.play("click");
                App.setCurrentUser(null);
                com.tilldawn.Main.getMain().setScreen(new SignupMenuView(new SignupMenuController(), skin));
            }
        });

        view.getContinueButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                SfxManager.play("click");
            }
        });

        view.getHintButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                SfxManager.play("click");
                com.tilldawn.Main.getMain().setScreen(new HintMenuView(new HintMenuController(), skin));
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
