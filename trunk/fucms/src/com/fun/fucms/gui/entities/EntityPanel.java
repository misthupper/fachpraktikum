package com.fun.fucms.gui.entities;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.fun.fucms.model.Entity;

public class EntityPanel extends JPanel implements ActionListener {
	
	private static final String BUTTON_OK_TEXT = "OK";
	private static final String BUTTON_CANEL_TEXT = "Cancel";
	
	private Box mFieldBox, mButtonBox;
	private ArrayList<JTextField> mTextFields = new ArrayList<JTextField>();
	private JButton mSaveButton,mCancelButton;
	
	public EntityPanel(Entity e) {
		super();
		this.setLayout(new BorderLayout());
		mFieldBox = new Box(BoxLayout.PAGE_AXIS);
		mButtonBox = new Box(BoxLayout.LINE_AXIS);
		this.add(mFieldBox,BorderLayout.NORTH);
		this.add(mButtonBox,BorderLayout.SOUTH);
		
		for (int i = 0 ; i < e.getFields().length ; i++) {
			Box box = new Box(BoxLayout.LINE_AXIS);
			mFieldBox.add(box);
			box.add(new JLabel(e.getFields()[i] + " : "));
			JTextField jtextField = new JTextField();
			jtextField.setName(e.getFields()[i]);
			jtextField.setText(e.getValueAsString(i));
			box.add(jtextField);
			mTextFields.add(jtextField);
		}
	    
	    mSaveButton = new JButton(BUTTON_OK_TEXT);
	    mSaveButton.addActionListener(this);
	    //mSaveButton.setActionCommand(EmployeeController.ACTION_OK);
	    mCancelButton = new JButton(BUTTON_CANEL_TEXT);
	    mCancelButton.addActionListener(this);
	    //mCancelButton.setActionCommand(EmployeeController.ACTION_CANCEL);
	    mButtonBox.add(mCancelButton);
	    mButtonBox.add(Box.createHorizontalGlue());
	    mButtonBox.add(mSaveButton);
	
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	


}
