package org.telosys.tools.editor;

import java.util.Hashtable;

import org.telosys.tools.editor.components.TextEditor;

public class TextEditorsManager {

	private int id = 0 ;
	
	private Hashtable<String, TextEditor> editors = new Hashtable<>();
	
	public int openTextEditor(String fileFullPath) {
		TextEditor editor = editors.get(fileFullPath);
		if ( editor != null ) {
			editor.setVisible(true);
			editor.setState(java.awt.Frame.NORMAL);
			return 0;
		}
		else {
			id++ ;
			TextEditor newEditor = new TextEditor(fileFullPath);
			editors.put(fileFullPath, newEditor);
			newEditor.setState(java.awt.Frame.NORMAL);
			newEditor.setVisible(true);
			return id;
		}
	}
}
