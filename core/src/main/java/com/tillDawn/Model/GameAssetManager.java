package com.tillDawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.tillDawn.Model.Enums.HeroType;
import com.tillDawn.Model.Enums.WeaponType;

import com.badlogic.gdx.utils.Array;

import java.util.EnumMap;

public class GameAssetManager {
    private static GameAssetManager gameAssetManager;
    private final Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

    private final EnumMap<HeroType, Animation<Texture>> heroAnimations = new EnumMap<>(HeroType.class);
    private final EnumMap<HeroType, Texture> heroTextures = new EnumMap<>(HeroType.class);
    private final EnumMap<WeaponType, Texture> weaponTextures = new EnumMap<>(WeaponType.class);


    private final String smg = "smg/SMGStill.png";
    private final Texture smgTexture = new Texture(smg);

    private final String bullet = "bullet.png";


    private GameAssetManager(){
        loadHeroAnimations();
        loadHeroTextures();
//        loadWeaponTextures();
    }

    public static GameAssetManager getGameAssetManager(){
        if (gameAssetManager == null){
            gameAssetManager = new GameAssetManager();
        }
        return gameAssetManager;
    }


    private void loadHeroAnimations() {
        for (HeroType hero : HeroType.values()) {
            Array<Texture> frames = new Array<>();
            int heroNumber = hero.getHeroNumber();
            for (int i = 0; i <= 5; i++) {
                String path = heroNumber + "/Idle_" + i + ".png";
                frames.add(new Texture(Gdx.files.internal(path)));
            }
            Animation<Texture> animation = new Animation<>(0.1f, frames, Animation.PlayMode.LOOP);
            heroAnimations.put(hero, animation);
        }
    }


    public void loadHeroTextures() {
        for (HeroType hero : HeroType.values()) {
            int heroNumber = hero.getHeroNumber();
            heroTextures.put(hero, new Texture(Gdx.files.internal(heroNumber + "/Idle_0.png")));
        }
    }

//    private void loadWeaponTextures() {
//        for (WeaponType weapon : WeaponType.values()) {
//            String path = "weapons/" + weapon.getName() + ".png"; // مثلاً: weapons/SMG.png
//            weaponTextures.put(weapon, new Texture(Gdx.files.internal(path)));
//        }
//    }


    public Skin getSkin() {
        return skin;
    }


//    public Animation<Texture> getCharacterAnimation (HeroType heroType) {
//
//        ArrayList<Texture> frames = new ArrayList<>();
//        for (int i = 0; i <= 5; i++) {
//            Texture frame = new Texture(heroType.getHeroNumber() + "/Idle_" + i + ".png");
//            frames.add(frame);
//        }
//        return new Animation<>(0.1, frames);
//    }

    public Animation<Texture> getHeroAnimation(HeroType hero) {
        return heroAnimations.get(hero);
    }

    public Texture getHeroTexture (HeroType heroType) {
        return heroTextures.get(heroType);
    }

    public String getHeroPath (HeroType heroType) {
        if (heroType != null) {
            System.out.println(heroType.getHeroNumber() + "/Idle_0.png");
            return heroType.getHeroNumber() + "/Idle_0.png";
        }
        return "1/Idle_0.png";
    }

//    public Texture getWeaponTexture(WeaponType weapon) {
//        return weaponTextures.get(weapon);
//    }


    public Texture getSmgTexture(){
        return smgTexture;
    }

    public String getSmg(){
        return smg;
    }

    public String getBullet(){
        return bullet;
    }

    public Texture getTreeTexture() {
        return new Texture("enemy/tree.png");
    }

}
