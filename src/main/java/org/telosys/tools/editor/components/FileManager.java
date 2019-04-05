package org.telosys.tools.editor.components;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.swing.JOptionPane;

/**
 * File manager to load/save text from/to file.
 * 
 * @author Laurent GUERIN
 *
 */
public class FileManager {
	
	private final static Charset   CHARSET = StandardCharsets.UTF_8 ;

	private void showError(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

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
		if ( ! file.isFile() ) {
			showError("Not a file\n"  + file.getAbsolutePath());
			return null;
		}
		StringBuffer sb = new StringBuffer();
		try {
			FileInputStream is = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(is, CHARSET));
			String str;
			while ((str = br.readLine()) != null) {
				sb.append(str);
				sb.append("\n");
			}
			br.close();
			is.close();
		} catch (FileNotFoundException e) {
			showError("File not found\n" + file.getAbsolutePath());
		} catch (IOException e) {
			showError("IOException\n"  + file.getAbsolutePath());
		}
		return sb.toString();
	}

	/**
	 * Saves the given text in the given file.
	 * @param text
	 * @param file
	 */
	public void saveTextToFile(String text, File file) {
		if ( file == null ) {
			showError("File is null");
			return ;
		}
        try {
            //BufferedWriter outFile = new BufferedWriter(new FileWriter(file));
        	BufferedWriter outFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), CHARSET));
            outFile.write(text);
            outFile.close();
        } catch (IOException ex) {
			showError("Cannot save file (IOException) \n" + file.getAbsolutePath());
        }		
	}
}
