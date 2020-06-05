package sample.game.objects;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.game.objects.scene.GameMenu;
import sample.game.objects.scene.MainGamePane;

public class Main extends Application {

    private static Stage primaryStage;
    private static MainGamePane mainGamePane;
    private static GameMenu gameMenu;

    private void setPrimaryStage(Stage stage) {
        Main.primaryStage = stage;
    }

    public void setMainGamePane(MainGamePane mainGamePane) {
        Main.mainGamePane = mainGamePane;
    }

    public void setGameMenu(GameMenu gameMenu) {
        Main.gameMenu = gameMenu;
    }

    public static MainGamePane getMainGamePane() {
        return mainGamePane;
    }

    public static GameMenu getGameMenu() {
        return gameMenu;
    }

    public static Stage getPrimaryStage() {
        return Main.primaryStage;
    }

    @Override
    public void start(Stage primaryStage) {
        setPrimaryStage(primaryStage);
        setGameMenu(new GameMenu());
        setMainGamePane(new MainGamePane());
        primaryStage.setScene(gameMenu.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}