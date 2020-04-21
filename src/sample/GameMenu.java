package sample;

import static sample.ControllerUtils.*;
import javafx.animation.*;
import javafx.application.Application;
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
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameMenu extends Pane {

    private Stage primaryStage;

    public GameMenu(Stage primaryStage) {
        super();
        this.primaryStage = primaryStage;
        Image MENU_IMG = new Image(MENU_IMG_PATH);
        ImageView img = new ImageView(MENU_IMG);
        img.setFitHeight(1000);
        img.setFitWidth(1200);
        this.getChildren().add(img);

        MenuItem newGame = new MenuItem("START!");
        newGame.setOnMouseClicked(event -> this.primaryStage.setScene((new MainGamePane(primaryStage)).getScene()));
        MenuItem options = new MenuItem("НАСТРОЙКИ");
        MenuItem exitGame = new MenuItem("ВЫХОД");
        SubMenu mainMenu = new SubMenu(
                newGame, options, exitGame
        );
        MenuItem sound = new MenuItem("ЗВУК");
        MenuItem gameDifficulty = new MenuItem("СЛОЖНОСТЬ");

        MenuItem video = new MenuItem("ВИДЕО");
    //    MenuItem keys = new MenuItem("УПРАВЛЕНИЕ");
        MenuItem optionsBack = new MenuItem("НАЗАД");
        SubMenu optionsMenu = new SubMenu(
                sound, video/* keys*/,gameDifficulty,optionsBack
        );
//        MenuItem NG1 = new MenuItem("ТУРНИР");

//        MenuItem NG2 = new MenuItem("ОДИН ЗАЕЗД");
//        MenuItem NG3 = new MenuItem("2 ИГРОКА");
//        MenuItem NG4 = new MenuItem("НАЗАД");
//        SubMenu newGameMenu = new SubMenu(
//                NG1, NG2, NG3, NG4
//        );
        MenuBox menuBox = new MenuBox(mainMenu);

   //     newGame.setOnMouseClicked(event -> menuBox.setSubMenu(newGameMenu));
        options.setOnMouseClicked(event -> menuBox.setSubMenu(optionsMenu));
        exitGame.setOnMouseClicked(event -> System.exit(0));
        optionsBack.setOnMouseClicked(event -> menuBox.setSubMenu(mainMenu));
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
    }
}

