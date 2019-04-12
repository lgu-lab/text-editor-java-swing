package org.telosys.tools.editor.components.textarea;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * DocumentListener implementation <br>
 * Listen to all changes on the text document <br>
 * If the document is modified it notifies the associated component
 * 
 * @author Laurent GUERIN
 *
 */
public class TxDocumentListener implements DocumentListener {

	private final TxScrollPane scrollPane ;

	/**
	 * Constructor
	 * @param scrollPane
	 */
	public TxDocumentListener(TxScrollPane scrollPane) {
		super();
		this.scrollPane = scrollPane;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		scrollPane.textUpdate();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		scrollPane.textUpdate();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// Gives notification that an attribute or set of attributes changed
		// Nothing to do
	}

}
