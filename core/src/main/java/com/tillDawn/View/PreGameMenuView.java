package com.tillDawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tillDawn.Controller.PreGameMenuController;

public class PreGameMenuView implements Screen {
    private Stage stage;
    private final Label title;
    private final TextButton playButton;
    private final SelectBox selectHero;
    private final SelectBox selectWeapon;
    private final SelectBox selectDuration;
    public Table table;
    private PreGameMenuController controller;

    public PreGameMenuView(PreGameMenuController controller, Skin skin) {
        this.title = new Label("Pregame Menu", skin);
        this.selectHero = new SelectBox<>(skin);
        this.selectWeapon = new SelectBox<>(skin);
        this.selectDuration = new SelectBox<>(skin);
        this.playButton = new TextButton("Play", skin);
        this.table = new Table();
        this.controller = controller;
        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Array<String> hero = new Array<>();
        Array<String> weapon = new Array<>();
        Array<String> duration = new Array<>();

        hero.add("Shana");
        hero.add("Diamond");
        hero.add("Scarlet");
        hero.add("Lilith");
        hero.add("Dasher");

        weapon.add("Revolver");
        weapon.add("Shotgun");
        weapon.add("SMG");

        duration.add("2");
        duration.add("5");
        duration.add("10");
        duration.add("20");


        selectHero.setItems(hero);
        selectWeapon.setItems(weapon);
        selectDuration.setItems(duration);

        table.setFillParent(true);
        table.center();
        table.row().pad(10, 0 , 10 , 0);
        table.add(title);
        table.row().pad(30, 0 , 10 , 0);
        table.add(selectHero).width(500);
        table.row().pad(20, 0 , 10 , 0);
        table.add(selectWeapon).width(500);
        table.row().pad(20, 0 , 10 , 0);
        table.add(selectDuration).width(500);
        table.row().pad(20, 0 , 10 , 0);
        table.add(playButton);

        stage.addActor(table);
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

    public SelectBox getSelectHero() {
        return selectHero;
    }

    public SelectBox getSelectWeapon() {
        return selectWeapon;
    }

    public SelectBox getSelectDuration() {
        return selectDuration;
    }

    public TextButton getPlayButton() {
        return playButton;
    }
}
