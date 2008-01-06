package com.fun.fucms.gui;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.text.*;

import com.fun.fucms.gui.entities.*;
import com.fun.fucms.model.*;
import com.fun.fucms.model.entities.Gebaeude;
import com.fun.fucms.model.entities.Person;

import java.awt.print.*;

public class EditFrame extends JFrame {

	//  Konstanten für die Größe der Textfläche
	final int ZEILEN = 100;

	final int SPALTEN = 400;

	// Web Menue
	private static final String sMENU1 = "Seite";

	//private static final String sMENU1_ITEM1 = "DB löschen";  
	private static final String sMENU1_ITEM1 = "Seite neu laden";

	private static final String sMENU1_ITEM2 = "Seite speichern";

	private static final String sMENU1_ITEM3 = "Seite drucken";

	private static final String sMENU1_ITEM4 = "Seiteneditor beenden";

	private static final String sMENU2 = "Bearbeiten";

	private static final String sMENU2_ITEM1 = "Ausschneiden";

	private static final String sMENU2_ITEM2 = "Kopieren";

	private static final String sMENU2_ITEM3 = "Einfuegen";

	private static final String sMENU3 = "Einfuegen";

	private static final String sMENU3_ITEM1 = "Person einfuegen...";

	private static final String sMENU3_ITEM2 = "Gebaeude einfuegen...";

	private static final String sMENU3_ITEM3 = "Einrichtung einfuegen...";

	private static final String sMENU4 = "Suchen";

	private static final String sMENU4_ITEM1 = "String suchen...";

	//private static final String sMENU3_ITEM2 = " ";

	// globale Variablen innerhalb der Klasse
	private String m_dateiname; // zu ladende/speichernde Datei

	private String m_aktText; // aktueller Text in der TextArea

	private CTextAnzeige m_textanzeige; // die eigentliche TextArea

	private JComboBox m_fonts, m_styles; // Auswahl von Fonttyp, Stil und

	private JComboBox m_farben; // Farbe

	private Hashtable m_befehle;
	
	
	public EditFrame() {
		super("Seiteneditor");
		init();
	}
	
	/**
	 * Editor mit diesem Inhalt laden
	 */
	public void setEditorText(String inhalt) {
		m_textanzeige.setText(inhalt);
		//TODO inhalt aus DB lesen
	}
	
