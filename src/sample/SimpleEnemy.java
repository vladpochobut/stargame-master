package sample;

import javafx.scene.image.Image;

import static sample.ControllerUtils.*;

public class SimpleEnemy extends ActiveObject {

    private static final Image ENEMY_IMAGE = new Image(ENEMY_IMG_PATH);

    public SimpleEnemy(int x, int y, int w, int h) {
        super(x, y, w, h, ENEMY_IMAGE);
    }

    @Override
    public Sprite shoot() {
        return new EnemyBullet((int) this.getTranslateX() + 20, (int) this.getTranslateY(), 5, 5, BULLET_IMAGE);
    }
}
