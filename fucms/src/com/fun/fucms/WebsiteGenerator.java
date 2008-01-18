package com.fun.fucms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import com.fun.fucms.conf.Configuration;
import com.fun.fucms.gui.MainFrame;

public class WebsiteGenerator {
	
	private static final String FUCMS_TITLE = "FUCMS_TITLE ";
	private static final String FUCMS_AUTHOR = "FUCMS_AUTHOR";
	private static final String FUCMS_KEYWORDS = "FUCMS_KEYWORDS";
	private static final String FUCMS_DATE = "FUCMS_DATE";
	private static final String FUCMS_CSS = "FUCMS_CSS";
	private static final String FUCMS_BROTKRUEMELPFAD = "FUCMS_BROTKRUEMELPFAD";
	private static final String FUCMS_RUBRIKEN = "FUCMS_RUBRIKEN";
	private static final String FUCMS_TITLE_FATHER = "FUCMS_TITLE_FATHER ";
	private static final String FUCMS_MENU = "FUCMS_MENU";
	private static final String FUCMS_INFORMATION = "FUCMS_INFORMATION";
	private static final String FUCMS_ZUSATZINFORMATIONEN = "FUCMS_ZUSATZINFORMATIONEN";
	private static final String FUCMS_PATH = "FUCMS_PATH";
	
	private static final String ENCODING = "ISO-8859-1";
	protected static Context mContext;
	private static String sTemplate = "";
	
	private static String mHtml;
	private static int mWebseitenID; // ID der zu generierenden Webseite
	private static String webseitenTitel;
	private static String websitePath;
	private static int pathDepth;
	private static String HTMLPath;
	
