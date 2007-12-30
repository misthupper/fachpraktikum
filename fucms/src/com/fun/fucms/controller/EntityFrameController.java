package com.fun.fucms.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.fun.fucms.gui.entities.EntityFrame;
import com.fun.fucms.gui.entities.EntityPanel;
import com.fun.fucms.model.Entity;
import com.fun.fucms.model.TableMediator;

public class EntityFrameController implements ActionListener, ListSelectionListener {
	
	private EntityFrame mEntityFrame;
	private int mSelectedRow = 0;
	
	public EntityFrameController(EntityFrame ef) {
		mEntityFrame = ef;
	}

	/**
	 * dispatch actions of the EntityFrame and the childs of EntityFrame
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			String text = ((JButton) e.getSource()).getText();
			if (text.equals(EntityFrame.SELECTION_TEXT_DELETE)) {
				Entity entity = mEntityFrame.getTableModel().getEntity(mSelectedRow);
				TableMediator.deleteEntity(entity);
				mEntityFrame.updateTable();
			} else if (text.equals(EntityFrame.SELECTION_TEXT_EDIT)) {
				Entity entity = mEntityFrame.getTableModel().getEntity(mSelectedRow);
				mEntityFrame.addEntityPanel(entity, false);
			} else if (text.equals(EntityFrame.SELECTION_TEXT_NEW)) {
				Entity entity = mEntityFrame.getTableModel().getNewEntity();
				mEntityFrame.addEntityPanel(entity, true);
			} else if (text.equals(EntityFrame.SELECTION_TEXT_UPDATE)) {
				mEntityFrame.updateTable();
			} else if (text.equals(EntityFrame.SELECTION_TEXT_CLOSE)) {
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
		if (mSelectedRow < 1) {
			mEntityFrame.setIsDataRow(false);
		} else {
			mEntityFrame.setIsDataRow(true);
		}
	}

	public void closeEntityPanel(EntityPanel panel) {
		mEntityFrame.closeEntityPanel(panel); 
	}

	public void saveAndCloseEntityPanel(EntityPanel panel) {
		Entity entity = panel.getEntity();
		TableMediator.saveEntity(entity);
		mEntityFrame.closeEntityPanel(panel); 
		mEntityFrame.updateTable();
	}

}
