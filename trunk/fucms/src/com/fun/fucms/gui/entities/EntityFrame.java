package com.fun.fucms.gui.entities;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.fun.fucms.controller.EntityFrameController;
import com.fun.fucms.model.Entity;
import com.fun.fucms.model.EntityTableModel;
import com.fun.fucms.model.Person;

public class EntityFrame extends JFrame {
	
	public static final String SELECTION_TEXT_NEW = "Neu";
	public static final String SELECTION_TEXT_EDIT = "Bearbeiten";
	public static final String SELECTION_TEXT_DELETE = "Löschen";
	
	private static final String SELECTION_TEXT = "Auswahl";
	
	private Container mContainer;
	private JTabbedPane mJTabbedPane;
	private JPanel mSelectPanel; 
	private EntityPanel mEntityPanel;
	private JScrollPane mTableScrollPane;
	private Box mButtonBox;
	private JButton mButtonNew, mButtonEdit, mButtonDelete;
	
	private Entity mEntity;
	private JTable mJTable;
	private EntityTableModel mEntityTableModel;
	
	private EntityFrameController mEntityFrameController;
	
	
	
	public EntityFrame(Entity e) {
		super();
		mEntity = e;
		init();
	}
	
	public void init() {
		
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        mContainer=this.getContentPane();
        mContainer.setLayout(new BorderLayout());
        
        mEntityFrameController = new EntityFrameController(this);

        
        //SelectPanel
        mSelectPanel = new JPanel();
        mSelectPanel.setLayout(new BorderLayout());
        
        mEntityTableModel = new EntityTableModel(mEntity);
        mJTable = new JTable(mEntityTableModel);
        mJTable.setTableHeader(null);
        mJTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mJTable.setRowSelectionAllowed(true);
        mJTable.setColumnSelectionAllowed(false);
        mJTable.getSelectionModel().addListSelectionListener(mEntityFrameController);
        mTableScrollPane = new JScrollPane(mJTable);
        mSelectPanel.add(mTableScrollPane,BorderLayout.CENTER);
        
        mButtonNew = new JButton(SELECTION_TEXT_NEW);
        mButtonNew.addActionListener(mEntityFrameController);
        mButtonEdit = new JButton(SELECTION_TEXT_EDIT);
        mButtonEdit.addActionListener(mEntityFrameController);
        mButtonDelete = new JButton(SELECTION_TEXT_DELETE);
        mButtonDelete.addActionListener(mEntityFrameController);
        mButtonBox = new Box(BoxLayout.LINE_AXIS);
        mButtonBox.add(mButtonNew);
        mButtonBox.add(Box.createHorizontalGlue());
        mButtonBox.add(mButtonEdit);
        mButtonBox.add(Box.createHorizontalGlue());
        mButtonBox.add(mButtonDelete);
        mSelectPanel.add(mButtonBox,BorderLayout.SOUTH);
        
        mJTabbedPane = new JTabbedPane();
        mJTabbedPane.add(SELECTION_TEXT,mSelectPanel);
        mContainer.add(mJTabbedPane,BorderLayout.CENTER);

        pack();
        setVisible(true);

	}
	
	public void setIsDataRow(boolean b) {
		mButtonEdit.setEnabled(b);
		mButtonDelete.setEnabled(b);
	}
	
	public int getSelectedRow() {
		return mJTable.getSelectedRow();
	}
	
	public EntityTableModel getTableModel() {
		return mEntityTableModel;
	}
	
	public void addEntityPanel(Entity entity, boolean newRecord) {
        mEntityPanel = new EntityPanel(mEntityFrameController, entity);
        String label = mEntity.getTable();
        if (! newRecord) {
        	label = label + " "+ entity.getKeyString();
        }
        mJTabbedPane.add(label,mEntityPanel);
        mJTabbedPane.setSelectedComponent(mEntityPanel);
	}

	public void closeEntityPanel(EntityPanel panel) {
		mJTabbedPane.remove(panel);
	}

	public void updateTable() {
		mEntityTableModel.update();
		mEntityTableModel.fireTableDataChanged();
	}
	

}
