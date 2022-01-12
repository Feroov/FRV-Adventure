package com.feroov.tile;

import com.feroov.main.GamePanel;
import com.feroov.main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager
{
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp)
    {
        this.gp = gp;

        tile = new Tile[50];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/world01.txt");
    }

    public void getTileImage()
    {
            //_________________ Generic Stuff__________________________
            setup(0, "grass", false);
            setup(1, "wall", true);
            setup(2, "water", true);
            setup(3, "tree", true);
            setup(4, "dirt", false);
            setup(5, "sand", false);
            //_________________________________________________________

            //____________________ Big Tree ___________________________
            setup(6, "big_tree_nw", true);
            setup(7, "big_tree_ne", true);
            setup(8, "big_tree_sw", true);
            setup(9, "big_tree_se", true);
            //_________________________________________________________


            //____________________ Road _______________________________
            setup(10, "dirt_patch", false);
            //_________________________________________________________

            //__________________ Grass Edges ________________________________
            setup(11, "grass_edge_down", false);
            setup(12, "grass_edge_left", false);
            setup(13, "grass_edge_ne", false);
            setup(14, "grass_edge_nw", false);
            setup(15, "grass_edge_right", false);
            setup(16, "grass_edge_se", false);
            setup(17, "grass_edge_sw", false);
            setup(18, "grass_edge_up", false);
            setup(19, "grass_wall", true);
            setup(20, "grass_wall_left", true);
            setup(21, "grass_wall_right", true);
            setup(22, "grass_wall_down", true);
            //_______________________________________________________________

            //___________________ Grass Accesories___________________________
            setup(23, "grass_flower", false);
            setup(24, "grass_flower2", false);
            setup(25, "grass_rock", false);
            setup(26, "grass_trunk", true);
            //_______________________________________________________________

            //________________________ Sign _________________________________
            setup(27, "sign_up", true);
            setup(28, "sign_down", true);
            //_______________________________________________________________


            //_________________ More walls / treasure _______________________
            setup(29, "door", true);
            setup(30, "wall_window", true);
            setup(31, "cobblestone", false);
            setup(32, "chest", false);
            setup(33, "wall_up", true);
            //_____________________________________________________________
    }

    public void setup(int index, String imageName, boolean collision)
    {
        UtilityTool uTool = new UtilityTool();

        try
        {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" +
                    imageName + ".png")));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        }catch(IOException e) { e.printStackTrace(); }
    }

    public void loadMap(String filePath)
    {
        try
        {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while(col < gp.maxWorldCol && row < gp.maxWorldRow)
            {
                String line = br.readLine();

                while(col < gp.maxWorldCol)
                {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol)
                {
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch(Exception e) { e.printStackTrace(); }
    }


    public void draw(Graphics2D g2)
    {
        int worldCol = 0;
        int worldRow = 0;


        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow)
        {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize> gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize< gp.player.worldY + gp.player.screenY)
            {
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }


            worldCol++;


            if(worldCol == gp.maxWorldCol)
            {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}


