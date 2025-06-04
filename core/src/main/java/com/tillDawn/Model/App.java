package com.tillDawn.Model;

import com.tillDawn.Model.Enums.KeyboardType;

public class App {
    private static User currentUser;
    private static String currentMusicPath = "music/music1.mp3";
    private static KeyboardManager keyboardManager = new KeyboardManager(KeyboardType.WASD);

    public static void setCurrentUser(User currentUser) {
        App.currentUser = currentUser;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static String getCurrentMusicPath() {
        return currentMusicPath;
    }

    public static void setCurrentMusicPath(String currentMusicPath) {
        App.currentMusicPath = currentMusicPath;
    }

    public static KeyboardManager getKeyboardManager() {
        return keyboardManager;
    }
}
