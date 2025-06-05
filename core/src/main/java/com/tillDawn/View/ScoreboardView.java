package com.tillDawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tillDawn.Controller.ScoreboardController;
import com.tillDawn.Model.App;
import com.tillDawn.Model.User;

import java.util.List;

public class ScoreboardView implements Screen {
    private Stage stage;
    private final ScoreboardController controller;
    private final Skin skin;
    private Table table;
    private SelectBox<String> sortDropdown;
    private Table scoresTable;

    public ScoreboardView(ScoreboardController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
        controller.setView(this);
        scoresTable = new Table();
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        table.center();

        Label title = new Label("Scoreboard", skin);
        title.setFontScale(2);

        sortDropdown = new SelectBox<>(skin);
        sortDropdown.setItems("Score", "Username", "Kills", "Time Alive");
        sortDropdown.setSelected("Score");
        sortDropdown.addListener(event -> {
            controller.onSortChanged(sortDropdown.getSelected());
            return false;
        });



        ScrollPane scrollPane = new ScrollPane(scoresTable, skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setScrollbarsVisible(true);


        Label usernameHeader = new Label("Username", skin);
        Label scoreHeader = new Label("Score", skin);
        Label killsHeader = new Label("Kills", skin);
        Label timeHeader = new Label("Time Alive", skin);

        Table headerTable = new Table(skin);
        headerTable.add(usernameHeader).width(200).pad(5);
        headerTable.add(scoreHeader).width(120).pad(5);
        headerTable.add(killsHeader).width(120).pad(5);
        headerTable.add(timeHeader).width(120).pad(5);


        TextButton backButton = new TextButton("Back", skin);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.onBackPressed();
            }
        });


        table.row();
        table.add(title).pad(20);
        table.row();
        table.add(sortDropdown).width(300).pad(10);
        table.row();
        table.add(headerTable).padBottom(5);
        table.row();
        table.add(scrollPane).width(700).height(300);
        table.row();
        table.add(backButton).padTop(20).width(400);

        stage.addActor(table);
    }

    public void updateScoresTable(List<User> users) {
        if (scoresTable == null || stage == null) {
            return;
        }
        scoresTable.clear();

        int rank = 1;
        for (User user : users) {

            Image icon = null;
            if (rank == 1) {
                icon = new Image(new Texture(Gdx.files.internal("medal/gold.png")));
            } else if (rank == 2) {
                icon = new Image(new Texture(Gdx.files.internal("medal/silver.png")));
            } else if (rank == 3) {
                icon = new Image(new Texture(Gdx.files.internal("medal/bronze.png")));
            }

            Label username = new Label(user.getUsername(), skin);
            Label score = new Label(String.valueOf(user.getScore()), skin);
            Label kills = new Label(String.valueOf(user.getKillNumber()), skin);
            Label timeAlive = new Label(Integer.toString(user.getMaxSurvivalTime()), skin);

            if (App.getCurrentUser() != null && user.getUsername().equals(App.getCurrentUser().getUsername())) {
                username.setColor(0, 1, 0, 1);
                score.setColor(0, 1, 0, 1);
                kills.setColor(0, 1, 0, 1);
                timeAlive.setColor(0, 1, 0, 1);
            }

            Table row = new Table(skin);

            if (icon != null) {
                row.add(icon).size(24).pad(5);
            } else {
                row.add().width(24).pad(5);
            }

            row.add(username).width(200).pad(5);
            row.add(score).width(120).pad(5);
            row.add(kills).width(120).pad(5);
            row.add(timeAlive).width(120).pad(5);

            scoresTable.add(row).fillX();
            scoresTable.row();

            rank++;
            if (rank > 10) break;
        }
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        com.tilldawn.Main.getBatch().begin();
        com.tilldawn.Main.getBatch().end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {
        stage.dispose();
    }
}
