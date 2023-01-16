package panele;

import figury.Figura;
import figury.Kwadrat;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Random;

public class AnimatorApp extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static boolean toStart=false;
	private static int nr=0;

	public static JLabel lTime;
	private int numer=3;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					final AnimatorApp frame = new AnimatorApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param delay 
	 */
	public AnimatorApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int ww = 450, wh = 310;
		setBounds((screen.width-ww)/2, (screen.height-wh)/2, ww, wh);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setBackground(Color.WHITE);

		AnimPanel kanwa = new AnimPanel();
		kanwa.setBounds(10, 11, 422, 219);
		contentPane.add(kanwa);
		SwingUtilities.invokeLater(() -> kanwa.initialize());

		JButton btnAdd = new JButton("Add");
		btnAdd.setBackground(Color.pink);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(numer>=3) kanwa.addFig();
				//kanwa.addFig();
				switch (numer) {
					case 0:
						kanwa.createKwadrat();
						break;
					case 1:
						kanwa.createElipsa();
						break;
					case 2:
						kanwa.createGwiazda();
						break;
				}
			}
		});
		btnAdd.setBounds(10, 239, 80, 23);
		contentPane.add(btnAdd);
		contentPane.setBackground(Color.GRAY);
		
		JButton btnAnimate = new JButton("Animate");
		btnAnimate.setBackground(Color.pink);
		btnAnimate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kanwa.animate();
			}
		});
		btnAnimate.setBounds(100, 239, 80, 23);
		contentPane.add(btnAnimate);

		JButton btnResetAnim = new JButton("Reset");
		btnResetAnim.setBounds(210,239,100,23);
		btnResetAnim.setBackground(Color.pink);
		contentPane.add(btnResetAnim);
		btnResetAnim.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(btnResetAnim,"Press the left mouse button for min. 3s.");
			}
		});


		lTime = new JLabel();
		lTime.setBounds(300,239,200,23);
		lTime.setBackground(Color.GRAY);
		contentPane.add(lTime);

		JRadioButton rbKwadrat = new JRadioButton("Kwadrat");
		JRadioButton rbElipsa = new JRadioButton("Elipsa");
		JRadioButton rbGwiazda = new JRadioButton("Gwiazda");
		rbKwadrat.setBounds(20,270, 80, 23);
		rbElipsa.setBounds(110,270, 80, 23);
		rbGwiazda.setBounds(200,270, 80, 23);
		rbKwadrat.setBackground(Color.GRAY);
		rbElipsa.setBackground(Color.GRAY);
		rbGwiazda.setBackground(Color.GRAY);
		contentPane.add(rbKwadrat);
		contentPane.add(rbElipsa);
		contentPane.add(rbGwiazda);
		ButtonGroup group= new ButtonGroup();
		group.add(rbKwadrat);
		group.add(rbElipsa);
		group.add(rbGwiazda);
		rbKwadrat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {numer=0;}});
		rbElipsa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {numer=1;}});
		rbKwadrat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {numer=2;}});








		contentPane.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				super.componentResized(e);
				kanwa.setBounds( 10, 11, contentPane.getWidth() - 28, contentPane.getHeight() - 81);
				kanwa.initialize();
				btnAdd.setBounds( 10, contentPane.getHeight() - 58, 80, 23);
				btnAnimate.setBounds( 100, contentPane.getHeight() - 58, 80, 23);
				btnResetAnim.setBounds( 190,contentPane.getHeight() - 58, 100, 23);
				lTime.setBounds(300,contentPane.getHeight()-58,150,23);
				rbKwadrat.setBounds(20,contentPane.getHeight()-28,80,23);
				rbElipsa.setBounds(110,contentPane.getHeight()-28,80,23);
				rbGwiazda.setBounds(200,contentPane.getHeight()-28,80,23);

			}
		});





	}

}
