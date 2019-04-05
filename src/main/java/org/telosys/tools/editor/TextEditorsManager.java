package org.telosys.tools.editor;

import java.io.File;

import org.telosys.tools.editor.components.TextEditor;

public class TextEditorsManager {

	private final File homeDir ;
	
	private TextEditor textEditor = null;
	
	
	public TextEditorsManager(String homeDirFullPath) {
		super();
		this.homeDir = new File(homeDirFullPath);
	}

	public TextEditorsManager(File homeDir) {
		super();
		this.homeDir = homeDir;
	}


	public void openTextEditor(File textFile) {
		if ( textEditor == null ) {
			textEditor = new TextEditor(homeDir);
		}
		textEditor.editFile(textFile); 
		textEditor.putOnFront();
	}
}
