/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mylierp.general.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import com.alee.extended.layout.ToolbarLayout;
import com.alee.extended.statusbar.WebMemoryBar;
import com.alee.extended.statusbar.WebStatusBar;
import com.alee.extended.statusbar.WebStatusLabel;
import com.alee.laf.desktoppane.WebInternalFrame;
import com.mylierp.general.bean.AccountsBean;
import com.mylierp.general.db.AccountsDbService;
import java.awt.Color;

/**
 *
 * @author Administrator
 */
public class AccountsInternalFrame extends WebInternalFrame {

    //private MainFrame mainFrame = new MainFrame();
    private JButton buttonMember;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AccountsInternalFrame frame = new AccountsInternalFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AccountsInternalFrame() {
        super("Web frame", true, true, true, true);
        setClosable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().add(createPanel());
        //Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
        //setBounds((ss.width - 900) / 2, (ss.height - 600) / 2, 900, 600);
        setSize(900, 600);
        setResizable(true);
        setLocation(40, 20);
        setVisible(true);
    }

    private JPanel createPanel() {
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        panel1.add(createToolBar(), BorderLayout.NORTH);
        JSplitPane splitPane = new JSplitPane();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(300, 100));
        scrollPane.setViewportView(createTree());
        splitPane.setLeftComponent(scrollPane);
        JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.setViewportView(createTable());
        splitPane.setRightComponent(scrollPane2);
        panel1.add(splitPane, BorderLayout.CENTER);
        panel1.add(createStatusBar(), BorderLayout.SOUTH);
        return panel1;
    }

    private JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar();
        JButton button1 = new JButton("Parent", new ImageIcon(getClass().getResource("/images/blog_add-32.png")));
        JButton button2 = new JButton("Parent", new ImageIcon(getClass().getResource("/images/Add-32.png")));
        JButton button3 = new JButton("Properties", new ImageIcon(getClass().getResource("/images/blog_add-32.png")));
        JButton button4 = new JButton("Search", new ImageIcon(getClass().getResource("/images/Add-32.png")));
        JButton button5 = new JButton("Update", new ImageIcon(getClass().getResource("/images/ok.png")));
        JButton button6 = new JButton("Copy", new ImageIcon(getClass().getResource("/images/blog_compose-32.png")));
        JButton button7 = new JButton("Cut", new ImageIcon(getClass().getResource("/images/blog_add-32.png")));
        JButton button8 = new JButton("Past", new ImageIcon(getClass().getResource("/images/Add-32.png")));
        JButton button9 = new JButton("Delete", new ImageIcon(getClass().getResource("/images/blog_delete-32.png")));
        toolBar.add(button1);
        toolBar.add(button2);
        toolBar.add(button3);
        toolBar.add(button4);
        toolBar.add(button5);
        toolBar.add(button6);
        toolBar.add(button7);
        toolBar.add(button8);
        toolBar.add(button9);
        return toolBar;
    }

    private JTree createTree() {

        AccountsDbService accountsDbService = new AccountsDbService();
        AccountsBean[] allRows = accountsDbService.selectAll();
        int rowCount = allRows.length; //Get the total number of row in database table 

        String[] nodeId = new String[rowCount];  //The node Id
        String[] parentId = new String[rowCount];  //A pointer to its parent Id
        DefaultMutableTreeNode rootName = new DefaultMutableTreeNode("Accounts Tree"); //Root Name
        DefaultMutableTreeNode[] nodeNames = new DefaultMutableTreeNode[rowCount]; //The node names

        for (int i = 0; i < rowCount; i++) {
            String name = allRows[i].getAccount_name();
            Boolean bool = allRows[i].getIs_group();

            nodeNames[i] = new DefaultMutableTreeNode(name, bool);
            nodeId[i] = allRows[i].getAccount_code();
            parentId[i] = allRows[i].getGroup_code();

            //Set as root node when the pointer is null               
            if (parentId[i].equals("null")) {
                rootName.add(nodeNames[i]);
            }
        }

        for (int x = 0; x < rowCount; x++) {
            for (int y = 0; y < rowCount; y++) {
                if (parentId[y].equals(nodeId[x]) && nodeNames[x] != null) {
                    nodeNames[x].add(nodeNames[y]);
                }
            }
        }
        //tree.setShowsRootHandles(false);
        //tree.putClientProperty("JTree.lineStyle", "Horizontal");
        JTree tree = new JTree(rootName);
        return tree;
    }

    private JTable createTable() {
        JTable table = new JTable();
        table.setForeground(new Color(255, 255, 255));
        table.setBackground(new Color(47, 79, 79));
        table.setFillsViewportHeight(true);
        table.setColumnSelectionAllowed(true);
        table.setCellSelectionEnabled(true);
        String headers[] = {"Account Code", "Account Name"};
        DefaultTableModel tableModel;
        AccountsDbService accountsDbService = new AccountsDbService();
        AccountsBean[] allRows = accountsDbService.selectAll();
        int numberOfAttributes = 2;
        Object[][] data = new Object[allRows.length][numberOfAttributes];
        for (int i = 0; i < data.length; i++) {
            Object[] row = new Object[numberOfAttributes];
            row[0] = allRows[i].getAccount_code();
            row[1] = allRows[i].getAccount_name();
            data[i] = row;
        }
        tableModel = new DefaultTableModel(data, headers);
        table.setModel(tableModel);

        return table;
    }
    
	private WebStatusBar createStatusBar() {
		// Simple status bar
		WebStatusBar statusBar = new WebStatusBar();

		// Simple label
		statusBar.add(new WebStatusLabel("Just a simple status bar",
				new ImageIcon(getClass().getResource("/icons/statusbar/info.png"))));

		// Simple memory bar
		WebMemoryBar memoryBar = new WebMemoryBar();
		memoryBar.setPreferredWidth(memoryBar.getPreferredSize().width + 20);
		statusBar.add(memoryBar, ToolbarLayout.END);

		return statusBar;
	}

}