	// holt die Template-Datei der Webseite und speichert den Inhalt zur weiteren Bearbeitung in sTemplate ab.
	private static void getTemplate() {
		String templateFileName = "template.html";
		try {
			ResultSet rs = Context.getInstance().executeQuery("select * from Version where id = " + mWebseitenID);
			rs.first();
			int formatID = rs.getInt("format");
			rs.close();
			rs = Context.getInstance().executeQuery("select * from Webseitenvorlage where id = " + formatID);
			rs.first();
			templateFileName = rs.getString("dateiname").trim();
        
			File templateFile = new File(Configuration.getTemplateDirectory().getAbsolutePath() +
				"/" + templateFileName);
		
			StringBuffer sb = new StringBuffer();
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(templateFile), ENCODING));
			String s = br.readLine();
			while (s != null) {
				sb.append(s+"\n");
				s = br.readLine();
			}
			br.close();
			rs.close();
			sTemplate =  sb.toString();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (EvilException e) {
			e.printStackTrace();
		}
	}
	
	public WebsiteGenerator(int WebseitenID) {
		WebsiteGenerator.generateWebsite(WebseitenID);
	}
	
	// Geniert eine Webseite fuer das Archiv im Ordner Archiv mit angehaengtem Datum und Uhrzeit
	public static void generateWebsiteForArchiv(int WebseitenID){
		mWebseitenID = WebseitenID;
		getTemplate();
		mHtml = sTemplate;
		mHtml = InhaltsParser.parse(generateWebsiteContent());
		//ersetzt eventuell weitere relative Pfadangaben zu Mediendateien etc.
		setFolder("../" + generateRelativeLinkPath(websitePath.replaceAll("\\\\", "/")));
		// Aktuelles Datum:
		Date heute = new Date();
		// Formatierer:
		SimpleDateFormat formatierer = new SimpleDateFormat ("yyyyMMdd_HHmm");
		//System.out.println(formatierer.format(heute));
		String Datum = formatierer.format(heute);
		write(new File(Configuration.getHTMLDirectory().getAbsolutePath() + "/archiv" + websitePath + webseitenTitel + Datum + ".html"));
	}
	
	// Generiert die Webseite basierend auf dem Template
	public static void generateWebsite(int WebseitenID){
		MainFrame.log("-- Webseite mit der ID " + WebseitenID + " wird generiert!");
		mWebseitenID = WebseitenID;
		getTemplate();
		MainFrame.log("Template-Datei wird geladen!");
		mHtml = sTemplate;
		mHtml = InhaltsParser.parse(generateWebsiteContent());
		MainFrame.log("Ersetzungsmarken werden durch Inhalte der Datenbank ersetzt!");
		//ersetzt eventuell weitere relative Pfadangaben zu Mediendateien etc.
		setFolder(generateRelativeLinkPath(websitePath.replaceAll("\\\\", "/")));
		write(new File(Configuration.getHTMLDirectory().getAbsolutePath() + websitePath + webseitenTitel + ".html"));
	}
	
	// Fuehrt die Ersetzung der Inhaltsmarken durch und liest dazu die Inhalte aus der DB aus.
	private static String generateWebsiteContent(){
		try {
			String mSQLString = "select * from Version where id=" + mWebseitenID;
			ResultSet rs = Context.getInstance().executeQuery(mSQLString);
	        rs.first();
	        webseitenTitel = rs.getString("path").trim();
	        setTitle(rs.getString("titel").trim());
			
	        MainFrame.log("Pfad der Webseite wird generiert!");
			websitePath = generateWebsitePath(mWebseitenID);
			setPfad("Fernuni" + websitePath + webseitenTitel);
			
			HTMLPath = Configuration.getHTMLDirectory().getAbsolutePath().trim();
			//HTMLPath = HTMLPath.replaceAll("\\\\", "/");
			//setFolder(generateRelativeLinkPath(websitePath.replaceAll("\\\\", "/")));
	        
			ResultSet rs4 = Context.getInstance().executeQuery("select * from Version where id =" + rs.getString("vaterseiteID").trim());
	        rs4.first();
	        setTitleFather(rs.getString("titel").trim());
	        
	        setCSS("arbeiten.css");
	        MainFrame.log("Menue wird generiert!");
	        setMenu();
	        
	        // HauptseitenInhalt einlesen und ersetzen
	        MainFrame.log("Inhalt der Hauptseite wird aus der DB eingelesen!");
	        String hauptseitenID = rs.getString("HauptseitenInhaltID");
	        ResultSet rs2 = Context.getInstance().executeQuery("select * from Inhalt where id=" + hauptseitenID);
	        rs2.first();
	        //System.out.println("Hauptinhalt:"+ rs2.getString("Inhaltstext"));
	        setHauptinhalt(rs2.getString("Inhaltstext"));
	        
	        // SeitenleistenInhalt
	        MainFrame.log("Inhalt der Seitenleiste wird aus der DB eingelesen!");
	        String seitenleistenID = rs.getString("SeitenleisteInhaltID");
	        //System.out.println("SeitenleistenInhaltsID: " + seitenleistenID);
	        ResultSet rs3 = Context.getInstance().executeQuery("select * from Inhalt where id=" + seitenleistenID);
	        rs3.first();
	        //System.out.println("Seiteninhalt:"+ rs3.getString("Inhaltstext"));
	        setSeitenleisteninhalt(rs3.getString("Inhaltstext"));
	        
	        rs.close();
	        rs2.close();
	        rs3.close();
	        rs4.close();
	        //Context.getInstance().close();
		}  catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		catch (EvilException e2) {
			System.out.println(e2.getMessage());
		}
		return mHtml;
	}
	
	public String getHtml() {
		return mHtml;
	}
	
	public static void setTitle(String title) {
		mHtml = mHtml.replaceAll(FUCMS_TITLE, title);
	}
	public static void setDate(String date) {
		mHtml = mHtml.replaceAll(FUCMS_DATE, date);
	}
	public static void setFolder(String folder){
		while (mHtml.contains(FUCMS_PATH))
			mHtml = mHtml.replace(FUCMS_PATH, folder);
	}
	public static void setPfad(String pfad) throws SQLException, EvilException {
		
		mHtml = mHtml.replaceAll(FUCMS_BROTKRUEMELPFAD, generateBrotkruemelPath(mWebseitenID));
		//mHtml = mHtml.replaceAll(FUCMS_BROTKRUEMELPFAD, pfad.replaceAll("/", " | "));
	}
	public static void setTitleFather(String titleFather) {
		if (titleFather.contains("root"))
			titleFather = "Fernuni";
		mHtml = mHtml.replaceAll(FUCMS_TITLE_FATHER, titleFather);
	}
	public static void setCSS(String css) {
		mHtml = mHtml.replaceAll(FUCMS_CSS, css);
	}
	public static void setHauptinhalt(String information) {
		mHtml = mHtml.replaceAll(FUCMS_INFORMATION, information);
	}
	public static void setSeitenleisteninhalt(String zusatzinformation) {
		mHtml = mHtml.replaceAll(FUCMS_ZUSATZINFORMATIONEN, zusatzinformation);
	}
	
	public static void setMenu() throws SQLException, EvilException {
		// holt alle Seiten mit der gleichen Vaterseite
		ResultSet rs = Context.getInstance().executeQuery("select * from Version where VaterseiteID=" + mWebseitenID);
        String link = "";
        String seitentitel = "";
        String tempWebseitenTitel = webseitenTitel + "/";
		if (!rs.first()){
			rs.close();
			rs = Context.getInstance().executeQuery("select * from Version where vaterseiteID = (select vaterseiteID from Version where id = " + mWebseitenID + ")");
			rs.first();
			tempWebseitenTitel = "";
			
		}
		do {
			if (!(rs.getString("path").contains("root"))){
				//System.out.println(rs.getString("path"));
				seitentitel = rs.getString("path").trim();
				if (seitentitel.contentEquals(webseitenTitel)){
					link = link + "<li>" + seitentitel + "</li>\n";
				} else {
					link = link + "<li><a href='"  + tempWebseitenTitel + seitentitel + ".html" + "' >" + seitentitel + "</a></li>\n";
				}
			}
		} while (rs.next());
		rs.close();
		mHtml = mHtml.replaceAll(FUCMS_MENU, link);
	}
	
	/**
	 * Generiert den Ordner-Pfad der Webseite basierend auf der Baumstruktur der Seiten
	 */
	public static String generateWebsitePath(int websiteID) throws SQLException, EvilException{
		String path = "";
		pathDepth = 0;
		String tempid = Integer.toString(websiteID);
		ResultSet rs = Context.getInstance().executeQuery("select * from version where id = " + tempid);
		rs.first();
		tempid = rs.getString("vaterseiteid");
		rs.close();
		rs = Context.getInstance().executeQuery("select * from version where id = " + tempid);
		rs.first();
		while (!(rs.getString("path").contains("root"))){
			path = rs.getString("path").trim() + "/" + path; // pfad anhaengen
			// tempid wird mit vaterseitenid geladen
			tempid = rs.getString("vaterseiteid");
			pathDepth++;
			rs.close();
			rs = Context.getInstance().executeQuery("select * from version where id = " + tempid);
			rs.first();
		}
		rs.close();
		return "/" + path;
	}
	
	/**
	 * Generiert den Brotkruemel-Pfad der Webseite inkl. Links basierend auf der Baumstruktur der Seiten
	 */
	public static String generateBrotkruemelPath(int websiteID) throws SQLException, EvilException{
		String path = "";
		String linkPath = "../";
		pathDepth = 0;
		String tempid = Integer.toString(websiteID);
		ResultSet rs = Context.getInstance().executeQuery("select * from version where id = " + tempid);
		rs.first();
		tempid = rs.getString("vaterseiteid");
		rs.close();
		rs = Context.getInstance().executeQuery("select * from version where id = " + tempid);
		rs.first();
		while (!(rs.getString("path").contains("root"))){
			path = "<li> <a href='" + linkPath + rs.getString("path").trim() + ".html' >" + rs.getString("path").trim() + "</a> </li> " + path; // pfad anhaengen
			// tempid wird mit vaterseitenid geladen
			linkPath = linkPath + "../";
			tempid = rs.getString("vaterseiteid");
			pathDepth++;
			rs.close();
			rs = Context.getInstance().executeQuery("select * from version where id = " + tempid);
			rs.first();
		}
		rs.close();
		//System.out.println("<a href = 'http://www.fernuni-hagen.de'>Fernuni</a> &nbsp;" + path + webseitenTitel);
		return "<li> <a href = 'http://www.fernuni-hagen.de'>Fernuni</a> </li> " + path + "<li> " + webseitenTitel + "</li>";
	}
	
	public static String generateRelativeLinkPath(String path){
		String linkPath = "";
		path = path.replaceFirst("/","");
		while (path.indexOf("/")!=-1){
			path = path.replaceFirst("/","");
			linkPath = linkPath + "../";
		}
		return linkPath;
	}
	
	public static void write(File file) {
		try {
			prepareDir(file.getParentFile());
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file), ENCODING));
			bw.write(mHtml);
			bw.flush();
			bw.close();
		} catch (UnsupportedEncodingException e) {
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private static void prepareDir(File dir) {
		File[] files = Configuration.getTemplateDirectory().listFiles();
		dir.mkdirs();
		/*
		for (File f : files) {
			if (f.isDirectory()) continue;
			File target = new File(dir.getAbsolutePath() + "/" +f.getName());
			try {
				FileInputStream fis = new FileInputStream(f);
				FileOutputStream fos = new FileOutputStream(target);
				int b = fis.read();
				while (b >= 0) {
					fos.write(b);
					b = fis.read();
				}
				fis.close();
				fos.flush();
				fos.close();
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			} catch (UnsupportedEncodingException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		*/
	}

}
