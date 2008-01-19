package com.fun.fucms.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeSet;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import com.fun.fucms.Context;
import com.fun.fucms.EvilException;
import com.fun.fucms.gui.MainFrame;

public class PageTreeModel implements TreeModel {
	
	private TreeNode mRoot;
	private TreeNode selectedTreeNode = null;
	ArrayList<TreeNode> mNodes = new ArrayList<TreeNode>();
	ArrayList<TreeModelListener> mTreeModelListener = new ArrayList<TreeModelListener>();
	
	public PageTreeModel() {
		update(new Date());
	}

	public void addTreeModelListener(TreeModelListener l) {
		mTreeModelListener.add(l);
	}

	public Object getChild(Object parent, int index) {
		return ((TreeNode) parent).getChild(index);
	}

	public int getChildCount(Object parent) {
		return ((TreeNode) parent).getChildCount();
	}

	public int getIndexOfChild(Object parent, Object child) {
		return ((TreeNode) parent).getIndexOfChild((TreeNode) child);
	}

	public Object getRoot() {
		return mRoot;
	}

	public boolean isLeaf(Object node) {
		if (((TreeNode) node).getChildCount() == 0) {
			return true;
		} 
		return false;
	}

	public void removeTreeModelListener(TreeModelListener l) {
		mTreeModelListener.remove(l);
	}

	public void valueForPathChanged(TreePath path, Object newValue) {
	}
	
	public void update(Date date) {
		mNodes = new ArrayList<TreeNode>();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM version"); // WHERE gueltig_ab >= ");
		//sb.append(Context.getDateAsString(date));
		ResultSet rs = null;
		try {
			rs = Context.getInstance().executeQuery(sb.toString());
			while (rs.next()) {
				int id = rs.getInt("id");
				int vaterseiteID = rs.getInt("vaterseiteID");
				String path = rs.getString("path");
				/*Date validFrom = rs.getDate("gueltig_ab");
				Date validTo= rs.getDate("gueltig_bis");*/
				TreeNode tn = new TreeNode(id, vaterseiteID);
				tn.setPath(path);
				mNodes.add(tn);
			}
		} catch (SQLException e) {
			MainFrame.log(e.getMessage());
		} catch (EvilException e) {
			MainFrame.log(e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
		}
		for (TreeNode tn : mNodes) {
			int father = tn.getFather();
			TreeNode fatherNode = getNode(father);
			if (fatherNode != null) {
				//MainFrame.log("father:"+fatherNode.getId() + " child: "+ tn.getId());
				if (tn.getId() == 0) {
					mRoot = tn;
				} else {
					fatherNode.addChild(tn);
				}
			} 
		}
		fireTreeStructureChanged();
	}
	
	private TreeNode getNode(int id) {
		for (TreeNode tn : mNodes) {
			if (tn.getId() == id) {
				//MainFrame.log("id:"+id+ " tn: "+ tn.getId());
				return tn;
			}
		}
		return null;
	}
	
	public void setSelectedTreeNode(TreeNode stn) {
		selectedTreeNode = stn;
	}
	
	public TreeNode getSelectedTreeNode(){
		return selectedTreeNode;
	}
	
    public void fireTreeStructureChanged() {
        int len = mTreeModelListener.size();
        TreeModelEvent e = new TreeModelEvent(this, 
                                              new Object[] {mRoot});
        for (TreeModelListener tml : mTreeModelListener) {
            tml.treeStructureChanged(e);
        }
    }
	
	public class TreeNode implements Comparable {
		
		private int mId;
		private String mPath;
		private String mTitle;
		private Date mValidFrom;
		private Date mValidTo;
		private int mFatherId;
		private TreeSet<TreeNode> mChilds = new TreeSet<TreeNode>();
		
		private TreeNode(int id, int father) {
			mId = id;
			mFatherId = father;
		}
		
		public int getId() {
			return mId;
		}

		public int compareTo(Object o) {
			return mId - ((TreeNode) o).getId();
		}

		public String getPath() {
			return mPath;
		}

		public void setPath(String path) {
			mPath = path;
		}

		public String getTitle() {
			return mTitle;
		}

		public void setTitle(String title) {
			mTitle = title;
		}

		public Date getValidFrom() {
			return mValidFrom;
		}

		public void setValidFrom(Date validFrom) {
			mValidFrom = validFrom;
		}

		public Date getValidTo() {
			return mValidTo;
		}

		public void setValidTo(Date validTo) {
			mValidTo = validTo;
		}
		
		public int getFather() {
			return mFatherId;
		}
		public void addChild(TreeNode tn) {
			mChilds.add(tn);
		}
		
		public Iterator getChilds() {
			return mChilds.iterator();
		}
		
		public TreeNode getChild(int index) {
			return (TreeNode) mChilds.toArray()[index];
		}
		
		public int getChildCount() {
			return mChilds.size();
		}
		
		public int getIndexOfChild(TreeNode child) {
			Object[] childs = mChilds.toArray();
			for (int i = 0 ; i< childs.length ; i++ ) {
				if (child == childs[i]) return i;
			}
			return -1;
		}
		
		public String toString() {
			if (mPath != null) return mPath.trim();
			return mPath;
		}
		
	}

}
