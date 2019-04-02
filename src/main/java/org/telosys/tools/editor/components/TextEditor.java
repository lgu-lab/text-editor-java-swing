package org.telosys.tools.editor.components;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URL;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentListener;

/**
 * Text editor ( JFrame specialization )
 * 
 * @author laguerin
 *
 */
public class TextEditor extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private final FileManager  fileManager = new FileManager();

	private final File         currentDir ;

	private final JFrame       frame ;
	private final JTabbedPane  tabbedPane;
	private final JLabel       bottomLabel;

	private final DocumentListener documentListener ; 

	private void log(String msg) {
		System.out.println("LOG : " + msg);
	}

	public TextEditor(File absoluteDirPath) {
		super();
		frame = this ;
		
		currentDir = absoluteDirPath ;
		
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
		
		//--- Central panel with tabs
		JPanel centralPanel = new JPanel(new BorderLayout());
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		//tabbedPane.addChangeListener(new TabListener());
		// centralPanel.add(toolBar, BorderLayout.NORTH);
	    tabbedPane.addChangeListener(new ChangeListener() {
	        public void stateChanged(ChangeEvent e) {
	            //System.out.println("Tab: " + tabbedPane.getSelectedIndex());
	    		TxScrollPane scrollPane = (TxScrollPane) tabbedPane.getSelectedComponent();
	    		if ( scrollPane != null ) {
		    		bottomLabel.setText(scrollPane.getFile().getAbsolutePath());
	    		}
	    		else {
		    		bottomLabel.setText("");
	    		}
	        }
	    });		
		centralPanel.add(tabbedPane, BorderLayout.CENTER);
		this.add(centralPanel, BorderLayout.CENTER);
		
		//--- Bottom label 
		bottomLabel = new JLabel("Bottom label ");
		JPanel panel = new JPanel();
		panel.add(bottomLabel);
		this.getContentPane().add(panel, BorderLayout.SOUTH);
		
		//--- The DocumentListener that will be used for each file
		documentListener = new TextEditorListener(this);
		
		//--- Frame icon
		this.setIconImage("icons/telosys_32.png");

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
	
	/**
	 * Returns the index of the tab containing the given file (or -1 if none)
	 * @param file
	 * @return
	 */
	public int getFileTabIndex(File file) {
		for ( int i = 0 ; i < tabbedPane.getTabCount() ; i++ ) {
			Component c = tabbedPane.getComponentAt(i);
			bottomLabel.setText(i + " : Component class " + c.getClass().getSimpleName());
			if ( c instanceof TxScrollPane ) {
				TxScrollPane scrollPane = (TxScrollPane) c ;
				if ( file.getAbsolutePath().equals(scrollPane.getFile().getAbsolutePath()) ) {
					// File found (this file is already loaded in tab 'i')
					return i ;
				}
			}
		}
		return -1 ;
	}
	
	/**
	 * Edit the given file <br>
	 * Create a new tab if the file is not already loaded or select the tab containing the file
	 * @param file
	 */
	public void editFile(File file) {
		
		int tabIndex = getFileTabIndex(file);
		
		if ( tabIndex >= 0 ) {
			// Already open in a Tab => select this Tab
			tabbedPane.setSelectedIndex(tabIndex);
		}
		else {
			// Not yet open => load it in a new Tab
			String text = fileManager.readTextFromFile(file);
			if ( text != null ) {
//				TextEditorPane textEditorTab = new TextEditorPane(file);
//				textEditorTab.setText(text);
////				textEditorTab.setCaretPosition(0);
////				textEditorTab.setPanePath(file.getAbsolutePath());
////				textEditorTab.setPaneName(file.getName());
				
				TxTextArea textArea = new TxTextArea(text);
				
				// Creates a JScrollPane that displays the contents of the specified component, 
				// where both horizontal and vertical scrollbars appear whenever the component's contents are larger than the view.
//				JScrollPane scrollPane = new JScrollPane(textEditorTab);
				//JScrollPane scrollPane = new JScrollPane(textArea);
				TxScrollPane scrollPane = new TxScrollPane(textArea, file.getName(), file);
				
				// Add the new tab in the "TabbedPane" component
//				tabbedPane.add(textEditorTab.getTitle(), scrollPane);
				tabbedPane.add(scrollPane.getTitle(), scrollPane);
				
//				documents.add(textEditorTab);
				
				tabbedPane.setSelectedIndex(tabbedPane.getTabCount()-1);
			}
		}

//		Component c = tabbedPane.getSelectedComponent();
//		TxScrollPane scrollPane = (TxScrollPane) tabbedPane.getSelectedComponent();
//		bottomLabel.setText(scrollPane.getFile().getAbsolutePath());
		
//		TextEditorPane textPane = null;
//		String fileText = null;
//
//		// if (file.getName().toUpperCase().endsWith(".FTF"))
//		// {
//		// textPane = readFTFFile(file);
//		// textPane.setCaretPosition(0);
//		// }
//		// else
//		// {
//		textPane = new TextEditorPane();
//		textPane.setAlreadySaved(true);
//		fileText = readTxtFile(file);
//		if (fileText != null) {
//			textPane.setText(fileText);
//			textPane.setCaretPosition(0);
//			textPane.setPanePath(file.getAbsolutePath());
//			// textPane.setMouseListener(new PopupListener());// Changed #LGU
//			textPane.setMouseListener(new PopupMenuListener(popupMenu));
//		}
//		// }
//
//		if (textPane != null || fileText != null) {
//			JScrollPane scrollPane = new JScrollPane(textPane);
//			tabbedPane.add(file.getName(), scrollPane);
//			documents.add(textPane);
//		} else {
//			JOptionPane.showMessageDialog(null, "Error in the loading of " + file.getName(), "Error",
//					JOptionPane.ERROR_MESSAGE);
//		}
//		
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
	
//	private void setCurrentFile(File file) {
//		this.file = file;
//		resetTitle();
//	}
//	protected void resetTitle() {
//		this.setTitle(file.getAbsolutePath());
//	}
//	
//	protected void textChanged() {
//		if ( ! textChanged ) {
//			// First change
//			this.setTitle("*"+this.getTitle());
//		}
//		textChanged = true ;
//	}
//	protected void resetTextChanged() {
//		resetTitle();
//		textChanged = false ;
//	}
	
	protected void actionOpen() {
		JFileChooser fileChooser = createFileChooser("Open file", "Open");
        int returnValue = fileChooser.showOpenDialog(this);		
        if (returnValue == JFileChooser.APPROVE_OPTION) {
    		File selectedFile = fileChooser.getSelectedFile();
//    		loadFile(selectedFile);
//    		String text = fileManager.readTextFromFile(selectedFile);
//            setCurrentFile(selectedFile);
    		editFile(selectedFile);
        }
	}
//	protected void actionLoad() {
//		loadFile(file); 
//	}
	
	protected void actionSave() {
		tabbedPane.getSelectedIndex();
		//saveFile(file);
		// TODO
		//fileManager.saveToFile(text, file);
	}
	
//	protected File getCurrentDir() {
//		if ( file != null ) {
//			return file.getParentFile();
//		}
//		return null ;
//	}
	protected void actionSaveAs() {
		JFileChooser fileChooser = createFileChooser("Save as", "Save");
        int returnValue = fileChooser.showOpenDialog(this);		
        if (returnValue == JFileChooser.APPROVE_OPTION) {
    		File selectedFile = fileChooser.getSelectedFile();
//            saveFile(selectedFile);
// TODO :
// Get current text 
//    		String text = xxxx ;
//    		fileManager.saveToFile(text, selectedFile);
//            setCurrentFile(selectedFile);
        }
	}
	
	private JFileChooser createFileChooser(String title, String buttonText) {
		JFileChooser.setDefaultLocale(Locale.US);
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle(title);
		fileChooser.setApproveButtonText(buttonText);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY );
//		fileChooser.setCurrentDirectory(getCurrentDir());
		fileChooser.setCurrentDirectory(currentDir);
		return fileChooser;
	}
	
	protected void actionClose() {
		TxScrollPane scrollPane = (TxScrollPane) tabbedPane.getSelectedComponent();
		// scrollPane.modified ?
		tabbedPane.remove(scrollPane);
//		if ( JOptionPane.showConfirmDialog(null, "Close ?", "Title", JOptionPane.YES_NO_OPTION ) == 0 ) {
//			frame.dispose();
//		}
	}
	
	/**
	 * Exit editor
	 */
	protected void actionExit() {
//		if ( JOptionPane.showConfirmDialog(null, "Are you sure you want to exit ?", "Confirm Exit", JOptionPane.YES_NO_OPTION ) == 0 ) {
//			
//		}
		
		Object[] options = { "Exit", "Cancel" };
		int choice = JOptionPane.showOptionDialog(null, 
				"Are you sure you want to exit ?", 
				"Confirm Exit",
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null, //do not use a custom Icon
				options, //the titles of buttons
				options[1] ); //default button title
	    if ( choice == 0 ) {
			frame.dispose();
	    }
	}
	protected void actionPaste() { 
		//textArea.paste();
//		documents.get(tabbedPane.getSelectedIndex()).paste(true);
		// TODO
	}
	protected void actionCut() {
//		textArea.cut();
		// TODO
	}
	protected void actionCopy() {
//		textArea.copy();
		// TODO
	}
	protected void actionSelectAll() {
//		textArea.selectAll();
		// TODO
	}
	
//	private void showError(String msg) {
//		JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
//	}
//	
//	private void loadFile(File file) {
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
//			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
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
//	
//	private void saveFile(File file) {
//		if ( file == null ) {
//			showError("File is null");
//			return ;
//		}
//        try {
//            //BufferedWriter outFile = new BufferedWriter(new FileWriter(file));
//        	BufferedWriter outFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset));
//            outFile.write(textArea.getText());
//            outFile.close();
//			resetTextChanged();
//        } catch (IOException ex) {
//			showError("Cannot save file (IOException) \n" + file.getAbsolutePath());
//        }		
//	}
	
}
