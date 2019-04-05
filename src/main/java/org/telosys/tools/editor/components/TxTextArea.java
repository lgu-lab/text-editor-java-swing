package org.telosys.tools.editor.components;

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
		
	}
	
	protected void setDocumentListener( TxDocumentListener listener) {
		this.getDocument().addDocumentListener(listener);
	}
	
}
