package texteditor;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

//public class TextEditor extends JFrame implements ActionListener {
public class TextEditor_OLD extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTextArea textArea ;
	
//	private JMenuBar menuBar;
//	private JMenu fileMenu, editMenu, viewMenu;
//	private JScrollPane scrollPane;
//	private JMenuItem exitI, cutI, copyI, pasteI, selectI, saveI, loadI, statusI;

	//private int count;
	
	private String pad = "" ;
	//private JToolBar toolBar;

	public TextEditor_OLD(String title) {
		super(title); // Window title
		setSize(600, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container pane = getContentPane();
		pane.setLayout(new BorderLayout());

		// Font for Menu and Menu items  
		Font font = new Font("sans-serif", Font.BOLD, 14);
		UIManager.put("Menu.font", font);
		UIManager.put("MenuItem.font", font);
		
		// Font for TextArea  
		//Font font2 = new Font("Courier New", Font.PLAIN, 16); // OK
		Font font2 = new Font(Font.MONOSPACED, Font.PLAIN, 16); // OK
		UIManager.put("TextArea.font", font2);

		//--- Menu bar and Tool bar
		setJMenuBar(createMenuBar());

		// Tool bar for icons, etc		
//		JToolBar toolBar = new JToolBar();
//		pane.add(toolBar, BorderLayout.SOUTH);
		
		
		//count = 0;
		// pad = "";

		//--- Text area for edition
		textArea = new JTextArea(); // textarea
		

		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);

		JScrollPane scrollPane = new JScrollPane(textArea); // scrollpane and add textarea to scrollpane

		pane.add(scrollPane, BorderLayout.CENTER);



		setVisible(true);
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar;
		JMenu fileMenu, editMenu, viewMenu;
		JMenuItem exitI, cutI, copyI, pasteI, selectI, saveI, loadI, statusI;

		menuBar = new JMenuBar(); // menubar
		fileMenu = new JMenu("File"); // file menu
		editMenu = new JMenu("Edit"); // edit menu
		viewMenu = new JMenu("View"); // edit menu

		exitI = new JMenuItem("Exit");
		cutI = new JMenuItem("Cut");
		copyI = new JMenuItem("Copy");
		pasteI = new JMenuItem("Paste");
		selectI = new JMenuItem("Select All"); // menuitems
		saveI = new JMenuItem("Save"); // menuitems
		loadI = new JMenuItem("Load"); // menuitems
		statusI = new JMenuItem("Status"); // menuitems
		
		//toolBar = new JToolBar();

		//setJMenuBar(menuBar);
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(viewMenu);

		fileMenu.add(saveI);
		fileMenu.add(loadI);
		fileMenu.add(exitI);

		editMenu.add(cutI);
		editMenu.add(copyI);
		editMenu.add(pasteI);
		editMenu.add(selectI);

		viewMenu.add(statusI);

		// Menu items accelerators keys
		saveI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		loadI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
		
		cutI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		copyI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		pasteI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		selectI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));

		// Menu items listeners
		// saveI.addActionListener(this);
		//loadI.addActionListener(this);

		//exitI.addActionListener(this);
		exitI.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
		    	System.exit(0);
		      }		
		});
		
		
		copyI.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
		    	pad = textArea.getSelectedText();
		      }		
		});
		
		pasteI.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
				textArea.insert(pad, textArea.getCaretPosition());
		      }		
		});
		
		cutI.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
				pad = textArea.getSelectedText();
				textArea.replaceRange("", textArea.getSelectionStart(), textArea.getSelectionEnd());
		      }		
		});

		selectI.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
		    	textArea.selectAll();
		      }		
		});
		
		//statusI.addActionListener(this);
		
		return menuBar ;
	}
	/**
	public void actionPerformed(ActionEvent e) {
		JMenuItem choice = (JMenuItem) e.getSource();
		if (choice == saveI) {
			// not yet implmented
		} else if (choice == exitI)
			System.exit(0);
		else if (choice == cutI) {
			pad = textArea.getSelectedText();
			textArea.replaceRange("", textArea.getSelectionStart(), textArea.getSelectionEnd());
		} else if (choice == copyI)
			pad = textArea.getSelectedText();
		else if (choice == pasteI)
			textArea.insert(pad, textArea.getCaretPosition());
		else if (choice == selectI)
			textArea.selectAll();
		else if (e.getSource() == statusI) {
			// not yet implmented
		}
	}
	**/
}
