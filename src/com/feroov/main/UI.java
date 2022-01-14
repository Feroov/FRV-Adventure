package com.feroov.main;

import com.feroov.object.Key;
import com.feroov.object.StrongKey;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI
{
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80;
    BufferedImage keyImage;
    BufferedImage keyStrongImage;
    public boolean messageOn = false;
    public String message = "";
    public int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp)
    {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80 = new Font("Arial", Font.BOLD, 80);

        Key key = new Key(gp);
        StrongKey keyStrong = new StrongKey(gp);
        keyImage = key.image;
        keyStrongImage = keyStrong.image;
    }

    public void showMessage(String text)
    {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2)
    {
        // Pause stuff
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);

        if(gp.gameState == gp.playState)
        {
            // Timer
            playTime += (double) 1 / 60;
            g2.drawString("Time: " + dFormat.format(playTime), 410, 48);
        }
        if(gp.gameState == gp.pauseState)
        {
            drawPauseScreen();
        }

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
        else
        {
            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);
            g2.drawImage(keyImage, gp.tileSize / 3, gp.tileSize / 3, gp.tileSize, gp.tileSize, null);
            g2.drawImage(keyStrongImage, 21, 100, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.hasKey, 97, 70);
            g2.drawString("x " + gp.player.hasStrongKey, 97, 155);



            g2.setFont(arial_40);
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

        if(gp.gameState == gp.dialogueState){ drawDialogueScreen(); }
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
