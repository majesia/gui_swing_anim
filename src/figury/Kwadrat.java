package figury;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Kwadrat extends Figura {
    public Kwadrat(Graphics2D buffer, int delay, int width, int height) {
        super(buffer,delay,width,height);
        Random random=new Random();
        shape=new Rectangle2D.Double(0,0,random.nextInt(50),random.nextInt(50));
        aft=new AffineTransform();
        area=new Area(shape);
    }
}
