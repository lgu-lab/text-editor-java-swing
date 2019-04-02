package org.telosys.tools.editor;

import java.io.File;

import org.telosys.tools.editor.components.TextEditor;

public class TextEditorsManager {

	private File dirFullPath = new File("D:/TMP") ; // TODO
	
	private TextEditor textEditor = null;
	
	public void openTextEditor(File textFile) {
		if ( textEditor == null ) {
			textEditor = new TextEditor(dirFullPath);
		}
		textEditor.editFile(textFile); 
		textEditor.putOnFront();
	}
}
