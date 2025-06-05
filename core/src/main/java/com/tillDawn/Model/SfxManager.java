package com.tillDawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;

public class SfxManager {
    private static final HashMap<String, Sound> soundMap = new HashMap<>();
    private static boolean sfxEnabled = true;

    static {
        soundMap.put("click", Gdx.audio.newSound(Gdx.files.internal("sfx/click.wav")));
        soundMap.put("shot", Gdx.audio.newSound(Gdx.files.internal("sfx/shot.wav")));
        soundMap.put("preshot", Gdx.audio.newSound(Gdx.files.internal("sfx/preshot.wav")));
        soundMap.put("lose", Gdx.audio.newSound(Gdx.files.internal("sfx/lose.wav")));
        soundMap.put("win", Gdx.audio.newSound(Gdx.files.internal("sfx/win.wav")));
    }

    public static void play(String soundName) {
        if (sfxEnabled && soundMap.containsKey(soundName)) {
            soundMap.get(soundName).play();
        }
    }

    public static void setSfxEnabled(boolean enabled) {
        sfxEnabled = enabled;
    }

    public static boolean isSfxEnabled() {
        return sfxEnabled;
    }

    public static void dispose() {
        for (Sound sound : soundMap.values()) {
            sound.dispose();
        }
    }
}
