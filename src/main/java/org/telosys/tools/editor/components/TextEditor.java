package org.telosys.tools.editor.components;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.event.DocumentListener;

public class TextEditor extends JFrame {

	private static final long serialVersionUID = 1L;

	private final JFrame    frame ;
	private final JTextArea textArea ;
	private final JLabel    bottomLabel;

	private final DocumentListener documentListener ; 

	private File      file = null ;
	private boolean   textChanged = false ;
	private Charset   charset = StandardCharsets.UTF_8 ;

	
	private void log(String msg) {
		System.out.println("LOG : " + msg);
	}

	public TextEditor(String absoluteFileName) {
		super();
		frame = this ;
		
		setCurrentFile(new File(absoluteFileName));
		
		setSize(600, 600);
		setLocationRelativeTo(null);
		
//		//--- Set LOCALE
//		JFileChooser.setDefaultLocale(Locale.US);

		
		//--- Window closing management
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		// Keep default value : HIDE_ON_CLOSE 
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
		this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){            	
            	log("windowClosing");
            	actionClose();
            }
        });
		
		initFonts();

		//--- Menu bar and Tool bar
		TextEditorMenu menu = new TextEditorMenu(this);
		setJMenuBar(menu.getMenuBar());

		//--- Tool bar for icons, etc		
//		JToolBar toolBar = new JToolBar();
//		pane.add(toolBar, BorderLayout.SOUTH);
		
		//--- Content 
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		
		//--- Text area for edition
		textArea = new JTextArea(); // textarea
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);

		JScrollPane scrollPane = new JScrollPane(textArea); // scrollpane and add textarea to scrollpane

		container.add(scrollPane, BorderLayout.CENTER);
		
		//--- Bottom label 
		bottomLabel = new JLabel("Bottom label ");
		JPanel panel = new JPanel();
		panel.add(bottomLabel);
		this.getContentPane().add(panel, BorderLayout.SOUTH);
		
		//--- The DocumentListener that will be used for each file
		documentListener = new TextEditorListener(this);
		
		//--- Frame icon
		this.setIconImage("icons/telosys_32.png");

		loadFile(file);

		this.setVisible(true);
	}
	@Override
	public void setVisible(final boolean visible) {
//	  // make sure that frame is marked as not disposed if it is asked to be visible
//	  if (visible) {
//	      super.setDisposed(false);
//	  }
	  
	  // let's handle visibility...
	  if (!visible || !isVisible()) { // have to check this condition simply because super.setVisible(true) invokes toFront if frame was already visible
	      super.setVisible(visible);
	  }
	  // ...and bring frame to the front.. in a strange and weird way
	  if (visible) {
		  putOnFront();
	  }
	}

	public void putOnFront() {
	  super.setVisible(true);
	  int state = super.getExtendedState();
	  state &= ~JFrame.ICONIFIED;
	  super.setExtendedState(state);
	  super.setAlwaysOnTop(true);
	  super.toFront();
	  super.requestFocus();
	  super.setAlwaysOnTop(false);
	}
	
	private void initFonts() {
		// Font for Menu and Menu items  
		Font menuFont = new Font("sans-serif", Font.BOLD, 14);
		UIManager.put("Menu.font", menuFont);
		UIManager.put("MenuItem.font", menuFont);
		
		// Font for TextArea  
		//Font textAreaFont = new Font("Courier New", Font.PLAIN, 16); // OK
		Font textAreaFont = new Font(Font.MONOSPACED, Font.PLAIN, 16); // OK
		UIManager.put("TextArea.font", textAreaFont);		
	}
	
	private void setIconImage(String imagePath) {
		//URL url = getClass().getResource(imagePath);
		URL url = getClass().getClassLoader().getResource(imagePath);
		if ( url != null ) {
			ImageIcon imageIcon = new ImageIcon(url);
			this.setIconImage(imageIcon.getImage());
		}
		else {
			log("Cannot get icon '" + imagePath + "'");
		}
	}
	
	private void setCurrentFile(File file) {
		this.file = file;
		resetTitle();
	}
	protected void resetTitle() {
		this.setTitle(file.getAbsolutePath());
	}
	
	protected void textChanged() {
		if ( ! textChanged ) {
			// First change
			this.setTitle("*"+this.getTitle());
		}
		textChanged = true ;
	}
	protected void resetTextChanged() {
		resetTitle();
		textChanged = false ;
	}
	
	protected void actionOpen() {
		JFileChooser fileChooser = createFileChooser("Open file", "Open");
        int returnValue = fileChooser.showOpenDialog(this);		
        if (returnValue == JFileChooser.APPROVE_OPTION) {
    		File selectedFile = fileChooser.getSelectedFile();
    		loadFile(selectedFile);
            setCurrentFile(selectedFile);
        }
	}
	protected void actionLoad() {
		loadFile(file); 
	}
	protected void actionSave() {
		saveFile(file); 
	}
	protected File getCurrentDir() {
		if ( file != null ) {
			return file.getParentFile();
		}
		return null ;
	}
	protected void actionSaveAs() {
		JFileChooser fileChooser = createFileChooser("Save as", "Save");
        int returnValue = fileChooser.showOpenDialog(this);		
        if (returnValue == JFileChooser.APPROVE_OPTION) {
    		File selectedFile = fileChooser.getSelectedFile();
            saveFile(selectedFile);
            setCurrentFile(selectedFile);
        }
	}
	
	private JFileChooser createFileChooser(String title, String buttonText) {
		JFileChooser.setDefaultLocale(Locale.US);
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle(title);
		fileChooser.setApproveButtonText(buttonText);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY );
		fileChooser.setCurrentDirectory(getCurrentDir());
		return fileChooser;
	}
	
	protected void actionClose() {
		if ( JOptionPane.showConfirmDialog(null, "Fermer ?", "Title", JOptionPane.YES_NO_OPTION ) == 0 ) {
			frame.dispose();
		}
	}
	protected void actionPaste() {
		textArea.paste();
	}
	protected void actionCut() {
		textArea.cut();
	}
	protected void actionCopy() {
		textArea.copy();
	}
	protected void actionSelectAll() {
		textArea.selectAll();
	}
	
	private void showError(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	private void loadFile(File file) {
		if ( file == null ) {
			showError("File is null");
			return ;
		}
		if ( ! file.exists() ) {
			showError("File not found\n" + file.getAbsolutePath());
			return ;
		}
		if ( ! file.isFile() ) {
			showError("Not a file\n"  + file.getAbsolutePath());
			return ;
		}
		// File is OK : read it
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
			//textArea.read(new FileReader(file), null);
			textArea.read(in, null);
			resetTextChanged();
			// set the document listener here (new file => new document)
			// textArea.getDocument().addDocumentListener( new TextEditorListener(this) ); // use a single instance
			textArea.getDocument().addDocumentListener( documentListener ); 
			
		} catch (IOException e) {
			//e.printStackTrace();
			showError("Cannot load file (IOException) \n" + file.getAbsolutePath());
		}
	}
	
	private void saveFile(File file) {
		if ( file == null ) {
			showError("File is null");
			return ;
		}
        try {
            //BufferedWriter outFile = new BufferedWriter(new FileWriter(file));
        	BufferedWriter outFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset));
            outFile.write(textArea.getText());
            outFile.close();
			resetTextChanged();
        } catch (IOException ex) {
			showError("Cannot save file (IOException) \n" + file.getAbsolutePath());
        }		
	}
	
}
