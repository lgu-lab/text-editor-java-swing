package org.telosys.tools.editor.components.tabs;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.telosys.tools.editor.components.TextEditor;
import org.telosys.tools.editor.components.TxScrollPane;

/**
 * Component to be used as tabComponent;
 * Contains a JLabel to show the text and 
 * a JButton to close the tab it belongs to 
 */ 
/**
 * Tab component 
 * @author laguerin
 *
 */
public class ButtonTabComponent extends JPanel {
	
	private static final long serialVersionUID = 1L;

//	private final JTabbedPane pane;
//	private final TxScrollPane scrollPane;
	
//    public ButtonTabComponent(final JTabbedPane pane) {
	public ButtonTabComponent(TextEditor textEditor, TxScrollPane scrollPane) {
    	
    	
        //unset default FlowLayout' gaps
        super(new FlowLayout(FlowLayout.LEFT, 0, 0));
        
//        this.scrollPane = scrollPane ;
        
//        if (pane == null) {
//            throw new NullPointerException("TabbedPane is null");
//        }
        
//        this.pane = pane;
        setOpaque(false);
        
        //----- Add tab text ( JLabel )
        //make JLabel read titles from JTabbedPane
//        JLabel label = new JLabel() {
////            public String getText() {
////                int i = pane.indexOfTabComponent(ButtonTabComponent.this);
////                if (i != -1) {
////                    return pane.getTitleAt(i);
////                }
////                return null;
////            }
//          public String getText() {
//        	return scrollPane.getTitle();
//          }
//        };
        
        JLabel label = new TabLabel(scrollPane);
        
        add(label);
        //add more space between the label and the button
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        
        //----- Add tab close button ( JButton )
//        JButton button = new TabButton();
//        JButton button = new TabButton(pane);
        JButton button = new TabButton(textEditor, scrollPane);
        
        add(button);
        
        //add more space to the top of the component
        setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
    }

}


