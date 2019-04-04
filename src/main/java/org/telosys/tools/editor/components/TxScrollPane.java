package org.telosys.tools.editor.components;

import java.io.File;
import java.io.Serializable;

import javax.swing.JScrollPane;

/**
 * 
 * JScrollPane specialization to keep the file, the tab title and return text.
 * 
 * @author Laurent GUERIN
 *
 */
public class TxScrollPane extends JScrollPane implements Serializable {

	private static final long serialVersionUID = 1L;

	private final  String      title;
	private final  File        file;
	private final  TxTextArea  textArea;

	public TxScrollPane(TxTextArea textArea, String title, File file) {
		super(textArea);
		this.textArea = textArea ;
		this.title = title ;
		this.file = file ;
	}

	public File getFile() {
		return file;
	}

	public String getTitle() {
		return title;
	}
	
	public TxTextArea getTextArea() {
		return textArea ;
	}

	public String getText() {
		return textArea.getText();
	}
	
	public boolean isModified() {
		return textArea.isModified();
	}
	
	public void reset() {
		textArea.reset();
	}
}
