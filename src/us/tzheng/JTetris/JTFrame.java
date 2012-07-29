package us.tzheng.JTetris;

import javax.swing.JFrame;

public class JTFrame extends JFrame {
	
	private static final int FRAME_WIDTH = 300;
	private static final int FRAME_HEIGHT = 200;
	
	public JTFrame() {
		JGame game = new JGame();
		setupGUI();
		addKeyListener(game);
		add(game);
	}

	private void setupGUI() {
		JTFrame frame = new JTFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setVisible(true);
	}
}
