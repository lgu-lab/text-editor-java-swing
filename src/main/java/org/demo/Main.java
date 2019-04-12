package org.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.telosys.tools.editor.TextEditorsManager;

public class Main {

	public static void main(String[] args) {
		// Open a text editor 
//		System.out.println("1rst text editor ");
//		new TextEditor("My title");
//		
//		System.out.println("2nd text editor ");
//		new TextEditor("Second");
//		
//		System.out.println("All editors are running... ");
//		
		
		TextEditorsManager editorsManager = new TextEditorsManager("D:/TMP");
		
		BufferedReader br = null;

        try {

            // Refer to this http://www.mkyong.com/java/how-to-read-input-from-console-java/
            // for JDK 1.6, please use java.io.Console class to read system input.
            br = new BufferedReader(new InputStreamReader(System.in));

            while (true) {

                System.out.print("Enter something : ");
                String input = br.readLine();

                if ("foo".equals(input)) {
            		editorsManager.openTextEditor(new File("D:/TMP/foo.txt") );
                }
                if ("aaa".equals(input)) {
            		editorsManager.openTextEditor(new File("D:/TMP/aaa.txt") );
                }
                if ("bbb".equals(input)) {
            		editorsManager.openTextEditor(new File("D:/TMP/bbb.txt") );
                }
                if ("db".equals(input)) {
            		editorsManager.openTextEditor(new File("D:/TMP/databases.dbcfg") );
                }
                if ("ccc".equals(input)) {
            		editorsManager.openTextEditor(new File("D:/TMP/Ccc.entity") );
                }

                if ("q".equals(input)) {
                    System.out.println("Exit!");
                    System.exit(0);
                }

                System.out.println("input : " + input);
                System.out.println("-----------\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

		System.out.println("End. ");
		
	}

}
