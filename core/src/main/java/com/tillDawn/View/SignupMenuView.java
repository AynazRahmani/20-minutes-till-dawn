package com.tillDawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tillDawn.Controller.SignupMenuController;

public class SignupMenuView implements Screen {
    private final SignupMenuController controller;
    private Stage stage;
    private Table table;

    private final Label title;
    private final TextField usernameField;
    private final TextField passwordField;
    private final SelectBox<String> questionBox;
    private final TextField answerField;

    private final TextButton signupButton;
    private final TextButton skipButton;
    private final TextButton signinButton;

    private Label errorLabel;


    public SignupMenuView(SignupMenuController controller, Skin skin) {
        this.controller = controller;

        Pixmap pixmap = new Pixmap(Gdx.files.internal("cursor/cursor.png"));
        Cursor customCursor = Gdx.graphics.newCursor(pixmap, pixmap.getWidth() / 2, pixmap.getHeight() / 2);
        Gdx.graphics.setCursor(customCursor);
        pixmap.dispose();


        this.title = new Label("Signup Menu", skin);
        title.setFontScale(2);
        usernameField = new TextField("", skin);
        passwordField = new TextField("", skin);
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('#');

        questionBox = new SelectBox<>(skin);
        questionBox.setItems("your first pet's name?", "your first grade teacher's name?", "behtarin TA?");
        questionBox.setAlignment(Align.center);

        answerField = new TextField("", skin);

        signupButton = new TextButton("Signup", skin);
        skipButton = new TextButton("Guest mode", skin);
        signinButton = new TextButton("Sign in", skin);

        errorLabel = new Label("", skin);
        errorLabel.setColor(1, 0, 0, 1);


        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.top().padTop(30);
        table.defaults().pad(10).width(600);

        table.add(title).center().padBottom(30);
        title.setAlignment(Align.center);
        table.row();


        Label usernameLabel = new Label("username:", controller.getSkin());
        usernameLabel.setAlignment(Align.center);
        table.add(usernameLabel).center();
        table.row();
        table.add(usernameField).center();
        table.row();


        Label passwordLabel = new Label("password:", controller.getSkin());
        passwordLabel.setAlignment(Align.center);
        table.add(passwordLabel).center();
        table.row();
        table.add(passwordField).center();
        table.row();


        Label questionLabel = new Label("security question:", controller.getSkin());
        questionLabel.setAlignment(Align.center);
        table.add(questionLabel).center();
        table.row();
        table.add(questionBox).center();
        table.row();


        Label answerLabel = new Label("answer:", controller.getSkin());
        answerLabel.setAlignment(Align.center);
        table.add(answerLabel).center();
        table.row();
        table.add(answerField).center();
        table.row();


        errorLabel.setWrap(true);
        errorLabel.setAlignment(Align.center);
        table.add(errorLabel).width(600).center().padTop(10).padBottom(10);
        table.row();


        table.add(signupButton).padTop(20).center();
        table.row();
        table.add(skipButton).center();
        table.row();
        table.add(signinButton).padBottom(20).center();
        table.row();

        ScrollPane scrollPane = new ScrollPane(table, controller.getSkin());
        scrollPane.setFillParent(true);
        scrollPane.setScrollingDisabled(true, false);

        stage.addActor(scrollPane);
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

    // Getters
    public TextField getUsernameField() { return usernameField; }
    public TextField getPasswordField() { return passwordField; }
    public SelectBox<String> getQuestionBox() { return questionBox; }
    public TextField getAnswerField() { return answerField; }
    public TextButton getSignupButton() { return signupButton; }
    public TextButton getSkipButton() { return skipButton; }
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
