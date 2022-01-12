package com.feroov.object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class PowerUpSpeed extends SuperObject
{
    public PowerUpSpeed()
    {
        name = "Speed Potion";
        try
        {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/speed.png")));
        }catch(IOException e) { e.printStackTrace(); }
    }
}
