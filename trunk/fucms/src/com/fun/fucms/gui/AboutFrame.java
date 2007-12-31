package com.fun.fucms.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class AboutFrame extends JDialog {
	private JLabel label;
	private JButton ok;
	
	public AboutFrame(JFrame jFrame) {
		super(jFrame, true);
		label = new JLabel();
		ok = new JButton("ok");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
			
		});
		init();
	}
	
	private void init() {
		this.setLayout(new BorderLayout());
		
		label.setText(
			"<html><body>" +
			"<b>FUCMS by FUN GmbH</b><br>" +
			"Version: 0.1<br><br>" +
			"This product is a simple Prototype to show<br>" +
			"some Features of our Database-Design<br><br>"+
			"(c) Copyright FUN GmbH 2007.  All rights reserved.<br><br>"+
			"</body></html>");
		
		
		this.getContentPane().add(label, BorderLayout.CENTER);
		this.getContentPane().add(ok, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("FUN GmbH");
		pack();
		setVisible(true);
	}
}
