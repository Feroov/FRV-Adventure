package com.feroov.entity;

import com.feroov.main.GamePanel;
import com.feroov.main.KeyHandler;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Player extends Entity
{

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    public int hasStrongKey = 0;

    public Player(GamePanel gp, KeyHandler keyH)
    {
        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 27;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues()
    {
        worldX = gp.tileSize * 43;
        worldY = gp.tileSize * 51;
        speed = 24;
        direction = "left";
    }

    public void getPlayerImage()
    {
        up1 = setup("/player/player_up_1");
        up2 = setup("/player/player_up_2");
        upIdle = setup("/player/player_up_idle");
        down1 = setup("/player/player_down_1");
        down2 = setup("/player/player_down_2");
        downIdle = setup("/player/player_down_idle");
        left1 = setup("/player/player_left_1");
        left2 = setup("/player/player_left_2");
        leftIdle = setup("/player/player_left_idle");
        right1 = setup("/player/player_right_1");
        right2 = setup("/player/player_right_2");
        rightIdle = setup("/player/player_right_idle");
    }


    public void update()
    {
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed)
        {
            if(keyH.upPressed)
            {
                direction = "up";
            }

            else if(keyH.downPressed)
            {
                direction = "down";
            }

            else if(keyH.leftPressed)
            {
                direction = "left";
            }

            else if(keyH.rightPressed)
            {
                direction = "right";
            }

            // Tile collision check
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // Entity collision checker
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            // Object collision check
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            if(!collisionOn)
            {
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }

            spriteCounter++;
            if(spriteCounter <= 12) {
                spriteNum = 1;
            }
            if(spriteCounter > 12 && spriteCounter <= 24) {
                spriteNum = 2;
            }
            if(spriteCounter > 24) {
                spriteCounter = 0;
            }
        }
        else {
            standCounter++;
            if(standCounter == 24) {
                spriteNum = 3;
                standCounter = 0;
            }
        }
    }

    public void pickUpObject(int i)
    {
        if(i != 999)
        {
            String objectName = gp.obj[i].name;

            switch(objectName)
            {
                case "Key":
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Picked up a key!");
                    break;

                case "Door":
                    if(hasKey > 0)
                    {
                        gp.playSE(3);
                        gp.obj[i] = null;
                        hasKey--;
                        gp.ui.showMessage("Door Unlocked!");
                    }
                    else
                    {
                        gp.ui.showMessage("The door is locked!");
                    }
                    break;

                case "StrongKey":
                    gp.playSE(1);
                    hasStrongKey++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Picked up a special key!");
                    break;

                case "StrongDoor":
                    if(hasStrongKey > 0)
                    {
                        gp.playSE(3);
                        gp.obj[i] = null;
                        hasStrongKey--;
                        gp.ui.showMessage("Door Unlocked!");
                    }
                    else
                    {
                        gp.ui.showMessage("The door is locked!");
                    }
                    break;

                case "Speed Potion":
                    gp.playSE(2);
                    speed += 1;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Drank Speed Potion!");
                    break;

                case "Treasure":
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSE(4);
                    break;
            }
        }
    }

    public void interactNPC(int i)
    {
        if(i != 999)
        {

        }
    }

    public void draw(Graphics2D g2)
    {
        BufferedImage image = null;

        switch(direction)
        {
            case "up":
                if(spriteNum == 1)
                {
                    image = up1;
                }
                if(spriteNum == 2)
                {
                    image = up2;
                }
                if(spriteNum == 3)
                {
                    image = upIdle;
                }
                break;

            case "down":
                if(spriteNum == 1)
                {
                    image = down1;
                }
                if(spriteNum == 2)
                {
                    image = down2;
                }
                if(spriteNum == 3)
                {
                    image = downIdle;
                }
                break;

            case "left":
                if(spriteNum == 1)
                {
                    image = left1;
                }
                if(spriteNum == 2)
                {
                    image = left2;
                }
                if(spriteNum == 3)
                {
                    image = leftIdle;
                }
                break;

            case "right":
                if(spriteNum == 1)
                {
                    image = right1;
                }
                if(spriteNum == 2)
                {
                    image = right2;
                }
                if(spriteNum == 3)
                {
                    image = rightIdle;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, null);
    }
}
