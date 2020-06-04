package sample.game.objects;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.util.Duration;

import static sample.game.objects.ControllerUtils.*;

public class SimpleEnemy extends ActiveObject {

    private static final Image ENEMY_IMAGE = new Image(ENEMY_IMG_PATH);

    public SimpleEnemy(int x, int y, int w, int h) {
        super(x, y, w, h, ENEMY_IMAGE, SIMPLE_ENEMY_HP);
    }

    @Override
    public void shoot() {
        if (!isDead() && Main.getMainGamePane() != null) {
            Main.getMainGamePane().getChildren().add(new EnemyBullet((int) getTranslateX() + 20,
                    (int) getTranslateY(), BULLET_WIDTH, BULLET_HEIGHT, BULLET_IMAGE, ENEMY_BULLET_DAMAGE));
        }
    }

    public void move(int direction) {
        TranslateTransition timeline = new TranslateTransition();
        timeline.setNode(this);
        timeline.setAutoReverse(true);
        timeline.setToX(getTranslateX() + direction * 100);
        timeline.setToY(getTranslateY() + direction * 100);
        timeline.setDuration(Duration.millis(3000));
            timeline.setOnFinished(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    shoot();
                    move(direction * -1);
                }
            });
        timeline.play();
    }
}
