package org.telosys.tools.editor.components;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * About dialog box
 *
 */
public class AboutDialog {

	//private final static ImageIcon icon = new ImageIcon("src/images/turtle64.png");
//	private final static ImageIcon ICON = new ImageIcon(AboutDialog.class.getResource("icons/telosys_64.png"));
	private final static URL ICON_URL = AboutDialog.class.getClassLoader().getResource("icons/telosys_64.png");
	
	public static void show() {
		
		// JOptionPane.PLAIN_MESSAGE : no icon
		//JOptionPane.showMessageDialog(null, "My Information Message", "About Telosys editor", JOptionPane.PLAIN_MESSAGE);

		// JOptionPane.INFORMATION_MESSAGE : 'Info' icon
		// JOptionPane.showMessageDialog(null, "My Information Message", "About Telosys editor", JOptionPane.INFORMATION_MESSAGE);

//		URL imageURL = AboutDialog.class.getClassLoader().getResource("icons/telosys_64.png");
		
		String title = "About Telosys editor";
		
		String msg = "Telosys editor version " + TextEditorVersion.VERSION 
				+ "\n"
				+ "home is '" + TextEditor.getHome() + "' "
				+ "\n" ;
		
		if (ICON_URL != null)   {
			ImageIcon icon = new ImageIcon(ICON_URL);
			JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE, icon);
		}
		else {
			JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
		}
		  
	}

}
