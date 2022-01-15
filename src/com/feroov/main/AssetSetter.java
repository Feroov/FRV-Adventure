package com.feroov.main;

import com.feroov.entity.NPC_BlondeGirl;
import com.feroov.monster.Goblin;
import com.feroov.monster.Skeleton;
import com.feroov.monster.SkeletonBoss;
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

        gp.obj[10] = new PowerUpSpeed(gp);
        gp.obj[10].worldX = 44 * gp.tileSize;
        gp.obj[10].worldY = 14 * gp.tileSize;

        gp.obj[11] = new PowerUpSpeed(gp);
        gp.obj[11].worldX = 86 * gp.tileSize;
        gp.obj[11].worldY = 13 * gp.tileSize;

        gp.obj[12] = new Health(gp);
        gp.obj[12].worldX = 76 * gp.tileSize;
        gp.obj[12].worldY = 13 * gp.tileSize;

        gp.obj[13] = new Health(gp);
        gp.obj[13].worldX = 56 * gp.tileSize;
        gp.obj[13].worldY = 22 * gp.tileSize;

        gp.obj[14] = new Health(gp);
        gp.obj[14].worldX = 43 * gp.tileSize;
        gp.obj[14].worldY = 23 * gp.tileSize;

        gp.obj[15] = new Health(gp);
        gp.obj[15].worldX = 24 * gp.tileSize;
        gp.obj[15].worldY = 21 * gp.tileSize;

    }

    public void setNPC()
    {
        gp.npc[0] = new NPC_BlondeGirl(gp);
        gp.npc[0].worldX = gp.tileSize * 45;
        gp.npc[0].worldY = gp.tileSize * 52;
    }

    public void setMonster()
    {
        gp.monster[0] = new Skeleton(gp);
        gp.monster[0].worldX = gp.tileSize * 19;
        gp.monster[0].worldY = gp.tileSize * 23;

        gp.monster[1] = new Skeleton(gp);
        gp.monster[1].worldX = gp.tileSize * 20;
        gp.monster[1].worldY = gp.tileSize * 23;

        gp.monster[2] = new Skeleton(gp);
        gp.monster[2].worldX = gp.tileSize * 21;
        gp.monster[2].worldY = gp.tileSize * 23;

        gp.monster[3] = new Skeleton(gp);
        gp.monster[3].worldX = gp.tileSize * 22;
        gp.monster[3].worldY = gp.tileSize * 23;

        gp.monster[4] = new Skeleton(gp);
        gp.monster[4].worldX = gp.tileSize * 23;
        gp.monster[4].worldY = gp.tileSize * 23;

        gp.monster[5] = new Skeleton(gp);
        gp.monster[5].worldX = gp.tileSize * 24;
        gp.monster[5].worldY = gp.tileSize * 23;

        gp.monster[6] = new Skeleton(gp);
        gp.monster[6].worldX = gp.tileSize * 38;
        gp.monster[6].worldY = gp.tileSize * 73;

        gp.monster[7] = new Skeleton(gp);
        gp.monster[7].worldX = gp.tileSize * 38;
        gp.monster[7].worldY = gp.tileSize * 73;

        gp.monster[8] = new Skeleton(gp);
        gp.monster[8].worldX = gp.tileSize * 39;
        gp.monster[8].worldY = gp.tileSize * 73;

        gp.monster[9] = new Skeleton(gp);
        gp.monster[9].worldX = gp.tileSize * 40;
        gp.monster[9].worldY = gp.tileSize * 73;

        gp.monster[10] = new SkeletonBoss(gp);
        gp.monster[10].worldX = gp.tileSize * 41;
        gp.monster[10].worldY = gp.tileSize * 73;

        gp.monster[11] = new Goblin(gp);
        gp.monster[11].worldX = gp.tileSize * 50;
        gp.monster[11].worldY = gp.tileSize * 73;

        gp.monster[12] = new Goblin(gp);
        gp.monster[12].worldX = gp.tileSize * 50;
        gp.monster[12].worldY = gp.tileSize * 74;

        gp.monster[13] = new Goblin(gp);
        gp.monster[13].worldX = gp.tileSize * 51;
        gp.monster[13].worldY = gp.tileSize * 75;

        gp.monster[14] = new Goblin(gp);
        gp.monster[14].worldX = gp.tileSize * 48;
        gp.monster[14].worldY = gp.tileSize * 75;

        gp.monster[15] = new Goblin(gp);
        gp.monster[15].worldX = gp.tileSize * 48;
        gp.monster[15].worldY = gp.tileSize * 74;

        gp.monster[16] = new Skeleton(gp);
        gp.monster[16].worldX = gp.tileSize * 59;
        gp.monster[16].worldY = gp.tileSize * 60;

        gp.monster[17] = new Skeleton(gp);
        gp.monster[17].worldX = gp.tileSize * 60;
        gp.monster[17].worldY = gp.tileSize * 60;

        gp.monster[18] = new Skeleton(gp);
        gp.monster[18].worldX = gp.tileSize * 61;
        gp.monster[18].worldY = gp.tileSize * 60;

        gp.monster[19] = new Skeleton(gp);
        gp.monster[19].worldX = gp.tileSize * 66;
        gp.monster[19].worldY = gp.tileSize * 55;

        gp.monster[20] = new Skeleton(gp);
        gp.monster[20].worldX = gp.tileSize * 69;
        gp.monster[20].worldY = gp.tileSize * 55;

        gp.monster[21] = new Skeleton(gp);
        gp.monster[21].worldX = gp.tileSize * 72;
        gp.monster[21].worldY = gp.tileSize * 55;

        gp.monster[22] = new Skeleton(gp);
        gp.monster[22].worldX = gp.tileSize * 66;
        gp.monster[22].worldY = gp.tileSize * 62;

        gp.monster[23] = new Skeleton(gp);
        gp.monster[23].worldX = gp.tileSize * 69;
        gp.monster[23].worldY = gp.tileSize * 62;

        gp.monster[24] = new Skeleton(gp);
        gp.monster[24].worldX = gp.tileSize * 46;
        gp.monster[24].worldY = gp.tileSize * 62;

        gp.monster[25] = new Skeleton(gp);
        gp.monster[25].worldX = gp.tileSize * 45;
        gp.monster[25].worldY = gp.tileSize * 62;

        gp.monster[26] = new Skeleton(gp);
        gp.monster[26].worldX = gp.tileSize * 43;
        gp.monster[26].worldY = gp.tileSize * 60;

        gp.monster[27] = new Skeleton(gp);
        gp.monster[27].worldX = gp.tileSize * 43;
        gp.monster[27].worldY = gp.tileSize * 61;

        gp.monster[28] = new Skeleton(gp);
        gp.monster[28].worldX = gp.tileSize * 43;
        gp.monster[28].worldY = gp.tileSize * 62;

        gp.monster[29] = new SkeletonBoss(gp);
        gp.monster[29].worldX = gp.tileSize * 74;
        gp.monster[29].worldY = gp.tileSize * 13;

        gp.monster[30] = new SkeletonBoss(gp);
        gp.monster[30].worldX = gp.tileSize * 45;
        gp.monster[30].worldY = gp.tileSize * 8;

        gp.monster[31] = new SkeletonBoss(gp);
        gp.monster[31].worldX = gp.tileSize * 45;
        gp.monster[31].worldY = gp.tileSize * 17;

        gp.monster[32] = new Skeleton(gp);
        gp.monster[32].worldX = gp.tileSize * 46;
        gp.monster[32].worldY = gp.tileSize * 8;

        gp.monster[33] = new Skeleton(gp);
        gp.monster[33].worldX = gp.tileSize * 47;
        gp.monster[33].worldY = gp.tileSize * 8;

        gp.monster[34] = new Skeleton(gp);
        gp.monster[34].worldX = gp.tileSize * 48;
        gp.monster[34].worldY = gp.tileSize * 8;

        gp.monster[35] = new Skeleton(gp);
        gp.monster[35].worldX = gp.tileSize * 49;
        gp.monster[35].worldY = gp.tileSize * 8;

        gp.monster[36] = new Goblin(gp);
        gp.monster[36].worldX = gp.tileSize * 46;
        gp.monster[36].worldY = gp.tileSize * 17;

        gp.monster[37] = new Goblin(gp);
        gp.monster[37].worldX = gp.tileSize * 52;
        gp.monster[37].worldY = gp.tileSize * 17;

        gp.monster[38] = new Goblin(gp);
        gp.monster[38].worldX = gp.tileSize * 54;
        gp.monster[38].worldY = gp.tileSize * 17;

        gp.monster[39] = new Goblin(gp);
        gp.monster[39].worldX = gp.tileSize * 48;
        gp.monster[39].worldY = gp.tileSize * 17;

        gp.monster[40] = new Goblin(gp);
        gp.monster[40].worldX = gp.tileSize * 54;
        gp.monster[40].worldY = gp.tileSize * 15;

        gp.monster[41] = new Skeleton(gp);
        gp.monster[41].worldX = gp.tileSize * 85;
        gp.monster[41].worldY = gp.tileSize * 8;

        gp.monster[42] = new Skeleton(gp);
        gp.monster[42].worldX = gp.tileSize * 87;
        gp.monster[42].worldY = gp.tileSize * 8;

        gp.monster[43] = new Skeleton(gp);
        gp.monster[43].worldX = gp.tileSize * 85;
        gp.monster[43].worldY = gp.tileSize * 7;

        gp.monster[44] = new Skeleton(gp);
        gp.monster[44].worldX = gp.tileSize * 86;
        gp.monster[44].worldY = gp.tileSize * 8;

        gp.monster[45] = new Skeleton(gp);
        gp.monster[45].worldX = gp.tileSize * 74;
        gp.monster[45].worldY = gp.tileSize * 17;

        gp.monster[46] = new Skeleton(gp);
        gp.monster[46].worldX = gp.tileSize * 76;
        gp.monster[46].worldY = gp.tileSize * 17;

        gp.monster[47] = new Skeleton(gp);
        gp.monster[47].worldX = gp.tileSize * 79;
        gp.monster[47].worldY = gp.tileSize * 17;

        gp.monster[48] = new Skeleton(gp);
        gp.monster[48].worldX = gp.tileSize * 59;
        gp.monster[48].worldY = gp.tileSize * 35;

        gp.monster[49] = new Skeleton(gp);
        gp.monster[49].worldX = gp.tileSize * 60;
        gp.monster[49].worldY = gp.tileSize * 35;

        gp.monster[50] = new Skeleton(gp);
        gp.monster[50].worldX = gp.tileSize * 61;
        gp.monster[50].worldY = gp.tileSize * 35;

        gp.monster[51] = new Goblin(gp);
        gp.monster[51].worldX = gp.tileSize * 62;
        gp.monster[51].worldY = gp.tileSize * 35;

        gp.monster[52] = new Goblin(gp);
        gp.monster[52].worldX = gp.tileSize * 62;
        gp.monster[52].worldY = gp.tileSize * 36;

        gp.monster[53] = new Goblin(gp);
        gp.monster[53].worldX = gp.tileSize * 12;
        gp.monster[53].worldY = gp.tileSize * 23;

        gp.monster[54] = new Goblin(gp);
        gp.monster[54].worldX = gp.tileSize * 13;
        gp.monster[54].worldY = gp.tileSize * 23;

        gp.monster[55] = new Goblin(gp);
        gp.monster[55].worldX = gp.tileSize * 14;
        gp.monster[55].worldY = gp.tileSize * 23;
    }
}
