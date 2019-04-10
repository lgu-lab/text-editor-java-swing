package org.telosys.tools.editor.components.tabs;

import javax.swing.JLabel;

import org.telosys.tools.editor.components.TxScrollPane;

public class TabLabel extends JLabel {

	private static final long serialVersionUID = 1L;
	
	private final TxScrollPane scrollPane;

	public TabLabel(TxScrollPane scrollPane) {
		super();
		this.scrollPane = scrollPane;
	}

	public String getText() {
		if ( scrollPane != null ) {
			return scrollPane.getTitle();
		}
		else {
			return "???" ;
		}
	}

}
