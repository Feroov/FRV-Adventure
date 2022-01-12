package com.feroov.object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class StrongDoor extends SuperObject
{
    public StrongDoor()
    {
        name = "StrongDoor";
        try
        {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/strong_door.png")));
        }catch(IOException e) { e.printStackTrace(); }
        collision = true;
    }
}
