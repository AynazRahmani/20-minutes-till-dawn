package com.tillDawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tillDawn.Controller.EndMenuController;
import com.tillDawn.Controller.HintMenuController;
import com.tillDawn.Model.App;
import com.tillDawn.Model.Enums.KeyboardType;

public class EndMenuView implements Screen {
    private final EndMenuController controller;
    private Stage stage;
    private Table table;

    private final Label title;

    private final TextButton exitButton;



    public EndMenuView(EndMenuController controller, Skin skin) {
        this.controller = controller;
        this.title = new Label(controller.getMessage(), skin);
        title.setFontScale(2.5f);

        exitButton = new TextButton("Exit", skin);

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

        Label heroes = new Label("details:", controller.getSkin());
        heroes.setFontScale(1.5f);

        table.add(heroes);
        table.row().pad(5,0,5,0);

        String username;
        if (App.getCurrentUser() == null) {
            username = "guest";
        }
        else {
            username = App.getCurrentUser().getUsername();
        }

        table.add(new Label("username: " + username, controller.getSkin()));
        table.row().pad(15,0,15,0);

        table.add(new Label("time survived: " + controller.getSurvivalTime() + "s", controller.getSkin()));
        table.row().pad(15,0,15,0);

        table.add(new Label("kills: " + controller.getPlayer().getKillsNumber(), controller.getSkin()));
        table.row().pad(15,0,15,0);

        int score = controller.getSurvivalTime() * controller.getPlayer().getKillsNumber();

        table.add(new Label("score: " + score, controller.getSkin()));
        table.row().pad(15,0,15,0);

        if (controller.isWon()) {
            table.add(new Label("won", controller.getSkin()));
        } else {
            table.add(new Label("dead", controller.getSkin()));
        }

        table.row().pad(15,0,15,0);


        table.add(exitButton).pad(20,0,20,0);
        table.row();

        stage.addActor(table);

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

    public TextButton getExitButton() { return exitButton; }

    public Table getTable() {
        return table;
    }

}
