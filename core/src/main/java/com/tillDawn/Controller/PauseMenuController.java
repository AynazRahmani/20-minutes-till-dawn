package com.tillDawn.Controller;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tillDawn.Model.App;
import com.tillDawn.Model.GameAssetManager;
import com.tillDawn.Model.User;
import com.tillDawn.Model.UserDataManager;
import com.tillDawn.View.ForgetPasswordView;
import com.tillDawn.View.MainMenuView;
import com.tillDawn.View.SignInMenuView;

public class PauseMenuController {
    private SignInMenuView view;
    private Skin skin;
    private Label error;

    public void setView(SignInMenuView view) {
        this.view = view;
        this.skin = GameAssetManager.getGameAssetManager().getSkin();
        this.error = view.getErrorLabel();
        initListeners();
    }

    private void initListeners() {
        view.getSigninButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                String username = view.getUsernameField().getText().trim();
                String password = view.getPasswordField().getText();
                User user;

                if ((user = UserDataManager.findUser(username)) == null) {
                    error.setText("this username doesn't exist!");
                    view.getTable().invalidateHierarchy();
                    return;
                }

                if (!user.getPassword().equals(password)) {
                    error.setText("incorrect password!");
                    view.getTable().invalidateHierarchy();
                    return;
                }

                App.setCurrentUser(user);
                com.tilldawn.Main.getMain().setScreen(new MainMenuView(new MainMenuController(), skin));
            }
        });

        view.getForgetPasswordButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                String username = view.getUsernameField().getText().trim();

                User user;
                if ((user = UserDataManager.findUser(username)) == null) {
                    error.setText("this username doesn't exist!");
                    view.getTable().invalidateHierarchy();
                    return;
                }

                com.tilldawn.Main.getMain().setScreen(new ForgetPasswordView(new ForgetPasswordController(user), skin));
            }
        });

    }

    public void handleButtons() {

    }

    public Skin getSkin() {
        return skin;
    }
}