	private void init() {
		JMenuBar menueleiste = new JMenuBar();
		setJMenuBar(menueleiste);

		JMenu menu1 = new JMenu(sMENU1);
		JMenuItem item1_1 = new JMenuItem(sMENU1_ITEM1);
		JMenuItem item1_2 = new JMenuItem(sMENU1_ITEM2);
		JMenuItem item1_3 = new JMenuItem(sMENU1_ITEM3);
		JMenuItem item1_4 = new JMenuItem(sMENU1_ITEM4);
		menu1.add(item1_1);
		menu1.add(item1_2);
		menu1.add(item1_3);
		menu1.add(item1_4);
		menueleiste.add(menu1);

		JMenu menu2 = new JMenu(sMENU2);
		JMenuItem item2_1 = new JMenuItem(sMENU2_ITEM1);
		JMenuItem item2_2 = new JMenuItem(sMENU2_ITEM2);
		JMenuItem item2_3 = new JMenuItem(sMENU2_ITEM3);
		menu2.add(item2_1);
		menu2.add(item2_2);
		menu2.add(item2_3);
		menueleiste.add(menu2);

		JMenu menu3 = new JMenu(sMENU3);
		JMenuItem item3_1 = new JMenuItem(sMENU3_ITEM1);
		JMenuItem item3_2 = new JMenuItem(sMENU3_ITEM2);
		JMenuItem item3_3 = new JMenuItem(sMENU3_ITEM3);
		menu3.add(item3_1);
		menu3.add(item3_2);
		menu3.add(item3_3);
		menueleiste.add(menu3);

		JMenu menu4 = new JMenu(sMENU4);
		JMenuItem item4_1 = new JMenuItem(sMENU4_ITEM1);
		menu4.add(item4_1);
		menueleiste.add(menu4);

		// Panel für den Textbereich und die Auswahlfelder
		JPanel p_außen = new JPanel();
		p_außen.setLayout(new BorderLayout());

		// Text-Komponente anlegen
		m_textanzeige = new CTextAnzeige(ZEILEN, SPALTEN);
		m_textanzeige.setEditable(true);

		// Textkomponente scrollbar machen
		JScrollPane scroll = new JScrollPane();
		scroll.getViewport().add(m_textanzeige);

		// Inneres Panel für die Auswahlmöglichkeiten 
		JPanel p_innen = new JPanel();
		p_innen.setLayout(new GridLayout(1, 4));

		p_außen.setPreferredSize(new Dimension(500, 400));
		p_außen.add("Center", scroll);
		p_außen.add("North", p_innen);

		// Panel zur Frame Klasse hinzufügen
		getContentPane().add(p_außen);

		class CMeinWindowAdapter extends WindowAdapter {
			public void windowClosing(WindowEvent e) {
//				System.exit(0);
			}
		}

		class CMeinActionLauscher implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				String label;

				label = e.getActionCommand();
				System.out.println(label);

				if (label.equals(sMENU1_ITEM1))
					seiteNeuLaden();

				if (label.equals(sMENU1_ITEM2))
					seiteSpeichern();

				if (label.equals(sMENU1_ITEM3))
					dateiDrucken();

				if (label.equals(sMENU1_ITEM4))
					fensterSchliessen();

				if (label.equals(sMENU3_ITEM3))
					einrichtungEinfuegen(); 

				if (label.equals(sMENU3_ITEM1))
					personEinfuegen();

				if (label.equals(sMENU3_ITEM2))
					gebaeudeEinfuegen(); 

				if (label.equals(sMENU4_ITEM1))
					stringSuchen();
			}
		}

		// Lauschobjekte registrieren
		addWindowListener(new CMeinWindowAdapter());

		// Die Instanz der Adapterklasse für den ActionListener brauchen
		// wir mehrmals, daher erzeugen wir nur eine Instanz und                            
		// verwenden sie mehrfach; das spart ein bißchen Speicher
		CMeinActionLauscher actionlistener = new CMeinActionLauscher();
		item1_1.addActionListener(actionlistener);
		item1_2.addActionListener(actionlistener);
		item1_3.addActionListener(actionlistener);
		item1_4.addActionListener(actionlistener);
		item3_1.addActionListener(actionlistener);
		item3_2.addActionListener(actionlistener);
		item3_3.addActionListener(actionlistener);
		item4_1.addActionListener(actionlistener);

		// Befehle für die Zwischenablage
		// erzeuge Action-Tabelle
		m_befehle = new Hashtable();
		Action[] actionsArray = m_textanzeige.getActions();
		for (int i = 0; i < actionsArray.length; i++) {
			Action a = actionsArray[i];
			m_befehle.put(a.getValue(Action.NAME), a);
		}

		item2_1.addActionListener((Action) m_befehle
				.get(DefaultEditorKit.cutAction));
		item2_2.addActionListener((Action) m_befehle
				.get(DefaultEditorKit.copyAction));
		item2_3.addActionListener((Action) m_befehle
				.get(DefaultEditorKit.pasteAction));

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Seiteneditor");
		pack();
		setVisible(true);
	}

	void fensterSchliessen() {
		this.setVisible(false);
		this.dispose();
	}

	void personEinfuegen() {
		EntityAttributeSelectionFrame personSelectionFrame = 
			new EntityAttributeSelectionFrame(new Person(), m_textanzeige);
			
	}
	
	void gebaeudeEinfuegen() {
		EntityAttributeSelectionFrame gebaeudeSelectionFrame = 
			new EntityAttributeSelectionFrame(new Gebaeude(), m_textanzeige);
	}
	
	void einrichtungEinfuegen() {
		EntityAttributeSelectionFrame einrichtungSelectionFrame = 
			new EntityAttributeSelectionFrame(new Person(), m_textanzeige);
	}

	// Eine Textdatei  laden
	// Methode von CEditor
	void seiteNeuLaden() {
		FileDialog d = new FileDialog(this, "Text laden...", FileDialog.LOAD);

		d.show();
		m_dateiname = d.getDirectory();
		m_dateiname += d.getFile();

		// Falls der Benutzer keine Datei ausgewählt hat, 
		// wird null zurückgegeben
		// Dann nichts weiter tun
		if (m_dateiname == null)
			return;

		// Einen Eingabestrom öffnen und die Datei laden
		StringBuffer lesepuffer = new StringBuffer(ZEILEN * SPALTEN);

		try {
			File eingabedatei = new File(m_dateiname);
			FileReader eingabe = new FileReader(eingabedatei);

			// solange Zeichen lesen, bis das Dateiende ( = -1) 
			// erreicht ist 
			char zeichen;
			int gelesen;
			int zeilen = 0;
			boolean weiter = true;

			while (weiter) {
				gelesen = eingabe.read();
				if (gelesen == -1) {
					weiter = false;
					continue;
				}

				zeichen = (char) gelesen;
				lesepuffer.append(zeichen);
			}

			// Datei schließen
			eingabe.close();

			m_aktText = new String(lesepuffer);
			m_textanzeige.setText(m_aktText);
			m_textanzeige.setCaretPosition(0);
		}

		catch (EOFException e) {
			// auf diese Exception haben wir ja gewartet , nichts weiter tun. 
		} catch (FileNotFoundException e) {
			System.out.println("Datei nicht vorhanden oder lesbar!\n");
			m_dateiname = null;
		} catch (IOException e) {
			// Sonst irgendwas ist schiefgegangen
			System.out.println("Fehler beim Lesen der Datei " + m_dateiname
					+ "\n");
			m_dateiname = null;
		}

	} // Ende von 'dateiLaden' 

	// Den aktuellen Text abspeichern
	// Methode von CEditor
	void seiteSpeichern() {
		// lokale Variablen
		int zeichen, i;

		FileDialog d = new FileDialog(this, "Text speichern...",
				FileDialog.SAVE);

		d.show();
		m_dateiname = d.getFile();

		if (m_dateiname == null)
			return;

		try {
			// Den Text nun in der Datei speichern
			File ausgabedatei = new File(m_dateiname);
			FileWriter ausgabe = new FileWriter(ausgabedatei);

			// den aktuellen Text ermitteln und speichern
			m_aktText = m_textanzeige.getText();

			for (i = 0; i < m_aktText.length(); i++) {
				zeichen = (int) m_aktText.charAt(i);
				ausgabe.write(zeichen);
			}

			// Datei schließen
			ausgabe.close();
		}

		catch (IOException e) {
			//  irgendwas ist schiefgegangen
			System.out.println("Fehler beim Schreiben der Datei  "
					+ m_dateiname + "\n");
			m_dateiname = null;
		}

	} // Ende von 'dateiSpeichern' 

	// den aktuellen Text drucken
	// Methode von CEditor 
	void dateiDrucken() {
		PrinterJob druckJob = PrinterJob.getPrinterJob();

		druckJob.setPrintable(m_textanzeige);
		PageFormat seitenFormat = druckJob.pageDialog(druckJob.defaultPage());

		if (druckJob.printDialog()) {
			try {
				druckJob.print();
			} catch (Exception e) {
				System.out.println("Fehler beim Drucken");
			}
		}
	}

	// Diese Funktion sucht einen String im Text
	// Methode von CEditor
	void stringSuchen() {
		String suchstring;
		CFrageDialog frage;
		int Index;

		// Ein Textfeld aufmachen, um nach dem Suchstring zu fragen
		frage = new CFrageDialog(this, "Suchen");
		frage.setLocation(150, 150);
		frage.pack();
		frage.show();

		suchstring = frage.getString();

		if (suchstring == null)
			return;

		// nun suchen
		m_aktText = m_textanzeige.getText();
		Index = m_aktText.indexOf(suchstring);

		if (Index == -1) {
			JOptionPane.showMessageDialog(null, "String nicht gefunden",
					"Meldung", JOptionPane.INFORMATION_MESSAGE);
		} else
			// Den String hervorheben
			m_textanzeige.select(Index, Index + suchstring.length());

	} // Ende von 'stringSuchen' 

} // Ende von Klasse 'CEditor' 



