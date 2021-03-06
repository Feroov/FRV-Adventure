package com.feroov.entity;

import com.feroov.main.GamePanel;

import java.util.Random;


public class NPC_BlondeGirl extends Entity
{

    public NPC_BlondeGirl(GamePanel gp)
    {
        super(gp);
        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
    }

    public void getImage()
    {
        up1 = setup("/npc/blonde_girl_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/blonde_girl_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/blonde_girl_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/blonde_girl_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/blonde_girl_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/blonde_girl_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/blonde_girl_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/blonde_girl_right_2", gp.tileSize, gp.tileSize);
    }

    public void setDialogue()
    {
        dialogues[0] = "Hello there!";
        dialogues[1] = "There are evil monsters all over the island!";
        dialogues[2] = "Legend says that there is a treasure out there!";
        dialogues[3] = "Dont get lost in the woods!";
        dialogues[4] = "There are speed potions hidden around!";
    }

    public void speak()
    {
        super.speak();
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
