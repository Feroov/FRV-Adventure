package com.feroov.object;

import com.feroov.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class StrongKey extends SuperObject
{
    GamePanel gp;

    public StrongKey(GamePanel gp)
    {
        this.gp = gp;

        name = "StrongKey";
        try
        {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/strong_key.png")));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e) { e.printStackTrace(); }
    }
}
