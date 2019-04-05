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

	private TxDocumentListener documentListener = null ;
	
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
		
//		this.getDocument().addDocumentListener( new TxDocumentListener() );
//		setKeyListener();
	}
	
	protected void setDocumentListener( TxDocumentListener listener) {
		documentListener = listener;
		this.getDocument().addDocumentListener(listener);
	}
	
	protected void reset(String title) {
		if ( documentListener != null ) {
			documentListener.reset(title);
		}
	}

	protected boolean isModified() {
		if ( documentListener != null ) {
			return documentListener.isModified();
		}
		return false ;
	}

}
