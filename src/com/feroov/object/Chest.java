package com.feroov.object;

import com.feroov.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Chest extends SuperObject
{
    GamePanel gp;

    public Chest(GamePanel gp)
    {
        this.gp = gp;
        name = "Treasure";
        try
        {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/treasure.png")));
        }catch(IOException e) { e.printStackTrace(); }
        collision = true;
    }
}
