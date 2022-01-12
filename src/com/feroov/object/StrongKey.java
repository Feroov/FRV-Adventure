package com.feroov.object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class StrongKey extends SuperObject
{
    public StrongKey()
    {
        name = "StrongKey";
        try
        {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/strong_key.png")));
        }catch(IOException e) { e.printStackTrace(); }
    }
}
