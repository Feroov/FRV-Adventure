package com.feroov.entity;

import com.feroov.main.GamePanel;
import com.feroov.main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Entity
{
    GamePanel gp;
    public int worldX, worldY;
    public int speed;
    public BufferedImage up1, up2, upIdle, down1, down2, downIdle, left1, left2, leftIdle, right1, right2, rightIdle;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    public String direction;
    public int spriteCounter = 0;
    public int standCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 54, 54);
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public String name;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    public boolean invincible = false;
    public int invincibleCounter = 0;
    String dialogues[] = new String[20];
    int dialogueIndex = 0;
    public int type;

    // Character Status
    public int maxLife;
    public int life;

    boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    int dyingCounter = 0;

    boolean hpBarOn = false;
    int hpBarCounter = 0;

    public Entity(GamePanel gp)
    {
        this.gp = gp;
    }

    public void setAction() {}
    public void speak()
    {
        if(dialogues[dialogueIndex] == null)
        {
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch (gp.player.direction)
        {
            case "up" -> direction = "down";
            case "down" -> direction = "up";
            case "left" -> direction = "right";
            case "right" -> direction = "left";
        }
    }
    public void update()
    {
        setAction();
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if(this.type == 2 && contactPlayer == true)
        {
            if(gp.player.invincible == false)
            {
                gp.playSE(6);
                gp.player.life -= 1;
                gp.player.invincible = true;
            }
        }

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

        if(invincible == true)
        {
            invincibleCounter++;
            if(invincibleCounter > 40)
            {
                invincible = false;
                invincibleCounter = 0;
            }
        }

    }

    public void draw(Graphics2D g2)
    {
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize> gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize< gp.player.worldY + gp.player.screenY)
        {
            switch (direction)
            {
                case "up" ->
                {
                    if (spriteNum == 1) {image = up1;}
                    if (spriteNum == 2) {image = up2;}
                    if (spriteNum == 3) {image = upIdle;}
                }
                case "down" ->
                {
                    if (spriteNum == 1) {image = down1;}
                    if (spriteNum == 2) {image = down2;}
                    if (spriteNum == 3) {image = downIdle;}
                }
                case "left" ->
                {
                    if (spriteNum == 1) {image = left1;}
                    if (spriteNum == 2) {image = left2;}
                    if (spriteNum == 3) {image = leftIdle;}
                }
                case "right" ->
                {
                    if (spriteNum == 1) {image = right1;}
                    if (spriteNum == 2) {image = right2;}
                    if (spriteNum == 3) {image = rightIdle;}
                }
            }

            // HP bar
            if(type == 2 && hpBarOn == true)
            {
                double oneScale = (double)gp.tileSize / maxLife;
                double hpBarValue = oneScale * life;

                g2.setColor(new Color(35,35,35));
                g2.fillRect(screenX - 1, screenY - 6, gp.tileSize + 2, 12);

                g2.setColor(new Color(255,0,30));
                g2.fillRect(screenX, screenY - 5, (int)hpBarValue, 10);

                hpBarCounter++;

                if(hpBarCounter > 300)
                {
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }


            if(invincible == true)
            {
                hpBarOn = true;
                hpBarCounter = 0;
                changeAlpha(g2, 0.4f);
            }
            if(dying == true)
            {
                dyingAnimation(g2);
            }
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            changeAlpha(g2, 1.0f);
        }
    }

    public void dyingAnimation(Graphics2D g2)
    {
        dyingCounter++;

        int i = 5;

        if(dyingCounter <= i) { changeAlpha(g2, 0f); }
        if(dyingCounter > i && dyingCounter <= i * 2) { changeAlpha(g2, 1f);}
        if(dyingCounter > i * 2 && dyingCounter <= i * 3) { changeAlpha(g2, 0f);}
        if(dyingCounter > i * 3 && dyingCounter <= i * 4) { changeAlpha(g2, 1f);}
        if(dyingCounter > i * 4 && dyingCounter <= i * 5) { changeAlpha(g2, 0f);}
        if(dyingCounter > i * 5 && dyingCounter <= i * 6) { changeAlpha(g2, 1f);}
        if(dyingCounter > i * 6 && dyingCounter <= i * 7) { changeAlpha(g2, 0f);}
        if(dyingCounter > i * 7 && dyingCounter <= i * 8) { changeAlpha(g2, 1f);}
        if(dyingCounter > i * 8 && dyingCounter <= i * 9) { changeAlpha(g2, 1f);}
        if(dyingCounter > i * 9)
        {
            dying = false;
            alive = false;
        }
    }

    public void changeAlpha(Graphics2D g2, float alphaValue)
    {
        g2.setComposite((AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue)));
    }

    public BufferedImage setup(String imagePath, int width, int height)
    {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try
        {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            image = uTool.scaleImage(image, width, height);
        }catch(IOException e){ e.printStackTrace(); }

        return image;
    }
}
