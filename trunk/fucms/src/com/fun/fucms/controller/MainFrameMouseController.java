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
		System.out.println(pageTreeModel.getRoot());
		
		System.out.println(e.getSource());
		if(e.getButton() == 2 || e.getButton() == 3) {
			EditFrame ef = new EditFrame();
		}
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
