package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

import static sample.ControllerUtils.*;

public class MainGamePane extends Pane {
    private final Player player;
    private Text score;
    private double t = 0;
    private double k = 0;
    private double bonus = 0;
    private int bonus_apd = 0;
    private Stage primaryStage;
    private int hits = 0;
    private int kolOfDead = 0;
    private int kolOfAllife = 0;
    private PlayerLife life;

    public MainGamePane(Stage primaryStage) {
        super();
        this.primaryStage = primaryStage;
        this.player = new Player(PLAYER_INIT_POS_X, 750, PLAYER_WIDTH, PLAYER_HEIGHT);
        this.life = new PlayerLife(580,780, LIFE_WIDTH,LIFE_HEIGHT);
        this.getChildren().add(life);
        this.score = new Text();
        this.getChildren().add(score);
        score.setX(0);
        score.setY(780);
        score.setText("Score : " + KILL_BONUS);
        this.setPrefSize(SCENE_WIDTH, SCENE_HEIGHT);
        this.getChildren().addAll(player);
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update();
                simpleBonus();
            }
        };
        animationTimer.start();
        firstLevel();
        Scene scene = new Scene(this);
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
                    this.getChildren().addAll(bullet);
                    if (bonus_apd == 1) {
                        Sprite doubleBullet = player.shoot1();
                        this.getChildren().add(doubleBullet);
                        this.getChildren().remove(bullet);
                    }

                    break;
            }

        });
    }

    private void update() {
        t += 0.016;
        if (bonus == NUMBER_OF_SIMPLE_ENEMIES) {
            secondLevel();
        }
        sprites().forEach(s -> {
            if (s instanceof EnemyBullet) {
                s.moveDown();
                if (s.getBoundsInParent().intersects(player.getBoundsInParent())) {
                    hits++;
                    player.setDead(true);
                    s.setDead(true);

                }
            }
            if (s instanceof BonusCoin) {
                s.moveDown();
                if (s.getBoundsInParent().intersects(player.getBoundsInParent())) {
                    s.setDead(true);
                    bonus_apd++;
                }
            }


            if (s instanceof PlayerBullet) {
                s.moveUp();
                sprites().stream().filter(e -> e instanceof SimpleEnemy).forEach(enemy -> {
                    if (s.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
                        enemy.setDead(true);
                        kolOfDead++;
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
                        kolOfDead++;
                        s.setDead(true);
                        bonus++;
                    }
                });
            }

            if (s instanceof SimpleEnemy) {

                if (t > 2) {
                    if (Math.random() < 0.8) {
                        Sprite enemyBullet = ((SimpleEnemy) s).shoot();
                        this.getChildren().add(enemyBullet);
                    }
                }

            }
            if (s instanceof NormalEnemy) {

                if (t > 2) {
                    if (Math.random() < 0.8) {
                        Sprite enemyBullet = ((NormalEnemy) s).shoot();
                        this.getChildren().add(enemyBullet);
                    }
                }

            }

        });
        this.getChildren().removeIf(n -> {
            if (n instanceof Sprite) {
                Sprite s = (Sprite) n;
                return s.isDead();
            }
            return false;
        });
        if (t > 2) {
            t = 0;
        }
        if (kolOfAllife == kolOfDead) {
            firstLevel();

        }
        this.setLife(hits);
        this.setScore(bonus);
    }


    private void secondLevel() {
        bonus++;
        for (int i = 0; i < NUMBER_OF_NORMAL_ENEMIES; i++, kolOfAllife++) {
            Sprite normalEnemy = new NormalEnemy(90 + i * 60, 200, ENEMY_WIDTH, ENEMY_HEIGHT);
            this.getChildren().add(normalEnemy);
        }
        for (Sprite enemyN : sprites()) {
            if (enemyN instanceof NormalEnemy) {
                ((NormalEnemy) enemyN).move();
            }
        }

    }

    private void firstLevel() {
        for (int i = 0; i < NUMBER_OF_SIMPLE_ENEMIES; i++, kolOfAllife++) {
            Sprite simpleEnemy = new SimpleEnemy(60 + i * 50, 100, ENEMY_WIDTH, ENEMY_HEIGHT);
            this.getChildren().add(simpleEnemy);
        }


        for (Sprite enemy : sprites()) {
            if (enemy instanceof SimpleEnemy) {
                ((SimpleEnemy) enemy).move();
            }
        }

    }

    private void simpleBonus() {
        k += 0.016;
        if (k > 2) {
            if (Math.random() < 0.05) {
                BonusCoin bonusCoin = new BonusCoin(12, 12);
                bonusCoin.move();
                this.getChildren().add(bonusCoin);
            }
        }
        if (k > 2) {
            k = 0;
        }
    }

    private List<Sprite> sprites() {
        return this.getChildren().stream().filter(n -> n instanceof Sprite).map(n -> (Sprite) n).collect(Collectors.toList());
    }

    public void setScore(double scoreValue) {
        score.setText("Score : " + scoreValue);
    }
    public void setLife(int kolOfTakenBullets){
        if(kolOfTakenBullets == 1){life.setW(life.getW() - (kolOfTakenBullets*19));}
    }
}

