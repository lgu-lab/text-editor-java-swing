package texteditor;

import java.util.Hashtable;

public class TextEditorsManager {

	private int id = 0 ;
	
	private Hashtable<String, TextEditor> editors = new Hashtable<>();
	
	public int openTextEditor(String fileFullPath) {
		TextEditor editor = editors.get(fileFullPath);
		if ( editor != null ) {
			editor.setVisible(true);
			editor.setState(java.awt.Frame.NORMAL);
			return 0;
		}
		else {
			id++ ;
			editors.put(fileFullPath, new TextEditor(fileFullPath));
			return id;
		}
	}
}
