package com.feroov.main;

import com.feroov.object.Chest;
import com.feroov.object.Door;
import com.feroov.object.Key;
import com.feroov.object.PowerUpSpeed;

public class AssetSetter
{
    GamePanel gp;

    public AssetSetter(GamePanel gp)
    {
        this.gp = gp;
    }

    public void setObject()
    {
        gp.obj[0] = new Key();
        gp.obj[0].worldX = 21 * gp.tileSize;
        gp.obj[0].worldY = 23 * gp.tileSize;

        gp.obj[1] = new Key();
        gp.obj[1].worldX = 45 * gp.tileSize;
        gp.obj[1].worldY = 8 * gp.tileSize;

        gp.obj[2] = new Key();
        gp.obj[2].worldX = 42 * gp.tileSize;
        gp.obj[2].worldY = 22 * gp.tileSize;

        gp.obj[3] = new Key();
        gp.obj[3].worldX = 74 * gp.tileSize;
        gp.obj[3].worldY = 12 * gp.tileSize;

        gp.obj[4] = new Door();
        gp.obj[4].worldX = 21 * gp.tileSize;
        gp.obj[4].worldY = 25 * gp.tileSize;

        gp.obj[5] = new Door();
        gp.obj[5].worldX = 21 * gp.tileSize;
        gp.obj[5].worldY = 26 * gp.tileSize;

        gp.obj[6] = new Door();
        gp.obj[6].worldX = 21 * gp.tileSize;
        gp.obj[6].worldY = 27 * gp.tileSize;

        gp.obj[7] = new Door();
        gp.obj[7].worldX = 40 * gp.tileSize;
        gp.obj[7].worldY = 75 * gp.tileSize;

        gp.obj[8] = new Chest();
        gp.obj[8].worldX = 40 * gp.tileSize;
        gp.obj[8].worldY = 72 * gp.tileSize;

        gp.obj[9] = new PowerUpSpeed();
        gp.obj[9].worldX = 11 * gp.tileSize;
        gp.obj[9].worldY = 34 * gp.tileSize;

    }
}
