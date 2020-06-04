package sample.game;

import javafx.scene.image.Image;

public abstract class ActiveObject extends Sprite {

    private int hp;

    ActiveObject(int x, int y, int w, int h, Image image, int hp) {
        super(x, y, w, h, image);
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public abstract void shoot();

    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp <= 0) {
            this.setDead(true);
        }
    }

}
