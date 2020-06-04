package sample.game;

import javafx.scene.image.Image;

import java.io.Serializable;

import static sample.game.ControllerUtils.*;

public class Player extends ActiveObject implements Serializable {

    private static final Image PLAYER_IMAGE = new Image(PLAYER_IMG_PATH);
    private Life life;

    public Player(int x, int y, int w, int h, int hp, Life life) {
        super(x, y, w, h, PLAYER_IMAGE, hp);
        this.life = life;
    }

    public Life getLife() {
        return life;
    }

    public void setLife(Life life) {
        this.life = life;
    }

    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);
        life.decreaseLife();
    }

    @Override
    public void shoot() {
        Main.getMainGamePane().getChildren().add(new PlayerBullet(((int) this.getTranslateX() + 15),
                ((int) this.getTranslateY()), BULLET_WIDTH, BULLET_HEIGHT, BULLET_IMAGE, PLAYER_BULLET_DAMAGE));
    }

    public void shoot1() {
        Main.getMainGamePane().getChildren().add(
                new PlayerBullet(((int) this.getTranslateX() + 15), ((int) this.getTranslateY()),
                        DOUBLE_BULLET_WIDTH, BULLET_HEIGHT, DOUBLE_BULLET_IMAGE, PLAYER_BULLET_DAMAGE));
    }

}
