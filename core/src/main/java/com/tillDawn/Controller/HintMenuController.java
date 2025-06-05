package com.tillDawn.Controller;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tillDawn.Model.*;
import com.tillDawn.View.ForgetPasswordView;
import com.tillDawn.View.HintMenuView;
import com.tillDawn.View.MainMenuView;
import com.tillDawn.View.SignInMenuView;

public class HintMenuController {
    private HintMenuView view;
    private Skin skin;


    public void setView(HintMenuView view) {
        this.view = view;
        this.skin = GameAssetManager.getGameAssetManager().getSkin();

        initListeners();
    }

    private void initListeners() {
        view.getExitButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                SfxManager.play("click");


                com.tilldawn.Main.getMain().setScreen(new MainMenuView(new MainMenuController(), skin));
            }
        });


    }

    public void handleButtons() {

    }

    public Skin getSkin() {
        return skin;
    }
}
