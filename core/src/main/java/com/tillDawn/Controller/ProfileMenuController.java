package com.tillDawn.Controller;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tillDawn.Model.*;
import com.tillDawn.View.*;

public class ProfileMenuController {
    private ProfileMenuView view;
    private Skin skin;
    private Label error;
//    private Table table;

    public void setView(ProfileMenuView view) {
        this.view = view;
        this.skin = GameAssetManager.getGameAssetManager().getSkin();
        this.error = view.getErrorLabel();
//        this.table = view.getTable();
        initListeners();
    }

    private void initListeners() {
        view.getApplyChanges().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                SfxManager.play("click");
                String username = view.getUsernameField().getText().trim();
                String password = view.getPasswordField().getText();
                int avatarIndex = view.getAvatar().getSelectedIndex();
                User user = App.getCurrentUser();

                if (user == null) {
                    error.setText("you must sign in first!");
                    view.getTable().invalidateHierarchy();
                    return;
                }

                if (!user.getUsername().equals(username)) {
                    if (username.isEmpty()) {
                        error.setText("invalid username!");
                        view.getTable().invalidateHierarchy();
                        return;
                    }

                    if (UserDataManager.isUsernameTaken(username)) {
                        error.setText("this username already exists!");
                        view.getTable().invalidateHierarchy();
                        return;
                    }

                    user.setUsername(username);
                }

                if (!user.getPassword().equals(password)) {
                    if (password.isEmpty()) {
                        error.setText("invalid password!");
                        view.getTable().invalidateHierarchy();
                        return;
                    }

                    if (password.length() < 8) {
                        error.setText("password must contain at least 8 characters!");
                        view.getTable().invalidateHierarchy();
                        return;
                    }

                    if (!password.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@_()*&%$#]).{8,}")) {
                        error.setText("weak password!");
                        view.getTable().invalidateHierarchy();
                        return;
                    }

                    user.setPassword(password);
                }


                avatarIndex++;
                user.setAvatarPath("avatars/avatar" + avatarIndex + ".png");
                UserDataManager.updateUser(user);

                com.tilldawn.Main.getMain().setScreen(new MainMenuView(new MainMenuController(), skin));
            }
        });

        view.getDeleteAccount().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                SfxManager.play("click");

                UserDataManager.deleteUser(App.getCurrentUser());
                App.setCurrentUser(null);
                com.tilldawn.Main.getMain().setScreen(new SignupMenuView(new SignupMenuController(), skin));
            }
        });

        view.getExitButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                SfxManager.play("click");

                com.tilldawn.Main.getMain().setScreen(new MainMenuView(new MainMenuController(), skin));
            }
        });

    }

    public void handleButtons() {
        // handled via listeners
    }

    public Skin getSkin() {
        return skin;
    }
}
