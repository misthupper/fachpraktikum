package com.fun.fucms.gui.entities;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.fun.fucms.controller.GebaeudeController;
import com.fun.fucms.model.EntityTableModel;
import com.fun.fucms.model.Gebaeude;

public class GebaeudeFrame extends JFrame {
	
	private static final String SELECTION_TEXT = "Auswahl";
	
	private static final String ENTITY_TEXT = "Gebaeude";
	private static final String ID_LABEL_TEXT = "ID: ";
	private static final String GEBAEUDENAME_LABEL_TEXT = "ID: ";
	private static final String STRASSE_LABEL_TEXT = "Strasse: ";
	private static final String HAUSNUMMER_LABEL_TEXT = "Hausnummer: ";
	private static final String PLZ_LABEL_TEXT = "PLZ: ";
	private static final String ORT_LABEL_TEXT = "ORT: ";
	private static final String BUTTON_OK_TEXT = "OK";
	private static final String BUTTON_CANEL_TEXT = "Cancel";
	
	private Container mContainer;
	private JTabbedPane mJTabbedPane;
	private JPanel mSelectPanel, mEntityPanel;
	
	private JLabel mIdLabel, mSNameLabel, mFNameLabel;
	private JTextField mIdField, mSNameField, mFNameField;
	private Box mEntityBox, mIdBox, mSNameBox, mFNameBox, mButtonBox;
	private JButton mSaveButton, mCancelButton;
	private JScrollPane mTableScrollPane;
	
	private JTable mJTable;
	private EntityTableModel mEntityTableModel;
	
	private GebaeudeController mGebaeudeController;
	
	
	public GebaeudeFrame() {
		super();
		init();
	}
	
	public void init() {
		
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        mContainer=this.getContentPane();
        mContainer.setLayout(new BorderLayout());
        
        mJTabbedPane = new JTabbedPane();
        mSelectPanel = new JPanel();
        mSelectPanel.setLayout(new BorderLayout());
        mEntityPanel = new JPanel();
        mEntityPanel.setLayout(new BorderLayout());
        mEntityBox = new Box(BoxLayout.PAGE_AXIS);
        mJTabbedPane.add(SELECTION_TEXT,mSelectPanel);
        mJTabbedPane.add(ENTITY_TEXT,mEntityPanel);
        
        mGebaeudeController = new GebaeudeController(this);
        
        //EntityPanel
        
        mIdLabel = new JLabel(ID_LABEL_TEXT);
        mIdField = new JTextField();
        mIdBox = new Box(BoxLayout.LINE_AXIS);
        mIdBox.add(mIdLabel);
        mIdBox.add(mIdField);
        
        mSNameLabel = new JLabel(GEBAEUDENAME_LABEL_TEXT);
        mSNameField = new JTextField();
        mSNameBox = new Box(BoxLayout.LINE_AXIS);
        mSNameBox.add(mSNameLabel);
        mSNameBox.add(mSNameField);
        
        mFNameLabel = new JLabel(STRASSE_LABEL_TEXT);
        mFNameField = new JTextField();
        mFNameBox = new Box(BoxLayout.LINE_AXIS);
        mFNameBox.add(mFNameLabel);
        mFNameBox.add(mFNameField);
        
        mSaveButton = new JButton(BUTTON_OK_TEXT);
        mSaveButton.addActionListener(mGebaeudeController);
        mSaveButton.setActionCommand(GebaeudeController.ACTION_OK);
        mCancelButton = new JButton(BUTTON_CANEL_TEXT);
        mCancelButton.addActionListener(mGebaeudeController);
        mCancelButton.setActionCommand(GebaeudeController.ACTION_CANCEL);
        mButtonBox = new Box(BoxLayout.LINE_AXIS);
        mButtonBox.add(mCancelButton);
        mButtonBox.add(Box.createHorizontalGlue());
        mButtonBox.add(mSaveButton);

        mEntityBox.add(mIdBox);
        mEntityBox.add(mSNameBox);
        mEntityBox.add(mFNameBox);
        
        mEntityPanel.add(mEntityBox,BorderLayout.NORTH);
        mEntityPanel.add(mButtonBox,BorderLayout.SOUTH);
        
        mContainer.add(mJTabbedPane,BorderLayout.CENTER);
        
        //SelectPanel
        
        mEntityTableModel = new EntityTableModel(new Gebaeude());
        mJTable = new JTable(mEntityTableModel);
        mJTable.setTableHeader(null);
        mTableScrollPane = new JScrollPane(mJTable);
        mSelectPanel.add(mTableScrollPane,BorderLayout.CENTER);

        pack();
        setVisible(true);

	}

}
