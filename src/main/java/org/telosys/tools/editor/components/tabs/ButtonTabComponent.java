package org.telosys.tools.editor.components.tabs;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.telosys.tools.editor.components.TextEditor;
import org.telosys.tools.editor.components.textarea.TxScrollPane;

/**
 * This component is design to be used as a "tab component"
 * It contains the components to be printed in the TAB :
 * a JLabel for the text and the file icon 
 * a JButton for the 'close' button (to close the tab it belongs to )
 *
 * @author laguerin
 *
 */
public class ButtonTabComponent extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public ButtonTabComponent(TextEditor textEditor, TxScrollPane scrollPane, Icon icon) {

        //unset default FlowLayout' gaps
        super(new FlowLayout(FlowLayout.LEFT, 0, 0));
        
//        if (pane == null) {
//            throw new NullPointerException("TabbedPane is null");
//        }
        
        setOpaque(false);
      
        //----- Add tab text ( JLabel ) with icon if any
        JLabel label = new TabLabel(scrollPane);
        if ( icon != null ) {
            label.setIcon(icon);
        }
        add(label);
        //add more space between the label and the button
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        
        //----- Add tab close button ( JButton )
        JButton button = new TabButton(textEditor, scrollPane);
        add(button);
        
        //add more space to the top of the component
        setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
    }

}


