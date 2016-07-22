/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mylierp.system.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

import com.mylierp.general.db.LoginDbService;
import com.mylierp.general.view.MainFrame;

/**
 *
 * @author Administrator
 */
public class LoginFrame extends JFrame {

    private JPanel panelMain;
    private JLabel labelUser;
    private JLabel labelPassword;
    private JTextField textUser;
    private JPasswordField textPassword;
    private JButton buttonLogin;
    private JButton buttonCancel;

    public LoginFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(500, 300));
        setAlwaysOnTop(true);
        setTitle("MyliERP Login");
        setResizable(false);
        setLocationRelativeTo(null); // Set Frame in Center of Screen
        add(createContent());
    }

    private JPanel createContent() {
        panelMain = new JPanel();
        panelMain.setBackground(Color.DARK_GRAY);

        labelUser = new JLabel("User Name");
        labelUser.setFont(new Font(Font.SANS_SERIF, 1, 15));
        labelUser.setForeground(Color.white);

        labelPassword = new JLabel("Password");
        labelPassword.setFont(new Font(Font.SANS_SERIF, 1, 15));
        labelPassword.setForeground(Color.white);

        textUser = new JTextField();

        textPassword = new JPasswordField();

        buttonLogin = new JButton("Login");
        buttonLogin.setFont(new Font(Font.SANS_SERIF, 1, 14));
        buttonLogin.setIcon(new ImageIcon(getClass().getResource("/images/Login_in-32.png")));
        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonLoginActionPerformed();
            }
        });


        buttonCancel = new JButton("Cancel");
        buttonCancel.setFont(new Font(Font.SANS_SERIF, 1, 14));
        buttonCancel.setIcon(new ImageIcon(getClass().getResource("/images/Cancel-32.png")));
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonCancelActionPerformed(e);
            }
        });

        panelMain.setLayout(new SpringLayout());
        panelMain.add(labelUser, new SpringLayout.Constraints(
                Spring.constant(50),// X gap
                Spring.constant(40),// Y gap
                Spring.constant(120),// Width
                Spring.constant(70))); // Hight
        panelMain.add(labelPassword, new SpringLayout.Constraints(
                Spring.constant(50),
                Spring.constant(110),
                Spring.constant(70),
                Spring.constant(30)));
        panelMain.add(textUser, new SpringLayout.Constraints(
                Spring.constant(160),
                Spring.constant(50),
                Spring.constant(280),
                Spring.constant(40)));
        panelMain.add(textPassword, new SpringLayout.Constraints(
                Spring.constant(160),
                Spring.constant(110),
                Spring.constant(280),
                Spring.constant(40)));
        panelMain.add(buttonLogin, new SpringLayout.Constraints(
                Spring.constant(50),
                Spring.constant(180),
                Spring.constant(130),
                Spring.constant(50)));
        panelMain.add(buttonCancel, new SpringLayout.Constraints(
                Spring.constant(310),
                Spring.constant(180),
                Spring.constant(130),
                Spring.constant(50)));

        return panelMain;
    }

    private void buttonLoginActionPerformed() {
        LoginDbService loginDbService = new LoginDbService();
        if (loginDbService.isUserExist(textUser.getText())) {
            if (loginDbService.isCorrectPassword(textPassword.getText())) {
                dispose();
                new MainFrame();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Password Wrong, Please insert Correct Password", "Error", JOptionPane.ERROR_MESSAGE, null);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "User Name Wrong, Please insert Correct User Name", "Error", JOptionPane.ERROR_MESSAGE, null);
        }
    }

    private void buttonCancelActionPerformed(ActionEvent e) {
        dispose();
    }
}
