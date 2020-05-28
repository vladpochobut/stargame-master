package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;

import static sample.ControllerUtils.*;

public class Life extends ImageView implements Serializable {

    private static final Image LIFE_IMG = new Image(LIFE_IMG_PATH);

    Life(int x, int y, int w, int h) {
        super(LIFE_IMG);
        setTranslateX(x);
        setTranslateY(y);
        setFitWidth(w);
        setFitHeight(h);
        setViewport(new Rectangle2D(0, 0, w, h));
    }

    public void getLife(int x, int y, int w, int h){
        getTranslateX();
        getTranslateY();
        getFitWidth();
        getFitHeight();
        getViewport();
    }

    public void increaseLife() {
//        imageView.setViewport(new Rectangle2D(offset_x, offset_y, width, height));
    }

    public void decreaseLife() {
        setViewport(new Rectangle2D(getViewport().getMinX() - ONE_HEART_WIDTH, 0, getFitWidth(), getFitHeight()));
    }
}
