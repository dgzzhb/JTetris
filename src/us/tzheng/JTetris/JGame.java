package us.tzheng.JTetris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class JGame extends JPanel implements KeyListener {

	private int cubeType;
	private int cubeTurn;
	private int x;
	private int y;
	private int[][] panel = new int[12][23];
	private int score;
    private final int shape[][][] = new int[][][] {
    	// i
    	{ { 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
    	{ 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 },
    	{ 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
    	{ 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 } },
    	// s
    	{ { 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    	{ 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
    	{ 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    	{ 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 } },
    	// z
    	{ { 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    	{ 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
    	{ 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 } },
        // j
        { { 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
        { 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
        { 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
        // o
        { { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
        // l
        { { 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
        { 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
        // t
        { { 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
        { 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0 } } };
    // Constructor
	public JGame() {
		newPanel();
		drawBoarder();
		newCube();
		Timer clock = new Timer(1000, new TimerListener());
		clock.start();
	}
	// Initial Panel
	private void newPanel() {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 23; j++) {
				panel[i][j] = 0;
			}
		}
	}
	// Draw Boarder of Panel
	private void drawBoarder() {
		for (int i = 0; i < 12; i++) {
			panel[i][22] = 2;
		}
		
		for (int j = 0; j < 22; j++) {
			panel[0][j] = 2;
			panel[11][j] = 2;
		}
	}
	// Create new cube
	private void newCube() {
		cubeType = (int) (Math.random() * 1000) % 7;
		cubeTurn = (int) (Math.random() * 1000) % 4;
		// initial position
		x = 4;
		y = 0;
		// chcek if game over
		if (gameOver(x,y) == 1) {
			newPanel();
			drawBoarder();
			score = 0;
			JOptionPane.showMessageDialog(null, "Game Over!");
		}
	}
	// Check if game over
	private int gameOver(int x, int y) {
		if (legal(x,y,cubeType,cubeTurn) == 0) {
			return 1;
		}
		return 0;
	}
	// Check if legal
	private int legal(int x, int y, int cubeType, int cubeTurn) {
		for (int i = 0; i <4; i++) {
			for (int j = 0; j<4; j++) {
				if (((shape[cubeType][cubeTurn][4 * i + j] == 1) && (panel[x + j + 1][y + i] == 1))
						||((shape[cubeType][cubeTurn][4 * i + j] == 1) && (panel[x + j + 1][y + i] == 2))) {
					return 0;
				}
			}
		}
		return 1;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent arg0) {
		// no use
	}

	public void keyTyped(KeyEvent arg0) {
		// no use
	}

}
