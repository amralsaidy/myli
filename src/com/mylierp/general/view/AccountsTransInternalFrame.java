package com.mylierp.general.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.alee.laf.desktoppane.WebInternalFrame;
import com.mylierp.general.bean.AccountsBean;
import com.mylierp.general.db.AccountsDbService;

public class AccountsTransInternalFrame extends WebInternalFrame {

    private JTextField txtAccountCode;
    private JTextField txtAccountName;
    private JTextField txtGroupCode;
    private JTextField txtGroupName;
    private final ButtonGroup buttonGroup1 = new ButtonGroup();
    private final ButtonGroup buttonGroup2 = new ButtonGroup();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	AccountsTransInternalFrame frame = new AccountsTransInternalFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private final JRadioButton rdbtnMember;
    private final JRadioButton rdbtnGroup;
    private final JRadioButton rdbtnDebit;
    private final JRadioButton rdbtnCredit;
    private final JRadioButton rdbtnNone;
    private boolean isGroup = true;
    private String nature = "";

    /**
     * Create the frame.
     */
    public AccountsTransInternalFrame() {
    	//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(SystemColor.menu);
		Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((ss.width - 700) / 2, (ss.height - 550) / 2,	700, 550);
        setResizable(true);
		setVisible(true);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setForeground(new Color(128, 0, 128));
        panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Main", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel.setBackground(SystemColor.menu);
        panel.setBounds(23, 11, 571, 131);
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Account Code");
        lblNewLabel.setForeground(SystemColor.controlText);
        lblNewLabel.setBounds(46, 32, 89, 14);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_2 = new JLabel("Account Name");
        lblNewLabel_2.setForeground(SystemColor.controlText);
        lblNewLabel_2.setBounds(46, 73, 100, 14);
        panel.add(lblNewLabel_2);

        txtAccountCode = new JTextField();
        txtAccountCode.setBounds(145, 24, 153, 30);
        panel.add(txtAccountCode);
        txtAccountCode.setColumns(10);

        txtAccountName = new JTextField();
        txtAccountName.setColumns(10);
        txtAccountName.setBounds(145, 65, 335, 30);
        panel.add(txtAccountName);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setForeground(SystemColor.controlText);
        tabbedPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        tabbedPane.setBounds(23, 145, 571, 258);
        getContentPane().add(tabbedPane);

        JPanel panel_2 = new JPanel();
        panel_2.setForeground(new Color(128, 0, 128));
        panel_2.setBorder(new EmptyBorder(0, 0, 0, 0));
        panel_2.setBackground(SystemColor.textInactiveText);
        tabbedPane.addTab("New tab", null, panel_2, null);
        panel_2.setLayout(null);

        JLabel lblGroupCode = new JLabel("Group Code");
        lblGroupCode.setForeground(SystemColor.info);
        lblGroupCode.setBounds(33, 18, 68, 14);
        panel_2.add(lblGroupCode);

        JLabel lblGroupName = new JLabel("Group Name");
        lblGroupName.setForeground(SystemColor.info);
        lblGroupName.setBounds(32, 55, 88, 14);
        panel_2.add(lblGroupName);

        JLabel lblType = new JLabel("Type");
        lblType.setForeground(SystemColor.info);
        lblType.setBounds(32, 94, 46, 14);
        panel_2.add(lblType);

        txtGroupCode = new JTextField();
        txtGroupCode.setColumns(10);
        txtGroupCode.setBounds(139, 11, 137, 29);
        panel_2.add(txtGroupCode);

        txtGroupName = new JTextField();
        txtGroupName.setColumns(10);
        txtGroupName.setBounds(140, 48, 330, 29);
        panel_2.add(txtGroupName);

        JLabel lblNature = new JLabel("Nature");
        lblNature.setForeground(SystemColor.info);
        lblNature.setBounds(32, 129, 46, 14);
        panel_2.add(lblNature);

        rdbtnMember = new JRadioButton("Member");
        rdbtnMember.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		rdbtnMemberActionPerformed();
        	}
        });
        rdbtnMember.setForeground(SystemColor.info);
        buttonGroup1.add(rdbtnMember);
        rdbtnMember.setBackground(SystemColor.textInactiveText);
        rdbtnMember.setBounds(140, 90, 109, 23);
        panel_2.add(rdbtnMember);

        rdbtnGroup = new JRadioButton("Group");
        rdbtnGroup.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		rdbtnGroupActionPerformed();
        	}
        });
        rdbtnGroup.setForeground(SystemColor.info);
        buttonGroup1.add(rdbtnGroup);
        rdbtnGroup.setBackground(SystemColor.textInactiveText);
        rdbtnGroup.setBounds(251, 90, 109, 23);
        panel_2.add(rdbtnGroup);

        rdbtnDebit = new JRadioButton("Debit");
        rdbtnDebit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		rdbtnDebitActionPerformed();
        	}
        });
        rdbtnDebit.setForeground(SystemColor.info);
        buttonGroup2.add(rdbtnDebit);
        rdbtnDebit.setBackground(SystemColor.textInactiveText);
        rdbtnDebit.setBounds(140, 125, 109, 23);
        panel_2.add(rdbtnDebit);

        rdbtnCredit = new JRadioButton("Credit");
        rdbtnCredit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		rdbtnCreditActionPerformed();
        	}
        });
        rdbtnCredit.setForeground(SystemColor.info);
        buttonGroup2.add(rdbtnCredit);
        rdbtnCredit.setBackground(SystemColor.textInactiveText);
        rdbtnCredit.setBounds(251, 125, 109, 23);
        panel_2.add(rdbtnCredit);

        rdbtnNone = new JRadioButton("None");
        rdbtnNone.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		rdbtnNoneActionPerformed();
        	}
        });
        rdbtnNone.setForeground(SystemColor.info);
        buttonGroup2.add(rdbtnNone);
        rdbtnNone.setBackground(SystemColor.textInactiveText);
        rdbtnNone.setBounds(362, 125, 109, 23);
        panel_2.add(rdbtnNone);

        JComboBox comboBox = new JComboBox();
        comboBox.setBounds(139, 167, 162, 29);
        panel_2.add(comboBox);

        JLabel lblNewLabel_3 = new JLabel("New label");
        lblNewLabel_3.setForeground(SystemColor.info);
        lblNewLabel_3.setBounds(32, 174, 69, 14);
        panel_2.add(lblNewLabel_3);

        JPanel panel_3 = new JPanel();
        panel_3.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        tabbedPane.addTab("New tab", null, panel_3, null);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_1.setBackground(SystemColor.menu);
        panel_1.setBounds(23, 414, 571, 57);
        getContentPane().add(panel_1);
        panel_1.setLayout(null);

        JButton btnAdd = new JButton("Add");
        btnAdd.setIcon(new ImageIcon(AccountsTransInternalFrame.class.getResource("/images/blog_add-32.png")));
        btnAdd.setBounds(20, 11, 108, 35);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAddActionPerformed();
            }
        });
        panel_1.add(btnAdd);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setIcon(new ImageIcon(AccountsTransInternalFrame.class.getResource("/images/blog_delete-32.png")));
        btnDelete.setBounds(141, 11, 106, 35);
        panel_1.add(btnDelete);

        JButton button_1 = new JButton("New button");
        button_1.setBounds(257, 11, 89, 35);
        panel_1.add(button_1);

        JButton button_2 = new JButton("New button");
        button_2.setBounds(356, 11, 89, 35);
        panel_1.add(button_2);

        JButton btnClose = new JButton("Close");
        btnClose.setIcon(new ImageIcon(AccountsTransInternalFrame.class.getResource("/images/Login_in-32.png")));
        btnClose.setBounds(455, 11, 106, 35);
        panel_1.add(btnClose);
    }

    private void btnAddActionPerformed() {
        try {
            AccountsBean accountBean = new AccountsBean();
            AccountsDbService accountsDbService = new AccountsDbService();
            accountBean.setAccount_code(txtAccountCode.getText().trim());
            accountBean.setAccount_name(txtAccountName.getText().trim());
            accountBean.setGroup_code(txtGroupCode.getText().trim());
            accountBean.setGroup_name(txtGroupName.getText().trim());
            accountBean.setIs_group(isGroup);

            accountsDbService.insertAccount(accountBean);
            JOptionPane.showMessageDialog(this, "Added Successfull", "Ok", JOptionPane.DEFAULT_OPTION);
            dispose();
            new AccountsInternalFrame().setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void rdbtnMemberActionPerformed(){
    	isGroup = false;
    }
    private void rdbtnGroupActionPerformed(){
    	isGroup = true;
    }
    private void rdbtnDebitActionPerformed(){
    	nature = "debit";
    }
    private void rdbtnCreditActionPerformed(){
    	nature = "credit";
    }
    private void rdbtnNoneActionPerformed(){
    	nature = "none";
    }
    
    
}
