package sample.game.objects;

import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

import static sample.game.objects.ControllerUtils.*;

public class NormalEnemy extends ActiveObject {
    private static final Image NORMAL_ENEMY_IMAGE = new Image(NORMAL_ENEMY_IMG_PATH);

    NormalEnemy(int x, int y, int w, int h) {
        super(x, y, w, h, NORMAL_ENEMY_IMAGE, NORMAL_ENEMY_HP);
    }

    @Override
    public void shoot() {
        if (!isDead()) {
            Main.getMainGamePane().getChildren().add(new EnemyBullet((int) this.getTranslateX() + 20, (int) this.getTranslateY(),
                    BULLET_WIDTH, BULLET_HEIGHT, BULLET_IMAGE, ENEMY_BULLET_DAMAGE));
        }
    }

    public void move() {

        movePivot(this, this.getTranslateX(), this.getTranslateY());

        RotateTransition rt = new RotateTransition(Duration.seconds(6),this);
        rt.setToAngle(360);
        rt.setCycleCount(Timeline.INDEFINITE);
        rt.setAutoReverse(true);
        rt.play();

    }
    private void movePivot(NormalEnemy normalEnemy, double x, double y){
        this.getTransforms().add(new Translate(-x,-y));
        this.setTranslateX(x); this.setTranslateY(y);
    }
}
