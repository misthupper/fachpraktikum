package com.fun.fucms.gui.entities;

import java.awt.Container;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.fun.fucms.controller.EmployeeController;

public class EmployeeFrame extends JFrame {
	
	private static final String SELECTION_TEXT = "Selection";
	
	private static final String ENTITY_TEXT = "Mitarbeiter";
	private static final String ID_LABEL_TEXT = "ID: ";
	private static final String SURNAME_LABEL_TEXT = "Surname: ";
	private static final String FIRSTNAME_LABEL_TEXT = "Firstname: ";
	private static final String BUTTON_OK_TEXT = "OK";
	private static final String BUTTON_CANEL_TEXT = "Cancel";
	
	private Container mContainer;
	private JTabbedPane mJTabbedPane;
	private JPanel mSelectPanel, mEntityPanel;
	
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
        
        mContainer=this.getContentPane();
        mContainer.setLayout(new BoxLayout(mContainer,BoxLayout.PAGE_AXIS));
        
        mJTabbedPane = new JTabbedPane();
        mSelectPanel = new JPanel();
        mSelectPanel.setLayout(new BoxLayout(mSelectPanel,BoxLayout.PAGE_AXIS));
        mEntityPanel = new JPanel();
        mEntityPanel.setLayout(new BoxLayout(mEntityPanel,BoxLayout.PAGE_AXIS));
        mJTabbedPane.add(SELECTION_TEXT,mSelectPanel);
        mJTabbedPane.add(ENTITY_TEXT,mEntityPanel);
        
        mEmployeeController = new EmployeeController(this);
        
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

        mEntityPanel.add(mIdBox);
        mEntityPanel.add(mSNameBox);
        mEntityPanel.add(mFNameBox);
        mEntityPanel.add(mButtonBox);
        
        mContainer.add(mJTabbedPane);
      

        pack();
        setVisible(true);

	}

}
