package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

import static sample.ControllerUtils.*;

public class SimpleEnemy extends ActiveObject {

    private static final Image ENEMY_IMAGE = new Image(ENEMY_IMG_PATH);

    public SimpleEnemy(int x, int y, int w, int h) {
        super(x, y, w, h, ENEMY_IMAGE);
    }

    @Override
    public Sprite shoot() {
        return new EnemyBullet((int) this.getTranslateX() + 20, (int) this.getTranslateY(), BULLET_WIDTH, BULLET_HEIGHT, BULLET_IMAGE);
    }

    public void move() {
        KeyValue xValue = new KeyValue(this.xProperty(), 100);
        KeyValue yValue = new KeyValue(this.yProperty(), 100);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(3000), xValue, yValue);

        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.getKeyFrames().addAll(keyFrame);
        timeline.play();
    }
}
