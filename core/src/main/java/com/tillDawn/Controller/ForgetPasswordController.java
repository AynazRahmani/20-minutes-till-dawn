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

public class ForgetPasswordController {
    private ForgetPasswordView view;
    private Skin skin;
    private Label error;
//    private Table table;

    public void setView(ForgetPasswordView view) {
        this.view = view;
        this.skin = GameAssetManager.getGameAssetManager().getSkin();
        this.error = view.getErrorLabel();
//        this.table = view.getTable();
        initListeners();
    }

    private void initListeners() {
//        view.getSigninButton().addListener(new ClickListener() {
//            public void clicked(InputEvent event, float x, float y) {
//                String username = view.getUsernameField().getText().trim();
//                String password = view.getPasswordField().getText();
//                User user;
//
//                if ((user = UserDataManager.findUser(username)) == null) {
//                    error.setText("this username doesn't exist!");
//                    view.getTable().invalidateHierarchy();
//                    return;
//                }
//
//                if (!user.getPassword().equals(password)) {
//                    error.setText("incorrect password!");
//                    view.getTable().invalidateHierarchy();
//                    return;
//                }
//
//                App.setCurrentUser(user);
////                com.tilldawn.Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController(), skin));
//                com.tilldawn.Main.getMain().setScreen(new MainMenuView(new MainMenuController(), skin));
//            }
//        });

        view.getChangePasswordButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
//                String username = view.getUsernameField().getText().trim();
//
//                User user;
//                if ((user = UserDataManager.findUser(username)) == null) {
//                    error.setText("this username doesn't exist!");
//                    view.getTable().invalidateHierarchy();
//                    return;
//                }



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
