package com.tillDawn.Model;

public class App {
    private static User currentUser;

    public static void setCurrentUser(User currentUser) {
        App.currentUser = currentUser;
    }

    public static User getCurrentUser() {
        return currentUser;
    }
}
