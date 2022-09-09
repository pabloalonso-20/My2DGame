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
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();

        loadMap("./src/main/resources/maps/worldMap1.txt");

    }

    public void getTileImage(){

        try{

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new File("./src/main/resources/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new File("./src/main/resources/tiles/wall.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new File("./src/main/resources/tiles/water.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(new File("./src/main/resources/tiles/earth.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(new File("./src/main/resources/tiles/tree.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(new File("./src/main/resources/tiles/sand.png"));




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

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){

                String line = br.readLine();//read a single line of text onto line

                while(col < gp.maxWorldCol){
                    String[] numbers = line.split(" "); //split the string after a space

                    int num = Integer.parseInt(numbers[col]);//change string to integer

                    mapTileNum[col][row] = num;

                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();

        }catch(Exception e){

        }
    }

    public void draw(Graphics2D g2){

        int worldCol = 0;
        int worldRow = 0;


        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize< gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize> gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize< gp.player.worldY + gp.player.screenY)
            {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            worldCol++;


            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;

            }
        }

    }


}
