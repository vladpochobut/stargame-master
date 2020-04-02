package sample;

import javafx.scene.image.Image;

import static sample.ControllerUtils.*;

public class Player extends ActiveObject {

    private static final Image PLAYER_IMAGE = new Image(PLAYER_IMG_PATH);

    Player(int x, int y, int w, int h) {
        super(x, y, w, h, PLAYER_IMAGE);
    }

    @Override
    public Sprite shoot() {
        return new PlayerBullet((int) this.getTranslateX() + 20, (int) this.getTranslateY(), 5, 5, BULLET_IMAGE);
    }
}
