package main;

import entity.Player;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 pixels is standard size for characters in retro 2d games
    final int scale = 3; //scaling the character since modern computers have hiRes

    public int tileSize = originalTileSize * scale; //48 x 48 actual tile size
    public int maxScreenCol = 16;
    public int maxScreenRow = 12;
    public int screenWidth = tileSize * maxScreenCol; // 48*16 = 760 pixels
    public int screenHeight = tileSize * maxScreenRow; // 48*12 = 576 pixels

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow =50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    //FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(this,keyH);

    //constructor
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); //enabling increasing rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this); //this=GamePanelClass
        gameThread.start(); //automaticall calls run
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS; //0.01666 seconds
        double nextDrawtime = System.nanoTime() + drawInterval;

        while(gameThread != null){

            //1 Update: update information such as character position
            update();
            //2 Draw: draw the screen with the updated information
            repaint();
            try {
                double remainingTime = nextDrawtime - System.nanoTime();
                remainingTime = remainingTime/1000000; //convert it to milliseconds

                if(remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawtime += drawInterval;
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);

        player.draw(g2);

        g2.dispose(); //when drawing is done, dispose of this graphics//good practice to save memory
    }
}
