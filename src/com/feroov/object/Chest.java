package com.feroov.object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Chest extends SuperObject
{
    public Chest()
    {
        name = "Treasure";
        try
        {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/treasure.png")));
        }catch(IOException e) { e.printStackTrace(); }
        collision = true;
    }
}
