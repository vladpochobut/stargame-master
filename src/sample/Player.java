package sample;

import javafx.scene.image.Image;

import static java.lang.StrictMath.*;
import static sample.ControllerUtils.*;

public class Player extends ActiveObject {

    private static final Image PLAYER_IMAGE = new Image(PLAYER_IMG_PATH);

    Player(int x, int y, int w, int h) {
        super(x, y, w, h, PLAYER_IMAGE);
    }

    @Override
    public Sprite shoot() {
        return new PlayerBullet(((int) this.getTranslateX() + 15), ((int) this.getTranslateY()), BULLET_WIDTH, BULLET_HEIGHT, BULLET_IMAGE);
    }
    public Sprite shoot1() {
        return new PlayerBullet(((int) this.getTranslateX() + 15), ((int) this.getTranslateY()), DOUBLE_BULLET_WIDTH, BULLET_HEIGHT, DOUBLE_BULLET_IMAGE);
    }

}
