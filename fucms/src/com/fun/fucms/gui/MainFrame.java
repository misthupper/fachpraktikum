package com.fun.fucms.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import com.fun.fucms.EvilException;
import com.fun.fucms.controller.MainController;
import com.fun.fucms.controller.MainFrameMouseController;
import com.fun.fucms.model.PageTreeModel;

public class MainFrame extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String sAppName="FernuniCMS";
    private static final String sAppVersion="0.1";
    
    public static MainFrame sInstance;
    
    private Container mContainer;
    private MainMenu mMainMenu;
    private JScrollPane mJScrollPane, mTreeScrollPane;
    private JTextArea mJTextArea;
    private PageTree mPageTree;
    private static PageTreeModel mPageTreeModel;
    
    private MainController mMainController;
    
    public static void main(String[] args) {
        sInstance = new MainFrame(sAppName+ " " +sAppVersion);
        sInstance.init();
    }
    
    public static void log(String s) {
    	if (sInstance != null) {
    		sInstance.mJTextArea.append("\n"+s);
    		//sorgt dafür, dass immer der letzte Eintrag sichtbar ist
    		sInstance.mJTextArea.setCaretPosition(sInstance.mJTextArea.getDocument().getLength());
    	}
    }

    public MainFrame(String title) throws HeadlessException {
        super(title);
        this.setPreferredSize(new Dimension(800,600));
        try {
            //String metal="javax.swing.plaf.metal.MetalLookAndFeel";
            //String motif="com.sun.java.swing.plaf.motif.MotifLookAndFeel";
            //String windows="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
            String platform = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(platform);
        } catch (Exception e) {
        }
    }
    
    public static PageTreeModel getPageTreeModel(){
    	return mPageTreeModel;
    }
    
    public PageTree getPageTree() {
    	return mPageTree;
    }
    
    private void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        mMainMenu = new MainMenu();
        this.setJMenuBar(mMainMenu);          
        
        //content Pane
        mContainer=this.getContentPane();
        mContainer.setLayout(new BorderLayout());
        mJTextArea = new JTextArea();
        mJTextArea.setEditable(false);
        mJScrollPane = new JScrollPane(mJTextArea);
        mJScrollPane.setPreferredSize(new Dimension(100,100));
        mContainer.add(mJScrollPane, BorderLayout.SOUTH);
        
        mPageTreeModel = new PageTreeModel();
        mPageTree = new PageTree(mPageTreeModel);
        mTreeScrollPane = new JScrollPane(mPageTree);
        mContainer.add(mTreeScrollPane, BorderLayout.CENTER);
        
        mPageTree.addMouseListener(new MainFrameMouseController(mPageTreeModel));
        
        
        pack();
        setVisible(true);
        
        try {
			mMainController = new MainController(this);
			mMainMenu.setActionListener(mMainController);
	        mPageTree.getSelectionModel().addTreeSelectionListener(mMainController);
		} catch (EvilException e) {
			MainFrame.log(e.getMessage());
		}
    } 
    
}
