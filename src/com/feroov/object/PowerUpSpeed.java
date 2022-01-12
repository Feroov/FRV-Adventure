package com.feroov.object;

import com.feroov.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class PowerUpSpeed extends SuperObject
{
    GamePanel gp;
    public PowerUpSpeed(GamePanel gp)
    {
        this.gp = gp;
        name = "Speed Potion";
        try
        {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/speed.png")));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e) { e.printStackTrace(); }
    }
}
