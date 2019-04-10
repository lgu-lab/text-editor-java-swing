package org.telosys.tools.editor.components;

import javax.swing.JOptionPane;

/**
 * About dialog box
 *
 */
public class DebugDialog {

	public static void show() {
		
		String title = "Debug";

		String msg = "Debug information :" 
				+ "\n"
				+ "tabbedPane.getTabCount() : " + DebugVariables.tabbedPane.getTabCount()
				+ "\n"
				+ "tabbedPane.getSelectedIndex() : " + DebugVariables.tabbedPane.getSelectedIndex()
				+ "\n"
				+ "tabbedPane.getComponentCount() : " + DebugVariables.tabbedPane.getComponentCount()
				+ "\n" ;
		
		JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
	}

}
