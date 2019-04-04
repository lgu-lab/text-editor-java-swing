package org.telosys.tools.editor.components;

import javax.swing.JTabbedPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

class TxDocumentListener implements DocumentListener {
 
	private final JTabbedPane tabbedPane ;
	private final int         tabIndex ;
	
	private boolean modified = false ;
	private String  originalTitle = ""; 
	
    public TxDocumentListener(JTabbedPane tabbedPane, int tabIndex) {
		super();
		this.tabbedPane    = tabbedPane;
		this.tabIndex      = tabIndex;
		this.originalTitle = tabbedPane.getTitleAt(tabIndex);
		this.modified = false ;
	}

//	private void log(String s) {
//    	System.out.println("LOG : " + s);
//    }
    
	private void markAsUpdated() {
		// tab.setTitle
		String title = tabbedPane.getTitleAt(tabIndex);
		tabbedPane.setTitleAt(tabIndex, " * "+title); 
		modified = true ;
    }
    
    public boolean isModified() {
		return modified ;
	}

    public void reset() {
    	this.tabbedPane.setTitleAt(tabIndex, originalTitle);
    	this.modified = false ;
    }

    public void insertUpdate(DocumentEvent e) {
    	//log("insertUpdate");
    	if (!modified) markAsUpdated() ;
    }
    
    public void removeUpdate(DocumentEvent e) {
    	//log("removeUpdate");
    	if (!modified) markAsUpdated() ;
    }
    
    public void changedUpdate(DocumentEvent e) {
    	// Gives notification that an attribute or set of attributes changed
    	// Nothing to do
    }

}
