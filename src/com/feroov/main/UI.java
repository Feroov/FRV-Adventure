package com.feroov.main;

import com.feroov.object.Heart;
import com.feroov.object.Key;
import com.feroov.object.StrongKey;
import com.feroov.object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class UI
{
    GamePanel gp;
    Graphics2D g2;
    Font eightBit, arial_40, arial_80;
    BufferedImage keyImage;
    BufferedImage keyStrongImage;
    BufferedImage heart_full, heart_half, heart_empty;
    public boolean messageOn = false;
    public String message = "";
    public int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp)
    {
        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80 = new Font("Arial", Font.PLAIN, 80);
        InputStream is = getClass().getResourceAsStream("/font/8bit.ttf");
        try
        {
            eightBit = Font.createFont(Font.TRUETYPE_FONT, is);
        }
        catch(FontFormatException | IOException e){ e.printStackTrace(); }


        Key key = new Key(gp);
        StrongKey keyStrong = new StrongKey(gp);
        keyImage = key.image;
        keyStrongImage = keyStrong.image;

        // Hud Objects
        SuperObject heart = new Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_empty = heart.image3;
    }

    public void showMessage(String text)
    {
        message = text;
        messageOn = true;
    }
    private void drawTitleScreen()
    {
        if(titleScreenState == 0)
        {
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            String text = "FRV Adventure";
            int x = getXForCenteredText(text);
            int y = gp.tileSize * 3;

            g2.setColor(Color.gray);
            g2.drawString(text, x+5, y+5);

            g2.setColor(Color.WHITE);
            g2.drawString(text, x, y);

            x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
            g2.drawImage(gp.player.down1, x, 250, gp.tileSize * 2, gp.tileSize * 2, null);

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));

            text = "START GAME";
            x = getXForCenteredText(text);
            g2.drawString(text, x, 480);
            if(commandNum == 0)
            {
                g2.drawString(">", 345, 480);
            }
            text = "HOW TO PLAY";
            x = getXForCenteredText(text);
            g2.drawString(text, x, 540);
            if(commandNum == 1)
            {
                g2.drawString(">", 330, 540);
            }

            text = "QUIT";
            x = getXForCenteredText(text);
            g2.drawString(text, x, 600);
            if(commandNum == 2)
            {
                g2.drawString(">", 420, 600);
            }
        }
        else if(titleScreenState == 1)
        {
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(32F));


            String text = "Move around with W, A, S, D";
            int x = getXForCenteredText(text);
            int y = gp.tileSize * 3;
            g2.drawString(text, x, y);

            String text2 = " \"P\" to pause the game, \"Enter\" to Attack/Interact ";
            int x2 = getXForCenteredText(text2);
            int y2 = gp.tileSize * 4;
            g2.drawString(text2, x2, y2);

            String text3 = "The game is pretty simple, find all the keys and the treasure!";
            int x3 = getXForCenteredText(text3);
            int y3 = gp.tileSize * 5;
            g2.drawString(text3, x3, y3);

            String text5 = "To talk to NPC's walk towards them and press enter!";
            int x5 = getXForCenteredText(text3);
            int y5 = gp.tileSize * 6;
            g2.drawString(text5, 150, y5);

            g2.setColor(Color.yellow);
            String text4 = "Press \"Down\" + \"Enter\" to go back";
            int x4 = getXForCenteredText(text4);
            int y4 = gp.tileSize * 9;
            g2.drawString(text4, x4, y4);

            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));
            text = "Back";
            x = getXForCenteredText(text);
            y += gp.tileSize * 7;
            g2.drawString(text, x, y);
            if(commandNum == 0)
            {
                g2.drawString(">", x-gp.tileSize, y);
            }
        }

    }

    public void draw(Graphics2D g2)
    {
        // Pause stuff
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);
        if(gp.gameState == gp.pauseState)
        {
            drawPauseScreen();
            drawPlayerLife();
        }

        if(gp.gameState == gp.playState)
        {
            // Timer
            playTime += (double) 1 / 60;
            g2.drawString("Time: " + dFormat.format(playTime), 410, 48);
            drawPlayerLife();
        }

        // Title State
        if(gp.gameState == gp.titleState){ drawTitleScreen(); }

        // Finishing game
        if(gameFinished)
        {
            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);

            String text;
            int textLength;
            int x;
            int y;

            text = "You found the treasure!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 - (gp.tileSize * 3);
            g2.drawString(text, x, y);

            text = "Time it took: " + dFormat.format(playTime) + " seconds!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 + (gp.tileSize * 4);
            g2.drawString(text, x, y);

            g2.setFont(arial_80);
            g2.setColor(Color.YELLOW);
            text = "Congratulations!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 + (gp.tileSize * 2);
            g2.drawString(text, x, y);

            gp.gameThread = null;
        }
        if(gp.gameState == gp.playState)
        {
            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);
            g2.drawImage(keyImage, gp.tileSize / 3, gp.tileSize / 3, gp.tileSize, gp.tileSize, null);
            g2.drawImage(keyStrongImage, 21, 100, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.hasKey, 97, 70);
            g2.drawString("x " + gp.player.hasStrongKey, 97, 155);



            g2.setFont(eightBit);
            g2.setColor(Color.YELLOW);
            // Messages / notifications
            if(messageOn)
            {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, 21, 300);

                messageCounter++;

                if(messageCounter > 120)
                {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
        g2.setFont(eightBit);
        if(gp.gameState == gp.dialogueState)
        {
            drawDialogueScreen();
        }
    }

    private void drawPlayerLife()
    {
        int x = gp.tileSize * 11;
        int y = gp.tileSize / 3;
        int i = 0;

        while(i < gp.player.maxLife / 2)
        {
            g2.drawImage(heart_empty, x, y, null);
            i++;
            x += gp.tileSize;
        }

        // Reset
        x = gp.tileSize * 11;
        y = gp.tileSize / 3;
        i = 0;

        // Current Life
        while(i < gp.player.life)
        {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if(i < gp.player.life)
            {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }

    }


    private void drawDialogueScreen()
    {
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));


        for(String line : currentDialogue.split("\n"))
        {
            g2.drawString(currentDialogue, 200, 70);
            y += 40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height)
    {
        Color c = new Color(0,0,0, 200);
        g2.setColor(c);
        g2.fillRoundRect(180, 20, width, height, 35, 35);

        c = new Color(255,255,255);
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(180, 20, width, height, 47,47);
    }

    public void drawPauseScreen()
    {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    public int getXForCenteredText(String text)
    {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }
}
