package sample;

import javafx.scene.image.Image;

import static sample.ControllerUtils.*;

public class PlayerLife extends Sprite {

    private static final Image LIFE_IMG = new Image(LIFE_IMG_PATH);
    private final int x;
    private final int y;
    private int w;
    private int h;


    PlayerLife(int x, int y, int w, int h) {
        super(x, y, w, h, LIFE_IMG);
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void setW(int w){
        this.w =w;
    }
    public int getW(){
        return this.w;
    }


}
