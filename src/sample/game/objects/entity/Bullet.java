package sample.game.objects.entity;

import javafx.scene.image.Image;

public class Bullet extends Sprite {

    private int damage;

    public Bullet(int x, int y, int w, int h, Image image, int damage) {
        super(x, y, w, h, image);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
