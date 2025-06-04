package com.tillDawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tillDawn.Controller.HintMenuController;
import com.tillDawn.Model.App;
import com.tillDawn.Model.Enums.KeyboardType;

public class HintMenuView implements Screen {
    private final HintMenuController controller;
    private Stage stage;
    private Table table;

    private final Label title;




    private final TextButton exitButton;

    private Label errorLabel;


    public HintMenuView(HintMenuController controller, Skin skin) {
        this.controller = controller;

        this.title = new Label("Hint Menu", skin);
        title.setFontScale(2);

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

        table.row().pad(20,0,30,0);
        table.add(title);
        table.row();
        table.row().pad(0,0,10,0);

        Label heroes = new Label("Heroes:", controller.getSkin());
        heroes.setFontScale(1.5f);

        table.add(heroes);
        table.row().pad(5,0,5,0);

        table.add(new Label("Shana:  Hp: 4,  Speed: 4", controller.getSkin()));
        table.row().pad(5,0,5,0);

        table.add(new Label("Diamond:  Hp: 7,  Speed: 1", controller.getSkin()));
        table.row().pad(5,0,5,0);

        table.add(new Label("Scarlet:  Hp: 3,  Speed: 5", controller.getSkin()));
        table.row().pad(5,0,5,0);

        table.add(new Label("Lilith:  Hp: 5,  Speed: 3", controller.getSkin()));
        table.row().pad(5,0,5,0);

        table.add(new Label("Dasher:  Hp: 2,  Speed: 10", controller.getSkin()));
        table.row().pad(5,0,5,0);


        if (App.getKeyboardManager().getKeyboardType().equals(KeyboardType.ARROW)) {
            table.add(new Label("moving keys: up, left, down, right", controller.getSkin()));
        }
        else if (App.getKeyboardManager().getKeyboardType().equals(KeyboardType.WASD)) {
            table.add(new Label("moving keys: w, a, s, d", controller.getSkin())).pad(40, 0, 0, 0);
        }
        table.row();
        table.row().pad(10,0,10,0);

        Label cheat = new Label("Cheat codes:", controller.getSkin());
        cheat.setFontScale(1.5f);

        table.add(cheat);
        table.row().pad(5,0,5,0);

        table.add(new Label("reducing remaining time", controller.getSkin()));
        table.row().pad(5,0,5,0);

        table.add(new Label("character level up", controller.getSkin()));
        table.row().pad(5,0,5,0);

        table.add(new Label("getting extra lives", controller.getSkin()));
        table.row().pad(5,0,5,0);

        table.add(new Label("going to boss fight", controller.getSkin()));
        table.row().pad(5,0,5,0);

        table.add(new Label("adding remaining time", controller.getSkin()));
        table.row().pad(5,0,5,0);

        table.row().pad(20,0,20,0);

        Label abilities = new Label("Game abilities:", controller.getSkin());
        abilities.setFontScale(1.5f);

        table.add(abilities);
        table.row().pad(5,0,5,0);

        table.add(new Label("vitality, damager, procrease, amocrease, speedy", controller.getSkin()));
        table.row().pad(5,0,5,0);


        table.add(exitButton).pad(20,0,20,0);
        table.row();


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

    // Getters

    public TextButton getExitButton() { return exitButton; }
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
