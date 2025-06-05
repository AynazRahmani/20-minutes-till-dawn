package com.tillDawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.tillDawn.Model.Enums.KeyboardType;

public class KeyboardManager {
    private KeyboardType keyboardType;

    public KeyboardManager(KeyboardType controlScheme) {
        this.keyboardType = controlScheme;
    }

    public void setKeyboardType(KeyboardType keyboardType) {
        this.keyboardType = keyboardType;
    }

    public KeyboardType getKeyboardType() {
        return keyboardType;
    }

    public boolean isUp() {
        if (keyboardType == KeyboardType.WASD) {
            return Gdx.input.isKeyPressed(Input.Keys.W);
        } else {
            return Gdx.input.isKeyPressed(Input.Keys.UP);
        }
    }

    public boolean isDown() {
        if (keyboardType == KeyboardType.WASD) {
            return Gdx.input.isKeyPressed(Input.Keys.S);
        } else {
            return Gdx.input.isKeyPressed(Input.Keys.DOWN);
        }
    }

    public boolean isLeft() {
        if (keyboardType == KeyboardType.WASD) {
            return Gdx.input.isKeyPressed(Input.Keys.A);
        } else {
            return Gdx.input.isKeyPressed(Input.Keys.LEFT);
        }
    }

    public boolean isRight() {
        if (keyboardType == KeyboardType.WASD) {
            return Gdx.input.isKeyPressed(Input.Keys.D);
        } else {
            return Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        }
    }

    public boolean reload() {
        return Gdx.input.isKeyPressed(Input.Keys.R);
    }


}
