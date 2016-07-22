package com.mylierp.general.view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import com.alee.laf.WebLookAndFeel;
import com.alee.laf.button.WebButton;
import com.alee.laf.panel.WebPanel;

public class TestFrame extends JFrame {

	public TestFrame() {
		super();
		// Look and Feel
		WebLookAndFeel.install();
		// Base content pane
		final WebPanel contentPane = new WebPanel();
		contentPane.setLayout(new FlowLayout());

		WebButton showFrame = new WebButton("Show frame");
		showFrame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Enabling frame decoration
				
				WebLookAndFeel.setDecorateFrames(true);
				MainFrame exampleFrame = new MainFrame();
				//exampleFrame.pack();
				exampleFrame.setVisible(true);

			}
		});
		contentPane.add(showFrame);

		setLayout(new BorderLayout());
		add(contentPane, BorderLayout.NORTH);
		setSize(new Dimension(400, 400));
		setVisible(true);
	}

	public static void main(final String[] args) {
		new TestFrame();
	}
}
