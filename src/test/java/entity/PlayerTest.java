package entity;

import main.GamePanel;
import main.KeyHandler;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    GamePanel gp = new GamePanel();
    KeyHandler kh = new KeyHandler();
    Player player = new Player(gp, kh);


    @Test
    public void setDefaultValueSpeed() {
        int expectedSpeed = 4;
        int actualSpeed = player.speed;
        assertEquals(expectedSpeed, actualSpeed);
    }

}