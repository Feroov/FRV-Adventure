package com.feroov.main;

import com.feroov.entity.Entity;
import com.feroov.entity.Player;
import com.feroov.object.SuperObject;
import com.feroov.tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable
{
    // Screen Settings
    final int originalTileSize = 32; // Tile 32x32
    final int scale = 2;

    public final int tileSize = originalTileSize * scale; // 64 x 64 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 1,024 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 768 pixels

    // World settings
    public final int maxWorldCol = 100;
    public final int maxWorldRow = 100;


    // FPS
    int FPS = 60;

    // System stuff
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;

    // Entity and Objects
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[20];
    public Entity npc[] = new Entity[10];
    public Entity monster[] = new Entity[20];
    ArrayList<Entity> entityList = new ArrayList<>();

    // Game state
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;


    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame()
    {
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();


        gameState = titleState;
    }

    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run()
    {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null)
        {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >=1) { update(); repaint(); delta--; drawCount++; }
            if(timer >= 1000000000) { System.out.println("FPS: " + drawCount); drawCount = 0; timer = 0; }
        }
    }

    public void update()
    {
        if(gameState == playState) {
            // Player
            player.update();

            for(int i = 0; i < npc.length; i++)
            {
                if(npc[i] != null)
                {
                    npc[i].update();
                }
            }

            for(int i = 0; i < monster.length; i++)
            {
                if(monster[i] != null)
                {
                    if(monster[i].alive == true && monster[i].dying == false)
                    {
                        monster[i].update();
                    }

                    if(monster[i].alive == false)
                    {
                        monster[i] = null;
                    }
                }
            }
        }
        if(gameState == pauseState)
        {

        }
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Title Screen
        if(gameState == titleState)
        {
            ui.draw(g2);
        }
        //others
        else
        {
            // Tile
            tileM.draw(g2);

            entityList.add(player);

            for(int i = 0; i < npc.length; i++)
            {
                if(npc[i] != null)
                {
                    entityList.add(npc[i]);
                }
            }

            for(int i = 0; i < monster.length; i++)
            {
                if(monster[i] != null)
                {
                    entityList.add(monster[i]);
                }
            }

            for(int i = 0; i < obj.length; i++)
            {
                if(obj[i] != null)
                {
                    obj[i].draw(g2, this);
                }
            }

            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity o1, Entity o2) {
                    int result = Integer.compare(o1.worldY, o2.worldY);
                    return result;
                }
            });
            /**
            // Object

            // Npcs / Entities
            for(int i = 0; i < npc.length; i++)
            {
                if(npc[i] != null)
                {
                    npc[i].draw(g2);
                }
            }

            for(int i = 0; i < monster.length; i++)
            {
                if(monster[i] != null)
                {
                    monster[i].draw(g2);
                }
            }
             **/
            // Player
            player.draw(g2);

            for(int i = 0; i < entityList.size(); i++)
            {
                entityList.get(i).draw(g2);
            }

            entityList.clear();
            // UI
            ui.draw(g2);
        }
        g2.dispose();
    }

    public void playMusic(int i)
    {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic()
    {
        music.stop();
    }

    public void playSE(int i)
    {
        se.setFile(i);
        se.play();
    }
}
