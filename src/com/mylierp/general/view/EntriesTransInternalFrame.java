package com.mylierp.general.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.alee.extended.date.WebDateField;
import com.alee.extended.layout.ToolbarLayout;
import com.alee.extended.statusbar.WebMemoryBar;
import com.alee.extended.statusbar.WebStatusBar;
import com.alee.extended.statusbar.WebStatusLabel;
import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.desktoppane.WebInternalFrame;

public class EntriesTransInternalFrame extends WebInternalFrame {

	private JTable table;
	private JButton btnInsertData;
	private JButton btnDeleteData;
	private DefaultTableModel tableModel;
	private JLabel lblEntryType;
	private JLabel lblEntryNotes;
	private JScrollPane scrollPane;
	private JLabel lblEntryDate;
	private JComboBox jcboPublishers;
	private TableColumn publisherColumn;
	private JLabel lblNewLabel;
	private JTextArea textArea;
	private JTextArea txtrDdd;
	private JButton buttonMember;
	private JPanel contentPanel;
	private JPanel panel1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new EntriesTransInternalFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EntriesTransInternalFrame() {
		super("Web frame", true, true, true, true);
		setSize(1000, 600);
        setResizable(true);
        setLocation(5, 5);
        setVisible(true);
        contentPanel = new JPanel();
        contentPanel.setBounds(5, 5, 1174, 652);
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(createToolBar(), BorderLayout.NORTH);
        contentPanel.add(createPanel(), BorderLayout.CENTER);
        contentPanel.add(createStatusBar(), BorderLayout.SOUTH);
		getContentPane().add(contentPanel);
	}

	private JPanel createPanel() {
		panel1 = new JPanel();
		panel1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel1.setBackground(new Color(255, 255, 255));
		panel1.setLayout(null);

		lblNewLabel = new JLabel("Entry Code");
		lblNewLabel.setBounds(54, 16, 71, 14);
		panel1.add(lblNewLabel);

		lblEntryType = new JLabel("Entry Type");
		lblEntryType.setBounds(54, 56, 71, 14);
		panel1.add(lblEntryType);

		lblEntryNotes = new JLabel("Entry Notes");
		lblEntryNotes.setBounds(54, 96, 73, 14);
		panel1.add(lblEntryNotes);

		lblEntryDate = new JLabel("Entry Date");
		lblEntryDate.setBounds(408, 16, 71, 14);
		panel1.add(lblEntryDate);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 148, 942, 341);
		panel1.add(scrollPane);

		table = new JTable();
		table.setForeground(Color.WHITE);
		table.setBackground(new Color(47, 79, 79));
		table.setFillsViewportHeight(true);

		// Create table data
		Object[][] rowData = {
				{ "Introduction to Java Programming", 120, "Que Education & Training",
						new GregorianCalendar(1998, 1 - 1, 6).getTime(), false },
				{ "Introduction to Java Programming, 2E", 220, "Que Education & Training",
						new GregorianCalendar(1999, 1 - 1, 6).getTime(), false },
				{ "Introduction to Java Programming, 3E", 220, "Prentice Hall",
						new GregorianCalendar(2000, 12 - 1, 0).getTime(), true }, };

		tableModel = new DefaultTableModel(rowData,
				new String[] { "Entry No", "Account Code", "Account Name", "Debit", "Credit", });
		table.setModel(tableModel);
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setRowHeight(40);
		// Create a combo box for publishers
		jcboPublishers = new JComboBox();
		jcboPublishers.addItem("Prentice Hall");
		jcboPublishers.addItem("Que Education & Training");
		jcboPublishers.addItem("McGraw-Hill");

		// Set combo box as the editor for the publisher column
		publisherColumn = table.getColumn("Account Name");
		publisherColumn.setCellEditor(new DefaultCellEditor(jcboPublishers));

		scrollPane.setViewportView(table);

		textArea = new JTextArea();
		textArea.setForeground(new Color(255, 255, 255));
		textArea.setBackground(new Color(47, 79, 79));
		textArea.setBounds(135, 91, 488, 34);
		panel1.add(textArea);

        String[] items = { "Element 1", "Element 2", "Element 3" };
        WebComboBox comboBox = new WebComboBox ( items );
        comboBox.setBackground(new Color(135, 206, 235));
        comboBox.setExpandedBgColor(new Color(64, 224, 208));
        comboBox.setBounds(135, 51, 241, 34);
        panel1.add(comboBox);

		txtrDdd = new JTextArea();
		txtrDdd.setText("ddd");
		txtrDdd.setForeground(new Color(255, 255, 255));
		txtrDdd.setBackground(new Color(47, 79, 79));
		txtrDdd.setBounds(135, 11, 241, 34);
		panel1.add(txtrDdd);

		WebDateField dateField = new WebDateField ();
		dateField.setForeground(new Color(255, 255, 255));

		dateField.setBackground(new Color(47, 79, 79));
		dateField.setBounds(469, 11, 154, 29);
		panel1.add(dateField);

		btnInsertData = new JButton("Insert Data");
		btnInsertData.setBounds(763, 114, 109, 23);
		btnInsertData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() >= 0)
					tableModel.insertRow(table.getSelectedRow(), new java.util.Vector());
				else
					tableModel.addRow(new java.util.Vector());
			}
		});
		panel1.add(btnInsertData);

		btnDeleteData = new JButton("Delete Data");
		btnDeleteData.setBounds(763, 82, 109, 23);
		btnDeleteData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() >= 0)
					tableModel.removeRow(table.getSelectedRow());
			}
		});
		panel1.add(btnDeleteData);
		return panel1;
	}
	
    private JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar();
        buttonMember = new JButton("Member", new ImageIcon(getClass().getResource("/images/blog_add-32.png")));
        buttonMember.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				//AccountsTransInternalFrame accountsInternalFrame = new AccountsTransInternalFrame();
            	//accountsInternalFrame.setVisible(true);;
            }
        });
        JButton button2 = new JButton("Parent", new ImageIcon(getClass().getResource("/images/Add-32.png")));
        JButton button3 = new JButton("Properties", new ImageIcon(getClass().getResource("/images/blog_add-32.png")));
        JButton button4 = new JButton("Search", new ImageIcon(getClass().getResource("/images/Add-32.png")));
        JButton button5 = new JButton("Update", new ImageIcon(getClass().getResource("/images/ok.png")));
        JButton button6 = new JButton("Copy", new ImageIcon(getClass().getResource("/images/blog_compose-32.png")));
        JButton button7 = new JButton("Cut", new ImageIcon(getClass().getResource("/images/blog_add-32.png")));
        JButton button8 = new JButton("Past", new ImageIcon(getClass().getResource("/images/Add-32.png")));
        JButton button9 = new JButton("Delete", new ImageIcon(getClass().getResource("/images/blog_delete-32.png")));
        toolBar.add(buttonMember);
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
