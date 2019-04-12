package org.telosys.tools.editor.components.textarea;

import java.awt.Color;
import java.io.Serializable;

import javax.swing.JTextArea;

/**
 * JTextArea specialization
 * 
 * @author Laurent GUERIN
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
		
//		this.addMouseListener(new MouseAdapter() {
//	      public void mouseClicked(MouseEvent e) {
////		        if (e.getButton() == MouseEvent.NOBUTTON) {
////			          textArea.setText("No button clicked...");
////			        } else if (e.getButton() == MouseEvent.BUTTON1) {
////			          textArea.setText("Button 1 clicked...");
////			        } else if (e.getButton() == MouseEvent.BUTTON2) {
////			          textArea.setText("Button 2 clicked...");
////			        } else if (e.getButton() == MouseEvent.BUTTON3) {
////			          textArea.setText("Button 3 clicked...");
////			        }
//	    	  // Close Menu if open when click on the text
//	    	  MenuSelectionManager.defaultManager().clearSelectedPath();
//	      }
//	      });
	}
	
	public void setDocumentListener( TxDocumentListener listener) {
		this.getDocument().addDocumentListener(listener);
	}
}
