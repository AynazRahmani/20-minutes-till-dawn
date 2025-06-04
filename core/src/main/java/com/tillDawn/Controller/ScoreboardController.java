package com.tillDawn.Controller;

import com.tillDawn.Model.*;
import com.tillDawn.View.MainMenuView;
import com.tillDawn.View.ScoreboardView;
import com.badlogic.gdx.Gdx;
import java.util.Collections;
import java.util.List;

public class ScoreboardController {

    private ScoreboardView view;

    public void setView(ScoreboardView view) {
        this.view = view;
        refreshScores("Score");
    }

    public void refreshScores(String sortBy) {
        List<User> users ;

        switch (sortBy) {
            case "Username":
                users = UserDataManager.getTopUsersByUsername();
                break;
            case "Kills":
                users = UserDataManager.getTopUsersByKills();
                break;
            case "Time Alive":
                users = UserDataManager.getTopUsersBySurvivalTime();
                break;
            case "Score":
            default:
                users = UserDataManager.getTopUsersByScore();
                break;
        }
//
        if (users == null) {
            users = Collections.emptyList();
        }

        view.updateScoresTable(users);
    }

    public void onSortChanged(String newSort) {
        refreshScores(newSort);
    }

    public void onBackPressed() {
        Gdx.app.postRunnable(() -> {
            SfxManager.play("click");
            com.tilldawn.Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        });
    }
}
