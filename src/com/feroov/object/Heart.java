package com.feroov.object;

import com.feroov.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Heart extends SuperObject
{
    GamePanel gp;

    public Heart(GamePanel gp)
    {
        this.gp = gp;
        name = "Heart";
        try
        {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heart.png")));
            image2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heart_half.png")));
            image3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heart_empty.png")));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            image2 = uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
            image3 = uTool.scaleImage(image3, gp.tileSize, gp.tileSize);
        }catch(IOException e) { e.printStackTrace(); }
    }
}
