package org.telosys.tools.editor;

import java.io.File;

import org.telosys.tools.editor.components.TextEditor;

public class TextEditorsManager {

	private int id = 0 ;
	
//	private Hashtable<String, TextEditor> editors = new Hashtable<>();
	private File dirFullPath = new File("D:/TMP") ; // TODO
	
	private TextEditor textEditor = null;
	
	public int openTextEditor(File textFile) {
//		TextEditor editor = editors.get(fileFullPath);
//		if ( editor != null ) {
//			editor.setVisible(true);
//			editor.setState(java.awt.Frame.NORMAL);
//			editor.setVisible(true);
//			editor.setState(java.awt.Frame.NORMAL);
		if ( textEditor != null ) {
			// Editor already exist
//			textEditor.selectTab(textFile); // TODO
			textEditor.putOnFront();
			return 0;
		}
		else {
			id++ ;
//			TextEditor newEditor = new TextEditor(fileFullPath);
//			editors.put(fileFullPath, newEditor);
//			newEditor.setState(java.awt.Frame.NORMAL);
//			newEditor.setVisible(true);
			textEditor = new TextEditor(dirFullPath);
			textEditor.editFile(textFile); 
			textEditor.putOnFront();
			return id;
		}
	}
}
