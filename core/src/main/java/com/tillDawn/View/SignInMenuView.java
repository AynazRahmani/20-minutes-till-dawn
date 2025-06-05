package com.tillDawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tillDawn.Controller.SignInMenuController;

public class SignInMenuView implements Screen {
    private final SignInMenuController controller;
    private Stage stage;
    private Table table;

    private final Label title;
    private final TextField usernameField;
    private final TextField passwordField;


    private final TextButton forgetPasswordButton;
    private final TextButton signinButton;

    private Label errorLabel;


    public SignInMenuView(SignInMenuController controller, Skin skin) {
        this.controller = controller;

        this.title = new Label("Sign in Menu", skin);
        title.setFontScale(2);
        usernameField = new TextField("", skin);
        passwordField = new TextField("", skin);
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('#');

        signinButton = new TextButton("Sign in", skin);
        forgetPasswordButton = new TextButton("Forget password", skin);

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


        table.add(signinButton).pad(50,0,20,0);
        table.row();
        table.add(forgetPasswordButton).pad(20,0,20,0);

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
    public TextButton getForgetPasswordButton() { return forgetPasswordButton; }
    public TextButton getSigninButton() { return signinButton; }
    public Label getErrorLabel() {
        return errorLabel;
    }
    public Table getTable() {
        return table;
    }

    public void setErrorLabel(Label errorLabel) {
        this.errorLabel = errorLabel;
    }
}
