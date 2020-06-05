package sample.game.objects.entity;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Sprite extends Rectangle {
    private boolean dead = false;

    //TODO:прописать модификаторы паблик
   public Sprite(int x, int y, int w, int h, Image image) {
        setWidth(w);
        setHeight(h);
        setTranslateX(x);
        setTranslateY(y);
        this.setFill(new ImagePattern(image));
    }

    public void moveLeft() {
        setTranslateX(getTranslateX() - 5);
    }

    public void moveRight() {
        setTranslateX(getTranslateX() + 5);
    }

    public void moveUp() {

        setTranslateY(getTranslateY() - 5);
    }

    public void moveDown() {
        setTranslateY(getTranslateY() + 5);
    }

    public void moveAtAnAngleRigth() {
        setTranslateY(getTranslateY() - 5);
        setTranslateX(getTranslateX() + 5);
    }

    public void moveAtAnAngleLeft() {
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