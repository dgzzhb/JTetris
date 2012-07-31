package us.tzheng.JTetris;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private int[][] panel = new int[13][23];
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
		newCube();
		newPanel();
		drawBoarder();
		Timer clock = new Timer(1000, new TimerListener());
		clock.start();
	}
	// Initial Panel
	private void newPanel() {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 22; j++) {
				panel[i][j] = 0;
			}
		}
	}
	// Draw Boarder of Panel
	private void drawBoarder() {
		for (int i = 0; i < 22; i++) {
			panel[0][i] = 2;
			panel[11][i] = 2;
		}
		
		for (int j = 0; j < 12; j++) {
			panel[j][21] = 2;
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
	public void keyPressed(KeyEvent event) {
		switch (event.getKeyCode()) {
		case KeyEvent.VK_UP:
			turn();
			break;
		case KeyEvent.VK_DOWN:
			down();
			break;
		case KeyEvent.VK_LEFT:
			left();
			break;
		case KeyEvent.VK_RIGHT:
			right();
			break;
		}
	}

	private void turn() {
		int turnFlag = cubeTurn;
		cubeTurn = (cubeTurn + 1) % 4;
		if (legal(x,y,cubeType,cubeTurn) == 0) {
			cubeTurn = turnFlag;
		}
		repaint();
	}
	private void down() {
		if (legal(x,y+1,cubeType,cubeTurn) == 1) {
			y = y + 1;
			delete();
		}
		if (legal(x,y+1,cubeType,cubeTurn) == 0) {
			stay(x,y,cubeType,cubeTurn);
			newCube();
			delete();
		}
		repaint();
	}
	
	private void left() {
		if (legal(x-1,y,cubeType,cubeTurn) == 1) {
			x = x - 1;
		}
		repaint();
	}
	
	private void right() {
		if (legal(x+1,y,cubeType,cubeTurn) == 1) {
			x = x + 1;
		}
		repaint();
	}
	
	private void delete() {
		int flag = 0;
		for (int i = 0; i < 22; i++) {
			for (int j = 0; j < 12; j++) {
				// different
				if (panel[j][i] ==1) {
					flag++;
					if (flag == 10) {
						score += 10;
						for (int k = i; k > 0; k--) {
							for (int l = 0; l < 11; l++) {
								panel[l][k] = panel[l][k - 1];
							}
						}
					}
				}
			}
			flag = 0;
		}
	}
	
	private void stay(int x, int y, int cubeType, int cubeTurn) {
		int flag = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (panel[x+j+1][y+i] == 0) {
					panel[x+j+1][y+i] = shape[cubeType][cubeTurn][flag];
				}
				flag++;
			}
		}
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < 16; i++) {
			if (shape[cubeType][cubeTurn][i] == 1) {
				g.fillRect((i%4+x+1)*10,(i/4+y)*10,10,10);
			}
		}
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 22; j++) {
				if (panel[i][j] == 1) {
					g.fillRect(i*10, j*10, 10, 10);
				}
				if (panel[i][j] == 2) {
					g.drawRect(i*10, j*10, 10, 10);
				}
			}
		}
		g.drawString("Score="+score, 125, 10);
	}
	public void keyReleased(KeyEvent arg0) {
		// no use
	}

	public void keyTyped(KeyEvent arg0) {
		// no use
	}
	
	class TimerListener implements ActionListener {
		private int flag;
		public void actionPerformed (ActionEvent e) {
			repaint();
			if (legal(x,y+1,cubeType,cubeTurn) == 1) {
				y++;
				delete();
			}
			if (legal(x,y+1,cubeType,cubeTurn) == 0) {
				if (flag == 1) {
					stay (x,y,cubeType,cubeTurn);
					delete();
					newCube();
					flag = 0;
				}
				flag = 1;
			}
		}
	}

}
