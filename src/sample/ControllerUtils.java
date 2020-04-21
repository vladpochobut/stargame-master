package sample;

import javafx.scene.image.Image;

public interface ControllerUtils {

    int SCENE_WIDTH = 650;
    int SCENE_HEIGHT = 800;


    String ENEMY_IMG_PATH = "file:///F:\\IntelliJ IDEA 2019.3.3\\stargame-master\\src\\sample\\pictures\\1.png";
    int ENEMY_WIDTH = 30;
    int ENEMY_HEIGHT = 30;
    int NUMBER_OF_SIMPLE_ENEMIES = 7;

    String NORMAL_ENEMY_IMG_PATH = "file:///F:\\IntelliJ IDEA 2019.3.3\\stargame-master\\src\\sample\\pictures\\4.png";
    int NUMBER_OF_NORMAL_ENEMIES = 7;

    String PLAYER_IMG_PATH = "file:///F:\\IntelliJ IDEA 2019.3.3\\stargame-master\\src\\sample\\pictures\\2.png";
    int PLAYER_WIDTH = 40;
    int PLAYER_HEIGHT = 40;

    String BULLET_IMG_PATH = "file:///F:\\IntelliJ IDEA 2019.3.3\\stargame-master\\src\\sample\\pictures\\3.png";
    String DOUBLE_BULLET_IMG_PATH = "file:///F:\\IntelliJ IDEA 2019.3.3\\stargame-master\\src\\sample\\pictures\\3v1.png";
    Image DOUBLE_BULLET_IMAGE = new Image(DOUBLE_BULLET_IMG_PATH);
    Image BULLET_IMAGE = new Image(BULLET_IMG_PATH);

    int BULLET_WIDTH = 6;
    int DOUBLE_BULLET_WIDTH = 24;
    int BULLET_HEIGHT = 12;

    int PLAYER_INIT_POS_X = 300;
    int KILL_BONUS = 0;

    String MENU_IMG_PATH = "file:///F:\\IntelliJ IDEA 2019.3.3\\stargame-master\\src\\sample\\pictures\\5.jpg";


    String COIN_IMG_PATH = "file:///F:\\IntelliJ IDEA 2019.3.3\\stargame-master\\src\\sample\\pictures\\coin.png";
    Image COIN_IMG = new Image(COIN_IMG_PATH);
    int COIN_WIDTH = 12;
    int COIN_HEIGHT = 12;

    String LIFE_IMG_PATH = "file:///F:\\IntelliJ IDEA 2019.3.3\\stargame-master\\src\\sample\\pictures\\life.png";
    int LIFE_WIDTH = 56;
    int LIFE_HEIGHT =15;
}
