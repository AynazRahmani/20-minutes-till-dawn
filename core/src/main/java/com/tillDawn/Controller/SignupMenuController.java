package com.tillDawn.Controller;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.tillDawn.Model.App;
import com.tillDawn.Model.GameAssetManager;
import com.tillDawn.Model.User;
import com.tillDawn.Model.UserDataManager;
import com.tillDawn.View.MainMenuView;
import com.tillDawn.View.PreGameMenuView;
import com.tillDawn.View.SignInMenuView;
import com.tillDawn.View.SignupMenuView;

public class SignupMenuController {
    private SignupMenuView view;
    private Skin skin;
    private Label error;
//    private Table table;

    public void setView(SignupMenuView view) {
        this.view = view;
        this.skin = GameAssetManager.getGameAssetManager().getSkin();
        this.error = view.getErrorLabel();
//        this.table = view.getTable();
        initListeners();
    }

    private void initListeners() {
        view.getSignupButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                String username = view.getUsernameField().getText().trim();
                String password = view.getPasswordField().getText();
                int questionIndex = view.getQuestionBox().getSelectedIndex();
                String answer = view.getAnswerField().getText().trim();

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

                if (answer.isEmpty()) {
                    error.setText("invalid answer!");
                    view.getTable().invalidateHierarchy();
                    return;
                }


                String avatar = UserDataManager.getRandomAvatarPath();
                User user = new User(username, password, questionIndex, answer, avatar);
                UserDataManager.addUser(user);
                App.setCurrentUser(user);
//                com.tilldawn.Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController(), skin));
                com.tilldawn.Main.getMain().setScreen(new MainMenuView(new MainMenuController(), skin));
            }
        });

        view.getSkipButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                com.tilldawn.Main.getMain().setScreen(new MainMenuView(new MainMenuController(), skin));
            }
        });

        view.getSigninButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                com.tilldawn.Main.getMain().setScreen(new SignInMenuView(new SignInMenuController(), skin));
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
