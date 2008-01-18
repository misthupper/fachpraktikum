package com.fun.fucms.conf;


import java.io.File;

import javax.swing.UIManager;

public class Configuration {
    
    private static final String SELECTEDLOOKANDFEEL = "selectedLookAndFeel";
    
    private static final String DATASOURCE_DRIVER   = "datasource_driver";  
    private static final String DATASOURCE_URL    = "datasource_url";  
    private static final String DATASOURCE_PASSWORD  = "datasource_password";  
    private static final String DATASOURCE_USERNAME  = "datasource_username";  
    
    private static final String SQL_DIR = "sql_dir";
    private static final String OUT_DIR = "out_dir";
    private static final String TEMPLATE_DIR = "template_dir";
    private static final String INHALTS_DIR = "inhalt_dir";
    private static final String HTML_DIR = "html_dir";
    
    private static Configuration sInstance;
    private ConfigurationFile mConfigFile;
    
    private Configuration() {
        mConfigFile = new ConfigurationFile(new File(Constants.sCONFIGURATION_FILE));
    }
    
    private static Configuration getInstance() {
        if (sInstance == null) {
            sInstance = new Configuration();
        }
        return sInstance;
    }
    
    public static File getTargetDirectory() {
    	String path = Configuration.getInstance().mConfigFile.getString(OUT_DIR, Constants.OUT_DIR);
    	File file = new File(path);
    	if (! file.exists()) {
    		file.mkdirs();
    	}
    	return file;
    }
    
    public static File getTemplateDirectory() {
    	String path = Configuration.getInstance().mConfigFile.getString(TEMPLATE_DIR, Constants.TEMPLATE_DIR);
    	File file = new File(path);
    	if (! file.exists()) {
    		file.mkdirs();
    	}
    	return file;
    }
    
    public static File getInhaltsDirectory() 
    // Nur vorruebergehend - Spaeter aus der DAtenbank lesen
    {
    	String path = Configuration.getInstance().mConfigFile.getString(INHALTS_DIR, Constants.INHALTS_DIR);
    	File file = new File(path);
    	if (! file.exists()) {
    		file.mkdirs();
    	}
    	return file;
    }
    
    public static File getHTMLDirectory() 
    // Nur vorruebergehend - Spaeter aus der DAtenbank lesen
    {
    	String path = Configuration.getInstance().mConfigFile.getString(HTML_DIR, Constants.HTML_DIR);
    	File file = new File(path);
    	if (! file.exists()) {
    		file.mkdirs();
    	}
    	return file;
    }


    
    public static File getSQLDirectory() {
    	String path = Configuration.getInstance().mConfigFile.getString(SQL_DIR, Constants.sSQL_DIR);
    	File file = new File(path);
    	if (! file.exists()) {
    		file.mkdirs();
    	}
    	return file;
    }
    
    public static String getDataSourceDriver() {
    	return Configuration.getInstance().mConfigFile.getString(DATASOURCE_DRIVER, Constants.sDATASOURCE_DRIVER);
    }
    
    public static String getDataSourceUrl() {
    	return Configuration.getInstance().mConfigFile.getString(DATASOURCE_URL,Constants.sDATASOURCE_URL);
    }
    
    
    public static String getDataSourceUsername() {
    	return Configuration.getInstance().mConfigFile.getString(DATASOURCE_USERNAME, Constants.sDATASOURCE_USERNAME);
    }
    
    public static String getDataSourcePassword() {
    	return Configuration.getInstance().mConfigFile.getString(DATASOURCE_PASSWORD, Constants.sDATASOURCE_PASSWORD);
    }

    
    public static void setLookAndFeel(String classname) {
        Configuration.getInstance().mConfigFile.setString(SELECTEDLOOKANDFEEL,
        		classname);
    }
    public static String getLookAndFeel() {
        String cl = Configuration.getInstance().mConfigFile.getString(SELECTEDLOOKANDFEEL, null);
        if (cl == null) {
        	cl = UIManager.getLookAndFeel().getClass().toString();
        	setLookAndFeel(cl);
        }
        return cl;
    }
    
    public static void write() {
        Configuration.getInstance().mConfigFile.write();
    }
}
