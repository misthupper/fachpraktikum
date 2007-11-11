package com.fun.fucms;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fun.fucms.conf.Configuration;

public class MainPage extends Page {
	
	public MainPage(Context context) {
		super("",context);
	}
	
	public void init() throws EvilException {
		try {
			String sqlStm = "Select * from homepage where validFrom < '"
					+ mContext.getDateAsString() + "' AND validTo >= '"
					+ mContext.getDateAsString() + "'";
			ResultSet rs = mContext.executeQuery(sqlStm);
			rs.first();
			mTitle = rs.getString("title");
			String child=null;
			int no=0;
			try {
			do {
				child = rs.getString("child"+no++);
				if (child != null){
					mChilds.add(child);
				}
			} while (child != null);
			} catch (SQLException e) {
			}
			mContext.closeLastQuery();
		} catch (SQLException e) {
			throw new EvilException(e);
		}
		
	}
	
	public void generate() throws EvilException {
		String path = Configuration.getTargetDirectory().getAbsolutePath();
		super.generate(path);
		/*
		Template template = new Template();
		template.setTitle(mTitle);
		template.setMenu(generateMenu(""));
		template.write(new File(path+"/index.html"));
		*/
	}

}
