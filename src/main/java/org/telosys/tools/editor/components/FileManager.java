package org.telosys.tools.editor.components;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.swing.JOptionPane;

public class FileManager {
	
	private final static Charset   CHARSET = StandardCharsets.UTF_8 ;

	private void showError(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

//	public void loadFile(File file) {
//		if ( file == null ) {
//			showError("File is null");
//			return ;
//		}
//		if ( ! file.exists() ) {
//			showError("File not found\n" + file.getAbsolutePath());
//			return ;
//		}
//		if ( ! file.isFile() ) {
//			showError("Not a file\n"  + file.getAbsolutePath());
//			return ;
//		}
//		// File is OK : read it
//		try {
//			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), CHARSET));
//			in.
//			//textArea.read(new FileReader(file), null);
//			textArea.read(in, null);
//			resetTextChanged();
//			// set the document listener here (new file => new document)
//			// textArea.getDocument().addDocumentListener( new TextEditorListener(this) ); // use a single instance
//			textArea.getDocument().addDocumentListener( documentListener ); 
//			
//		} catch (IOException e) {
//			//e.printStackTrace();
//			showError("Cannot load file (IOException) \n" + file.getAbsolutePath());
//		}
//	}
	
	/**
	 * Read text from the given file 
	 * @param file
	 * @return the text (or null if an error occurred)
	 */
	public String readTextFromFile(File file) {
		if ( file == null ) {
			showError("File is null");
			return null;
		}
		if ( ! file.exists() ) {
			showError("File not found\n" + file.getAbsolutePath());
			return null;
		}
		if ( ! file.isFile() ) {
			showError("Not a file\n"  + file.getAbsolutePath());
			return null;
		}
		// File is OK : read it
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String inputLine;
			String result = "";
			inputLine = bufferedReader.readLine();

			while (inputLine != null) {
				result += inputLine;
				inputLine = bufferedReader.readLine();
				// questo controllo viene effettuato per evitare che anche l'ultima riga
				// contenga il carattere di fine riga
				if (inputLine != null)
					result += "\n";
			}

			fileReader.close();
			bufferedReader.close();

			return result;

		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			return null;
		}

	}
	
	public void saveToFile(char[] text, File file) {
		if ( file == null ) {
			showError("File is null");
			return ;
		}
        try {
            //BufferedWriter outFile = new BufferedWriter(new FileWriter(file));
        	BufferedWriter outFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), CHARSET));
//            outFile.write(textArea.getText());
            outFile.write(text);
            
            outFile.close();
//			resetTextChanged();
        } catch (IOException ex) {
			showError("Cannot save file (IOException) \n" + file.getAbsolutePath());
        }		
	}

}
