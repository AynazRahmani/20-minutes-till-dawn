package com.tillDawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tillDawn.Controller.MainMenuController;
import com.tillDawn.Model.App;
import com.tillDawn.Model.MusicManager;
import com.tillDawn.Model.User;

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
    private final TextButton hintButton;
    private final TextButton continueButton;
    private final TextButton exitButton;

    private final Skin skin;

    public MainMenuView(MainMenuController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;

        this.playButton = new TextButton("play", skin);
        this.settingButton = new TextButton("setting", skin);
        this.profileButton = new TextButton("profile menu", skin);
        this.pregameButton = new TextButton("pregame menu", skin);
        this.scoreboardButton = new TextButton("scoreboard", skin);
        this.hintButton = new TextButton("hint menu", skin);
        this.continueButton = new TextButton("continue saved game", skin);
        this.exitButton = new TextButton("logout", skin);

        this.gameTitle = new Label("Main Menu", skin);
        gameTitle.setFontScale(2);

        this.field = new TextField("this is a field", skin);
        this.table = new Table();

        controller.setView(this);
    }

    @Override
    public void show() {
        MusicManager.playMusic(App.getCurrentMusicPath(), true);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();

        table.row();
        table.add(gameTitle).pad(20, 0, 35, 0);
        table.row();
        table.add(pregameButton).pad(5, 0, 5, 0);
        table.row();
        table.add(settingButton).pad(5, 0, 5, 0);
        table.row();
        table.add(profileButton).pad(5, 0, 5, 0);
        table.row();
        table.add(scoreboardButton).pad(5, 0, 5, 0);
        table.row();
        table.add(hintButton).pad(5, 0, 5, 0);
        table.row();
        table.add(continueButton).pad(5, 0, 5, 0);
        table.row();
        table.add(exitButton).pad(5, 0, 5, 0);

        stage.addActor(table);


        Table topLeftTable = new Table();
        topLeftTable.top().left();
        topLeftTable.setFillParent(true);

        Label usernameLabel;
        Label scoreLabel;
        User currentUser = App.getCurrentUser();
        String path;

        if (currentUser == null) {
            usernameLabel = new Label("guest mode", skin);
            scoreLabel = new Label("", skin);
            path = "avatars/avatar2.png";
        } else {
            usernameLabel = new Label("Username: " + currentUser.getUsername(), skin);
            scoreLabel = new Label("Score: " + currentUser.getScore(), skin);
            path = currentUser.getAvatarPath();
        }

        Image avatarImage = new Image(new Texture(Gdx.files.internal(path)));
        avatarImage.setSize(64, 64);
        Label avatarLabel = new Label("avatar", skin);



        topLeftTable.add(avatarImage).pad(10);
        topLeftTable.row().pad(10, 0, 10, 0);;

        topLeftTable.row();
        topLeftTable.add(usernameLabel).pad(5).left();
        topLeftTable.row();
        topLeftTable.add(scoreLabel).pad(5).left();

        stage.addActor(topLeftTable);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        com.tilldawn.Main.getBatch().begin();
        com.tilldawn.Main.getBatch().end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleButtons();
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {}

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

    public TextButton getContinueButton() {
        return continueButton;
    }

    public TextButton getExitButton() {
        return exitButton;
    }

    public TextButton getHintButton() {
        return hintButton;
    }
}
