package com.fun.fucms;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fun.fucms.conf.Configuration;

public class Page {
	
	private String mId;
	protected Context mContext;
	protected String mTitle;
	protected ArrayList<String> mChilds = new ArrayList<String>();
	protected ArrayList<String> mInfoEls = new ArrayList<String>();
	protected ArrayList<Page> mChildPages;
	
	public Page(String id, Context context) {
		mId = id;
		mContext = context;
	}
	
	public void init() throws EvilException {
		try {
			String sqlStm = "Select * from page where id = '" + mId
					+ "' AND validFrom < '" + mContext.getDateAsString()
					+ "' AND validTo >= '" + mContext.getDateAsString() + "'";
			ResultSet rs = mContext.executeQuery(sqlStm);
			// System.out.println(sqlStm);
			rs.first();
			mTitle = rs.getString("title");
			String child = null;
			int no = 0;
			try {
				do {
					child = rs.getString("child" + no++);
					if (child != null) {
						mChilds.add(child);
					}
				} while (child != null);
			} catch (SQLException e) {
			}
			String infoEl = null;
			no = 0;
			try {
				do {
					infoEl = rs.getString("contentEl" + no++);
					if (infoEl != null) {
						mInfoEls.add(infoEl);
					}
				} while (infoEl != null);
			} catch (SQLException e) {
			}
			mContext.closeLastQuery();
		} catch (SQLException e) {
			throw new EvilException(e);
		}
		
	}
	
	public String getTitle() {
		return mTitle;
	}
	
	public ArrayList<String> getChilds() {
		return mChilds;
	}
	
	public void generate(String path) throws EvilException {
		Template template = new Template();
		template.setTitle(mTitle);
		if (path.endsWith("/")) {
			path = path+mId.trim();
		} else {
			path = path+"/"+mId.trim();
		}
		template.setMenu(generateMenu(path));
		template.setInformation(getContent(mInfoEls));
		File file = new File(path+"/index.html");
		template.write(file);
		for (Page p : mChildPages) {
			p.generate(path);
		}
		
	}
	
	protected void initChilds() throws EvilException {
		mChildPages = new ArrayList<Page>();
		for (String child: mChilds) {
			Page page = new Page(child,mContext);
			page.init();
			mChildPages.add(page);
		}
	}
	
	protected String generateMenu(String path) throws EvilException {
		if (mChildPages == null) initChilds();
		StringBuffer sb = new StringBuffer();
		for (Page p : mChildPages) {
			sb.append("<li><a href=\"" + path +"/"+p.mId.trim() +"/index.html\">"+
					p.mTitle.trim()+"</a></li>\n");
		}
		return sb.toString();
	}
	
	public String getContent(ArrayList<String> elements) {
		StringBuffer sb = new StringBuffer();
		for (String el : elements) {
			String sqlStm = "Select * from contentEl where id = '" + el + "'";
			/*
			 * AND validFrom < '" + mContext.getDateAsString() + "' AND validTo >= '" +
			 * mContext.getDateAsString() + "'";
			 */
			try {
				ResultSet rs = mContext.executeQuery(sqlStm);
				rs.first();
				String type = rs.getString("type").trim();
				String content = rs.getString("content").trim();
				mContext.closeLastQuery();
				sqlStm = "Select * from type where id = '" + type + "'";
				rs = mContext.executeQuery(sqlStm);
				rs.first();
				String table = rs.getString("dbtable").trim();
				String header = rs.getString("header").trim();
				String footer = rs.getString("footer").trim();
				
				String[] head = new String[10];
				String[] column = new String[10];
				String[] foot = new String[10];
				String[] fieldcontent = new String[10];
				
				sb.append(header);
				
				for (int i=0; i< 10 ; i++) {
					head[i] = rs.getString("headc"+i);
					column[i] = rs.getString("column"+i);
					foot[i] = rs.getString("footc"+i);
				}
				mContext.closeLastQuery();
				
				sqlStm = "Select * from "+table+" where id = '" + content + "'";
				rs = mContext.executeQuery(sqlStm);
				rs.first();
				
				for (int i=0; i< 10 ; i++) {
					if (column[i] == null) continue;
					fieldcontent[i] = rs.getString(column[i].trim());
					sb.append(head[i]);
					sb.append(fieldcontent[i]);
					sb.append(foot[i]);
				}
				
				sb.append(footer);
				
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			// System.out.println(sqlStm);
		}

		return sb.toString();
	}

}
