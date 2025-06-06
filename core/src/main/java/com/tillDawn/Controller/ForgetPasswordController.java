package com.tillDawn.Controller;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tillDawn.Model.*;
import com.tillDawn.View.ForgetPasswordView;
import com.tillDawn.View.MainMenuView;

public class ForgetPasswordController {
    private User user;
    private ForgetPasswordView view;
    private Skin skin;
    private Label error;
//    private Table table;

    public ForgetPasswordController(User user) {
        this.user = user;
    }

    public void setView(ForgetPasswordView view) {
        this.view = view;
        this.skin = GameAssetManager.getGameAssetManager().getSkin();
        this.error = view.getErrorLabel();
//        this.table = view.getTable();
        initListeners();
    }

    private void initListeners() {

        view.getChangePasswordButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                SfxManager.play("click");

                if (user == null) {
                    error.setText("this username doesn't exist!");
                    view.getTable().invalidateHierarchy();
                    return;
                }


                if (!user.getSecurityAnswer().equalsIgnoreCase(view.getAnswerField().getText())) {
                    error.setText("incorrect answer to the security question!");
                    view.getTable().invalidateHierarchy();
                    return;
                }

                String password = view.getPasswordField().getText();

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
                UserDataManager.updateUser(user);
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getQuestion() {
        if (user == null) {
            return "answer to your security question.";
        }
        if (user.getSecurityQuestionIndex() == 0) {
            return "your first pet's name?";
        }
        if (user.getSecurityQuestionIndex() == 1) {
            return "your first grade teacher's name";
        }
        if (user.getSecurityQuestionIndex() == 2) {
            return "behtarin TA?";
        }
        return "error!";
    }
}
