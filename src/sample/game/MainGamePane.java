package sample.game;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;


import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;

import javax.swing.*;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

import static sample.game.ControllerUtils.*;
import static sample.game.Main.getPrimaryStage;

public class MainGamePane extends Pane implements Serializable {

    public int kolOfDead = 0;
    private Player player;
    private transient Text score;
    private Life life;
    private double t = 0;
    private double k = 0;
    private double bonus = 0;
    private int bonus_apd = 0;
    private int kolOfAlive = 0;
    private Polyline polyline;
    private transient Line DHline;
    private transient Line UHline;
    private transient Line LVline;
    private transient Line RVline;
    private NormalEnemy normalEnemy;
    private SimpleEnemy simpleEnemy;
    private SimpleEnemy[] enemies;
    int firstLevelPointer = 0;
    private AnimationTimer animationTimer;
    int animationPointer = 0;
    private JPanel myRootPane;
    private int deathFlag = 0;
    private String playerName;

    public MainGamePane() {
        super();
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
                    player.shoot();
                    if (bonus_apd == 1) {
                        player.shoot1();
                    }

                    break;
            }

        });
        scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                if (t.getCode() == KeyCode.ESCAPE) {
                    getPrimaryStage().setScene(Main.getGameMenu().getScene());
                }
            }
        });
    }

    private void createNewelements() {
        this.life = new Life(580, 780, LIFE_WIDTH, LIFE_HEIGHT);
        this.player = new Player(PLAYER_INIT_POS_X, PLAYER_INIT_POS_Y, PLAYER_WIDTH, PLAYER_HEIGHT, PLAYER_HP, life);
        this.getChildren().addAll(player);
        this.getChildren().add(life);
        this.score = new Text();
        this.getChildren().add(score);

        myRootPane = new JPanel();

        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update();
                simpleBonus();
                animationPointer = 1;
            }
        };
        //TODO:pattern builder. try to do 2 scenes by pattern builder
        animationTimer.start();
        this.DHline = new Line(0, 800, 650, 800);
        this.LVline = new Line(0, 0, 0, 800);
        this.UHline = new Line(0, 0, 650, 0);
        this.RVline = new Line(650, 0, 650, 800);
        this.getChildren().addAll(DHline, LVline, RVline, UHline);

    }

    public void WriteFile(SimpleEnemy[] enemies) {
        try {
            FileOutputStream fos = new FileOutputStream("save.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (SimpleEnemy enemy : enemies) {
                oos.writeObject(enemy.getTranslateX());
                oos.writeObject(enemy.getTranslateY());
                oos.writeObject(enemy.isDead());
                oos.writeObject(enemy.getHp());
            }
            oos.writeObject(player.getTranslateX());
            oos.writeObject(player.getTranslateY());
            oos.writeObject(player.isDead());
            oos.writeObject(player.getHp());
            oos.writeObject(bonus);
            //  System.out.println(bonus);


            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void WriteFileNames() {
        try {
            FileOutputStream fos = new FileOutputStream("names.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(playerName);
            oos.writeObject(bonus);
            List<Score> scores = Main.getGameMenu().getScores();
            for (Score score : scores) {
                oos.writeObject(score.getName());
                oos.writeObject(score.getScore());
            }

            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void ReadFile(SimpleEnemy[] enemies) {
        try {
            FileInputStream fis = new FileInputStream("save.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);

            for (int i = 0; i < NUMBER_OF_SIMPLE_ENEMIES; i++) {
                Double currentX = (Double) ois.readObject();
                enemies[i].setTranslateX(currentX);
                Double currentY = (Double) ois.readObject();
                enemies[i].setTranslateY(currentY);
                Boolean isDead = (Boolean) ois.readObject();
                enemies[i].setDead(isDead);
                Integer currentHP = (Integer) ois.readObject();
                enemies[i].setHp(currentHP);
            }
            Double playerCoordX = (Double) ois.readObject();
            System.out.println(playerCoordX);
            player.setTranslateX(playerCoordX);
            Double playerCoordY = (Double) ois.readObject();
            System.out.println(playerCoordY);
            player.setTranslateY(playerCoordY);
            Boolean pisDead = (Boolean) ois.readObject();
            player.setDead(pisDead);
            Integer currentHP = (Integer) ois.readObject();
            player.setHp(currentHP);
            Double PlayerBonus = (Double) ois.readObject();
            // System.out.println(PlayerBonus);
            setScore(PlayerBonus);

            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void removeElements(int i) {
        if (firstLevelPointer == 1) {
            for (SimpleEnemy enemy : enemies) {
                enemy.setDead(true);
                this.getChildren().remove(enemy);
            }
        }
        if (animationPointer == 1) {
            animationTimer.stop();
        }
        bonus_apd = 0;
        if (i == 0) {
            bonus = 0;
        }
        this.getChildren().removeAll(score, player, life);
        sprites().clear();
    }

    public void setElements() {
        this.setPrefSize(SCENE_WIDTH, SCENE_HEIGHT);
        score.setX(0);
        score.setY(780);
        firstLevel();
    }


    public void start(int i) {
        createNewelements();
        setElements();
        if (i == 1) {
            ReadFile(enemies);
        }
        deathFlag = 0;

    }


    private String getName() {
        return playerName;
    }


    private void update() {
        WriteFile(enemies);

        if (player.isDead()) {
            if (deathFlag == 0) {
                playerName = JOptionPane.showInputDialog(myRootPane, "Your score is : " + bonus, "Enter you name", JOptionPane.DEFAULT_OPTION);
                deathFlag = 1;
                WriteFileNames();
            }
            player.setDead(false);
            getPrimaryStage().setScene(Main.getGameMenu().getScene());
        }

        t += 0.016;
        //TODO: пределать под fx timer
        if (bonus == NUMBER_OF_SIMPLE_ENEMIES) {
            secondLevel();
        }
        bulletCleaning();
        sprites().forEach(s -> {
            if (s instanceof EnemyBullet) {
                s.moveDown();
                if (s.getBoundsInParent().intersects(player.getBoundsInParent())) {
                    player.takeDamage(((EnemyBullet) s).getDamage());
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
                        ((SimpleEnemy) enemy).takeDamage(((PlayerBullet) s).getDamage());
                        if (enemy.isDead()) {
                            bonus++;
                            kolOfDead++;
                        }
                        s.setDead(true);
                    }
                });
            }

            if (s instanceof PlayerBullet) {
                s.moveUp();
                sprites().stream().filter(e -> e instanceof NormalEnemy).forEach(enemy -> {
                    if (s.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
                        ((NormalEnemy) enemy).takeDamage(((PlayerBullet) s).getDamage());
                        if (enemy.isDead()) {
                            kolOfDead++;
                            bonus++;
                        }
                        s.setDead(true);

                    }
                });
            }


            if (s instanceof NormalEnemy) {

                if (t > 2) {
                    if (Math.random() < 0.8) {
                        ((NormalEnemy) s).shoot();
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

        this.setScore(bonus);
    }


    private void secondLevel() {
        bonus++;
        for (int i = 0; i < NUMBER_OF_NORMAL_ENEMIES; i++, kolOfAlive++) {
            this.normalEnemy = new NormalEnemy(90 + i * 60, 200, ENEMY_WIDTH, ENEMY_HEIGHT);
            this.getChildren().add(normalEnemy);
        }
        for (Sprite enemyN : sprites()) {
            if (enemyN instanceof NormalEnemy) {
                ((NormalEnemy) enemyN).move();
            }
        }

    }

    private void firstLevel() {
        firstLevelPointer = 1;
        this.enemies = new SimpleEnemy[NUMBER_OF_SIMPLE_ENEMIES];
        for (int i = 0; i < NUMBER_OF_SIMPLE_ENEMIES; i++, kolOfAlive++) {
            enemies[i] = new SimpleEnemy(60 + i * 50, 100, ENEMY_WIDTH, ENEMY_HEIGHT);
        }
        this.getChildren().addAll(enemies);

        for (Sprite enemy : sprites()) {
            if (enemy instanceof SimpleEnemy) {
                ((SimpleEnemy) enemy).move(1);
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

    public int getBonus() {
        return (int) bonus;
    }

    private void bulletCleaning() {
        sprites().forEach(s -> {
            if (s instanceof EnemyBullet) {
                s.moveDown();
                if (s.getBoundsInParent().intersects(DHline.getBoundsInParent())) {
                    s.setDead(true);
                }
            }
            if (s instanceof PlayerBullet) {
                s.moveUp();
                if (s.getBoundsInParent().intersects(UHline.getBoundsInParent())) {
                    s.setDead(true);
                }
            }


        });

    }
}

