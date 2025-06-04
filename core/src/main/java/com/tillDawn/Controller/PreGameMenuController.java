package com.tillDawn.Controller;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tillDawn.Model.*;
import com.tillDawn.Model.Enums.HeroType;
import com.tillDawn.Model.Enums.WeaponType;
import com.tillDawn.View.GameView;
import com.tillDawn.View.MainMenuView;
import com.tillDawn.View.PreGameMenuView;
import com.tillDawn.View.SignInMenuView;

public class PreGameMenuController {
    private PreGameMenuView view;
    private PreGame pregame;


    public void setView(PreGameMenuView view) {
        this.view = view;
        this.pregame = new PreGame();
        initListeners();
    }

    private void initListeners() {
        view.getPlayButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                HeroType heroType = HeroType.values()[view.getSelectHero().getSelectedIndex()];
                WeaponType weaponType = WeaponType.values()[view.getSelectWeapon().getSelectedIndex()];
                int duration = Integer.parseInt(view.getSelectDuration().getSelected().toString());

                pregame.setDuration(duration);
                pregame.setHeroType(heroType);
                pregame.setWeaponType(weaponType);

                if (App.getCurrentUser() != null) {
                    App.getCurrentUser().setHeroType(heroType);
                    App.getCurrentUser().setWeaponType(weaponType);
                }

                com.tilldawn.Main.getMain().setScreen(new GameView(new GameController(heroType, weaponType, duration),
                    GameAssetManager.getGameAssetManager().getSkin()));

            }
        });

    }

    public void handleButtons() {
        // handled via listeners
    }


//    public void handlePreGameMenuButtons() {
//        if (view != null) {
//            com.tilldawn.Main.getMain().getScreen().dispose();
////            com.tilldawn.Main.getMain().setScreen(new GameView(new GameController(), GameAssetManager.getGameAssetManager().getSkin()));
//        }
//    }

    public PreGame getPregame() {
        return pregame;
    }

    public void setPregame(PreGame pregame) {
        this.pregame = pregame;
    }

}
