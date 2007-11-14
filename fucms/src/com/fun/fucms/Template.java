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

import com.fun.fucms.conf.Configuration;

public class Template {
	
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
	private static final String sTemplate = getTemplate();
	
	private String mHtml;
	
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
	
	public Template() {
		mHtml = sTemplate;
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
	public void setMenu(String menu) {
		mHtml = mHtml.replaceAll(FUCMS_MENU, menu);
	}
	
	public void write(File file) {
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
