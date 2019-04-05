package org.telosys.tools.editor.components;

import javax.swing.JFileChooser;

/**
 * Specific FileChooser with System Look And Feel
 * 
 * @author Laurent GUERIN 
 *
 */
public class TxFileChooser extends JFileChooser {

	private static final long serialVersionUID = 1L;

//	@Override
//	public void updateUI() {
//		super.updateUI();
//	}

//	@Override
//	public void updateUI() {
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		} catch (Throwable ex) {
//			//originalLookAndFeel = null;
//		}
//		super.updateUI();
//	}

	
//	@Override
//	public void updateUI() {
//		LookAndFeel originalLookAndFeel = UIManager.getLookAndFeel();
//		
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		} catch (Throwable ex) {
//			originalLookAndFeel = null;
//		}
//
//		super.updateUI();
//
//		if (originalLookAndFeel != null) {
//////			FilePane filePane = findFilePane(this);
//////			filePane.setViewType(FilePane.VIEWTYPE_DETAILS);
//////			filePane.setViewType(FilePane.VIEWTYPE_LIST);
////
////			Color background = UIManager.getColor("Label.background");
////			setBackground(background);
////			setOpaque(true);
//
//			try {
//				UIManager.setLookAndFeel(originalLookAndFeel);
//			} catch (UnsupportedLookAndFeelException e) {
//			} // shouldn't get here
//		}
//	}

}
