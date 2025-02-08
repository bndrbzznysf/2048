package boundary;

import control.IControl;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class MainWindow extends JFrame implements KeyListener {
	private IControl control;
	private Grid grid ;
	
	public MainWindow(IControl c) {
		this.control = c;
		this.grid = new Grid(c);
		
		this.setTitle("Score : " + this.control.score());
		this.setPreferredSize(new Dimension(455, 478));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		this.add(grid);
		this.addKeyListener(this);
		
		// à la fin
		this.pack();
		this.setVisible(true);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		//EMPTY
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// EMPTY
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_UP ->  {this.control.up();}
			case KeyEvent.VK_DOWN ->  {this.control.down();}
			case KeyEvent.VK_LEFT ->  {this.control.left();}
			case KeyEvent.VK_RIGHT ->  {this.control.right();}
			case KeyEvent.VK_ESCAPE ->  {this.control.init();}
		}
		this.grid.repaint();
		this.setTitle("Score : " + this.control.score());
		this.pack();
	}
}
