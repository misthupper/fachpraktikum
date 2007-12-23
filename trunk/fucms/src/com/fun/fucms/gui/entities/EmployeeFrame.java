package com.fun.fucms.gui.entities;

import java.awt.Container;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.fun.fucms.controller.EmployeeController;

public class EmployeeFrame extends JFrame {
	
	private static final String ID_LABEL_TEXT = "ID: ";
	private static final String SURNAME_LABEL_TEXT = "Surname: ";
	private static final String FIRSTNAME_LABEL_TEXT = "Firstname: ";
	private static final String BUTTON_OK_TEXT = "OK";
	private static final String BUTTON_CANEL_TEXT = "Cancel";
	
	public Container mContainer;
	
	private JLabel mIdLabel, mSNameLabel, mFNameLabel;
	private JTextField mIdField, mSNameField, mFNameField;
	private Box mIdBox, mSNameBox, mFNameBox, mButtonBox;
	private JButton mSaveButton, mCancelButton;
	
	private EmployeeController mEmployeeController;
	
	
	public EmployeeFrame() {
		super();
		init();
	}
	
	public void init() {
		
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        mEmployeeController = new EmployeeController(this);
        
        //content Pane
        mContainer=this.getContentPane();
        mContainer.setLayout(new BoxLayout(mContainer,BoxLayout.PAGE_AXIS));
        
        mIdLabel = new JLabel(ID_LABEL_TEXT);
        mIdField = new JTextField();
        mIdBox = new Box(BoxLayout.LINE_AXIS);
        mIdBox.add(mIdLabel);
        mIdBox.add(mIdField);
        
        mSNameLabel = new JLabel(SURNAME_LABEL_TEXT);
        mSNameField = new JTextField();
        mSNameBox = new Box(BoxLayout.LINE_AXIS);
        mSNameBox.add(mSNameLabel);
        mSNameBox.add(mSNameField);
        
        mFNameLabel = new JLabel(FIRSTNAME_LABEL_TEXT);
        mFNameField = new JTextField();
        mFNameBox = new Box(BoxLayout.LINE_AXIS);
        mFNameBox.add(mFNameLabel);
        mFNameBox.add(mFNameField);
        
        mSaveButton = new JButton(BUTTON_OK_TEXT);
        mSaveButton.addActionListener(mEmployeeController);
        mSaveButton.setActionCommand(EmployeeController.ACTION_OK);
        mCancelButton = new JButton(BUTTON_CANEL_TEXT);
        mCancelButton.addActionListener(mEmployeeController);
        mCancelButton.setActionCommand(EmployeeController.ACTION_CANCEL);
        mButtonBox = new Box(BoxLayout.LINE_AXIS);
        mButtonBox.add(mCancelButton);
        mButtonBox.add(Box.createHorizontalGlue());
        mButtonBox.add(mSaveButton);

        mContainer.add(mIdBox);
        mContainer.add(mSNameBox);
        mContainer.add(mFNameBox);
        mContainer.add(mButtonBox);
        
      

        pack();
        setVisible(true);

	}

}
