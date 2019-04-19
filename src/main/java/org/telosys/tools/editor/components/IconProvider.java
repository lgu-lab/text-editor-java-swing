package org.telosys.tools.editor.components;

import java.io.File;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class IconProvider {

//	private static final ImageIcon TEXT_FILE_ICON     = createImageIcon("/icons/file_16pix.png");
	private static final ImageIcon TEXT_FILE_ICON     = createImageIcon("/icons/text_file_16pix.png");
	private static final ImageIcon DBCFG_FILE_ICON    = createImageIcon("/icons/dbcfg_file_16pix.png");
	private static final ImageIcon VELOCITY_FILE_ICON = createImageIcon("/icons/velocity_file_16pix.png");
	private static final ImageIcon MODEL_FILE_ICON    = createImageIcon("/icons/model_file_16pix.png");
	private static final ImageIcon ENTITY_FILE_ICON   = createImageIcon("/icons/entity_file_16pix.png");
	private static final ImageIcon TEMPLATES_CFG_FILE_ICON = createImageIcon("/icons/templates_cfg_file_16pix.png");
	private static final ImageIcon TELOSYS_CFG_FILE_ICON   = createImageIcon("/icons/telosys_cfg_file_16pix.png");
	
	private static ImageIcon createImageIcon(String path) {
		URL url = TextEditor.class.getResource(path);
		if (url != null) {
			return new ImageIcon(url);
		} else {
			throw new RuntimeException("Couldn't find icon file " + path );
		}
	}
	
	public static Icon getIcon(File file) {
		String fileName =  file.getName();
		if ( fileName.endsWith(".entity") ) return ENTITY_FILE_ICON ;
		if ( fileName.endsWith(".model") ) return MODEL_FILE_ICON ;
		if ( fileName.endsWith(".dbcfg") ) return DBCFG_FILE_ICON ;
		if ( fileName.endsWith(".vm") ) return VELOCITY_FILE_ICON ;
		if ( fileName.equals("templates.cfg") ) return TEMPLATES_CFG_FILE_ICON ;
		if ( fileName.startsWith("telosys") && fileName.endsWith(".cfg")) return TELOSYS_CFG_FILE_ICON ;
		// Default icon 
		return TEXT_FILE_ICON ;
	}
	


}
