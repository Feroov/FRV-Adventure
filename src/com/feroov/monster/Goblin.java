package com.feroov.monster;

import com.feroov.entity.Entity;
import com.feroov.main.GamePanel;

import java.util.Random;

public class Goblin extends Entity
{
    GamePanel gp;
    public Goblin(GamePanel gp)
    {
        super(gp);
        this.gp = gp;
        type = 2;
        name = "Goblin";
        direction = "down";
        speed = 3;
        maxLife = 5;
        life = maxLife;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage()
    {
        up1 = setup("/monster/goblin_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/goblin_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/monster/goblin_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/goblin_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/monster/goblin_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/goblin_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/monster/goblin_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/goblin_right_2", gp.tileSize, gp.tileSize);
    }

    public void setAction()
    {
        actionLockCounter++;

        if(actionLockCounter == 120)
        {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if(i <= 25){
                direction = "up";
            }
            if(i > 25 && i <= 50){
                direction = "down";
            }
            if(i > 50 && i <= 75){
                direction = "left";
            }
            if(i > 75 && i <= 100){
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }
}
