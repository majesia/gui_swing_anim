/**
 * 
 */
package figury;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static figury.AnimPanel.newFigures;

/**
 * @author tb
 *
 */
public abstract class Figura implements Runnable, ActionListener/*, Shape*/ {

	// wspolny bufor
	protected Graphics2D buffer;
	protected Area area;
	// do wykreslania
	protected Shape shape;
	// przeksztalcenie obiektu
	protected AffineTransform aft;

	// przesuniecie
	private int dx, dy;
	// rozciaganie
	private double sf;
	// kat obrotu
	private double an;
	private int delay;
	private int width;
	private int height;
	private Color clr;
	boolean touchX=false, touchY=false;
	int numberOfTouch=0, number;
	//public HashMap<Integer,Integer> newFigures = new HashMap<>();

	protected static final Random rand = new Random();

	public Figura(Graphics2D buf, int del, int w, int h) {
		delay = del;
		buffer = buf;
		width = w;
		height = h;

		dx = 1 + rand.nextInt(5);
		dy = 1 + rand.nextInt(5);
		sf = 1 + 0.05 * rand.nextDouble();
		an = 0.1 * rand.nextDouble();

		clr = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
		// reszta musi być zawarta w realizacji klasy Figure
		// (tworzenie figury i przygotowanie transformacji)

	}

	@Override
	public void run() {
		// przesuniecie na srodek
		aft.translate(100, 100);
		area.transform(aft);
		shape = area;

		while (true) {
			// przygotowanie nastepnego kadru
			shape = nextFrame();
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
			}
		}
	}

	protected Shape nextFrame() {
		// zapamietanie na zmiennej tymczasowej
		// aby nie przeszkadzalo w wykreslaniu
		area = new Area(area);
		aft = new AffineTransform();
		Rectangle bounds = area.getBounds();
		int cx = bounds.x + bounds.width / 2; //x srodka ksztaltu
		int cy = bounds.y + bounds.height / 2;//y srodka ksztaltu

		//double cx= area.getBounds().getFrame().getX();
		//int cx = bounds.x + bounds.width / 2; //x srodka ksztaltu
		//double cy = area.getBounds().getFrame().getY();//y srodka ksztaltu
		if(!AnimPanel.isPaused()){
			number++;
			numberOfTouch++;
			// odbicie
			if (cx + bounds.width/2<= bounds.width)  {
				dx = Math.abs(dx);
				touchX=true;
				//aft.translate(cx, cy);
			}
			if(cx- bounds.width/2>= width-bounds.width){
				dx = -Math.abs(dx);
				touchX=true;
				//aft.translate(cx, cy);

			}

			if (cy + bounds.height/2 < bounds.height){
				dy = Math.abs(dy);
				touchX=true;
				//aft.translate(cx, cy);
			}
			if(cy- bounds.height/2> height- bounds.height){
				dy = -Math.abs(dy);
				touchX=true;
				//aft.translate(cx, cy);
			}

				if (bounds.height > height / 3 || bounds.height < 10)
					sf = 1 / sf;





			// zwiekszenie lub zmniejszenie


			// konstrukcja przeksztalcenia
			//aft.translate(cx, cy);

			/*if(!(sf*cx + bounds.width/2< bounds.width  || sf*cx+ bounds.width/2> width-bounds.width))
				if (!(sf*cy + bounds.height/2 < bounds.height || sf*cy+ bounds.height/2> height- bounds.height))
					*/

			if(touchX) numberOfTouch++;
			aft.translate(cx, cy);
					//aft.scale(sf, sf);
			//aft.translate(cx, cy);
			aft.rotate(an);
			aft.scale(sf, sf);
				aft.translate(-cx, -cy);
					aft.translate(dx, dy);
					// przeksztalcenie obiektu
					area.transform(aft);

				}

		return area;

	}


	@Override
	public void actionPerformed(ActionEvent evt) {
		if(!(numberOfTouch==number)){
			buffer.setColor(Color.BLACK);
			buffer.fill(shape);
			numberOfTouch--;
			// wykreslenie ramki
			//buffer.setColor(Color.BLACK);
			//buffer.draw(shape);
		}
		else{
			buffer.setColor(clr.brighter());
			buffer.fill(shape);
			// wykreslenie ramki
			buffer.setColor(clr.darker());
			buffer.draw(shape);
		}
		// wypelnienie obiektu

		touchY=false;
		touchX=false;
	}

}
