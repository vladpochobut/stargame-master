package sample.game.objects;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import static sample.game.objects.ControllerUtils.MENU_IMG_PATH;


public class GameMenu extends Pane {

    private List<Score> scores;

    public GameMenu() {
        super();
        Image MENU_IMG = new Image(MENU_IMG_PATH);
        ImageView img = new ImageView(MENU_IMG);
        img.setFitHeight(1000);
        img.setFitWidth(1200);
        this.getChildren().add(img);


        MenuItem newGame = new MenuItem("START!");
        newGame.setOnMouseClicked(event -> {
            Main.getMainGamePane().removeElements(0);
            Main.getMainGamePane().start(0);
            Main.getPrimaryStage().setScene(Main.getMainGamePane().getScene());

        });
        MenuItem options = new MenuItem("НАСТРОЙКИ");
        MenuItem results = new MenuItem("ТАБЛИЦА РЕЗУЛЬТАТОВ");
        MenuItem load = new MenuItem("ЗАГРУЗИТЬ");
        load.setOnMouseClicked(mouseEvent -> {
            Main.getMainGamePane().removeElements(1);
            Main.getMainGamePane().start(1);
            Main.getPrimaryStage().setScene(Main.getMainGamePane().getScene());
        });
        MenuItem exitGame = new MenuItem("ВЫХОД");
        SubMenu mainMenu = new SubMenu(
                newGame, options,results, load, exitGame
        );
        MenuItem sound = new MenuItem("ЗВУК");
        MenuItem gameDifficulty = new MenuItem("СЛОЖНОСТЬ");

        MenuItem video = new MenuItem("ВИДЕО");
        MenuItem optionsBack = new MenuItem("НАЗАД");
        ReadFileNames();
        SubMenu optionsMenu = new SubMenu(
                sound, video, gameDifficulty, optionsBack
        );
        MenuItem playerResalts = new MenuItem("ТАБЛИЦА РЕЗУЛЬАТОВ :");
        MenuItem resultsBack = new MenuItem("НАЗАД");
        SubMenu resultsMenu = new SubMenu(playerResalts,resultsBack);
        for (Score score : scores) {
            resultsMenu.add(new MenuItem("" + score.toString()));
        }




        MenuBox menuBox = new MenuBox(mainMenu);

        //     newGame.setOnMouseClicked(event -> menuBox.setSubMenu(newGameMenu));
        options.setOnMouseClicked(event -> menuBox.setSubMenu(optionsMenu));
        results.setOnMouseClicked(event -> menuBox.setSubMenu(resultsMenu));
        exitGame.setOnMouseClicked(event -> System.exit(0));
        optionsBack.setOnMouseClicked(event -> menuBox.setSubMenu(mainMenu));
        resultsBack.setOnMouseClicked(event -> menuBox.setSubMenu(mainMenu));
        //    NG4.setOnMouseClicked(event -> menuBox.setSubMenu(mainMenu));
        this.getChildren().addAll(menuBox);

        Scene scene = new Scene(this, 1200, 1000);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                FadeTransition ft = new FadeTransition(Duration.seconds(1), menuBox);
                if (!menuBox.isVisible()) {
                    ft.setFromValue(0);
                    ft.setToValue(1);
                    ft.play();
                    menuBox.setVisible(true);
                } else {
                    ft.setFromValue(1);
                    ft.setToValue(0);
                    ft.setOnFinished(evt -> menuBox.setVisible(false));
                    ft.play();

                }
            }
        });
    }


    private void ReadFileNames() {
        this.scores = new ArrayList<>();
        boolean isEOF = false;

        FileInputStream fis = null;
        try {
            fis = new FileInputStream("names.bin");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        while (!isEOF) {
            try {
                String name = (String) ois.readObject();
                Double scoreValue = (Double) ois.readObject();
                Score score = new Score(name, scoreValue);
                scores.add(score);
            } catch (IOException | ClassNotFoundException e) {
                isEOF = true;
            }
        }
    }

    private static class MenuItem extends StackPane {
        public MenuItem(String name) {
            Rectangle bg = new Rectangle(200, 20, Color.WHITE);
            bg.setOpacity(0.7);

            Text text = new Text(name);
            text.setFill(Color.WHITE);
            text.setFont(Font.font("Arial", FontWeight.BOLD, 14));

            setAlignment(Pos.CENTER);
            getChildren().addAll(bg, text);
            FillTransition st = new FillTransition(Duration.seconds(0.5), bg);
            setOnMouseEntered(event -> {
                st.setFromValue(Color.DARKGRAY);
                st.setToValue(Color.DARKGOLDENROD);
                st.setCycleCount(Animation.INDEFINITE);
                st.setAutoReverse(true);
                st.play();
            });
            setOnMouseExited(event -> {
                st.stop();
                bg.setFill(Color.WHITE);
            });
        }
    }

    private static class MenuBox extends Pane {
        static SubMenu subMenu;

        public MenuBox(SubMenu subMenu) {
            MenuBox.subMenu = subMenu;

            setVisible(false);
            Rectangle bg = new Rectangle(1200, 1000, Color.BLACK);
            bg.setOpacity(0.4);
            getChildren().addAll(bg, subMenu);
        }

        public void setSubMenu(SubMenu subMenu) {
            getChildren().remove(MenuBox.subMenu);
            MenuBox.subMenu = subMenu;
            getChildren().add(MenuBox.subMenu);
        }
    }

    private static class SubMenu extends VBox {
        public SubMenu(MenuItem... items) {
            setSpacing(15);
            setTranslateY(100);
            setTranslateX(50);
            for (MenuItem item : items) {
                getChildren().addAll(item);
            }
        }

        public void add(MenuItem menuItem) {
            getChildren().addAll(menuItem);
        }
    }

    public List<Score> getScores() {
        return scores;
    }
}

