package org.telosys.tools.editor.components;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TextEditorListener implements DocumentListener {

	private final TextEditor editor ;
	
	public TextEditorListener(TextEditor editor) {
		super();
		this.editor = editor;
	}

	@Override
	public void changedUpdate(DocumentEvent event) {
		// Plain text components do not fire these events ( only when the 'style' has changed )
		//System.out.println("changedUpdate");
		//editor.textChanged();		
	}

	@Override
	public void insertUpdate(DocumentEvent event) {
		//System.out.println("insertUpdate");
		editor.textChanged();		
	}

	@Override
	public void removeUpdate(DocumentEvent event) {
		//System.out.println("removeUpdate");
		editor.textChanged();		
	}

}
