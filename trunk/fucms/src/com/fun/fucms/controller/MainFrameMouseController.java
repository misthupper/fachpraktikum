package com.fun.fucms.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import sun.applet.Main;

import com.fun.fucms.gui.EditFrame;
import com.fun.fucms.gui.PageTree;
import com.fun.fucms.model.PageTreeModel;
	
public class MainFrameMouseController implements MouseListener {
	
	private PageTreeModel pageTreeModel;
	
	public MainFrameMouseController(PageTreeModel pageTreeModel) {
		this.pageTreeModel = pageTreeModel;
	}
	
	public void mouseClicked(MouseEvent e) {
//		if(e.getButton() == 2 || e.getButton() == 3) {
//			EditFrame ef = new EditFrame();
//			ef.setEditorText(pageTreeModel.getSelectedTreeNode().toString());
//		}
	}

	public void mouseEntered(MouseEvent e) {
		
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		
		
	}

	public void mouseReleased(MouseEvent e) {
		
		
	}

}
