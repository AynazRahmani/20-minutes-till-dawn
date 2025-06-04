package com.tillDawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserDataManager {
    private static final String FILE_PATH = "users.json";
    private static final Gson gson = new Gson();

    public static List<User> getUsers() {
        FileHandle file = Gdx.files.local(FILE_PATH);
        if (!file.exists()) return new ArrayList<>();
        String json = file.readString();
        return gson.fromJson(json, new TypeToken<List<User>>() {}.getType());
    }

    public static boolean isUsernameTaken(String username) {
        for (User user : getUsers()) {
            if (user.username.equals(username)) return true;
        }
        return false;
    }

    public static void addUser(User user) {
        List<User> users = getUsers();
        users.add(user);
        FileHandle file = Gdx.files.local(FILE_PATH);
        file.writeString(gson.toJson(users), false);
    }

    public static String getRandomAvatarPath() {
        int num = new Random().nextInt(3) + 1; // avatar1.png to avatar5.png
        return "avatars/avatar" + num + ".png";
    }

    public static User findUser(String username) {
        for (User user : getUsers()) {
            if (user.username.equals(username)) return user;
        }
        return null;
    }
}
