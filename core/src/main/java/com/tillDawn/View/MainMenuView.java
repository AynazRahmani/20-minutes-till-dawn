package com.tillDawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tillDawn.Controller.MainMenuController;

public class MainMenuView implements Screen {
    private Stage stage;
    private final TextButton playButton;
    private final Label gameTitle;
    private final TextField field;
    public Table table;
    private final MainMenuController controller;

    private final TextButton settingButton;
    private final TextButton profileButton;
    private final TextButton pregameButton;
    private final TextButton scoreboardButton;
    private final TextButton exitButton;

    public MainMenuView(MainMenuController controller, Skin skin) {
        this.controller = controller;
        this.playButton = new TextButton("play", skin);
        this.settingButton = new TextButton("setting", skin);
        this.profileButton = new TextButton("profile menu", skin);
        this.pregameButton = new TextButton("pregame menu", skin);
        this.scoreboardButton = new TextButton("scoreboard", skin);
        this.exitButton = new TextButton("exit", skin);

        this.gameTitle = new Label("Main Menu", skin);
        gameTitle.setFontScale(2);
        this.field = new TextField("this is a field", skin);
        this.table = new Table();

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();
//        table.add(gameTitle);
//        table.row().pad(10, 0 , 10 , 0);
//        table.add(field).width(600);
//        table.row().pad(10, 0 , 10 , 0);
//        table.add(playButton);
        table.row();
        table.add(gameTitle).pad(20, 0, 35, 0);
        table.row();
        table.add(pregameButton).pad(15, 0, 15, 0);
        table.row();
        table.add(settingButton).pad(15, 0, 15, 0);
        table.row();
        table.add(profileButton).pad(15, 0, 15, 0);
        table.row();
        table.add(scoreboardButton).pad(15, 0, 15, 0);
        table.row();
        table.add(exitButton).pad(50, 0, 20, 0);

        stage.addActor(table);
//        table.setDebug(true);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        com.tilldawn.Main.getBatch().begin();
        com.tilldawn.Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleMainMenuButtons();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public TextButton getPlayButton() {
        return playButton;
    }

    public TextField getField() {
        return field;
    }

    public TextButton getSettingButton() {
        return settingButton;
    }

    public TextButton getProfileButton() {
        return profileButton;
    }

    public TextButton getPregameButton() {
        return pregameButton;
    }

    public TextButton getScoreboardButton() {
        return scoreboardButton;
    }

    public TextButton getExitButton() {
        return exitButton;
    }
}

