package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

import static sample.ControllerUtils.*;

public class BonusCoin extends Sprite {

    private static final Image COIN_IMG = new Image(COIN_IMG_PATH);

    BonusCoin(int w, int h) {
        super((int)(100+Math.random()*400), 0, w, h, COIN_IMG);
    }


    public void move(){
        KeyValue xValue = new KeyValue(this.xProperty(), 100);
        KeyValue yValue = new KeyValue(this.yProperty(), 100);
        KeyValue xValue1 = new KeyValue(this.xProperty(), 0);
        KeyValue yValue1 = new KeyValue(this.yProperty(), 200);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(3000), xValue, yValue);

        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().addAll(keyFrame);
        timeline.play();

    }
}
