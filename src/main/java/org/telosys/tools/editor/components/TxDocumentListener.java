package org.telosys.tools.editor.components;

import javax.swing.JTabbedPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * DocumentListener implementation <br>
 * Listen to all changes on the text document <br>
 * If the document is modified it updates the 'modified' flag and the tab title ('*' added/removed) 
 * 
 * @author Laurent GUERIN
 *
 */
class TxDocumentListener implements DocumentListener {

	private final JTabbedPane tabbedPane; // set of tabs
	private final int tabIndex; // index of the current tab in the JTabbedPane

	private boolean modified = false;

	/**
	 * Constructor
	 * @param tabbedPane the set of tabs
	 * @param tabIndex the tab index managed by this DocumentListener
	 */
	public TxDocumentListener(JTabbedPane tabbedPane, int tabIndex) {
		super();
		this.tabbedPane = tabbedPane;
		this.tabIndex = tabIndex;
		this.modified = false;
	}

	/**
	 * Marks the current Tab as 'modified'
	 */
	private void markAsUpdated() {
		// Get current title and add "*" at the beginning
		String title = tabbedPane.getTitleAt(tabIndex);
		tabbedPane.setTitleAt(tabIndex, " * " + title);
		// Flag as modified
		modified = true;
	}

	public boolean isModified() {
		return modified;
	}

	/**
	 * Resets the current Tab as 'not modified'
	 * @param title
	 */
	public void reset(String title) {
		this.tabbedPane.setTitleAt(tabIndex, title);
		this.modified = false;
	}

	public void insertUpdate(DocumentEvent e) {
		if (!modified)
			markAsUpdated();
	}

	public void removeUpdate(DocumentEvent e) {
		if (!modified)
			markAsUpdated();
	}

	public void changedUpdate(DocumentEvent e) {
		// Gives notification that an attribute or set of attributes changed
		// Nothing to do
	}

}
