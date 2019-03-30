package org.telosys.tools.editor.components;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

import javax.swing.JTextArea;

/**
 * 
 *
 */
public class TxTextArea extends JTextArea implements Serializable {

	private static final long serialVersionUID = 1L;

	public TxTextArea(String text) {
		super();
		
		this.setText(text);
		this.setEnabled(true);
		this.setBackground(Color.WHITE);
		this.setForeground(Color.BLACK);

		this.setWrapStyleWord(false);
		this.setLineWrap(false);
		
		this.setTabSize(3);
		this.setCaretPosition(0);
		
		setKeyListener();
	}

	public void setKeyListener() {
		KeyTextListener listener = new KeyTextListener();
		this.addKeyListener(listener);
	}

	public void setMouseListener(MouseListener e) {
		this.addMouseListener(e);
	}

	/**
	 * class to implement a keyboard listener
	 */
	class KeyTextListener extends KeyAdapter {
		public KeyTextListener() {
		}

		public void keyPressed(KeyEvent e) {
			int code = e.getKeyCode();

//			if (controlPress(code))
//				modified = true;
		}

		private boolean controlPress(int code) {
			if (code != KeyEvent.VK_ALT && code != KeyEvent.VK_ALT_GRAPH && code != KeyEvent.VK_CAPS_LOCK
					&& code != KeyEvent.VK_CONTROL && code != KeyEvent.VK_DOWN && code != KeyEvent.VK_LEFT
					&& code != KeyEvent.VK_UP && code != KeyEvent.VK_RIGHT && code != KeyEvent.VK_WINDOWS
					&& code != KeyEvent.VK_SHIFT && code != KeyEvent.VK_END && code != KeyEvent.VK_PAGE_DOWN
					&& code != KeyEvent.VK_PAGE_UP && code != KeyEvent.VK_PRINTSCREEN && code != KeyEvent.VK_ESCAPE
					&& code != KeyEvent.VK_INSERT && code != KeyEvent.VK_PAUSE && code != KeyEvent.VK_NUM_LOCK
					&& code != KeyEvent.VK_F1 && code != KeyEvent.VK_F2 && code != KeyEvent.VK_F3
					&& code != KeyEvent.VK_F4 && code != KeyEvent.VK_F5 && code != KeyEvent.VK_F6
					&& code != KeyEvent.VK_F7 && code != KeyEvent.VK_F8 && code != KeyEvent.VK_F9
					&& code != KeyEvent.VK_F10 && code != KeyEvent.VK_F11 && code != KeyEvent.VK_F12 && code != 36
					&& code != 0 && code != 525)
				return true;
			else
				return false;
		}
	}
}
