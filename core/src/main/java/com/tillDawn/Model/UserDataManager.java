package com.tillDawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Comparator;
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
        int userID = getUsers().get(getUsers().size() - 1).getUsrID() + 10;
        user.setUsrID(userID);
        List<User> users = getUsers();
        users.add(user);
        FileHandle file = Gdx.files.local(FILE_PATH);
        file.writeString(gson.toJson(users), false);
    }

    public static String getRandomAvatarPath() {
        int num = new Random().nextInt(3) + 1;
        return "avatars/avatar" + num + ".png";
    }

    public static User findUser(String username) {
        for (User user : getUsers()) {
            if (user.username.equals(username)) return user;
        }
        return null;
    }

    public static void updateUser(User updatedUser) {
        List<User> users = getUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsrID() == updatedUser.getUsrID()) {
                users.set(i, updatedUser);
                FileHandle file = Gdx.files.local(FILE_PATH);
                file.writeString(gson.toJson(users), false);
                return;
            }
        }
    }

    public static void deleteUser(User userToRemove) {
        int userId = userToRemove.getUsrID();
        List<User> users = getUsers();
        users.removeIf(user -> user.getUsrID() == userId);

        FileHandle file = Gdx.files.local(FILE_PATH);
        file.writeString(gson.toJson(users), false);
    }


    public static ArrayList<User> getTopUsersByUsername() {
        List<User> users = getUsers();
        users.sort(Comparator.comparing(User::getUsername));
        return new ArrayList<>(users.subList(0, Math.min(10, users.size())));
    }

    public static ArrayList<User> getTopUsersByScore() {
        List<User> users = getUsers();
        users.sort((u1, u2) -> Integer.compare(u2.getScore(), u1.getScore()));
        return new ArrayList<>(users.subList(0, Math.min(10, users.size())));
    }

    public static ArrayList<User> getTopUsersByKills() {
        List<User> users = getUsers();
        users.sort((u1, u2) -> Integer.compare(u2.getKillNumber(), u1.getKillNumber()));
        return new ArrayList<>(users.subList(0, Math.min(10, users.size())));
    }

    public static ArrayList<User> getTopUsersBySurvivalTime() {
        List<User> users = getUsers();
        users.sort((u1, u2) -> Integer.compare(u2.getMaxSurvivalTime(), u1.getMaxSurvivalTime()));
        return new ArrayList<>(users.subList(0, Math.min(10, users.size())));
    }

}
