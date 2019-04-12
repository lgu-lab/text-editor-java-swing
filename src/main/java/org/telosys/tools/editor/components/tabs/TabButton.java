package org.telosys.tools.editor.components.tabs;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicButtonUI;

import org.telosys.tools.editor.components.TextEditor;
import org.telosys.tools.editor.components.textarea.TxScrollPane;

/**
 * Tab "X" button used to close the current tab
 * @author laguerin
 *
 */
public class TabButton extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private final static MouseListener buttonMouseListener = new TabButtonMouseListener() ;

//	private final JTabbedPane pane;
	
	private final TextEditor   textEditor ;
	private final TxScrollPane scrollPane ;

//	public TabButton(JTabbedPane pane) {
	public TabButton(TextEditor textEditor, TxScrollPane scrollPane) {

//		this.pane = pane;
		this.textEditor = textEditor;
		this.scrollPane = scrollPane;

		int size = 17;
		setPreferredSize(new Dimension(size, size));
		setToolTipText("close this tab");
		// Make the button looks the same for all Laf's
		setUI(new BasicButtonUI());
		// Make it transparent
		setContentAreaFilled(false);
		// No need to be focusable
		setFocusable(false);
		setBorder(BorderFactory.createEtchedBorder());
		setBorderPainted(false);
		// Making nice rollover effect
		// we use the same listener for all buttons
		addMouseListener(buttonMouseListener);
		setRolloverEnabled(true);
		// Close the proper tab by clicking the button
		addActionListener(this);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		// Close the current tab 
//		JOptionPane.showMessageDialog(null, "CLOSE TAB", "", JOptionPane.INFORMATION_MESSAGE);
//		int i = pane.indexOfTabComponent(TabButton.this);
//		if (i != -1) {
//			pane.remove(i);
//		}
		this.textEditor.closeTab(scrollPane);
	}

	/* (non-Javadoc)
	 * @see javax.swing.JButton#updateUI()
	 */
	public void updateUI() {
		// Nothing to do, don't want to update UI for this button
	}

	// paint the cross
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g.create();
		// shift the image for pressed buttons
		if (getModel().isPressed()) {
			g2.translate(1, 1);
		}
		g2.setStroke(new BasicStroke(2));
		g2.setColor(Color.BLACK);
		if (getModel().isRollover()) {
			g2.setColor(Color.MAGENTA);
		}
		int delta = 6;
		g2.drawLine(delta, delta, getWidth() - delta - 1, getHeight() - delta - 1);
		g2.drawLine(getWidth() - delta - 1, delta, delta, getHeight() - delta - 1);
		g2.dispose();
	}

//	private final static MouseListener buttonMouseListener = new MouseAdapter() {
//		public void mouseEntered(MouseEvent e) {
//			Component component = e.getComponent();
//			if (component instanceof AbstractButton) {
//				AbstractButton button = (AbstractButton) component;
//				button.setBorderPainted(true);
//			}
//		}
//
//		public void mouseExited(MouseEvent e) {
//			Component component = e.getComponent();
//			if (component instanceof AbstractButton) {
//				AbstractButton button = (AbstractButton) component;
//				button.setBorderPainted(false);
//			}
//		}
//	};

}
