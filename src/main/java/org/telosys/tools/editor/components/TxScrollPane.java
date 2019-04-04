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

	private final  TxTextArea  textArea;
	private  String      title;
	private  File        file;

//	public TxScrollPane(TxTextArea textArea, String title, File file) {
	public TxScrollPane(TxTextArea textArea, File file) {
		super(textArea);
		this.textArea = textArea ;
		this.file = file ;
		this.title = file.getName() ;
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
	
	/**
	 * Reset status 
	 */
	public void reset() {
		textArea.reset(this.title);
	}

	/**
	 * Reset status and change the current file (eg for "Save As")
	 * @param file
	 */
	public void reset(File file) {
		this.file = file ;
		this.title = file.getName() ;		
		this.textArea.reset(this.title);
	}
}
