package com.tillDawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tillDawn.Controller.SettingMenuController;
import com.tillDawn.Controller.SignInMenuController;
import com.tillDawn.Model.MusicManager;

public class SettingMenuView implements Screen {
    private final SettingMenuController controller;
    private Stage stage;
    private Table table;

    private final Label title;
    private Slider volumeSlider;
//    private TextButton muteButton;
    private final SelectBox<String> music;
    private final SelectBox<String> sfx;
    private final SelectBox<String> keyboardControllers;


    private final TextButton applyChanges;



    public SettingMenuView(SettingMenuController controller, Skin skin) {
        this.controller = controller;

        this.title = new Label("Settings", skin);
        title.setFontScale(2);

        this.volumeSlider = new Slider(0f, 1f, 0.01f, false, skin);
        this.volumeSlider.setValue(MusicManager.getVolume());

//        this.muteButton = new TextButton("Mute", skin);

        music = new SelectBox<>(skin);
        music.setItems("1", "2", "3", "4");
        music.setAlignment(Align.center);

        sfx = new SelectBox<>(skin);
        sfx.setItems("off", "on");
        sfx.setAlignment(Align.center);

        keyboardControllers = new SelectBox<>(skin);
        keyboardControllers.setItems("WASD", "Arrow keys");
        keyboardControllers.setAlignment(Align.center);

        applyChanges = new TextButton("Apply changes", skin);


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

        table.add(new Label("music volume:", controller.getSkin()));
        table.row();
        table.add(volumeSlider).width(700);
        table.row().pad(10,0,10,0);

//        table.add(muteButton);
//        table.row();

        table.add(new Label("music:", controller.getSkin()));
        table.row();
        table.add(music).width(700);
        table.row().pad(10,0,10,0);

        table.add(new Label("sfx:", controller.getSkin()));
        table.row();
        table.add(sfx).width(700);
        table.row().pad(10,0,10,0);

        table.add(new Label("keyboard controller system:", controller.getSkin()));
        table.row();
        table.add(keyboardControllers).width(700);
        table.row().pad(10,0,10,0);


        table.row().pad(20,0,20,0);


        table.add(applyChanges).pad(50,0,20,0);
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


    public SelectBox<String> getMusic() {
        return music;
    }

    public SelectBox<String> getSfx() {
        return sfx;
    }

    public SelectBox<String> getKeyboardControllers() {
        return keyboardControllers;
    }

    public Table getTable() {
        return table;
    }

    public TextButton getApplyChanges() {
        return applyChanges;
    }

    public Slider getVolumeSlider() {
        return volumeSlider;
    }

    public void setVolumeSlider(Slider volumeSlider) {
        this.volumeSlider = volumeSlider;
    }

}