// die Dialogklasse für die Eingabe des Suchstrings
class CFrageDialog extends JDialog implements ActionListener {
	private JTextField eingabefeld;

	private JButton oK, abbruch;

	private String suchstring;

	// der Konstruktor
	CFrageDialog(JFrame f, String titel) {
		super(f, titel, true); // Konstruktor der Basisklasse aufrufen
		setResizable(false);

		getContentPane().setLayout(new BorderLayout());

		// es werden 2 Panels angelegt. Das eine enthält das TextField,
		//  das andere die Buttons
		JPanel panel1 = new JPanel();
		JLabel label = new JLabel("Bitte Suchstring eingeben:");
		panel1.add(label);
		eingabefeld = new JTextField(40);
		panel1.add(eingabefeld);
		getContentPane().add("Center", panel1);

		JPanel panel2 = new JPanel();
		oK = new JButton("OK");
		abbruch = new JButton("Abbruch");
		panel2.add(oK);
		panel2.add(abbruch);
		getContentPane().add("South", panel2);

		pack(); // Anordnung der Oberflächenelemente auf bevorzugte 
		// Größe initialisieren

		// das Maus-Handling für die Buttons  macht die Klasse 
		// selbst, also bei sich selber registrieren
		oK.addActionListener(this);
		abbruch.addActionListener(this);

		class CMeinDialogAdapter extends WindowAdapter {
			public void windowClosing(WindowEvent e) {
				suchstring = null;
				setVisible(false);
			}
		}

		addWindowListener(new CMeinDialogAdapter());
	}

	public void actionPerformed(ActionEvent e) {
		String label;

		label = e.getActionCommand();

		if (label.equals("Abbruch")) {
			suchstring = null;
			setVisible(false);
			return;
		}

		if (label.equals("OK")) {
			suchstring = eingabefeld.getText();
			setVisible(false);
			return;
		}
	} // Ende von 'ActionPerformed' 

	// Diese Funktion liefert den eingegebenen Suchstring zurück
	public String getString() {
		return suchstring;
	}

} // Ende von 'CFrageDialog' 

class CTextAnzeige extends JTextArea implements Printable {
	// der Konstruktor
	CTextAnzeige(int zeilen, int spalten) {
		super(zeilen, spalten);
	}

	// die print Methode des Interface Printable
	public int print(Graphics g, PageFormat pf, int pi) throws PrinterException {
		if (pi >= 1)
			return Printable.NO_SUCH_PAGE;

		Graphics2D g2d = (Graphics2D) g;

		// auf den sichtbaren Bereich ausrichten 
		g2d.translate(pf.getImageableX(), pf.getImageableY());
		paint(g2d);

		return Printable.PAGE_EXISTS;
	}

}
