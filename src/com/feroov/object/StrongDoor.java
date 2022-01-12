package com.feroov.object;

import com.feroov.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class StrongDoor extends SuperObject
{
    GamePanel gp;

    public StrongDoor(GamePanel gp)
    {
        this.gp = gp;

        name = "StrongDoor";
        try
        {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/strong_door.png")));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e) { e.printStackTrace(); }
        collision = true;
    }
}
