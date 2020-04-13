package sample;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static sample.ControllerUtils.KILL_BONUS;

public class MainGamePane extends Pane {
    private Text score;

    private GameMenu root  = new GameMenu();


    public MainGamePane() {
        super();
        this.score = new Text();
        this.getChildren().add(score);
        score.setX(0);
        score.setY(780);
        score.setText("Score : " + KILL_BONUS);

    }


    public void setScore(double scoreValue) {
        score.setText("Score : " + scoreValue);
    }

}
