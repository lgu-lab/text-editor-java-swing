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

/**
 * Text editor main frame ( JFrame specialization )
 * 
 * @author Laurent GUERIN
 *
 */
public class TextEditor extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private static String HOME = "" ;

	private final FileManager fileManager = new FileManager();

	private final File currentDir;

	private final JFrame frame;
	private final JTabbedPane tabbedPane;
	private final JLabel bottomLabel;

	// private void log(String msg) {
	// System.out.println("LOG : " + msg);
	// }

	public static String getHome() {
		return HOME ;
	}
	
	public TextEditor(File home) {
		super();
		frame = this;

		HOME = home.getAbsolutePath();
		currentDir = home;

		setSize(600, 600);
		setLocationRelativeTo(null);

		// //--- Set LOCALE
		// JFileChooser.setDefaultLocale(Locale.US);

		// --- Window closing management
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Keep default value : HIDE_ON_CLOSE
		// setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// log("windowClosing");
				actionExit();
			}
		});

		initFonts();

		// --- Menu bar and Tool bar
		TextEditorMenu menu = new TextEditorMenu(this);
		setJMenuBar(menu.getMenuBar());

		// --- Tool bar for icons, etc
		// JToolBar toolBar = new JToolBar();
		// pane.add(toolBar, BorderLayout.SOUTH);

		// --- Central panel with tabs
		JPanel centralPanel = new JPanel(new BorderLayout());
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		// tabbedPane.addChangeListener(new TabListener());
		// centralPanel.add(toolBar, BorderLayout.NORTH);
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// System.out.println("Tab: " + tabbedPane.getSelectedIndex());
				TxScrollPane scrollPane = (TxScrollPane) tabbedPane.getSelectedComponent();
				if (scrollPane != null) {
					bottomLabel.setText(scrollPane.getFile().getAbsolutePath());
				} else {
					bottomLabel.setText("");
				}
			}
		});
		centralPanel.add(tabbedPane, BorderLayout.CENTER);
		this.add(centralPanel, BorderLayout.CENTER);

		// --- Bottom label
		bottomLabel = new JLabel("Bottom label ");
		JPanel panel = new JPanel();
		panel.add(bottomLabel);
		this.getContentPane().add(panel, BorderLayout.SOUTH);

		// --- The DocumentListener that will be used for each file
		// documentListener = new TextEditorListener(this);

		// --- Frame icon
		this.setIconImage("icons/telosys_32.png");
		this.setTitle("Telosys Editor");

		this.setVisible(true);
	}

	@Override
	public void setVisible(final boolean visible) {
		// // make sure that frame is marked as not disposed if it is asked to be
		// visible
		// if (visible) {
		// super.setDisposed(false);
		// }

		// let's handle visibility...
		if (!visible || !isVisible()) { // have to check this condition simply because super.setVisible(true) invokes
										// toFront if frame was already visible
			super.setVisible(visible);
		}
		// ...and bring frame to the front.. in a strange and weird way
		if (visible) {
			putOnFront();
		}
	}

	/**
	 * Returns the index of the tab containing the given file (or -1 if none)
	 * 
	 * @param file
	 * @return
	 */
	public int getFileTabIndex(File file) {
		for (int i = 0; i < tabbedPane.getTabCount(); i++) {
			Component c = tabbedPane.getComponentAt(i);
			bottomLabel.setText(i + " : Component class " + c.getClass().getSimpleName());
			if (c instanceof TxScrollPane) {
				TxScrollPane scrollPane = (TxScrollPane) c;
				if (file.getAbsolutePath().equals(scrollPane.getFile().getAbsolutePath())) {
					// File found (this file is already loaded in tab 'i')
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * Edit the given file <br>
	 * Create a new tab if the file is not already loaded or select the tab
	 * containing the file
	 * 
	 * @param file
	 */
	public void editFile(File file) {

		int tabIndex = getFileTabIndex(file);

		if (tabIndex >= 0) {
			// Already open in a Tab => select this Tab
			tabbedPane.setSelectedIndex(tabIndex);
		} else {
			// Not yet open => load it in a new Tab
			String text = fileManager.readTextFromFile(file);
			if (text != null) {

				TxTextArea textArea = new TxTextArea(text);

				// Creates a JScrollPane that displays the contents of the specified component,
				// where both horizontal and vertical scrollbars appear whenever the component's
				// contents are larger than the view.
				TxScrollPane scrollPane = new TxScrollPane(textArea, file);

				// Add the new tab in the "TabbedPane" component
				tabbedPane.add(scrollPane.getTitle(), scrollPane);
				// Component tab = tabbedPane.add(scrollPane.getTitle(), scrollPane);
				// log("New tab. Class = " + tab.getClass());

				int newTabIndex = tabbedPane.getTabCount() - 1;
				textArea.setDocumentListener(new TxDocumentListener(tabbedPane, newTabIndex));
				tabbedPane.setSelectedIndex(newTabIndex);

			}
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
		// Font textAreaFont = new Font("Courier New", Font.PLAIN, 16); // OK
		Font textAreaFont = new Font(Font.MONOSPACED, Font.PLAIN, 16); // OK
		UIManager.put("TextArea.font", textAreaFont);
	}

	private void setIconImage(String imagePath) {
		URL url = getClass().getClassLoader().getResource(imagePath);
		if (url != null) {
			ImageIcon imageIcon = new ImageIcon(url);
			this.setIconImage(imageIcon.getImage());
		}
		// else {
		// log("Cannot get icon '" + imagePath + "'");
		// }
	}

	protected void actionOpen() {
		JFileChooser fileChooser = createFileChooser("Open file", "Open");
		int returnValue = fileChooser.showOpenDialog(this);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			editFile(selectedFile);
		}
	}

	protected void actionSave() {
		// tabbedPane.getSelectedIndex();
		TxScrollPane scrollPane = (TxScrollPane) tabbedPane.getSelectedComponent();
		save(scrollPane);
	}

	protected void actionSaveAs() {
		TxScrollPane scrollPane = (TxScrollPane) tabbedPane.getSelectedComponent();
		int tabIndex = tabbedPane.getSelectedIndex();
		JFileChooser fileChooser = createFileChooser("Save as", "Save");
		int returnValue = fileChooser.showOpenDialog(this);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			if (selectedFile != null) {
				fileManager.saveTextToFile(scrollPane.getText(), selectedFile);
				scrollPane.reset(selectedFile);
				tabbedPane.setTitleAt(tabIndex, scrollPane.getTitle());
				bottomLabel.setText(scrollPane.getFile().getAbsolutePath());
			}
		}
	}

	private JFileChooser createFileChooser(String title, String buttonText) {
		JFileChooser.setDefaultLocale(Locale.US);
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle(title);
		fileChooser.setApproveButtonText(buttonText);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setCurrentDirectory(currentDir);
		return fileChooser;
	}

	/**
	 * Saves the file associated with the given ScrollPane
	 * 
	 * @param scrollPane
	 */
	protected void save(TxScrollPane scrollPane) {
		if (scrollPane != null) {
			fileManager.saveTextToFile(scrollPane.getText(), scrollPane.getFile());
			scrollPane.reset();
		}
	}

	/**
	 * Close all tabs (with confirmation if some texts have been modified)
	 */
	protected boolean closeAllTabs() {
		int n = tabbedPane.getComponentCount();
		for (int i = n - 1; i >= 0; i--) {
			boolean r = closeTab((TxScrollPane) tabbedPane.getComponentAt(i));
			if (!r) {
				// Cancel
				return false;
			}
		}
		return true; // All closed
	}

	/**
	 * Close the given ScrollPane (with confirmation if the text has been modified)
	 * 
	 * @param scrollPane
	 */
	protected boolean closeTab(TxScrollPane scrollPane) {
		if (scrollPane != null && scrollPane.isModified()) {
			// The text has been modified (and not yet saved) => Confirmation
			String msg = "'" + scrollPane.getFile().getName() + "' has been modified.  Save changes ?";
			Object[] options = { "Save", "Don't Save", "Cancel" };
			int choice = JOptionPane.showOptionDialog(null, msg, "Close file ", JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, // do not use a custom Icon
					options, // the titles of buttons
					options[0]); // default button title
			if (choice == 0) {
				// Save and close
				save(scrollPane);
				tabbedPane.remove(scrollPane);
				return true;
			} else if (choice == 1) {
				// Don't Save and close
				tabbedPane.remove(scrollPane);
				return true;
			} else {
				// Cancel
				return false;
			}
		} else {
			// Text not modified => Close directly
			tabbedPane.remove(scrollPane);
			return true;
		}
	}

	protected void actionClose() {
		closeTab((TxScrollPane) tabbedPane.getSelectedComponent());
	}

	protected void actionCloseAll() {
		closeAllTabs();
	}

	/**
	 * Exit editor
	 */
	protected void actionExit() {
		if (closeAllTabs()) {
			// Ok, everything closed
			frame.dispose();
		}
	}

	protected void actionPaste() {
		TxScrollPane scrollPane = (TxScrollPane) tabbedPane.getSelectedComponent();
		if (scrollPane != null) {
			scrollPane.getTextArea().paste();
		}
	}

	protected void actionCut() {
		TxScrollPane scrollPane = (TxScrollPane) tabbedPane.getSelectedComponent();
		if (scrollPane != null) {
			scrollPane.getTextArea().cut();
		}
	}

	protected void actionCopy() {
		TxScrollPane scrollPane = (TxScrollPane) tabbedPane.getSelectedComponent();
		if (scrollPane != null) {
			scrollPane.getTextArea().copy();
		}
	}

	protected void actionSelectAll() {
		TxScrollPane scrollPane = (TxScrollPane) tabbedPane.getSelectedComponent();
		if (scrollPane != null) {
			scrollPane.getTextArea().selectAll();
		}
	}

}
