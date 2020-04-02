package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

import static sample.ControllerUtils.PLAYER_INIT_POS_X;

public class Main extends Application {
    private Pane root = new Pane();
    private double t = 0;

    private Player player = new Player(PLAYER_INIT_POS_X, 750, 40, 40);

    private Parent createContent() {
        root.setPrefSize(600, 800);
        root.getChildren().addAll(player);
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update();
            }
        };
        animationTimer.start();
        nextLevel();
        return root;
    }

    private void nextLevel() {
        for (int i = 0; i < 20; i++) {
            Sprite s = new SimpleEnemy(90 + i * 40, 150, 30, 30);

            root.getChildren().add(s);
        }
    }

    private List<Sprite> sprites() {
        return root.getChildren().stream().map(n -> (Sprite) n).collect(Collectors.toList());
    }

    private void chaoticMove(Sprite enemy) {
//        Random random = new Random();
//        int move = random.nextInt(20);
//        if (random.nextInt(5) == 2) {
//            move = move * -1;
//        }
//        enemy.setTranslateX(enemy.getTranslateX() + move);
    }

    private void update() {
        t += 0.016;

        for (Sprite enemy : sprites()) {
            if (enemy instanceof SimpleEnemy) {
                chaoticMove(enemy);
            }
        }

        sprites().forEach(s -> {
            if (s instanceof EnemyBullet) {
                s.moveDown();

                if (s.getBoundsInParent().intersects(player.getBoundsInParent())) {
                    player.setDead(true);
                    s.setDead(true);
                }
            }

            if (s instanceof PlayerBullet) {
                s.moveUp();
                sprites().stream().filter(e -> e instanceof SimpleEnemy).forEach(enemy -> {
                    if (s.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
                        enemy.setDead(true);
                        s.setDead(true);
                    }
                });
            }

            if (s instanceof SimpleEnemy) {

                if (t > 2) {
                    if (Math.random() < 0.3) {
                        Sprite enemyBullet = ((SimpleEnemy) s).shoot();
                        root.getChildren().add(enemyBullet);
                    }
                }

            }
        });
        root.getChildren().removeIf(n -> {
            Sprite s = (Sprite) n;
            return s.isDead();
        });
        if (t > 2) {
            t = 0;
        }
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(createContent());

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case A:
                    player.moveLeft();
                    break;
                case D:
                    player.moveRight();
                    break;
                case SPACE:
                    Sprite bullet = player.shoot();
                    root.getChildren().add(bullet);
                    break;
            }

        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}