//package com.tillDawn.View;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.InputProcessor;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.scenes.scene2d.ui.Skin;
//import com.badlogic.gdx.utils.ScreenUtils;
//import com.badlogic.gdx.utils.viewport.ScreenViewport;
//import com.tillDawn.Controller.GameController;
//
//public class GameView implements Screen, InputProcessor {
//    private Stage stage;
//    private GameController controller;
//
//    public GameView(GameController controller, Skin skin) {
//        this.controller = controller;
//        controller.setView(this);
//    }
//
//    @Override
//    public void show() {
//        stage = new Stage(new ScreenViewport());
//        Gdx.input.setInputProcessor(this);
//    }
//
//    @Override
//    public void render(float delta) {
//        ScreenUtils.clear(0, 0, 0, 1);
//        com.tilldawn.Main.getBatch().begin();
//        controller.updateGame();
//        com.tilldawn.Main.getBatch().end();
//        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
//        stage.draw();
//
//    }
//
//    @Override
//    public void resize(int width, int height) {
//
//    }
//
//    @Override
//    public void pause() {
//
//    }
//
//    @Override
//    public void resume() {
//
//    }
//
//    @Override
//    public void hide() {
//
//    }
//
//    @Override
//    public void dispose() {
//    }
//
//    @Override
//    public boolean keyDown(int keycode) {
//        return false;
//    }
//
//    @Override
//    public boolean keyUp(int keycode) {
//        return false;
//    }
//
//    @Override
//    public boolean keyTyped(char character) {
//        return false;
//    }
//
//    @Override
//    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//        controller.getWeaponController().handleWeaponShoot(screenX, screenY);
//        return false;
//    }
//
//    @Override
//    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
//        return false;
//    }
//
//    @Override
//    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
//        return false;
//    }
//
//    @Override
//    public boolean touchDragged(int screenX, int screenY, int pointer) {
//        return false;
//    }
//
//    @Override
//    public boolean mouseMoved(int screenX, int screenY) {
//        controller.getWeaponController().handleWeaponRotation(screenX, screenY);
//        return false;
//    }
//
//    @Override
//    public boolean scrolled(float amountX, float amountY) {
//        return false;
//    }
//}






//package com.tillDawn.View;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.InputProcessor;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.scenes.scene2d.ui.Label;
//import com.badlogic.gdx.scenes.scene2d.ui.Skin;
//import com.badlogic.gdx.utils.ScreenUtils;
//import com.badlogic.gdx.utils.viewport.FitViewport;
//import com.badlogic.gdx.utils.viewport.Viewport;
//import com.tillDawn.Controller.GameController;
//import com.tillDawn.Model.Player;
//
//public class GameView implements Screen, InputProcessor {
//    private Skin skin;
//    private Stage stage;
//    private GameController controller;
//    private OrthographicCamera camera;
//    private Viewport viewport;
//    private final float V_WIDTH = 1280, V_HEIGHT = 720;
//    private Label timerLabel;
//
//
//    public GameView(GameController controller, Skin skin) {
//        this.controller = controller;
//        controller.setView(this);
//        this.skin = skin;
//    }
//
//    @Override
//    public void show() {
//        camera = new OrthographicCamera();
//        viewport = new FitViewport(V_WIDTH, V_HEIGHT, camera);
//        stage = new Stage(viewport);
//
//        timerLabel = new Label("Time: 00:00", skin, "default");
//        timerLabel.setPosition(20, V_HEIGHT - 40);
//        stage.addActor(timerLabel);
//
//
//        Gdx.input.setInputProcessor(this);
//    }
//
//    @Override
//    public void render(float delta) {
//        ScreenUtils.clear(0, 0, 0, 1);
//
//        updateCamera();
//
//        com.tilldawn.Main.getBatch().setProjectionMatrix(camera.combined);
//        com.tilldawn.Main.getBatch().begin();
//        controller.updateGame(delta);
//        com.tilldawn.Main.getBatch().end();
//
//        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
//        stage.draw();
//    }
//
//    private void updateCamera() {
//        Player player = controller.getPlayerController().getPlayer();
//        camera.position.set(player.getPlayerSprite().getX(), player.getPlayerSprite().getY(), 0);
//        camera.update();
//    }
//
//    @Override
//    public void resize(int width, int height) {
//        viewport.update(width, height);
//    }
//
//    @Override
//    public void pause() {}
//
//    @Override
//    public void resume() {}
//
//    @Override
//    public void hide() {}
//
//    @Override
//    public void dispose() {}
//
//    @Override
//    public boolean keyDown(int keycode) {
//        return false;
//    }
//
//    @Override
//    public boolean keyUp(int keycode) {
//        return false;
//    }
//
//    @Override
//    public boolean keyTyped(char character) {
//        return false;
//    }
//
//    @Override
//    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//        controller.getWeaponController().handleWeaponShoot(screenX, screenY);
//        return false;
//    }
//
//    @Override
//    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
//        return false;
//    }
//
//    @Override
//    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
//        return false;
//    }
//
//    @Override
//    public boolean touchDragged(int screenX, int screenY, int pointer) {
//        return false;
//    }
//
//    @Override
//    public boolean mouseMoved(int screenX, int screenY) {
//        controller.getWeaponController().handleWeaponRotation(screenX, screenY);
//        return false;
//    }
//
//    @Override
//    public boolean scrolled(float amountX, float amountY) {
//        return false;
//    }
//
//
//    public void updateTimerDisplay(int remainingSeconds) {
//        timerLabel.setText("Time: " + remainingSeconds + "s");
//    }
//
//}













