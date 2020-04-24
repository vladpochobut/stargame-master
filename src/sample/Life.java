package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static sample.ControllerUtils.*;

public class Life extends ImageView {

    private static final Image LIFE_IMG = new Image(LIFE_IMG_PATH);

    Life(int x, int y, int w, int h) {
        super(LIFE_IMG);
        setTranslateX(x);
        setTranslateY(y);
        setFitWidth(w);
        setFitHeight(h);
        setViewport(new Rectangle2D(0, 0, w, h));
    }

    public void increaseLife() {
//        imageView.setViewport(new Rectangle2D(offset_x, offset_y, width, height));
    }

    public void decreaseLife() {
        setViewport(new Rectangle2D(getViewport().getMinX() - ONE_HEART_WIDTH, 0, getFitWidth(), getFitHeight()));
    }
}
