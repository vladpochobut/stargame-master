package sample;

import javafx.scene.image.Image;

public abstract class ActiveObject extends Sprite {

    ActiveObject(int x, int y, int w, int h, Image image) {
        super(x, y, w, h, image);

    }

    public abstract Sprite shoot();
}
