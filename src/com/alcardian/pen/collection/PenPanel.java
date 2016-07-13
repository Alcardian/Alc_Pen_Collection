package com.alcardian.pen.collection;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 * GUI for the application.
 * @author Alcardian
 *
 */
@SuppressWarnings("serial")
public class PenPanel extends JPanel{
	private JTextArea textArea = new JTextArea(17,10);	//Used to hold text that will display wanted information.
	private JScrollPane scrollPane = new JScrollPane(textArea);	//Used as a warper around textArea to make it scroll-able When filled with lots of information.
	private JTextField textField = new JTextField();
	private JLabel statusLabel = new JLabel("---");
	//private JTextArea statusArea = new JTextArea(2,9);
	//private JScrollPane statusScrollPane = new JScrollPane(statusArea);
	private JButton searchButton = new JButton("Search");
	private JButton addButton = new JButton("Add Pen");
	private JButton deleteButton = new JButton("Delete Pen");
	
	private JPanel panel = new JPanel();
	
	
	public PenPanel(){
		setLayout(new GridBagLayout());	//Layout manager.
		GridBagConstraints c = new GridBagConstraints();	//Needed to place components within the panel properly.
		
		textArea.setEditable(false);
		displayPen();
		
		textField.setMinimumSize(new Dimension(100,25));
		textField.setMaximumSize(textField.getMinimumSize());
		textField.setPreferredSize(textField.getMinimumSize());
		searchButton.setMinimumSize(textField.getMinimumSize());
		searchButton.setMaximumSize(textField.getMinimumSize());
		searchButton.setPreferredSize(textField.getMinimumSize());
		
		addButton.setMinimumSize(new Dimension(100,25));
		addButton.setMaximumSize(addButton.getMinimumSize());
		addButton.setPreferredSize(addButton.getMinimumSize());
		deleteButton.setMinimumSize(addButton.getMinimumSize());
		deleteButton.setMaximumSize(addButton.getMinimumSize());
		deleteButton.setPreferredSize(addButton.getMinimumSize());
		
		statusLabel.setMinimumSize(new Dimension(100,25));
		statusLabel.setMaximumSize(statusLabel.getMinimumSize());
		statusLabel.setPreferredSize(statusLabel.getMinimumSize());
		
		c.gridheight = 5;
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 0;	//Sets X coordinate.
		c.gridy = 0;	//Sets Y coordinate.
		add(scrollPane, c);	//Adds scrollPane to the Panel.
		
		c.insets = new Insets(0,25,0,0);	//A bit of distance on the X angle between components
		c.gridheight = 1;
		c.fill = GridBagConstraints.NONE;
		c.gridx = 1;
		c.gridy = 0;
		add(new JLabel("Looking for Pen:"), c);
		
		c.gridx = 1;
		c.gridy = 1;
		add(textField, c);
		
		c.gridx = 1;
		c.gridy = 2;
		add(searchButton, c);
		
		c.anchor = GridBagConstraints.SOUTH;
		c.gridx = 1;
		c.gridy = 3;
		add(statusLabel, c);
		//add(new JScrollPane(statusArea), c);
		
		panel.setLayout(new GridBagLayout());
		
		c.insets = new Insets(5,0,0,0);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(deleteButton, c);
		
		c.gridx = 0;
		c.gridy = 1;
		panel.add(addButton, c);
		
		c.insets = new Insets(0,25,0,0);
		c.anchor = GridBagConstraints.SOUTH;
		c.gridx = 1;
		c.gridy = 4;
		add(panel, c);
		
		ButtonListener bListener = new ButtonListener();	//Adds an ActionListener.
		searchButton.addActionListener(bListener);	//Adds the ActionListener to the button.
		addButton.addActionListener(bListener);
		deleteButton.addActionListener(bListener);
	}
	
	
	/**
	 * Function to display the pens in the textArea.
	 */
	public void displayPen(){
		if(Main.pens.isEmpty()){
			// End of Line symbol \n
			textArea.setText("No Pens in list");
			}else{
				textArea.setText("");
				for(int a=0; a<Main.pens.size(); a++){
					textArea.setText(textArea.getText() + (a+1) + ": " + Main.pens.get(a) + "\n");
				}
			}
	}
	
	public void addPen(){
		String ans;
		ans = JOptionPane.showInputDialog(null, "Pen Name?");
		if(ans == null || ans == "" || ans.length() == 0){
			JOptionPane.showMessageDialog(null, "NULL or emtyp String");
		}else{
			Main.addPen(ans);
			displayPen();
		}
		
	}
	
	public void deletePen(){
		String ans;
		ans = JOptionPane.showInputDialog(null, "Pen number?");
		if(ans == null || ans == "" || ans.length() == 0){
			JOptionPane.showMessageDialog(null, "NULL or emtyp String");
		}else{
			if(AlcardianCheck.isNumber(ans, false)){
				int temp  = Integer.parseInt(ans)-1;
				if(temp < Main.pens.size()){
					Main.deletePen(temp);
					displayPen();
				}else{
					JOptionPane.showMessageDialog(null, "Not a index number!");
				}
			}else{
				JOptionPane.showMessageDialog(null, "Not a number!");
			}
		}
	}
	
	public void searchForPen(){
		String temp1 = textField.getText();
		String temp2;
		if(temp1 == null || temp1.length() == 0){
			JOptionPane.showMessageDialog(null, "Bad search, either NULL or no text");
		}else{
			temp2 = Main.findPen(temp1);
			if(temp2.length() == 0){
				statusLabel.setText(temp1 + " not in list");
				//statusArea.setText(temp1 + "\n" + " not in list");
			}else{
				statusLabel.setText(temp1 + " at: " + temp2);
				//statusArea.setText(temp1 + "\n" + " at: " + temp2);
			}
		}
	}
	
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == searchButton){
				searchForPen();
			}else if(e.getSource() == addButton){
				addPen();
			}else if(e.getSource() == deleteButton){
				deletePen();
			}
		}
	}
}
