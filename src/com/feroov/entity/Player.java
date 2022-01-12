package com.feroov.entity;

import com.feroov.main.GamePanel;
import com.feroov.main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity
{
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    int hasKey = 0;
    int hasStrongKey = 0;

    public Player(GamePanel gp, KeyHandler keyH)
    {
        this.gp = gp;
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
        speed = 4;
        direction = "left";
    }

    public void getPlayerImage()
    {
        try
        {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_up_2.png")));
            upIdle = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_up_idle.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_down_2.png")));
            downIdle = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_down_idle.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_left_2.png")));
            leftIdle = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_left_idle.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_right_2.png")));
            rightIdle = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_right_idle.png")));
        }catch(IOException e) { e.printStackTrace(); }
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

            // Object collision check
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            if(collisionOn == false)
            {
                switch(direction)
                {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
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
                    System.out.println("Key: " + hasKey);
                    break;

                case "Door":
                    if(hasKey > 0)
                    {
                        gp.playSE(3);
                        gp.obj[i] = null;
                        hasKey--;
                    }
                    System.out.println("Key: " + hasKey);
                    break;

                case "StrongKey":
                    gp.playSE(1);
                    hasStrongKey++;
                    gp.obj[i] = null;
                    System.out.println("Key: " + hasStrongKey);
                    break;

                case "StrongDoor":
                    if(hasStrongKey > 0)
                    {
                        gp.playSE(3);
                        gp.obj[i] = null;
                        hasStrongKey--;
                    }
                    System.out.println("Key: " + hasStrongKey);
                    break;

                case "Speed Potion":
                    gp.playSE(2);
                    speed += 1;
                    gp.obj[i] = null;
                    break;
            }
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
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
