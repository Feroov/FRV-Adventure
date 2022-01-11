package com.feroov.entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity
{
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, upIdle, down1, down2, downIdle, left1, left2, leftIdle, right1, right2, rightIdle;
    public String direction;

    public int spriteCounter = 0;
    public int standCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea;
    public boolean collisionOn = false;
}
