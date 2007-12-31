package com.fun.fucms.gui;

import java.util.Date;

import javax.swing.JTree;

import com.fun.fucms.model.PageTreeModel;

public class PageTree extends JTree {
	
	PageTreeModel mPageTreeModel;

	public PageTree() {
		super(new PageTreeModel());
		mPageTreeModel = (PageTreeModel) this.getModel();
		this.revalidate();
	}
	
}
