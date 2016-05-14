package com.mataskaairaitis.placeholder.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;

/**
 * Created by mataskairaitis on 12/05/16.
 */
public class PlayerModel {

    private float x;
    private float y;
    private float width;
    private float height;
    private float radius;
    private Color color;

    public PlayerModel(float x, float y, float radius, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }

    public PlayerModel(Circle circ, Color color) {
        this(circ.x, circ.y, circ.radius, color);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
