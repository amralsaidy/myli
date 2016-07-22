package com.mylierp.general.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.alee.extended.layout.ToolbarLayout;
import com.alee.extended.statusbar.WebMemoryBar;
import com.alee.extended.statusbar.WebStatusBar;
import com.alee.extended.statusbar.WebStatusLabel;
import com.alee.laf.desktoppane.WebInternalFrame;
import com.mylierp.general.bean.AccountsVwBean;
import com.mylierp.general.db.AccountsDbService;

public class TrialBalanceInternalFrame extends WebInternalFrame {

    private JPanel contentPane;
    private JTable table;
    private JTextField txtFinalBalanceCr;
    private JTextField txtFinalBalanceDr;
    private JTextField txtCurrentBalanceDr;
    private JTextField txtCurrentBalanceCr;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TrialBalanceInternalFrame frame = new TrialBalanceInternalFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private AccountsDbService accountsDbService;
    private JPanel panel1;

    /**
     * Create the frame.
     */
    public TrialBalanceInternalFrame() {
        super("Web frame", true, true, true, true);
        // setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 600);
        setResizable(true);
        setLocation(5, 5);
        setVisible(true);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());
        panel1 = new JPanel();
        panel1.setBackground(Color.WHITE);
        panel1.setLayout(new BorderLayout());
        panel1.add(createJScrollPane(), BorderLayout.NORTH);
        panel1.add(createSumPanel(), BorderLayout.CENTER);
        contentPane.add(createToolBar(), BorderLayout.NORTH);
        contentPane.add(panel1, BorderLayout.CENTER);
        contentPane.add(createStatusBar(), BorderLayout.SOUTH);
    }

    private JPanel createSumPanel() {

        JPanel sumPanel = new JPanel();
        sumPanel.setBorder(new LineBorder(new Color(0, 0, 0)));

        txtCurrentBalanceCr = new JTextField();
        txtCurrentBalanceCr.setBounds(500, 11, 154, 29);
        txtCurrentBalanceCr.setForeground(Color.WHITE);
        txtCurrentBalanceCr.setBackground(new Color(47, 79, 79));
        txtCurrentBalanceCr.setColumns(10);
        txtCurrentBalanceCr.setText(accountsDbService.sumColumn("current_balance_dr"));

        txtCurrentBalanceDr = new JTextField();
        txtCurrentBalanceDr.setBounds(334, 11, 156, 29);
        txtCurrentBalanceDr.setForeground(Color.WHITE);
        txtCurrentBalanceDr.setBackground(new Color(47, 79, 79));
        txtCurrentBalanceDr.setColumns(10);
        txtCurrentBalanceDr.setText(accountsDbService.sumColumn("current_balance_cr"));

        txtFinalBalanceDr = new JTextField();
        txtFinalBalanceDr.setBounds(664, 11, 156, 29);
        txtFinalBalanceDr.setForeground(Color.WHITE);
        txtFinalBalanceDr.setBackground(new Color(47, 79, 79));
        txtFinalBalanceDr.setColumns(10);
        txtFinalBalanceDr.setText(accountsDbService.sumColumn("final_balance_dr"));

        txtFinalBalanceCr = new JTextField();
        txtFinalBalanceCr.setBounds(830, 11, 140, 29);
        txtFinalBalanceCr.setForeground(Color.WHITE);
        txtFinalBalanceCr.setBackground(new Color(47, 79, 79));
        txtFinalBalanceCr.setColumns(10);
        txtFinalBalanceCr.setText(accountsDbService.sumColumn("final_balance_cr"));

        sumPanel.setLayout(null);
        sumPanel.add(txtCurrentBalanceDr);
        sumPanel.add(txtCurrentBalanceCr);
        sumPanel.add(txtFinalBalanceDr);
        sumPanel.add(txtFinalBalanceCr);
        //sumPanel.setSize(100,300);
        return sumPanel;
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

    private JScrollPane createJScrollPane() {
        JScrollPane scrollPane = new JScrollPane();

        table = new JTable();
        table.setColumnSelectionAllowed(true);
        table.setCellSelectionEnabled(true);
        table.setFillsViewportHeight(true);
        table.setForeground(Color.WHITE);
        table.setBackground(new Color(47, 79, 79));
        String headers[] = {"Account Code", "Account Name", "Current Balance(dr)", "Current Balance(cr)",
            "Final Balance(dr) ", "Final Balance(cr)"};
        DefaultTableModel tableModel;
        accountsDbService = new AccountsDbService();
        AccountsVwBean[] allRows = accountsDbService.selectVwAll();
        int numberOfAttributes = 6;
        Object[][] data = new Object[allRows.length][numberOfAttributes];
        for (int i = 0; i < data.length; i++) {
            Object[] row = new Object[numberOfAttributes];
            row[0] = allRows[i].getAccount_code();
            row[1] = allRows[i].getAccount_name();
            row[2] = allRows[i].getCurrent_balance_dr();
            row[3] = allRows[i].getCurrent_balance_cr();
            row[4] = allRows[i].getFinal_balance_dr();
            row[5] = allRows[i].getFinal_balance_cr();
            data[i] = row;
        }
        tableModel = new DefaultTableModel(data, headers);
        table.setModel(tableModel);
        scrollPane.setViewportView(table);
        return scrollPane;
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
