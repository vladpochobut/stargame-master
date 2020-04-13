package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

import static sample.ControllerUtils.*;

public class Main extends Application {

    private MainGamePane root = new MainGamePane();
    private double t = 0;
    private double bonus = 0;

    private Player player = new Player(PLAYER_INIT_POS_X, 750, PLAYER_WIDTH, PLAYER_HEIGHT);

    private Parent createContent() {
        root.setPrefSize(SCENE_WIDTH, SCENE_HEIGHT);
        root.getChildren().addAll(player);
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update();
            }
        };
        animationTimer.start();
        firstLevel();
        return root;
    }

    private void secondLevel() {
        for (int i = 0; i < NUMBER_OF_NORMAL_ENEMIES; i++) {
            Sprite normalEnemy = new NormalEnemy(60 + i * 50, 200, ENEMY_WIDTH, ENEMY_HEIGHT);
            root.getChildren().add(normalEnemy);
            bonus++;
        }
        for (Sprite enemyN : sprites()) {
            if (enemyN instanceof NormalEnemy) {
                ((NormalEnemy) enemyN).move();
            }
        }
    }

    private void firstLevel() {
        for (int i = 0; i < NUMBER_OF_SIMPLE_ENEMIES; i++) {
            Sprite simpleEnemy = new SimpleEnemy(60 + i * 50, 100, ENEMY_WIDTH, ENEMY_HEIGHT);
            root.getChildren().add(simpleEnemy);
        }


        for (Sprite enemy : sprites()) {
            if (enemy instanceof SimpleEnemy) {
                ((SimpleEnemy) enemy).move();
            }
        }

    }

    private List<Sprite> sprites() {
        return root.getChildren().stream().filter(n -> n instanceof Sprite).map(n -> (Sprite) n).collect(Collectors.toList());
    }


    private void update() {
        t += 0.016;
        if(bonus == NUMBER_OF_SIMPLE_ENEMIES){secondLevel();}
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
                        bonus++;
                    }
                });
            }

            if (s instanceof PlayerBullet) {
                s.moveUp();
                sprites().stream().filter(e -> e instanceof NormalEnemy).forEach(enemy -> {
                    if (s.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
                        enemy.setDead(true);
                        s.setDead(true);
                        bonus++;
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
            if (s instanceof NormalEnemy) {

                if (t > 2) {
                    if (Math.random() < 0.5) {
                        Sprite enemyBullet = ((NormalEnemy) s).shoot();
                        root.getChildren().add(enemyBullet);
                    }
                }

            }
        });
        root.getChildren().removeIf(n -> {
            if (n instanceof Sprite) {
                Sprite s = (Sprite) n;
                return s.isDead();
            }
            return false;
        });
        if (t > 2) {
            t = 0;
        }
        root.setScore(bonus);
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