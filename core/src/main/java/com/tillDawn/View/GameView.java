package com.tillDawn.View;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tillDawn.Controller.GameController;
import com.tillDawn.Model.App;
import com.tillDawn.Model.Enums.Ability;
import com.tillDawn.Model.MusicManager;
import com.tillDawn.Model.Player;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import java.util.ArrayList;


public class GameView implements Screen, InputProcessor {
    private Skin skin;
    private Stage hudStage;
    private Stage pauseStage;
    private GameController controller;
    private OrthographicCamera camera;
    private Viewport viewport;
    private final float V_WIDTH = 1280, V_HEIGHT = 720;
    private Label timerLabel;
    private TextButton pauseButton;
    private Dialog pauseDialog;
    private InputMultiplexer multiplexer;

    public GameView(GameController controller, Skin skin) {
        this.controller = controller;
        controller.setView(this);
        this.skin = skin;
    }

    @Override
    public void show() {
        MusicManager.playMusic(App.getCurrentMusicPath(), true);

        camera = new OrthographicCamera();
        viewport = new FitViewport(V_WIDTH, V_HEIGHT, camera);

        hudStage = new Stage(new ScreenViewport());

        timerLabel = new Label("Time: 00:00", skin, "default");
        timerLabel.setPosition(20, Gdx.graphics.getHeight() - 40);
        hudStage.addActor(timerLabel);

        pauseButton = new TextButton("Pause", skin);
        pauseButton.setPosition(Gdx.graphics.getWidth() - 120, Gdx.graphics.getHeight() - 50);
        hudStage.addActor(pauseButton);

        pauseButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                openPauseMenu();
            }
        });

        pauseStage = new Stage(new ScreenViewport());

        // ساخت دیالوگ و افزودن دکمه‌ها
        pauseDialog = new Dialog("Paused", skin);
        pauseDialog.setMovable(false);
        pauseDialog.getButtonTable().clear();

        // اضافه کردن لیبل abilities
        Label abilitiesLabel = new Label("Abilities", skin, "title");
        pauseDialog.getContentTable().add(abilitiesLabel).colspan(2).padBottom(15).row();

        // تعریف دکمه‌ها
        TextButton resumeBtn = new TextButton("Resume", skin);
        TextButton giveUpBtn = new TextButton("Give Up", skin);
        TextButton reduceTimeBtn = new TextButton("Reduce Time", skin);
        TextButton reduceHpBtn = new TextButton("Reduce HP", skin);
        TextButton increaseHpBtn = new TextButton("Increase HP", skin);
        TextButton levelUpBtn = new TextButton("Level Up", skin);
        TextButton bossFightBtn = new TextButton("Boss Fight", skin);
        TextButton exitBtn = new TextButton("Exit", skin);

        TextButton ability1 = new TextButton("VITALITY", skin);
        TextButton ability2 = new TextButton("DAMAGER", skin);
        TextButton ability3 = new TextButton("PROCREASE", skin);
        TextButton ability4 = new TextButton("AMOCREASE", skin);
        TextButton ability5 = new TextButton("SPEEDY", skin);

        ArrayList<TextButton> abilityButtons = new ArrayList<>();
        abilityButtons.add(ability1);
        abilityButtons.add(ability2);
        abilityButtons.add(ability3);
        abilityButtons.add(ability4);
        abilityButtons.add(ability5);

        // چیدن دکمه‌ها در جدول
        Table table = pauseDialog.getContentTable();

        table.add(resumeBtn).pad(10).width(340);
        table.add(giveUpBtn).pad(10).width(340);
        table.row();

        table.add(reduceTimeBtn).pad(10).width(340);
        table.add(reduceHpBtn).pad(10).width(340);
        table.row();

        table.add(increaseHpBtn).pad(10).width(340);
        table.add(levelUpBtn).pad(10).width(340);
        table.row();

        table.add(bossFightBtn).pad(10).width(340);
        table.add(exitBtn).pad(10).width(340);
        table.row();

        for (TextButton a : abilityButtons) {
            for (Ability ab : controller.getPlayerController().getPlayer().getAbilities()) {
                if (ab.toString().equalsIgnoreCase(a.getName())) {
                    table.add(a).pad(10).width(250);
                }
            }
        }


        // لیسنرها
        resumeBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                closePauseMenu();
            }
        });

        giveUpBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.giveUp();
            }
        });

        reduceTimeBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.cheatReduceTime();
            }
        });

        reduceHpBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.cheatReduceHp();
            }
        });

        increaseHpBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.cheatIncreaseHp();
            }
        });

        levelUpBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.cheatAddExp();
            }
        });

        bossFightBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // TODO: باس فایت
            }
        });

        exitBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(hudStage);
        multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(multiplexer);
    }



    private void openPauseMenu() {
        controller.setPaused(true);
        pauseDialog.show(pauseStage);
        Gdx.input.setInputProcessor(pauseStage);
    }

    private void closePauseMenu() {
        controller.setPaused(false);
        pauseDialog.hide();
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

        if (controller.isPaused()) {
            pauseStage.act(delta);
            pauseStage.draw();
        }
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
        pauseStage.getViewport().update(width, height, true);
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {
        hudStage.dispose();
        pauseStage.dispose();
    }

    @Override public boolean keyDown(int keycode) { return false; }
    @Override public boolean keyUp(int keycode) { return false; }
    @Override public boolean keyTyped(char character) { return false; }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (!controller.isPaused()) {
            controller.getWeaponController().handleWeaponShoot(screenX, screenY);
        }
        return false;
    }

    @Override public boolean touchUp(int screenX, int screenY, int pointer, int button) { return false; }
    @Override public boolean touchCancelled(int screenX, int screenY, int pointer, int button) { return false; }
    @Override public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        if (!controller.isPaused()) {
            controller.getWeaponController().handleWeaponRotation(screenX, screenY);
        }
        return false;
    }

    @Override public boolean scrolled(float amountX, float amountY) { return false; }

    public void updateTimerDisplay(int remainingSeconds) {
        timerLabel.setText("Time: " + remainingSeconds + "s");
    }
}
