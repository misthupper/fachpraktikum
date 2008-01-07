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

import javax.swing.JOptionPane;

import com.fun.fucms.conf.Configuration;

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
	private static final String sTemplate = getTemplate();
	
	private static String mHtml;
	private static int mWebseitenID; // ID der zu generierenden Webseite
	private static String webseitenTitel;
	private static String websitePath;
	private static int pathDepth;
	private static String HTMLPath;
	
	private static String getTemplate() {
		File templateFile = new File(Configuration.getTemplateDirectory().getAbsolutePath() +
				"/template.html");
		try {
			StringBuffer sb = new StringBuffer();
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(templateFile), ENCODING));
			String s = br.readLine();
			while (s != null) {
				sb.append(s+"\n");
				s = br.readLine();
			}
			br.close();
			return sb.toString();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return "";
	}
	
	public WebsiteGenerator(int WebseitenID) {
		mWebseitenID = WebseitenID;
		//TODO Template je nach Webseite auslesen
		mHtml = sTemplate;
		mHtml = InhaltsParser.parse(generateWebsiteContent());
		write(new File(Configuration.getHTMLDirectory().getAbsolutePath() + websitePath + webseitenTitel + ".html"));
	}
	
	public static void generateWebsite(int WebseitenID){
		mWebseitenID = WebseitenID;
		//TODO Template je nach Webseite auslesen
		mHtml = sTemplate;
		mHtml = InhaltsParser.parse(generateWebsiteContent());
		write(new File(Configuration.getHTMLDirectory().getAbsolutePath() + websitePath + webseitenTitel + ".html"));
		JOptionPane.showMessageDialog(null, "Die Seite wurde generiert und im Ordner 'HTML" + websitePath + "' abgespeichert.", "Fertig!", JOptionPane.CANCEL_OPTION);
	}
	
	private static String generateWebsiteContent(){
		try {
			String mSQLString = "select * from Version where id=" + mWebseitenID;
			ResultSet rs = Context.getInstance().executeQuery(mSQLString);
	        rs.first();
	        webseitenTitel = rs.getString("path").trim();
	        setTitle(webseitenTitel);
			webseitenTitel = rs.getString("path").trim();
	        setTitle(webseitenTitel);
			
			websitePath = generateWebsitePath(mWebseitenID);
			System.out.println("Fernuni" + websitePath + webseitenTitel);
			setPfad("Fernuni" + websitePath + webseitenTitel);
			System.out.println(Configuration.getHTMLDirectory().getAbsolutePath());
			
			HTMLPath = Configuration.getHTMLDirectory().getAbsolutePath().trim();
			//HTMLPath = HTMLPath.replaceAll("\\\\", "/");
			setFolder(generateLinkPath(websitePath.replaceAll("\\\\", "/")));
	        
	        
	        //Übergeordneter Titel feststellen
	        ResultSet rs_fathertitel = Context.getInstance().executeQuery("select * from Version where id = (select vaterseiteid from version where id =" + mWebseitenID + ")");
	        rs_fathertitel.first();
	        setTitleFather(rs_fathertitel.getString("path"));
	        
	        setCSS("arbeiten.css");
	        //TODO Menügenerierung
	        setMenu();
	        
	        
	        
	        // HauptseitenInhalt
	        String hauptseitenID = rs.getString("HauptseitenInhaltID");
	        //System.out.println("HauptseitenInhaltsID: " + hauptseitenID);
	        ResultSet rs2 = Context.getInstance().executeQuery("select * from Inhalt where id=" + hauptseitenID);
	        rs2.first();
	        //System.out.println("Hauptinhalt:"+ rs2.getString("Inhaltstext"));
	        setInformation(rs2.getString("Inhaltstext"));
	        
	        // SeitenleistenInhalt
	        String seitenleistenID = rs.getString("SeitenleisteInhaltID");
	        //System.out.println("SeitenleistenInhaltsID: " + seitenleistenID);
	        ResultSet rs3 = Context.getInstance().executeQuery("select * from Inhalt where id=" + seitenleistenID);
	        rs3.first();
	        //System.out.println("Seiteninhalt:"+ rs3.getString("Inhaltstext"));
	        setZusatzInformationen(rs3.getString("Inhaltstext"));
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
	
	public static void setFolder(String folder){
		while (mHtml.contains(FUCMS_PATH))
			mHtml = mHtml.replace(FUCMS_PATH, folder);
	}
	public static void setPfad(String pfad) {
		mHtml = mHtml.replaceAll(FUCMS_BROTKRUEMELPFAD, pfad);
	}
	public static void setTitleFather(String titleFather) {
		mHtml = mHtml.replaceAll(FUCMS_TITLE_FATHER, titleFather);
	}
	public static void setCSS(String css) {
		mHtml = mHtml.replaceAll(FUCMS_CSS, css);
	}
	public static void setInformation(String information) {
		mHtml = mHtml.replaceAll(FUCMS_INFORMATION, information);
	}
	public static void setZusatzInformationen(String zusatzinformation) {
		mHtml = mHtml.replaceAll(FUCMS_ZUSATZINFORMATIONEN, zusatzinformation);
	}
	public static void setMenu() throws SQLException, EvilException {
		// holt alle Seiten mit der gleichen Vaterseite
		ResultSet rs = Context.getInstance().executeQuery("select * from Version where VaterseiteID=" + mWebseitenID);
        String link = "";
        String seitentitel = "";
		
		while (rs.next()){
			if (!(rs.getString("path").contains("root"))){
				//System.out.println(rs.getString("path"));
				seitentitel = rs.getString("path").trim();
				link = link + "<li><a href='"  + webseitenTitel + "/" + seitentitel + ".html" + "' >" + seitentitel + "</a></li>\n";
			}
		}
		mHtml = mHtml.replaceAll(FUCMS_MENU, link);
	}
	
	/**
	 * Generiert den (relativen) Ordner-Pfad der Webseite basierend auf der Baumstruktur der Seiten
	 * @return
	 * @throws SQLException
	 * @throws EvilException
	 */
	public static String generateWebsitePath(int websiteID) throws SQLException, EvilException{
		String path = "";
		pathDepth = 0;
		String tempid = Integer.toString(websiteID);
		ResultSet rs = Context.getInstance().executeQuery("select * from version where id = " + tempid);
		rs.first();
		tempid = rs.getString("vaterseiteid");
		rs = Context.getInstance().executeQuery("select * from version where id = " + tempid);
		rs.first();
		while (!(rs.getString("path").contains("root"))){
			path = rs.getString("path").trim() + "/" + path; // pfad anhängen
			// tempid wird mit vaterseitenid geladen
			tempid = rs.getString("vaterseiteid");
			pathDepth++;
			rs = Context.getInstance().executeQuery("select * from version where id = " + tempid);
			rs.first();
		}
		return "/" + path;
	}
	
	public static String generateLinkPath(String path){
		String linkPath = "";
		path = path.replaceFirst("/","");
		while (path.indexOf("/")!=-1){
			path = path.replaceFirst("/","");
			linkPath = linkPath + "../";
		}
		System.out.println("Link-Pfad: " + linkPath);
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
