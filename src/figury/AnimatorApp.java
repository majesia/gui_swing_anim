package figury;

import java.awt.*;

import javax.swing.*;
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
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kanwa.addFig();
			}
		});
		btnAdd.setBounds(10, 239, 80, 23);
		contentPane.add(btnAdd);
		
		JButton btnAnimate = new JButton("Animate");
		btnAnimate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kanwa.animate();
			}
		});
		btnAnimate.setBounds(100, 239, 80, 23);
		contentPane.add(btnAnimate);

		/*JButton btnLowFPS = new JButton("Low FPS");
		btnLowFPS.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(nr++ % 2==0){
					toStart=true;
					kanwa.LowFPS();
				}else{
					toStart=false;
					kanwa.LowFPS();
				}
			}
		});
		btnLowFPS.setBounds(210,239,100,23);
		contentPane.add(btnLowFPS);*/
		JButton btnResetAnim = new JButton("Reset");
		btnResetAnim.setBounds(210,239,700,23);
		contentPane.add(btnResetAnim);
		btnResetAnim.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(btnResetAnim,"Press the left mouse button for min. 3s.");
			}
		});
		contentPane.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				super.componentResized(e);
				kanwa.setBounds( 10, 11, contentPane.getWidth() - 28, contentPane.getHeight() - 51);
				kanwa.initialize();
				btnAdd.setBounds( 10, contentPane.getHeight() - 28, 80, 23);
				btnAnimate.setBounds( 100, contentPane.getHeight() - 28, 80, 23);
				btnResetAnim.setBounds( 190,contentPane.getHeight() - 28, 160, 23);
			}
		});



	}

}
