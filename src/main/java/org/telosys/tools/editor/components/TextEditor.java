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

public class TextEditor extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private final FileManager  fileManager = new FileManager();

	private final JFrame    frame ;
//	private final JTextArea textArea ;
	private final JTabbedPane tabbedPane;
//	private final ArrayList<TextEditorPane> documents = new ArrayList<TextEditorPane>();

	private final File currentDir ;

	private final JLabel    bottomLabel;

	private final DocumentListener documentListener ; 

//	private File      file = null ;
//	private boolean   textChanged = false ;
//	private Charset   charset = StandardCharsets.UTF_8 ;

	
	private void log(String msg) {
		System.out.println("LOG : " + msg);
	}

//	public TextEditor(String absoluteFileName) {
	public TextEditor(File absoluteDirPath) {
		super();
		frame = this ;
		
		//setCurrentFile(new File(absoluteFileName));
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
		
//		//--- Content 
//		Container container = getContentPane();
//		container.setLayout(new BorderLayout());
//		
//		//--- Text area for edition
//		textArea = new JTextArea(); // textarea
//		textArea.setLineWrap(true);
//		textArea.setWrapStyleWord(true);
//
//		JScrollPane scrollPane = new JScrollPane(textArea); // scrollpane and add textarea to scrollpane
//
//		container.add(scrollPane, BorderLayout.CENTER);

		
		//--- Central panel with tabs
		JPanel centralPanel = new JPanel(new BorderLayout());
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		//tabbedPane.addChangeListener(new TabListener());
		// centralPanel.add(toolBar, BorderLayout.NORTH);
	    tabbedPane.addChangeListener(new ChangeListener() {
	        public void stateChanged(ChangeEvent e) {
	            //System.out.println("Tab: " + tabbedPane.getSelectedIndex());
	    		TxScrollPane scrollPane = (TxScrollPane) tabbedPane.getSelectedComponent();
	    		bottomLabel.setText(scrollPane.getFile().getAbsolutePath());
//	    		bottomLabel.setText("selected index : " + tabbedPane.getSelectedIndex());	            
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

//		loadFile(file);

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
	
//	private void tabExamples() {
//		
//		String s = tabbedPane.getTitleAt(2);
//		
//		Component c = tabbedPane.getComponentAt(2);
//		
//		// select the last tab
//        tabbedPane.setSelectedIndex(tabbedPane.getTabCount()-1);
//        
//        
//		int selectedIndex = tabbedPane.getSelectedIndex();
//	}

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
		if ( JOptionPane.showConfirmDialog(null, "Fermer ?", "Title", JOptionPane.YES_NO_OPTION ) == 0 ) {
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
