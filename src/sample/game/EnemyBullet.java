package sample.game;

import javafx.scene.image.Image;

public class EnemyBullet extends Bullet {

    public EnemyBullet(int x, int y, int w, int h, Image image, int damage) {
        super(x, y, w, h, image, damage);
        setRotate(180);
    }

}
