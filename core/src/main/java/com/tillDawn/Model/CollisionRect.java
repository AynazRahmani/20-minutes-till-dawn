package com.tillDawn.Model;

public class CollisionRect {

    float x, y;
    float width, height;
    public CollisionRect(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void move(float x, float y){
        this.x = x;
        this.y = y;
    }

    public boolean collidesWith(CollisionRect rect){
        return x < rect.x + rect.width && y < rect.y + rect.height && x > rect.x && y  > rect.y;
    }

    public boolean isCloseTo(CollisionRect rect){
        return x < rect.x + rect.width + 2 && y < rect.y + rect.height + 2 && 2 + x > rect.x && 2 + y > rect.y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
