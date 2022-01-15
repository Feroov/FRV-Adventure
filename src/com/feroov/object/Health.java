package com.feroov.object;

import com.feroov.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Health extends SuperObject
{
    GamePanel gp;
    public Health(GamePanel gp)
    {
        this.gp = gp;
        name = "health";
        try
        {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/health.png")));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e) { e.printStackTrace(); }
    }
}
