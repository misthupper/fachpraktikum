package com.fun.fucms.gui.entities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

import com.fun.fucms.controller.EntityAttributeSelectionFrameController;
import com.fun.fucms.controller.EntityFrameController;
import com.fun.fucms.model.Entity;
import com.fun.fucms.model.EntityTableModel;

/**
 * Frame for editing on type of Entity
 * @author rod
 *
 */
public class EntityAttributeSelectionFrame extends JFrame {
	

	public static final String SELECTION_TEXT_AUSWAHL = "Attribute auswählen";
	public static final String SELECTION_TEXT_NEW = "Neu";
	public static final String SELECTION_TEXT_EDIT = "Bearbeiten";
	public static final String SELECTION_TEXT_DELETE = "Löschen";
	public static final String SELECTION_TEXT_UPDATE = "Aktualisieren";
	public static final String SELECTION_TEXT_CLOSE= "Schließen";
	
	private static final String SELECTION_TEXT = "Auswahl";
	private boolean isDataRow = true;
	
	private Container mContainer;
	private JTabbedPane mJTabbedPane;
	private JPanel mSelectPanel; 
	private EntityAttributeSelectionPanel mEntityPanel;
	private JScrollPane mTableScrollPane;
	private Box mButtonBox;
	private JButton mButtonAuswahl, mButtonNew, mButtonEdit, mButtonDelete, mButtonUpdate, mButtonClose;
	
	private Entity mEntity;
	private JTable mJTable;
	private EntityTableModel mEntityTableModel;
	
	private EntityAttributeSelectionFrameController mEntityAttributeSelectionFrameController;
	
	private JTextArea textanzeige;
	
	/**
	 * Construktor
	 * @param e one Entity is needed to define the type of Entities to work with.
	 */
	public EntityAttributeSelectionFrame(Entity e, JTextArea textanzeige) {
		super();
		mEntity = e;
		this.setTitle(mEntity.getTable());
		this.textanzeige = textanzeige;
		init();
	}
	
	private void init() {
		
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        mContainer=this.getContentPane();
        mContainer.setLayout(new BorderLayout());
        
        mEntityAttributeSelectionFrameController = new EntityAttributeSelectionFrameController(this, textanzeige);

        
        //SelectPanel
        mSelectPanel = new JPanel();
        mSelectPanel.setLayout(new BorderLayout());
        
        mEntityTableModel = new EntityTableModel(mEntity);
        mJTable = new JTable(mEntityTableModel);
        mJTable.setTableHeader(null);
        mJTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mJTable.setRowSelectionAllowed(false);
        mJTable.setColumnSelectionAllowed(false);
        mJTable.setCellSelectionEnabled(true);
        mJTable.getSelectionModel().addListSelectionListener(mEntityAttributeSelectionFrameController);
        mTableScrollPane = new JScrollPane(mJTable);
        mSelectPanel.add(mTableScrollPane,BorderLayout.CENTER);
        
        mButtonAuswahl = new JButton(SELECTION_TEXT_AUSWAHL);
        mButtonAuswahl.addActionListener(mEntityAttributeSelectionFrameController);
        mButtonNew = new JButton(SELECTION_TEXT_NEW);
        mButtonNew.addActionListener(mEntityAttributeSelectionFrameController);
        mButtonEdit = new JButton(SELECTION_TEXT_EDIT);
        mButtonEdit.addActionListener(mEntityAttributeSelectionFrameController);
        mButtonDelete = new JButton(SELECTION_TEXT_DELETE);
        mButtonDelete.addActionListener(mEntityAttributeSelectionFrameController);
        mButtonUpdate = new JButton(SELECTION_TEXT_UPDATE);
        mButtonUpdate.addActionListener(mEntityAttributeSelectionFrameController);
        mButtonClose = new JButton(SELECTION_TEXT_CLOSE);
        mButtonClose.addActionListener(mEntityAttributeSelectionFrameController);
        mButtonBox = new Box(BoxLayout.LINE_AXIS);
        mButtonBox.add(mButtonAuswahl);
        mButtonBox.add(Box.createHorizontalGlue());
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
		isDataRow = b;
	}
	
	/**
	 * returns the selected row in the table
	 * @return
	 */
	public int getSelectedRow() {
		return mJTable.getSelectedRow();
	}
	
	public int getSelectedCol(){
		return mJTable.getSelectedColumn();
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
        mEntityPanel = new EntityAttributeSelectionPanel(mEntityAttributeSelectionFrameController, entity);
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
	public void closeEntityPanel(EntityAttributeSelectionPanel panel) {
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
	
	public boolean isDataRow(){
		return isDataRow;
	}

}

