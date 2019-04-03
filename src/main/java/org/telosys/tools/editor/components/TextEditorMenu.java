package org.telosys.tools.editor.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class TextEditorMenu {

	private final TextEditor editor;
	private final JMenuBar menuBar;

	protected TextEditorMenu(TextEditor editor) {

		this.editor = editor;

		menuBar = new JMenuBar();
		menuBar.add(createFileMenu());
		menuBar.add(createEditMenu());
		menuBar.add(createToolsMenu());
		menuBar.add(createHelpMenu());
	}

	protected JMenuBar getMenuBar() {
		return menuBar;
	}

	private JMenu createFileMenu() {
		JMenu menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);

		JMenuItem openMenuItem = new JMenuItem("Open");
		openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK)); // Ctrl-O
		openMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				editor.actionOpen();
			}
		});
		menu.add(openMenuItem);

		JMenuItem reloadMenuItem = new JMenuItem("Reload");
		reloadMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK)); // Ctrl-L
		reloadMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				// editor.actionLoad();
				// TODO
			}
		});
		menu.add(reloadMenuItem);

		JMenuItem saveMenuItem = new JMenuItem("Save");
		saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK)); // Ctrl-S
		saveMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				editor.actionSave();
			}
		});
		menu.add(saveMenuItem);

		JMenuItem saveAsMenuItem = new JMenuItem("Save as");
		saveAsMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				editor.actionSaveAs();
			}
		});
		menu.add(saveAsMenuItem);

		menu.addSeparator();

		JMenuItem closeMenuItem = new JMenuItem("Close");
		closeMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				editor.actionClose();
			}
		});
		menu.add(closeMenuItem);

		menu.addSeparator();

		JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				editor.actionExit();
			}
		});
		menu.add(exitMenuItem);

		return menu;
	}

	private JMenu createEditMenu() {
		JMenu menu = new JMenu("Edit");
		menu.setMnemonic(KeyEvent.VK_E);

		JMenuItem cutI = new JMenuItem("Cut");
		cutI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		cutI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				// pad = textArea.getSelectedText();
				// textArea.replaceRange("", textArea.getSelectionStart(),
				// textArea.getSelectionEnd());
				editor.actionCut();
			}
		});
		menu.add(cutI);

		JMenuItem copyI = new JMenuItem("Copy");
		copyI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		copyI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				// pad = textArea.getSelectedText();
				editor.actionCopy();
			}
		});
		menu.add(copyI);

		JMenuItem pasteI = new JMenuItem("Paste");
		pasteI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		pasteI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				// textArea.insert(pad, textArea.getCaretPosition());
				// getTextArea().paste();
				editor.actionPaste();
			}
		});
		menu.add(pasteI);

		JMenuItem selectI = new JMenuItem("Select All");
		selectI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		selectI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				// getTextArea().selectAll();
				editor.actionSelectAll();
			}
		});
		menu.add(selectI);

		return menu;
	}

	private JMenu createToolsMenu() {
		JMenu menu = new JMenu("Tools");
		menu.setMnemonic(KeyEvent.VK_T);

		JMenuItem menuItem = new JMenuItem("Tool1");
		// cutI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
		// ActionEvent.CTRL_MASK));
		// cutI.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent ev) {
		// //pad = textArea.getSelectedText();
		// //textArea.replaceRange("", textArea.getSelectionStart(),
		// textArea.getSelectionEnd());
		// editor.actionCut();
		// }
		// });
		menu.add(menuItem);

		return menu;
	}

	private JMenu createHelpMenu() {
		JMenu menu = new JMenu("Help");
		menu.setMnemonic(KeyEvent.VK_H);

		JMenuItem menuItem = new JMenuItem("About");
		// menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
		// ActionEvent.CTRL_MASK));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				new AboutDialog();
			}
		});

		menu.add(menuItem);

		return menu;
	}

}
