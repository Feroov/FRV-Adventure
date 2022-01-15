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

        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }

    public void setDefaultValues()
    {
        worldX = gp.tileSize * 43;
        worldY = gp.tileSize * 51;
        speed = 4;
        direction = "down";

        // Player Status
        maxLife = 12;
        life = maxLife;
    }

    public void getPlayerImage()
    {
        up1 = setup("/player/player_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/player/player_up_2", gp.tileSize, gp.tileSize);
        upIdle = setup("/player/player_up_idle", gp.tileSize, gp.tileSize);
        down1 = setup("/player/player_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/player/player_down_2", gp.tileSize, gp.tileSize);
        downIdle = setup("/player/player_down_idle", gp.tileSize, gp.tileSize);
        left1 = setup("/player/player_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/player/player_left_2", gp.tileSize, gp.tileSize);
        leftIdle = setup("/player/player_left_idle", gp.tileSize, gp.tileSize);
        right1 = setup("/player/player_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/player/player_right_2", gp.tileSize, gp.tileSize);
        rightIdle = setup("/player/player_right_idle", gp.tileSize, gp.tileSize);
    }

    public void getPlayerAttackImage()
    {
        attackUp1 = setup("/player/player_attack_up_1", gp.tileSize, gp.tileSize * 2);
        attackUp2 = setup("/player/player_attack_up_2", gp.tileSize, gp.tileSize * 2);
        attackDown1 = setup("/player/player_attack_down_1", gp.tileSize, gp.tileSize * 2);
        attackDown2 = setup("/player/player_attack_down_2", gp.tileSize, gp.tileSize * 2);
        attackLeft1 = setup("/player/player_attack_left_1", gp.tileSize * 2, gp.tileSize);
        attackLeft2 = setup("/player/player_attack_left_2", gp.tileSize * 2, gp.tileSize);
        attackRight1 = setup("/player/player_attack_right_1", gp.tileSize * 2, gp.tileSize);
        attackRight2 = setup("/player/player_attack_right_2", gp.tileSize * 2, gp.tileSize);
    }

    public void update()
    {
        if(attacking == true)
        {
            attacking();
        }
        else if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed == true)
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

            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            // Object collision check
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            if(!collisionOn && keyH.enterPressed == false)
            {
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }

            gp.keyH.enterPressed = false;
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

        if(life == 0)
        {
            System.exit(0);
        }

        if(invincible == true)
        {
            invincibleCounter++;
            if(invincibleCounter > 60)
            {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void attacking()
    {
        spriteCounter++;
        if(spriteCounter <= 5)
        {
            spriteNum = 1;
        }
        if(spriteCounter > 5 && spriteCounter <= 25)
        {
            spriteNum = 2;

            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            switch (direction)
            {
                case "up" -> worldY -= attackArea.height;
                case "down" -> worldY += attackArea.height;
                case "left" -> worldX -= attackArea.width;
                case "right" -> worldX += attackArea.height;
            }

            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);

            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if(spriteCounter > 25)
        {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;

        }
    }

    private void contactMonster(int i)
    {
        if(i != 999)
        {
            if(invincible == false)
            {
                gp.playSE(6);
                life -= 1;
                invincible = true;
            }

        }
    }

    public void damageMonster(int i)
    {
        if(i != 999)
        {
            if(gp.monster[i].invincible == false)
            {
                gp.playSE(5);
                gp.monster[i].life -= 1;
                gp.monster[i].invincible = true;

                if(gp.monster[i].life <= 0)
                {
                    gp.monster[i].dying = true;
                }
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

                case "health":
                    gp.playSE(2);
                    life += 2;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Drank Health Potion!");
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
        if(gp.keyH.enterPressed == true)
        {
            if(i != 999)
            {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
            else
            {
                gp.playSE(7);
                attacking = true;
            }
        }
        gp.keyH.enterPressed = false;
    }

    public void draw(Graphics2D g2)
    {
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {
            case "up" ->
            {
                if(!attacking)
                {
                    if (spriteNum == 1) {image = up1;}
                    if (spriteNum == 2) {image = up2;}
                    if (spriteNum == 3) {image = upIdle;}
                }

                if(attacking)
                {
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNum == 1) {image = attackUp1;}
                    if (spriteNum == 2) {image = attackUp2;}
                }
            }
            case "down" ->
            {
                if(!attacking)
                {
                    if (spriteNum == 1) {image = down1;}
                    if (spriteNum == 2) {image = down2;}
                    if (spriteNum == 3) {image = downIdle;}
                }
                if(attacking)
                {
                    if (spriteNum == 1) {image = attackDown1;}
                    if (spriteNum == 2) {image = attackDown2;}
                }
            }
            case "left" ->
            {
                if(!attacking)
                {
                    if (spriteNum == 1) {image = left1;}
                    if (spriteNum == 2) {image = left2;}
                    if (spriteNum == 3) {image = leftIdle;}
                }
                if(attacking)
                {
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNum == 1) {image = attackLeft1;}
                    if (spriteNum == 2) {image = attackLeft2;}
                }
            }
            case "right" ->
            {
                if(!attacking)
                {
                    if (spriteNum == 1) {image = right1;}
                    if (spriteNum == 2) {image = right2;}
                    if (spriteNum == 3) {image = rightIdle;}
                }
                if(attacking)
                {
                    if (spriteNum == 1) {image = attackRight1;}
                    if (spriteNum == 2) {image = attackRight2;}
                }
            }
        }

        if(invincible == true)
        {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage(image, tempScreenX, tempScreenY, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
