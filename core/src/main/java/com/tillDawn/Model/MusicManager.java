package com.tillDawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicManager {
    private static Music currentMusic;
    private static float volume = 1f;
    private static boolean muted = false;

    public static void playMusic(String path, boolean loop) {
        stopMusic();
        currentMusic = Gdx.audio.newMusic(Gdx.files.internal(path));
        currentMusic.setLooping(loop);
        currentMusic.setVolume(muted ? 0 : volume);
        currentMusic.play();
    }

    public static void stopMusic() {
        if (currentMusic != null) {
            currentMusic.stop();
            currentMusic.dispose();
            currentMusic = null;
        }
    }

    public static void setVolume(float newVolume) {
        volume = newVolume;
        if (currentMusic != null && !muted) {
            currentMusic.setVolume(volume);
        }
    }

    public static float getVolume() {
        return volume;
    }

    public static void mute() {
        muted = true;
        if (currentMusic != null) currentMusic.setVolume(0);
    }

    public static void unmute() {
        muted = false;
        if (currentMusic != null) currentMusic.setVolume(volume);
    }

    public static boolean isMuted() {
        return muted;
    }
}