package com.tillDawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tillDawn.Controller.GameController;
import com.tillDawn.Model.Player;

public class GameView implements Screen, InputProcessor {
    private Skin skin;
    private Stage hudStage; // فقط برای HUD
    private GameController controller;
    private OrthographicCamera camera;
    private Viewport viewport;
    private final float V_WIDTH = 1280, V_HEIGHT = 720;
    private Label timerLabel;

    public GameView(GameController controller, Skin skin) {
        this.controller = controller;
        controller.setView(this);
        this.skin = skin;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(V_WIDTH, V_HEIGHT, camera);

        // Stage برای HUD با دوربین ثابت
        hudStage = new Stage(new ScreenViewport());

        timerLabel = new Label("Time: 00:00", skin, "default");
        timerLabel.setPosition(20, Gdx.graphics.getHeight() - 40);
        hudStage.addActor(timerLabel);

        // هندل ورودی هم برای HUD و هم این کلاس
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(hudStage);
        multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        updateCamera();

        com.tilldawn.Main.getBatch().setProjectionMatrix(camera.combined);
        com.tilldawn.Main.getBatch().begin();
        controller.updateGame(delta);
        com.tilldawn.Main.getBatch().end();

        hudStage.act(delta);
        hudStage.draw();
    }

    private void updateCamera() {
        Player player = controller.getPlayerController().getPlayer();
        camera.position.set(player.getPlayerSprite().getX(), player.getPlayerSprite().getY(), 0);
        camera.update();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        hudStage.getViewport().update(width, height, true);
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {}

    @Override public boolean keyDown(int keycode) { return false; }
    @Override public boolean keyUp(int keycode) { return false; }
    @Override public boolean keyTyped(char character) { return false; }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        controller.getWeaponController().handleWeaponShoot(screenX, screenY);
        return false;
    }

    @Override public boolean touchUp(int screenX, int screenY, int pointer, int button) { return false; }
    @Override public boolean touchCancelled(int screenX, int screenY, int pointer, int button) { return false; }
    @Override public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        controller.getWeaponController().handleWeaponRotation(screenX, screenY);
        return false;
    }

    @Override public boolean scrolled(float amountX, float amountY) { return false; }

    public void updateTimerDisplay(int remainingSeconds) {
        timerLabel.setText("Time: " + remainingSeconds + "s");
    }
}
