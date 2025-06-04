package com.tillDawn.Controller;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tillDawn.Model.*;
import com.tillDawn.Model.Enums.KeyboardType;
import com.tillDawn.View.ForgetPasswordView;
import com.tillDawn.View.MainMenuView;
import com.tillDawn.View.SettingMenuView;
import com.tillDawn.View.SignInMenuView;

public class SettingMenuController {
    private SettingMenuView view;
    private Skin skin;
    private Label error;
//    private Table table;

    public void setView(SettingMenuView view) {
        this.view = view;
        this.skin = GameAssetManager.getGameAssetManager().getSkin();
//        this.table = view.getTable();
        initListeners();
    }

    private void initListeners() {
        view.getVolumeSlider().addListener(event -> {
            MusicManager.setVolume(view.getVolumeSlider().getValue());
            return false;
        });


        view.getApplyChanges().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                SfxManager.play("click");

                if (view.getSfx().getSelected().equals("on")) {
                    SfxManager.setSfxEnabled(true);
                    SfxManager.play("click");
                } else {
                    SfxManager.setSfxEnabled(false);
                }


                if (view.getKeyboardControllers().getSelected().equals("WASD")) {
                    App.getKeyboardManager().setKeyboardType(KeyboardType.WASD);
                }
                else {
                    App.getKeyboardManager().setKeyboardType(KeyboardType.ARROW);
                }

                String selectedMusic = view.getMusic().getSelected();

                App.setCurrentMusicPath("music/music" + selectedMusic + ".mp3");
                com.tilldawn.Main.getMain().setScreen(new MainMenuView(new MainMenuController(), skin));
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
