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

/**
 * Frame for editing on type of Entity
 * @author rod
 *
 */
public class EntityFrame extends JFrame {
	
	public static final String SELECTION_TEXT_NEW = "Neu";
	public static final String SELECTION_TEXT_EDIT = "Bearbeiten";
	public static final String SELECTION_TEXT_DELETE = "Löschen";
	public static final String SELECTION_TEXT_UPDATE = "Aktualisieren";
	public static final String SELECTION_TEXT_CLOSE= "Schließen";
	
	private static final String SELECTION_TEXT = "Auswahl";
	
	private Container mContainer;
	private JTabbedPane mJTabbedPane;
	private JPanel mSelectPanel; 
	private EntityPanel mEntityPanel;
	private JScrollPane mTableScrollPane;
	private Box mButtonBox;
	private JButton mButtonNew, mButtonEdit, mButtonDelete, mButtonUpdate, mButtonClose;
	
	private Entity mEntity;
	private JTable mJTable;
	private EntityTableModel mEntityTableModel;
	
	private EntityFrameController mEntityFrameController;
	
	
	/**
	 * Construktor
	 * @param e one Entity is needed to define the type of Entities to work with.
	 */
	public EntityFrame(Entity e) {
		super();
		mEntity = e;
		this.setTitle(mEntity.getTable());
		init();
	}
	
	private void init() {
		
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
        mButtonUpdate = new JButton(SELECTION_TEXT_UPDATE);
        mButtonUpdate.addActionListener(mEntityFrameController);
        mButtonClose = new JButton(SELECTION_TEXT_CLOSE);
        mButtonClose.addActionListener(mEntityFrameController);
        mButtonBox = new Box(BoxLayout.LINE_AXIS);
        mButtonBox.add(mButtonNew);
        mButtonBox.add(Box.createHorizontalGlue());
        mButtonBox.add(mButtonEdit);
        mButtonBox.add(Box.createHorizontalGlue());
        mButtonBox.add(mButtonDelete);
        mButtonBox.add(Box.createHorizontalGlue());
        mButtonBox.add(mButtonUpdate);
        mButtonBox.add(Box.createHorizontalGlue());
        mButtonBox.add(mButtonClose);
        mSelectPanel.add(mButtonBox,BorderLayout.SOUTH);
        
        mJTabbedPane = new JTabbedPane();
        mJTabbedPane.add(SELECTION_TEXT,mSelectPanel);
        mContainer.add(mJTabbedPane,BorderLayout.CENTER);

        setIsDataRow(false);
        
        pack();
        setVisible(true);

	}
	
	/**
	 * enables or disables the buttons to edit or delete a row.
	 * If no valid row is selected, the buttons should be disabled
	 * @param b
	 */
	public void setIsDataRow(boolean b) {
		mButtonEdit.setEnabled(b);
		mButtonDelete.setEnabled(b);
	}
	
	/**
	 * returns the selected row in the table
	 * @return
	 */
	public int getSelectedRow() {
		return mJTable.getSelectedRow();
	}
	
	/**
	 * returns the TableModel
	 * @return
	 */
	public EntityTableModel getTableModel() {
		return mEntityTableModel;
	}
	
	/**
	 * adds an new Panel in the TabbedPane, for a new Entity (empty or loaded
	 * from the database)
	 * 
	 * @param entity
	 * @param newRecord
	 *            yes, if the record does not exist in the database. Toggles the
	 *            display of the key in the title
	 */
	public void addEntityPanel(Entity entity, boolean newRecord) {
        mEntityPanel = new EntityPanel(mEntityFrameController, entity);
        String label = mEntity.getTable();
        if (! newRecord) {
        	label = label + " "+ entity.getKeyString();
        }
        mJTabbedPane.add(label,mEntityPanel);
        mJTabbedPane.setSelectedComponent(mEntityPanel);
	}

	/**
	 * close the given EntityPanel (after saving for example)
	 * @param panel
	 */
	public void closeEntityPanel(EntityPanel panel) {
		mJTabbedPane.remove(panel);
	}

	/**
	 * force a reload of the table
	 *
	 */
	public void updateTable() {
		mEntityTableModel.update();
		mEntityTableModel.fireTableDataChanged();
	}
	

}
