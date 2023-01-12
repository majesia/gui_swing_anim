package figury;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.Timer;

import static figury.AnimatorApp.toStart;

public class AnimPanel extends JPanel implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static boolean paused=true;
	//public static HashMap<Integer,Integer> newFigures = new HashMap<>();
	public static int [][] newFigures= new int[2][1000] ;

	// bufor
	Image image;
	// wykreslacz ekranowy
	Graphics2D device;
	// wykreslacz bufora
	static Graphics2D buffer;

	private static int delay = 70;

	private static Timer timer;

	private static int numer = 0;
	public static int numbers;
	public static int width,height;

	public AnimPanel() {
		super();
		setBackground(Color.WHITE);
		timer = new Timer(delay, this);
		addMouseListener(this);

	}

	public void initialize() {
		width = getWidth();
		height = getHeight();

		image = createImage(width, height);
		buffer = (Graphics2D) image.getGraphics();
		buffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		device = (Graphics2D) getGraphics();
		device.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}


	public static void addFig() {
		Figura fig = (numer++ % 2 == 0) ? new Kwadrat(buffer, delay, width, height)
				: new Elipsa(buffer, delay,width, height);
		timer.addActionListener(fig);
		new Thread(fig).start();
	}

	void animate() {
		if (timer.isRunning()) {
			timer.stop();
			paused=true;
		} else {
			timer.start();
			paused=false;
		}
	}
	void LowFPS(){
		if(toStart) {
			timer.setDelay(500);
		}else{
			timer.setDelay(delay);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//newFigures = new int[2][numbers];
		device.drawImage(image, 0, 0, null);
		buffer.clearRect(0, 0, getWidth(), getHeight());
		/*System.out.println(numbers);
		for (int i=0; i<numbers; i++) {
			int x = newFigures[0][numbers];
			int y = newFigures[1][numbers];
			System.out.println(x+" "+y);
			if(x!=0 && y!=0){
				Figura fig = (numer++ % 2 == 0) ? new Kwadrat(buffer, delay, x, y)
						: new Elipsa(buffer, delay, x,y);
				timer.addActionListener(fig);
				new Thread(fig).start();
			}
		}
		/*for (int i=0; i<numbers; i++) {
			newFigures[0][numbers]=0;
			newFigures[1][numbers]=0;
		}*/
		numbers=0;
	}

	public static boolean isPaused(){
		return paused;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		addFig();
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
