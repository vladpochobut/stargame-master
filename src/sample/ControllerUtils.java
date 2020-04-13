package sample;

import javafx.scene.image.Image;

public interface ControllerUtils {

    int SCENE_WIDTH = 650;
    int SCENE_HEIGHT = 800;


    String ENEMY_IMG_PATH = "file:///F:\\IntelliJ IDEA 2019.3.3\\stargame-master\\src\\sample\\1.png";
    int ENEMY_WIDTH = 30;
    int ENEMY_HEIGHT = 30;
    int NUMBER_OF_SIMPLE_ENEMIES = 1;

    String NORMAL_ENEMY_IMG_PATH = "file:///F:\\IntelliJ IDEA 2019.3.3\\stargame-master\\src\\sample\\4.png";
    int NUMBER_OF_NORMAL_ENEMIES = 7;

    String PLAYER_IMG_PATH = "file:///F:\\IntelliJ IDEA 2019.3.3\\stargame-master\\src\\sample\\2.png";
    int PLAYER_WIDTH = 40;
    int PLAYER_HEIGHT = 40;

    String BULLET_IMG_PATH = "file:///F:\\IntelliJ IDEA 2019.3.3\\stargame-master\\src\\sample\\3.png";

    Image BULLET_IMAGE = new Image(BULLET_IMG_PATH);
    int BULLET_WIDTH = 7;
    int BULLET_HEIGHT = 12;

    int PLAYER_INIT_POS_X = 300;
    int KILL_BONUS = 0;
}
