package org.telosys.tools.editor.components;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Specific FileChooser with System Look And Feel
 * 
 * @author Laurent GUERIN 
 *
 */
public class TxFileChooser extends JFileChooser {

	private static final long serialVersionUID = 1L;

	public TxFileChooser(File dir) {
		super();
		
		this.setCurrentDirectory(dir);

//		this.setDialogTitle(title);
//		this.setApproveButtonText(buttonText);

		this.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
//		FileNameExtensionFilter filter;
//		filter = new FileNameExtensionFilter("Entity", "entity");
		this.addChoosableFileFilter( new FileNameExtensionFilter("Entity (.entity)", "entity") );
		this.addChoosableFileFilter( new FileNameExtensionFilter("DSL Model (.model)", "model") );
		this.addChoosableFileFilter( new FileNameExtensionFilter("Template (.vm)", "vm") );
		this.addChoosableFileFilter( new FileNameExtensionFilter("Configuration (.cfg)", "cfg") );
		this.addChoosableFileFilter( new FileNameExtensionFilter("Database Configuration (.dbcfg)", "dbcfg") );
//		filter = new FileNameExtensionFilter("Entity", "entity");
//		FileNameExtensionFilter filter = new FileNameExtensionFilter("Template (Velocity file)", "vm");
//		fileChooser.setFileFilter(filter);
	}

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
