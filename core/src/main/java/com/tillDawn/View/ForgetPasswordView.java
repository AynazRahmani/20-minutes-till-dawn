package com.tillDawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tillDawn.Controller.ForgetPasswordController;
import com.tillDawn.Model.User;

public class ForgetPasswordView implements Screen {
    private final ForgetPasswordController controller;
    private Stage stage;
    private Table table;

    private final Label question;
    private final TextField answerField;

    private final Label title;
    private final TextField passwordField;
    private User user;


    private final TextButton changePasswordButton;


    private Label errorLabel;


    public ForgetPasswordView(ForgetPasswordController controller, Skin skin) {
        this.controller = controller;

        this.title = new Label("Change password Menu", skin);
        title.setFontScale(2);
        passwordField = new TextField("", skin);
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('#');

        question = new Label(controller.getQuestion(), skin);
        answerField = new TextField("", skin);


        changePasswordButton = new TextButton("Change password", skin);

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

        table.add(question);
        table.row().pad(10,0,0,0);
        table.add(answerField).width(700);
        table.row().pad(10,0,10,0);

        table.row().pad(10,0,10,0);

        table.add(new Label("new password:", controller.getSkin()));
        table.row();
        table.add(passwordField).width(700);
        table.row().pad(10,0,10,0);


        table.row().pad(20,0,20,0);


        table.row();
        table.add(changePasswordButton).pad(50,0,20,0);

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


    public void setUser(User user) {
        this.user = user;
    }

    // Getters
    public User getUser() { return user; }
    public TextField getPasswordField() { return passwordField; }
    public TextButton getChangePasswordButton() { return changePasswordButton; }
    public Label getErrorLabel() {
        return errorLabel;
    }
    public Table getTable() {
        return table;
    }

    public void setErrorLabel(Label errorLabel) {
        this.errorLabel = errorLabel;
    }

    public TextField getAnswerField() {
        return answerField;
    }

    public ForgetPasswordController getController() {
        return controller;
    }
}
