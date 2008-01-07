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
	
	
	private static final String ENCODING = "ISO-8859-1";
	protected static Context mContext;
	private static final String sTemplate = getTemplate();
	
	private String mHtml;
	private int mWebseitenID; // ID der zu generierenden Webseite
	
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
		mHtml = InhaltsParser.parse(generateWebsite());
		write(new File(Configuration.getTemplateDirectory().getAbsolutePath() +"/generierteSeite.html"));
	}
	
	private String generateWebsite(){
		try {
			//TODO
			String mSQLString = "select * from Version where id=" + mWebseitenID;
			//mSQLString = "Select * from Person where id=1";
	        //System.out.println(mSQLString);
	        ResultSet rs = Context.getInstance().executeQuery(mSQLString);
	        rs.first();
	        setTitle(rs.getString("path"));
	        
	        setTitleFather("Testüberschrift");
	        
	        setCSS("arbeiten.css");
	        //TODO Menügenerierung
	        setMenu("Testmenü");
	        
	        
	        
	        // HauptseitenInhalt
	        String hauptseitenID = rs.getString("HauptseitenInhaltID");
	        //System.out.println("HauptseitenInhaltsID: " + hauptseitenID);
	        ResultSet rs2 = Context.getInstance().executeQuery("select * from Inhalt where id=" + hauptseitenID);
	        rs2.first();
	        //System.out.println("Hauptinhalt:"+ rs2.getString("Inhaltstext"));
	        setInformation(rs2.getString("Inhaltstext"));
	        
	        // SeitenleistenInhalt
	        String seitenleistenID = rs.getString("SeitenleistenInhaltID");
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
	
	public void setTitle(String title) {
		mHtml = mHtml.replaceAll(FUCMS_TITLE, title);
	}
	public void setTitleFather(String titleFather) {
		mHtml = mHtml.replaceAll(FUCMS_TITLE_FATHER, titleFather);
	}
	public void setCSS(String css) {
		mHtml = mHtml.replaceAll(FUCMS_CSS, css);
	}
	public void setInformation(String information) {
		mHtml = mHtml.replaceAll(FUCMS_INFORMATION, information);
	}
	public void setZusatzInformationen(String zusatzinformation) {
		mHtml = mHtml.replaceAll(FUCMS_ZUSATZINFORMATIONEN, zusatzinformation);
	}
	public void setMenu(String menu) throws SQLException, EvilException {
		// holt alle Seiten mit der gleichen Vaterseite
		ResultSet rs = Context.getInstance().executeQuery("select * from Version where VaterseiteID= (select vaterseiteID from Version where id=" + mWebseitenID + ")");
        String link = "";
		
		while (rs.next()){
			if (!(rs.getString("path").contains("root"))){
				System.out.println(rs.getString("path"));
				link = link + "<li><a href='' >" + rs.getString("path")+ "</a></li>";
			}
			//<li id="navigation_css_id_1160998674"><a href="/arbeiten/organisation/beauftragte/gleichstellungsbeauftragte.shtml">Gleichstellungs- <br />beauftragte</a></li>
			
		}
		
		mHtml = mHtml.replaceAll(FUCMS_MENU, link);
	}
	
	public void write(File file) {
		try {
			//prepareDir(file.getParentFile());
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
	
	private void prepareDir(File dir) {
		File[] files = Configuration.getTemplateDirectory().listFiles();
		dir.mkdirs();
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
	}

}
