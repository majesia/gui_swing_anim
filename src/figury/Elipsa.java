package figury;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Elipsa extends Figura {
    public Elipsa(Graphics2D buffer, int delay, int width, int height) {
        super(buffer,delay,width,height);
        Random random=new Random();
        shape=new Ellipse2D.Double(0,0,random.nextInt(50),random.nextInt(50));
        aft=new AffineTransform();
        area=new Area(shape);

    }
}
