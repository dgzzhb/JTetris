import javax.swing.JFrame;

import us.tzheng.JTetris.JGame;

public class JTetris extends JFrame {
	
	private static final int FRAME_WIDTH = 200;
	private static final int FRAME_HEIGHT = 300;
	
	public JTetris() {
		JGame game = new JGame();
		addKeyListener(game);
		add(game);
	}
	
	public static void main(String[] args) {
		JTetris frame = new JTetris();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setVisible(true);
	}
}
