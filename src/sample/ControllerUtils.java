package sample;

import javafx.scene.image.Image;

public interface ControllerUtils {

    String ENEMY_IMG_PATH = "file:///F:\\IntelliJ IDEA 2019.3.3\\stargame-master\\src\\sample\\1.png";
    String PLAYER_IMG_PATH = "file:///F:\\IntelliJ IDEA 2019.3.3\\stargame-master\\src\\sample\\2.png";
    String BULLET_IMG_PATH = "file:///F:\\IntelliJ IDEA 2019.3.3\\stargame-master\\src\\sample\\3.png";

    Image BULLET_IMAGE = new Image(BULLET_IMG_PATH);

    int PLAYER_INIT_POS_X = 300;
}
