package sample;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;

public class Sprite extends Rectangle {
    private boolean dead = false;

    //TODO:прописать модификаторы паблик
    Sprite(int x, int y, int w, int h, Image image) {
        setWidth(w);
        setHeight(h);
        setTranslateX(x);
        setTranslateY(y);
        this.setFill(new ImagePattern(image));
    }

    void moveLeft() {
        setTranslateX(getTranslateX() - 5);
    }

    void moveRight() {
        setTranslateX(getTranslateX() + 5);
    }

    void moveUp() {

        setTranslateY(getTranslateY() - 5);
    }

    void moveDown() {
        setTranslateY(getTranslateY() + 5);
    }

    void moveAtAnAngleRigth() {
        setTranslateY(getTranslateY() - 5);
        setTranslateX(getTranslateX() + 5);
    }

    void moveAtAnAngleLeft() {
        setTranslateY(getTranslateY() - 5);
        setTranslateX(getTranslateX() - 5);
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
}