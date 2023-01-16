package panele;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class AnimatorApp extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static boolean toStart=false;
	private static int nr=0;

	public static JLabel lTime;
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
		int ww = 450, wh = 300;
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
				kanwa.addFig();
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


		contentPane.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				super.componentResized(e);
				kanwa.setBounds( 10, 11, contentPane.getWidth() - 28, contentPane.getHeight() - 51);
				kanwa.initialize();
				btnAdd.setBounds( 10, contentPane.getHeight() - 28, 80, 23);
				btnAnimate.setBounds( 100, contentPane.getHeight() - 28, 80, 23);
				btnResetAnim.setBounds( 190,contentPane.getHeight() - 28, 100, 23);
				lTime.setBounds(300,contentPane.getHeight()-28,150,23);
			}
		});





	}

}
