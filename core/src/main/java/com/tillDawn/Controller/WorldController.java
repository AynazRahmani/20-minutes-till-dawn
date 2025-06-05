package com.tillDawn.Controller;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.tillDawn.Model.CollisionRect;
import com.tillDawn.Model.GameAssetManager;
import com.tillDawn.Model.TreeEnemy;

import java.util.ArrayList;

public class WorldController {
    private PlayerController playerController;
    private Texture backgroundTexture;
    private float backgroundX = 0;
    private float backgroundY = 0;
    private ArrayList<TreeEnemy> trees = new ArrayList<>();


    public WorldController(PlayerController playerController) {
        this.backgroundTexture = new Texture("background.png");
        this.playerController = playerController;
    }

    public void update() {

        com.tilldawn.Main.getBatch().draw(backgroundTexture, backgroundX, backgroundY);
    }

    public ArrayList<TreeEnemy> getTrees() {
        return trees;
    }

    public void setTrees(ArrayList<TreeEnemy> trees) {
        this.trees = trees;
    }

    public void generateTrees(int count) {
        Texture treeTexture = GameAssetManager.getGameAssetManager().getTreeTexture();

        for (int i = 0; i < count; i++) {
            float x = MathUtils.random(0, backgroundTexture.getWidth() - 64);
            float y = MathUtils.random(0, backgroundTexture.getHeight() - 64);

            if (playerIsTooClose(x, y)) {
                i--;
                continue;
            }

            trees.add(new TreeEnemy(treeTexture, x, y));
        }
    }

    private boolean playerIsTooClose(float x, float y) {
//        float px = playerController.getPlayer().getX();
//        float py = playerController.getPlayer().getY();
//        return Vector2.dst(x, y, px, py) < 150;
        return false;
    }

    public boolean isBlocked(CollisionRect playerRect) {
        for (TreeEnemy tree : trees) {
            if (tree.getCollisionRect().collidesWith(playerRect)) {
                return true;
            }
        }
        return false;
    }
    public boolean isClose(CollisionRect playerRect) {
        for (TreeEnemy tree : trees) {
            if (tree.getCollisionRect().isCloseTo(playerRect)) {
                return true;
            }
        }
        return false;
    }



}
