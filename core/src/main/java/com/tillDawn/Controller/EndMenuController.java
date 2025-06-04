package com.tillDawn.Controller;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tillDawn.Model.GameAssetManager;
import com.tillDawn.Model.Player;
import com.tillDawn.Model.SfxManager;
import com.tillDawn.View.EndMenuView;
import com.tillDawn.View.HintMenuView;
import com.tillDawn.View.MainMenuView;

public class EndMenuController {
    private final Player player;
    private final String message;
    private final boolean won;
    private final int survivalTime;
    private EndMenuView view;
    private Skin skin;


    public EndMenuController(Player player, String message, boolean won, int survivalTime) {
        this.player = player;
        this.message = message;
        this.won = won;
        this.survivalTime = survivalTime;
    }

    public void setView(EndMenuView view) {
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
        // handled via listeners
    }

    public Skin getSkin() {
        return skin;
    }

    public Player getPlayer() {
        return player;
    }

    public String getMessage() {
        return message;
    }

    public boolean isWon() {
        return won;
    }

    public int getSurvivalTime() {
        return survivalTime;
    }
}
