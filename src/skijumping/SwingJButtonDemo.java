package skijumping;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

/**
 * This program demonstrates common programming practices for using JButton
 * in Swing.
 * @author www.codejava.net
 *
 */
public class SwingJButtonDemo extends JFrame implements ActionListener {

	public SwingJButtonDemo() {
		super("Demo program for JButton");
		setLayout(new FlowLayout());
		
		// a default button
		JButton button1 = new JButton("Edit");
		add(button1);
		
		// a button with only an icon in file system
		JButton button2 = new JButton(new ImageIcon("images/start.gif"));
		add(button2);
		
		// a button with an icon inside jar file/classpath
		String iconPath = "/net/codejava/swing/jbutton/stop.jpg";
		Icon icon = new ImageIcon(getClass().getResource(iconPath));
		JButton button3 = new JButton(icon);
		add(button3);
		
		// a button with both text and icon
		JButton button4 = new JButton("Start", new ImageIcon("images/start.gif"));
		add(button4);
		
		// add event listener - shortcut
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				JOptionPane.showMessageDialog(SwingJButtonDemo.this, "Button #1 is clicked!");
			}
		});
		
		// add event listener - recommended way
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				// delegate to event handler method
				buttonActionPerformed(evt);
			}
		});
		
		// this button's event listener is this JFrame (not recommended)
		button3.addActionListener(this);
		
		// set mnemonic
		button1.setMnemonic(KeyEvent.VK_E);
		
		// set hot key
		String mapKey = "KEY_F2";
		InputMap inputMap = button2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke("F2"), mapKey);
		button2.getActionMap().put(mapKey, new AbstractAction() {
			public void actionPerformed(ActionEvent evt) {
				buttonActionPerformed(evt);
			}
		});		
		
		// set default button
		getRootPane().setDefaultButton(button3);
		
		// customize button's appearance
		button1.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
		button1.setBackground(Color.YELLOW);
		button1.setForeground(Color.BLUE);
	
		button1.setText("<html><color=blue><b>Edit</b></font></html>");
		
		setSize(300, 100);		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	private void buttonActionPerformed(ActionEvent evt) {
		JOptionPane.showMessageDialog(SwingJButtonDemo.this, "Button #2 is clicked!");
	}
	
	/*public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new SwingJButtonDemo().setVisible(true);
			}
		});
	}*/

	@Override
	public void actionPerformed(ActionEvent evt) {
		JOptionPane.showMessageDialog(SwingJButtonDemo.this, "Button #3 is clicked!");		
	}
}