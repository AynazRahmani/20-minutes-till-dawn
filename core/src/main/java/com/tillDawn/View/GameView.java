package com.tillDawn.View;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
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
import com.tillDawn.Model.TreeEnemy;

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
    private Label killLabel;
    private Label ammoLabel;
    private Label levelLabel;
    private Label expLabel;
    private TextButton pauseButton;
    private Dialog pauseDialog;
    private InputMultiplexer multiplexer;
    private ArrayList<Image> heartImages = new ArrayList<>();

    private Label errorLabel;


    public GameView(GameController controller, Skin skin) {
        this.controller = controller;
        controller.setView(this);
        this.skin = skin;
        errorLabel = new Label("", skin);
        errorLabel.setColor(1,0,0,1);
    }

    @Override
    public void show() {
//        MusicManager.playMusic(App.getCurrentMusicPath(), true);

        camera = new OrthographicCamera();
        viewport = new FitViewport(V_WIDTH, V_HEIGHT, camera);

        hudStage = new Stage(new ScreenViewport());

        timerLabel = new Label("Time: 00:00", skin, "default");
        timerLabel.setPosition(20, Gdx.graphics.getHeight() - 40);
        hudStage.addActor(timerLabel);

        pauseButton = new TextButton("Pause", skin);
        pauseButton.setPosition(Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 150);
        hudStage.addActor(pauseButton);

        killLabel = new Label("kills: 0",skin);
        killLabel.setPosition(20, Gdx.graphics.getHeight() - 120);
        hudStage.addActor(killLabel);

        ammoLabel = new Label("remained shots: 0",skin);
        ammoLabel.setPosition(20, Gdx.graphics.getHeight() - 150);
        hudStage.addActor(ammoLabel);

        levelLabel = new Label("level: 0",skin);
        levelLabel.setPosition(20, Gdx.graphics.getHeight() - 180);
        hudStage.addActor(levelLabel);

        expLabel = new Label("xp: 0",skin);
        expLabel.setPosition(20, Gdx.graphics.getHeight() - 210);
        hudStage.addActor(expLabel);

        pauseButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                openPauseMenu();
            }
        });

        pauseStage = new Stage(new ScreenViewport());

        pauseDialog = new Dialog("Paused", skin);
        pauseDialog.setMovable(false);
        pauseDialog.getButtonTable().clear();

        Label abilitiesLabel = new Label("Abilities", skin, "title");
        pauseDialog.getContentTable().add(abilitiesLabel).colspan(2).padBottom(15).row();

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

        Table table = pauseDialog.getContentTable();

        table.center();
        table.defaults().center();


        table.setFillParent(true);
        table.align(Align.center);

        table.add(resumeBtn).pad(10).width(340);
        table.add(giveUpBtn).pad(10).width(340);
        table.add(ability1).pad(10).width(300);
        table.row();

        table.add(reduceTimeBtn).pad(10).width(340);
        table.add(reduceHpBtn).pad(10).width(340);
        table.add(ability2).pad(10).width(300);
        table.row();

        table.add(increaseHpBtn).pad(10).width(340);
        table.add(levelUpBtn).pad(10).width(340);
        table.add(ability3).pad(10).width(300);
        table.row();

        table.add(bossFightBtn).pad(10).width(340);
        table.add(exitBtn).pad(10).width(340);
        table.add(ability4).pad(10).width(300);
        table.row();

        errorLabel.setWrap(true);
        errorLabel.setAlignment(Align.center);
        table.add(errorLabel).width(600).center().padTop(10).padBottom(10);
        table.add().expandX();
        table.add(ability5).pad(10).width(300);
        table.row();

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
                // TODO: boss fight
            }
        });

        exitBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        ability1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.ability1();
            }
        });

        ability2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.ability2();
            }
        });

        ability3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.ability3();
            }
        });

        ability4.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.ability4();
            }
        });

        ability5.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.ability5();
            }
        });

        multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(hudStage);
        multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(multiplexer);

        int hp = controller.getPlayerController().getPlayer().getHp();
        for (int i = 0; i < hp; i++) {
            Image heart = new Image(new Texture("hp/hp.png"));
            heart.setSize(30, 30);
            heart.setPosition(20 + i * 35, Gdx.graphics.getHeight() - 80);
            hudStage.addActor(heart);
            heartImages.add(heart);
        }

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
        for (TreeEnemy tree : controller.getWorldController().getTrees()) {
            tree.render(com.tilldawn.Main.getBatch());
        }


        com.tilldawn.Main.getBatch().end();

        hudStage.act(delta);
        hudStage.draw();

        updateHeartsDisplay();
        updateKillsDisplay();
        updateAmmoDisplay();
        updateLevelDisplay();
        updateExpDisplay();


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


    public void updateHeartsDisplay() {
        int currentHp = controller.getPlayerController().getPlayer().getHp();

        for (Image img : heartImages) {
            img.remove();
        }
        heartImages.clear();

        for (int i = 0; i < currentHp; i++) {
            Image heart = new Image(new Texture("hp/hp.png"));
            heart.setSize(30, 30);
            heart.setPosition(20 + i * 35, Gdx.graphics.getHeight() - 80);
            hudStage.addActor(heart);
            heartImages.add(heart);
        }
    }

    public void updateKillsDisplay() {
        killLabel.setText("kills: " + controller.getPlayerController().getPlayer().getKillsNumber());
    }

    public void updateAmmoDisplay() {
        ammoLabel.setText("remained shots: " + controller.getWeaponController().getWeapon().getAmmo());
    }

    public void updateLevelDisplay() {
        levelLabel.setText("level: " + controller.getPlayerController().getPlayer().getLevel());
    }

    public void updateExpDisplay() {
        expLabel.setText("xp: " + controller.getPlayerController().getPlayer().getExp());
    }

    public Label getErrorLabel() {
        return errorLabel;
    }
}
