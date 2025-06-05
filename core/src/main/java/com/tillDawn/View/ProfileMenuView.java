package com.tillDawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tillDawn.Controller.ProfileMenuController;

public class ProfileMenuView implements Screen {
    private final ProfileMenuController controller;
    private Stage stage;
    private Table table;

    private final Label title;
    private final TextField usernameField;
    private final TextField passwordField;
    private final SelectBox<String> avatar;


    private final TextButton deleteAccount;
    private final TextButton applyChanges;
    private final TextButton exitButton;

    private Label errorLabel;


    public ProfileMenuView(ProfileMenuController controller, Skin skin) {
        this.controller = controller;

        this.title = new Label("Profile Menu", skin);
        title.setFontScale(2);
        usernameField = new TextField("", skin);
        passwordField = new TextField("", skin);
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('#');

        avatar = new SelectBox<>(skin);
        avatar.setItems("avatar 1", "avatar 2", "avatar 3");
        avatar.setAlignment(Align.center);

        applyChanges = new TextButton("Apply changes", skin);
        deleteAccount = new TextButton("Delete account", skin);
        exitButton = new TextButton("Exit", skin);

        errorLabel = new Label("", skin);
        errorLabel.setColor(1, 0, 0, 1);

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        table.setFillParent(true);
        table.center();

        table.row().pad(50,0,50,0);
        table.add(title);
        table.row();
        table.row().pad(0,0,10,0);
        table.add(new Label("username:", controller.getSkin()));
        table.row();
        table.add(usernameField).width(700);
        table.row().pad(10,0,10,0);

        table.add(new Label("password:", controller.getSkin()));
        table.row();
        table.add(passwordField).width(700);
        table.row().pad(10,0,10,0);


        table.row().pad(20,0,20,0);


        table.add(applyChanges).pad(50,0,20,0);
        table.row();
        table.add(deleteAccount).pad(20,0,20,0);
        table.row();
        table.add(exitButton).pad(20,0,20,0);

        table.row();
        table.add(errorLabel).colspan(2).center().padBottom(10);
        table.row();


        stage.addActor(table);

//        table.setDebug(true);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.act(delta);

        stage.draw();
        controller.handleButtons();
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {}


    public TextField getUsernameField() { return usernameField; }
    public TextField getPasswordField() { return passwordField; }
    public TextButton getDeleteAccount() { return deleteAccount; }
    public TextButton getApplyChanges() { return applyChanges; }
    public Label getErrorLabel() {
        return errorLabel;
    }
    public Table getTable() {
        return table;
    }

    public void setErrorLabel(Label errorLabel) {
        this.errorLabel = errorLabel;
    }

    public TextButton getExitButton() {
        return exitButton;
    }

    public SelectBox<String> getAvatar() {
        return avatar;
    }
}
