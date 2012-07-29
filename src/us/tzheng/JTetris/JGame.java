package us.tzheng.JTetris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class JGame extends JPanel implements KeyListener {

	public JGame() {
		newPanel();
		drawBoarder();
		newCube();
		Timer clock = new Timer(1000, new TimerListener());
		clock.start();
	}
	
	private void newPanel() {
		// TODO Auto-generated method stub
		
	}

	private void drawBoarder() {
		// TODO Auto-generated method stub
		
	}

	private void newCube() {
		// TODO Auto-generated method stub
		
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
