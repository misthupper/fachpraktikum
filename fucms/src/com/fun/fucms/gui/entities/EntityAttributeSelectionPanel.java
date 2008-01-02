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

import com.fun.fucms.controller.*;
import com.fun.fucms.model.*;

/**
 * Displays one record of an Table (one entity)
 * @author rod
 *
 */
public class EntityAttributeSelectionPanel extends JPanel implements ActionListener {
	
	private static final String BUTTON_OK_TEXT = "Speichern";
	private static final String BUTTON_CANEL_TEXT = "Beenden";
	
	private Box mFieldBox, mButtonBox;
	private ArrayList<JTextField> mTextFields = new ArrayList<JTextField>();
	private JButton mSaveButton,mCancelButton;
	
	private EntityAttributeSelectionFrameController mEntityAttributeSelectionController;
	
	private Entity mEntity;
	
	/**
	 * Constructor
	 * @param efc
	 * @param e Entity to show (and edit...)
	 */
	public EntityAttributeSelectionPanel(EntityAttributeSelectionFrameController efc, Entity e) {
		super();
		mEntityAttributeSelectionController = efc;
		mEntity = e;
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
	    mCancelButton = new JButton(BUTTON_CANEL_TEXT);
	    mCancelButton.addActionListener(this);
	    mButtonBox.add(mCancelButton);
	    mButtonBox.add(Box.createHorizontalGlue());
	    mButtonBox.add(mSaveButton);
	
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mCancelButton) {
			mEntityAttributeSelectionController.closeEntityPanel(this);
		} else if (e.getSource() == mSaveButton) {
			mEntityAttributeSelectionController.saveAndCloseEntityPanel(this);
		}
	}
	
	private void fillEntity() {
		for (JTextField jtf : mTextFields) {
			String field = jtf.getName();
			String value = jtf.getText();
			mEntity.setValueAsString(field, value);
		}
	}
	
	/**
	 * returns the data in an entity
	 * @return
	 */
	public Entity getEntity() {
		fillEntity();
		return mEntity;
	}
	


}

