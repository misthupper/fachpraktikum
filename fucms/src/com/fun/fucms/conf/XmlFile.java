package com.fun.fucms.conf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class XmlFile {

	public static final String ENCODING_UTF8 = "UTF-8";

	public static final String STRINGS = "strings";
	
	public static final String BOOLEAN = "boolean";
	
	public static final String INT = "int";

	private File mFile;

	private Document mDoc;

	public static Document getDocument(File f) {
		Document doc = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(f), ENCODING_UTF8));
			String line = br.readLine();
			StringBuffer docSB = new StringBuffer("");
			while (line != null) {
				docSB.append(line);
				line = br.readLine();
			}
			br.close();
			doc = DocumentHelper.parseText(docSB.toString());
		} catch (UnsupportedEncodingException e1) {
		} catch (IOException e) {
			doc = null;
		} catch (DocumentException e) {
			doc = null;
		}
		return doc;
	}

	public static void writeDocument(Document doc, File file) {
		XMLWriter w;

		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setTrimText(true);
		format.setSuppressDeclaration(false);
		format.setOmitEncoding(false);
		format.setEncoding(ENCODING_UTF8);
		try {
			w = new XMLWriter(new OutputStreamWriter(
					new FileOutputStream(file), ENCODING_UTF8), format);
			w.write(doc);
			w.flush();
			w.close();
		} catch (IOException e) {
		}
	}

	public XmlFile(File f) {
		mFile = f;
		mDoc = XmlFile.getDocument(mFile);
		if (mDoc == null) {
			mDoc = DocumentHelper.createDocument();
			mDoc.addElement("root");
		}
	}

	public void write() {
		XmlFile.writeDocument(mDoc, mFile);
	}
	
	public void setBoolean(String id, boolean b) {
		setBoolean(null, id, b);
	}
	
	public void setBoolean(Element e, String id, boolean b) {
		Element base;
		if (e==null) {
			base = getBaseElement(BOOLEAN);
		} else {
			base = e;
		}
		Element element = base.element(normalizeId(id));
		if (element == null) {
			element = base.addElement(normalizeId(id));
		}
		element.setText(new Boolean(b).toString());
	}

	public boolean getBoolean(String id, boolean defaultValue) {
		return getBoolean(null, id, defaultValue);
	}
	
	public boolean getBoolean(Element e, String id, boolean defaultValue) {
		Element base;
		if (e==null) {
			base = getBaseElement(BOOLEAN);
		} else {
			base = e;
		}
		boolean b = defaultValue;
		Element element = base.element(normalizeId(id));
		if (element != null) {
			String s = element.getText();
			b = Boolean.parseBoolean(s);
		}
		return b;
	}
	
	public void setString(String id, String value) {
		setString(null, id, value);
	}

	public void setString(Element e, String id, String value) {
		Element base;
		if (e == null) {
			base = getBaseElement(STRINGS);
		} else {
			base = e;
		}
		Element element = base.element(normalizeId(id));
		if (element == null) {
			element = base.addElement(normalizeId(id));
		}
		element.setText(value);
	}
	
	public String getString(String id, String defaultValue) {
		return getString(null, id, defaultValue);
	}

	public String getString(Element e, String id, String defaultValue) {
		Element base;
		String s = null;
		if (e == null) {
			base = getBaseElement(STRINGS);
		} else {
			base = e;
		}
		Element element = base.element(normalizeId(id));
		if (element != null) {
			s = element.getText();
		}
		if (s == null && defaultValue != null) {
			setString(e, id, defaultValue);
			s = defaultValue;
		}
		return s;
	}
	
	public void setInt(String id, int i) {
		setInt(null, id, i);
	}
	
	public void setInt(Element e, String id, int i) {
		Element base;
		if (e==null) {
			base = getBaseElement(INT);
		} else {
			base = e;
		}
		Element element = base.element(normalizeId(id));
		if (element == null) {
			element = base.addElement(normalizeId(id));
		}
		element.setText(String.valueOf(i));
	}

	public int getInt(String id, int defaultValue) {
		return getInt(null, id, defaultValue);
	}
	
	public int getInt(Element e, String id, int defaultValue) {
		Element base;
		if (e==null) {
			base = getBaseElement(INT);
		} else {
			base = e;
		}
		int i = defaultValue;
		Element element = base.element(normalizeId(id));
		if (element != null) {
			String s = element.getText();
			try {
				i = Integer.parseInt(s);
			} catch (NumberFormatException nfe) {

			}
		}
		return i;
	}

	protected Element getBaseElement(String base) {
		Element e = mDoc.getRootElement().element(base);
		if (e == null) {
			e = mDoc.getRootElement().addElement(base);
		}
		return e;
	}

	protected String normalizeId(String id) {
		String newId = id.replaceAll(" ", "_");
		newId = newId.replaceAll("\\.", "_");
		return newId;
	}

}
