package org.telosys.tools.editor.components;

import java.io.File;
import java.io.Serializable;

import javax.swing.JScrollPane;

public class TxScrollPane extends JScrollPane implements Serializable {

	private static final long serialVersionUID = 1L;

	private final  File    file;

	private final  String  title;

	public TxScrollPane(TxTextArea view, String title, File file) {
		super(view);
		this.file = file ;
		this.title = title ;
	}

	public File getFile() {
		return file;
	}

	public String getTitle() {
		return title;
	}
	
}
