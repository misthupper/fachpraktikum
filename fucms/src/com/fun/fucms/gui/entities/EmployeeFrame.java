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

import com.fun.fucms.controller.EmployeeController;
import com.fun.fucms.model.EntityTableModel;
import com.fun.fucms.model.Person;

public class EmployeeFrame extends JFrame {
	
	private static final String SELECTION_TEXT = "Auswahl";
	
	private static final String ENTITY_TEXT = "Mitarbeiter";
	
	private Container mContainer;
	private JTabbedPane mJTabbedPane;
	private JPanel mSelectPanel; 
	private EntityPanel mEntityPanel;
	
	private JScrollPane mTableScrollPane;
	
	private JTable mJTable;
	private EntityTableModel mEntityTableModel;
	
	
	
	public EmployeeFrame() {
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
        mEntityPanel = new EntityPanel(new Person());
        mJTabbedPane.add(SELECTION_TEXT,mSelectPanel);
        mJTabbedPane.add(ENTITY_TEXT,mEntityPanel);

        mContainer.add(mJTabbedPane,BorderLayout.CENTER);

        
        //SelectPanel
        
        mEntityTableModel = new EntityTableModel(new Person());
        mJTable = new JTable(mEntityTableModel);
        mJTable.setTableHeader(null);
        mTableScrollPane = new JScrollPane(mJTable);
        mSelectPanel.add(mTableScrollPane,BorderLayout.CENTER);

        pack();
        setVisible(true);

	}

}
