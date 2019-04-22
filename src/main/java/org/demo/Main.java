package org.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.telosys.tools.editor.TextEditorsManager;

public class Main {
	
	private static String ROOT = "D:/TMP"; // Default value
	
	public static File getFile (String fileName) {
		if ( ROOT.endsWith("/") ) {
			return new File( ROOT + fileName); 
		}
		else {
			return new File( ROOT + "/" + fileName); 
		}
	}
	
	public static void main(String[] args) {
		
		System.out.println("Starting, args.length = " + args.length );
		if ( args.length > 0 ) {
			String arg = args[0] ;
			System.out.println("File arg = " + arg );
			File root = new File(arg);
			if ( root.exists() ) {
				if ( root.isDirectory() ) {
					ROOT = arg ;
				}
				else {
					System.out.println(arg + " is not a directory ! ");
					System.exit(2);
				}
			}
			else {
				System.out.println(arg + " doesn't exist ! ");
				System.exit(1);
			}
		}
		
		System.out.println("ROOT directory is " + ROOT);
		
		TextEditorsManager editorsManager = new TextEditorsManager(ROOT);
		
		BufferedReader br = null;

        try {

            // Refer to this http://www.mkyong.com/java/how-to-read-input-from-console-java/
            // for JDK 1.6, please use java.io.Console class to read system input.
            br = new BufferedReader(new InputStreamReader(System.in));

            while (true) {

                System.out.print("Enter file name : ");
                String input = br.readLine();

//                if ("foo".equals(input)) {
//            		editorsManager.openTextEditor(getFile("foo.txt") );
//                }
//                if ("aaa".equals(input)) {
//            		editorsManager.openTextEditor(getFile("aaa.txt") );
//                }
//                if ("bbb".equals(input)) {
//            		editorsManager.openTextEditor(getFile("bbb.txt") );
//                }
//                if ("dbcfg".equals(input)) {
//            		editorsManager.openTextEditor(getFile("databases.dbcfg") );
//                }
//                if ("ccc".equals(input)) {
//            		editorsManager.openTextEditor(getFile("Ccc.entity") );
//                }

                if ("q".equals(input)) {
                    System.out.println("Exit!");
                    System.exit(0);
                }
                else {
                	File file = getFile(input);
                	System.out.println("Edit file " + file + "...");
                	editorsManager.openTextEditor(file);
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
