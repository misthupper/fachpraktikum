package com.fun.fucms.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.fun.fucms.gui.entities.EntityAttributeSelectionFrame;
import com.fun.fucms.gui.entities.EntityAttributeSelectionPanel;
import com.fun.fucms.gui.entities.EntityFrame;
import com.fun.fucms.gui.entities.EntityPanel;
import com.fun.fucms.model.Entity;
import com.fun.fucms.model.TableMediator;

public class EntityAttributeSelectionFrameController implements ActionListener, ListSelectionListener {
	
	private EntityAttributeSelectionFrame mEntityFrame;
	private int mSelectedRow = 0;
	private int mSelectedCol = 0;
	
	private JTextArea textanzeige;
	
	public EntityAttributeSelectionFrameController(EntityAttributeSelectionFrame ef, JTextArea textanzeige) {
		mEntityFrame = ef;
		this.textanzeige = textanzeige;
	}

	/**
	 * dispatch actions of the EntityFrame and the childs of EntityFrame
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			String text = ((JButton) e.getSource()).getText();
			
			if (text.equals(EntityAttributeSelectionFrame.SELECTION_TEXT_DELETE)) {
				Entity entity = mEntityFrame.getTableModel().getEntity(mSelectedRow);
				TableMediator.deleteEntity(entity);
				mEntityFrame.updateTable();
			} else if (text.equals(EntityAttributeSelectionFrame.SELECTION_TEXT_AUSWAHL)) {
				mSelectedCol = mEntityFrame.getSelectedCol();
				if (mEntityFrame.isDataRow()){
					Entity entity = mEntityFrame.getTableModel().getEntity(mSelectedRow);
					//String att = entity.getStringValue(mSelectedCol);
//					System.out.println(entity.getClass().toString());
					String attributeName = (String) mEntityFrame.getTableModel().getValueAt(0, mSelectedCol);
//					System.out.println("Attribut-Bezeichner: " + attributeName);
					String id = entity.getValueAsString("id");
//					System.out.println("ID: " + id);
					String ersetzungsmarke = "<!-- FUCMS."+ entity.getTable() + "." + id + "." + attributeName + " -->";
					//System.out.println(ersetzungsmarke);
					// Falls eine link-adresse eingefuegt wird, muss noch der (relative) Pfad ebenfalls hinzugefuegt werden (FUCMS_PATH)
					if (ersetzungsmarke.contains("LINK")){
						ersetzungsmarke = "FUCMS_PATH" + ersetzungsmarke;
					}
					textanzeige.insert(ersetzungsmarke, textanzeige.getCaretPosition());
				}
				
			} else if (text.equals(EntityAttributeSelectionFrame.SELECTION_TEXT_EDIT)) {
				Entity entity = mEntityFrame.getTableModel().getEntity(mSelectedRow);
				mEntityFrame.addEntityPanel(entity, false);
			} else if (text.equals(EntityAttributeSelectionFrame.SELECTION_TEXT_NEW)) {
				Entity entity = mEntityFrame.getTableModel().getNewEntity();
				mEntityFrame.addEntityPanel(entity, true);
			} else if (text.equals(EntityAttributeSelectionFrame.SELECTION_TEXT_UPDATE)) {
				mEntityFrame.updateTable();
			} else if (text.equals(EntityAttributeSelectionFrame.SELECTION_TEXT_CLOSE)) {
				mEntityFrame.setVisible(false);
				mEntityFrame.dispose();
			}
		}

	}

	/**
	 * this method is called when a new row in the JTable is selected
	 */
	public void valueChanged(ListSelectionEvent e) {
		mSelectedRow = mEntityFrame.getSelectedRow();
		mSelectedCol = mEntityFrame.getSelectedCol();
		if (mSelectedRow < 1) {
			mEntityFrame.setIsDataRow(false);
		} else {
			mEntityFrame.setIsDataRow(true);
		}
	}

	public void closeEntityPanel(EntityAttributeSelectionPanel panel) {
		mEntityFrame.closeEntityPanel(panel); 
	}

	public void saveAndCloseEntityPanel(EntityAttributeSelectionPanel panel) {
		Entity entity = panel.getEntity();
		TableMediator.saveEntity(entity);
		mEntityFrame.closeEntityPanel(panel); 
		mEntityFrame.updateTable();
	}

}

