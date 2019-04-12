package org.telosys.tools.editor.components.textarea;

import java.io.File;
import java.io.Serializable;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

/**
 * JScrollPane specialization to keep the file, the tab title and return
 * text.<br>
 * 
 * The 'ScrollPane' is the component containing the 'TextArea' <br>
 * ( it's a TextArea with scroll bars )
 * 
 * @author Laurent GUERIN
 *
 */
public class TxScrollPane extends JScrollPane implements Serializable {

	private static final long serialVersionUID = 1L;

	private final JTabbedPane tabbedPane; // set of tabs
	private final TxTextArea textArea;
	private File file; // Can change in case of 'save as'
	private String title;
	private boolean modified = false;

	/**
	 * Constructor
	 * 
	 * @param textArea
	 * @param file
	 */
	public TxScrollPane(TxTextArea textArea, File file, JTabbedPane tabbedPane) {
		super(textArea);
		this.tabbedPane = tabbedPane;
		this.textArea = textArea;
		this.file = file;
		this.title = "   " + file.getName();
		this.modified = false;
	}

	public File getFile() {
		return file;
	}

	public String getTitle() {
		return title;
	}

	public TxTextArea getTextArea() {
		return textArea;
	}

	public String getText() {
		return textArea.getText();
	}

	// ---------------------------------------------------------------------------------------

	protected void textUpdate() {
		if (!modified) {
			markAsUpdated();
		}
	}

	private int getTabIndex() {
		return tabbedPane.indexOfComponent(this);
	}

	/**
	 * Reset status and change the current file (eg for "Save As")
	 * 
	 * @param file
	 */
	public void reset(File file) {
		this.file = file;
		this.title = file.getName();
		reset();
	}

	/**
	 * Reset status as 'not modified'
	 */
	public void reset() {
		modified = false;
		title = "   " + file.getName(); // Reset title without '*'
		tabbedPane.setTitleAt(getTabIndex(), title);   
//		tabbedPane.repaint();
	}

	/**
	 * Marks the current Tab as 'modified'
	 */
	private void markAsUpdated() {
		modified = true; // Flag as modified
		title = "* " + file.getName();
//		tabbedPane.setTitleAt(getTabIndex(), " * " + title); // add "*" at the beginning of the title
		tabbedPane.setTitleAt(getTabIndex(), title);
//		tabbedPane.repaint();
	}

	public boolean isModified() {
		return modified;
	}
}
