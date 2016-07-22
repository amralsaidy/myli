/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mylierp.system.view;

import com.alee.laf.WebLookAndFeel;
import com.mylierp.general.view.MainFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

/**
 *
 * @author Administrator
 */
public class StartSplash extends JFrame {

    Timer t;
    int i, j;

    public StartSplash() {
    	// Look and Feel
        WebLookAndFeel.install();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(500, 300));
        setAlwaysOnTop(true);
        setUndecorated(true);  // Disable Border of Frame
        setResizable(false);
        setLocationRelativeTo(null); // Set Frame in Center of Screen
        add(createContent());
        // pack();
        t.start();
    }

    private JPanel createContent() {
        t = new Timer(190, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j++;
                i++;
                if (j > 4) {
                    j = 0;
                    if (i > 15) {
                        t.stop();
                        dispose();
                        new MainFrame();
                        //new Main_Jdialog(null, true).setVisible(true);
                    }
                }
            }
        });
        
        JPanel panel = new JPanel();
        JLabel label1 = new JLabel(new ImageIcon(getClass().getResource("/images/splash.png")));
        BorderLayout panelLayout = new BorderLayout();
        panel.setLayout(panelLayout);
        panel.add(label1, BorderLayout.NORTH);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                t.stop();
                dispose();
				// Enabling frame decoration
				WebLookAndFeel.setDecorateFrames(true);
				
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
                //new LoginFrame().setVisible(true);
            }

        });
        return panel;
    }

    public static void main(String[] args) {

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new StartSplash().setVisible(true);
            }
        });
    }

}
