package com.fun.fucms;

import com.fun.fucms.conf.CMDActions;
import com.fun.fucms.conf.Configuration;
import com.fun.fucms.sql.SQLUtils;

public class Fucms {
	
	private Context mContext;

	/**
	 * @param args
	 */
	/*public static void main(String[] args) {
		Fucms fucms = new Fucms();
		
		if (args.length > 0) {
			//if (args[0].equalsIgnoreCase(CMDActions.sCREATE_TABLES) ) {
				fucms.createTables();
				fucms.generate();
			//}
		}


	
	public Fucms() {
		try {
			mContext = new Context();
		} catch (EvilException e) {
		} finally  {
			Configuration.write();
			//if (mContext != null) mContext.close();
		}
		
	}
	
	public void createTables() {
		SQLUtils.executeSQLStmsInDirectory(mContext, Configuration.getSQLDirectory());
	}
	public void generate() {
		MainPage mp = new MainPage(mContext);
		try {
			mp.init();
			mp.generate();
			System.out.println("generated to "+Configuration.getTargetDirectory().getAbsolutePath());
		} catch (EvilException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	*/

}
