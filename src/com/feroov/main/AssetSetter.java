package com.feroov.main;

import com.feroov.entity.NPC_BlondeGirl;
import com.feroov.object.*;

public class AssetSetter
{
    GamePanel gp;

    public AssetSetter(GamePanel gp)
    {
        this.gp = gp;
    }

    public void setObject()
    {
        gp.obj[0] = new StrongKey(gp);
        gp.obj[0].worldX = 21 * gp.tileSize;
        gp.obj[0].worldY = 23 * gp.tileSize;

        gp.obj[1] = new Key(gp);
        gp.obj[1].worldX = 45 * gp.tileSize;
        gp.obj[1].worldY = 8 * gp.tileSize;

        gp.obj[2] = new Key(gp);
        gp.obj[2].worldX = 42 * gp.tileSize;
        gp.obj[2].worldY = 22 * gp.tileSize;

        gp.obj[3] = new Key(gp);
        gp.obj[3].worldX = 74 * gp.tileSize;
        gp.obj[3].worldY = 12 * gp.tileSize;

        gp.obj[4] = new Door(gp);
        gp.obj[4].worldX = 21 * gp.tileSize;
        gp.obj[4].worldY = 25 * gp.tileSize;

        gp.obj[5] = new Door(gp);
        gp.obj[5].worldX = 21 * gp.tileSize;
        gp.obj[5].worldY = 26 * gp.tileSize;

        gp.obj[6] = new Door(gp);
        gp.obj[6].worldX = 21 * gp.tileSize;
        gp.obj[6].worldY = 27 * gp.tileSize;

        gp.obj[7] = new StrongDoor(gp);
        gp.obj[7].worldX = 40 * gp.tileSize;
        gp.obj[7].worldY = 75 * gp.tileSize;

        gp.obj[8] = new Chest(gp);
        gp.obj[8].worldX = 40 * gp.tileSize;
        gp.obj[8].worldY = 72 * gp.tileSize;

        gp.obj[9] = new PowerUpSpeed(gp);
        gp.obj[9].worldX = 11 * gp.tileSize;
        gp.obj[9].worldY = 34 * gp.tileSize;
    }

    public void setNPC()
    {
        gp.npc[0] = new NPC_BlondeGirl(gp);
        gp.npc[0].worldX = gp.tileSize * 45;
        gp.npc[0].worldY = gp.tileSize * 52;
    }
}
