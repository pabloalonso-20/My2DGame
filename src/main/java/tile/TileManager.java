package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

public class TileManager {

    GamePanel gp;
    Tile[] tile;

    int[][] mapTileNum;

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10];//10 types of tiles
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();

        loadMap("./src/main/resources/maps/map1.txt");

    }

    public void getTileImage(){

        try{

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new File("./src/main/resources/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new File("./src/main/resources/tiles/wall.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new File("./src/main/resources/tiles/water.png"));


        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath){
        try{

            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String contentLine = br.readLine();

            int col = 0;
            int row = 0;

            while(col <gp.maxScreenCol && row< gp.maxScreenRow){

                String line = br.readLine();//read a single line of text onto line

                while(col < gp.maxScreenCol){
                    String numbers[] = line.split(" "); //split the string after a space

                    int num = Integer.parseInt(numbers[col]);//change string to integer

                    mapTileNum[col][row] = num;

                    col++;
                }
                if(col == gp.maxScreenCol){
                    col = 0;
                    row++;
                }
            }
            br.close();

        }catch(Exception e){

        }
    }

    public void draw(Graphics2D g2){

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gp.maxScreenCol && row < gp.maxScreenRow){

            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].image, x ,y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if(col == gp.maxScreenCol){
                col = 0;
                x =0;
                row++;
                y+= gp.tileSize;
            }
        }

    }


}
