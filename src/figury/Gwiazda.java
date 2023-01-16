package figury;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

public class Gwiazda extends Figura{
    public Gwiazda(Graphics2D buf, int del, int w, int h) {
        super(buf, del, w, h);
        int xPoints[] = { 55, 67, 109, 73, 83, 55, 27, 37, 1, 43 };
        int yPoints[] = { 0, 36, 36, 54, 96, 72, 96, 54, 36, 36 };
        int nPoints = 10;
        shape = new Polygon(xPoints, yPoints, nPoints);
        aft=new AffineTransform();
        area=new Area(shape);
    }
}
