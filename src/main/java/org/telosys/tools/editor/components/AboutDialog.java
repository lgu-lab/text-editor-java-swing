package org.telosys.tools.editor.components;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JDialog;
import javax.swing.JEditorPane;

/**
 * About dialog box
 *
 */
public class AboutDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private JEditorPane pane;

	public AboutDialog() {
		setTitle("About");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setSize(300, 200);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x, y;
		y = (screenSize.height / 2) - (getHeight() / 2);
		x = (screenSize.width / 2) - (getWidth() / 2);
		setLocation(x, y);
		createComponent();
		setVisible(true);
	}

	private void createComponent() {
		pane = new JEditorPane();
		pane.setEditable(false);
		pane.setContentType("text/html");
		String html = ""
				+ "<p align=center> Telosys text editor</p>"
				+ "<p align=center> Version 0.1 (beta) </p>"

				+ "<p align=center>Official web site : "
				+ "<a href='http://www.telosys.org/'><b>http://www.telosys.org/</b></a></p>"
				
				;
		pane.setText(html);
		add(pane);
	}

}
