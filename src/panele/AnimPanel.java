package panele;

import figury.Elipsa;
import figury.Figura;
import figury.Gwiazda;
import figury.Kwadrat;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import static panele.AnimatorApp.toStart;

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

	static int delay = 70;

	private static Timer timer, timerToClear;

	private static int numer = 0;
	public static int numbers;
	public static int width,height;
	int numbersOfTimer=0;

	public AnimPanel() {
		super();
		setBackground(Color.WHITE);
		timer = new Timer(delay, this);
		addMouseListener(this);
		timerToClear = new Timer(1000, this);
		timerToClear.addActionListener(this);

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
		/*Figura fig = (numer++ % 2 == 0) ? new Kwadrat(buffer, delay, width, height)
				: new Gwiazda(buffer, delay,width, height);
		timer.addActionListener(fig);
		new Thread(fig).start();*/
		Figura fig = null;
		switch (new Random().nextInt(3)){
			case 0: {
				fig = new Kwadrat(buffer, delay, width, height);
				break;
			}
			
			case 1: {
				fig = new Elipsa(buffer, delay, width, height);
				break;
			}
			
			case 2: {
				fig = new Gwiazda(buffer, delay, width, height);
				break;
			}
			
			
		}
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
		Object source=e.getSource();
		if (source==timer){
			device.drawImage(image, 0, 0, null);
			buffer.clearRect(0, 0, getWidth(), getHeight());
			numbers=0;
		}

		if(source==timerToClear){
			numbersOfTimer++;
			System.out.println("Timer: " +numbersOfTimer);
			AnimatorApp.lTime.setText("Button pressed for: " + numbersOfTimer + "s");
		}


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
		timerToClear.start();
		System.out.println("Timer start");

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		timerToClear.stop();
		System.out.println("Timer stop");
		System.out.println(timerToClear.getDelay());

		if(numbersOfTimer>=3){
			System.out.println("Good job!");

			System.out.println(">3000");
			initialize();
			numbersOfTimer=0;
		}
		//timerToClear.restart();
		AnimatorApp.lTime.setText("");
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
