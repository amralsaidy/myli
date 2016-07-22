package com.mylierp.general.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.AncestorEvent;

import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

import com.alee.extended.image.DisplayType;
import com.alee.extended.image.WebImage;
import com.alee.extended.label.WebLinkLabel;
import com.alee.extended.layout.ToolbarLayout;
import com.alee.extended.painter.TexturePainter;
import com.alee.extended.statusbar.WebMemoryBar;
import com.alee.extended.statusbar.WebStatusBar;
import com.alee.extended.statusbar.WebStatusLabel;
import com.alee.extended.transition.ComponentTransition;
import com.alee.extended.transition.effects.Direction;
import com.alee.extended.transition.effects.curtain.CurtainTransitionEffect;
import com.alee.extended.transition.effects.curtain.CurtainType;
import com.alee.global.StyleConstants;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.button.WebButton;
import com.alee.laf.desktoppane.WebDesktopPane;
import com.alee.laf.tabbedpane.WebTabbedPane;
import com.alee.laf.toolbar.WebToolBar;
import com.alee.utils.swing.AncestorAdapter;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JFlashPlayer;

public class MainFrame extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MainFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JMenuBar menuBar;
	private JMenuItem menuItemAccounts;
	private JMenu menuSystem;
	private JMenu menuGLedger;
	private JMenu menuSales;
	private JMenu menuCustomers;
	private JMenu menuPurchases;
	private JMenu menuSuppliers;
	private JMenu menuInventory;
	private JMenu menuProduction;
	private WebLinkLabel webLinkLabelEntries;
	private WebLinkLabel webLinkLabelReports;
	private WebLinkLabel webLinkLabelAccounts;
	private JSplitPane splitPane;
	private JScrollPane scrollPane;
	private WebTabbedPane tabbedPane;
	private AccountsInternalFrame accountsInternalFrame;
	private WebDesktopPane desktopPane;
	private EntriesTransInternalFrame entriesTransInternalFrame;
	private JPanel panel1;
	private WebToolBar toolBar;
	private ComponentTransition componentTransition;
	private TrialBalanceInternalFrame trialBalanceInternalFrame;

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		super();
		NativeInterface.open();
		// Look and Feel
		WebLookAndFeel.install();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// setBounds(100, 100, 450, 300);
		setSize(new Dimension(1280, 770));
		setJMenuBar(createJMenuBar());
		panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		panel1.add(createJToolBar(), BorderLayout.NORTH);
		panel1.add(createJSplitPane(), BorderLayout.CENTER);
		panel1.add(createStatusBar(), BorderLayout.SOUTH);
		add(panel1);
		// add(createComponentTransition(panel1));
		setVisible(true);
		// NativeInterface.runEventPump();
	}

	private ComponentTransition createComponentTransition(JComponent component) {

		// Base content
		componentTransition = new ComponentTransition(createBackgroundPanel()) {
			@Override
			public Dimension getPreferredSize() {
				return component.getPreferredSize();
			}
		};

		final CurtainTransitionEffect effect = new CurtainTransitionEffect();
		effect.setDirection(Direction.down);
		effect.setType(CurtainType.fade);
		componentTransition.setTransitionEffect(effect);
		componentTransition.addAncestorListener(new AncestorAdapter() {
			@Override
			public void ancestorAdded(final AncestorEvent event) {
				componentTransition.delayTransition(1000, component);
			}
		});

		return componentTransition;
	}

	private JComponent createBackgroundPanel() {
		final WebImage wi = new WebImage(new ImageIcon(getClass().getResource("/icons/text.png"))) {
			@Override
			protected void paintComponent(final Graphics g) {
				final Graphics2D g2d = (Graphics2D) g;
				g2d.setPaint(new LinearGradientPaint(0, 0, 0, getHeight(), new float[] { 0f, 0.4f, 0.6f, 1f },
						new Color[] { StyleConstants.bottomBgColor, Color.WHITE, Color.WHITE,
								StyleConstants.bottomBgColor }));
				g2d.fill(g2d.getClip() != null ? g2d.getClip() : getVisibleRect());

				super.paintComponent(g);
			}
		};
		wi.setDisplayType(DisplayType.preferred);
		wi.setHorizontalAlignment(SwingConstants.CENTER);
		wi.setVerticalAlignment(SwingConstants.CENTER);
		return wi;
	}

	private JSplitPane createJSplitPane() {
		splitPane = new JSplitPane();
		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(250, 100));
		scrollPane.setViewportView(createJXTaskPane());
		splitPane.setLeftComponent(scrollPane);
		splitPane.setRightComponent(createJTabbedPane());
		return splitPane;
	}

	private JMenuBar createJMenuBar() {
		menuBar = new JMenuBar();
		menuSystem = new JMenu("System");
		menuGLedger = new JMenu("G. Ledger");
		menuSales = new JMenu("Sales");
		menuCustomers = new JMenu("Customers");
		menuPurchases = new JMenu("Purchases");
		menuSuppliers = new JMenu("Suppliers");
		menuInventory = new JMenu("Inventory");
		menuProduction = new JMenu("Production");
		menuItemAccounts = new JMenuItem("Accounts");
		menuItemAccounts.addActionListener(new ActionListener() {
			// AccountsFrame accountsFrame = new AccountsFrame();

			@Override
			public void actionPerformed(ActionEvent arg0) {
				menuItemAccountsActionPerformed();
			}
		});
		menuGLedger.add(menuItemAccounts);

		menuBar.add(menuSystem);
		menuBar.add(menuGLedger);
		menuBar.add(menuSales);
		menuBar.add(menuCustomers);
		menuBar.add(menuPurchases);
		menuBar.add(menuSuppliers);
		menuBar.add(menuInventory);
		menuBar.add(menuProduction);
		return menuBar;
	}

	public static JComponent createJFlash() {
		JFlashPlayer flashPlayer = new JFlashPlayer();
		// flashPlayer.load(MainFrame.class, "/resources/Accounts.swf");
		return flashPlayer;
	}

	private JXTaskPaneContainer createJXTaskPane() {
		JXTaskPaneContainer taskPaneContainer = new JXTaskPaneContainer();

		JXTaskPane gledgerTaskPane = new JXTaskPane();
		gledgerTaskPane.setTitle("G. Ledger");

		webLinkLabelAccounts = new WebLinkLabel("Account Transactions",
				new ImageIcon(getClass().getResource("/images/3D-Sheet-Of-Paper-32.png")));
		webLinkLabelAccounts.setForeground(Color.darkGray);
		webLinkLabelAccounts.setVisitedForeground(Color.darkGray);
		webLinkLabelAccounts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				webLinkLabelAccountsMouseClicked();
			}

		});
		webLinkLabelEntries = new WebLinkLabel("Entry Transactions",
				new ImageIcon(getClass().getResource("/images/3D-Sheet-Of-Paper-32.png")));
		webLinkLabelEntries.setForeground(Color.darkGray);
		webLinkLabelEntries.setVisitedForeground(Color.darkGray);
		webLinkLabelEntries.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				webLinkLabelEntriesMouseClicked();
			}

		});
		webLinkLabelReports = new WebLinkLabel("Reports",
				new ImageIcon(getClass().getResource("/images/3D-Sheet-Of-Paper-32.png")));
		webLinkLabelReports.setForeground(Color.darkGray);
		webLinkLabelReports.setVisitedForeground(Color.darkGray);
		webLinkLabelReports.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				webLinkLabelReportsMouseClicked();
			}
		});
		gledgerTaskPane.add(webLinkLabelAccounts);
		gledgerTaskPane.add(webLinkLabelEntries);
		gledgerTaskPane.add(webLinkLabelReports);
		taskPaneContainer.add(gledgerTaskPane);

		JXTaskPane salesTaskPane = new JXTaskPane();
		salesTaskPane.setTitle("Sales");
		taskPaneContainer.add(salesTaskPane);

		JXTaskPane customersTaskPane = new JXTaskPane();
		customersTaskPane.setTitle("Customers");
		taskPaneContainer.add(customersTaskPane);

		JXTaskPane purchasesTaskPane = new JXTaskPane();
		purchasesTaskPane.setTitle("Purchases");
		taskPaneContainer.add(purchasesTaskPane);

		JXTaskPane suppliersTaskPane = new JXTaskPane();
		suppliersTaskPane.setTitle("Supplies");
		taskPaneContainer.add(suppliersTaskPane);

		JXTaskPane inventoryTaskPane = new JXTaskPane();
		inventoryTaskPane.setTitle("Inventory");
		taskPaneContainer.add(inventoryTaskPane);

		JXTaskPane productionTaskPane = new JXTaskPane();
		productionTaskPane.setTitle("Production");
		taskPaneContainer.add(productionTaskPane);

		return taskPaneContainer;
	}

	private WebToolBar createJToolBar() {
		toolBar = new WebToolBar(WebToolBar.HORIZONTAL);
		WebButton webButton1 = new WebButton(new ImageIcon(getClass().getResource("/images/toolbar/save.png")));
		webButton1.setRolloverDecoratedOnly(true);
		WebButton webButton2 = new WebButton(new ImageIcon(getClass().getResource("/images/toolbar/saveall.png")));
		webButton2.setRolloverDecoratedOnly(true);
		WebButton webButton3 = new WebButton(new ImageIcon(getClass().getResource("/images/toolbar/cut.png")));
		webButton3.setRolloverDecoratedOnly(true);
		WebButton webButton4 = new WebButton(new ImageIcon(getClass().getResource("/images/toolbar/copy.png")));
		webButton4.setRolloverDecoratedOnly(true);
		WebButton webButton5 = new WebButton(new ImageIcon(getClass().getResource("/images/toolbar/paste.png")));
		webButton5.setRolloverDecoratedOnly(true);
		WebButton webButton6 = new WebButton(new ImageIcon(getClass().getResource("/images/toolbar/settings.png")));
		webButton6.setRolloverDecoratedOnly(true);
		toolBar.add(webButton1);
		toolBar.add(webButton2);
		toolBar.addSeparator();
		toolBar.add(webButton3);
		toolBar.add(webButton4);
		toolBar.add(webButton5);
		toolBar.add(webButton6);
		// toolBar.addToEnd(webButton6);
		return toolBar;
	}

	private JComponent createJTabbedPane() {
		// Tab background painters
		TexturePainter tp1 = new TexturePainter(new ImageIcon(getClass().getResource("/icons/tabbedpane/bg1.jpg")));
		TexturePainter tp2 = new TexturePainter(new ImageIcon(getClass().getResource("/icons/tabbedpane/bg1.jpg")));
		TexturePainter tp3 = new TexturePainter(new ImageIcon(getClass().getResource("/icons/tabbedpane/bg1.jpg")));
		TexturePainter tp4 = new TexturePainter(new ImageIcon(getClass().getResource("/icons/tabbedpane/bg1.jpg")));

		tabbedPane = new WebTabbedPane();
		// tabbedPane.setPreferredSize(new Dimension(300, 200));

		tabbedPane.addTab("Tab 1", createJDesktopPane());
		tabbedPane.setBackgroundPainterAt(tabbedPane.getTabCount() - 1, tp1);
		tabbedPane.setSelectedForegroundAt(tabbedPane.getTabCount() - 1, Color.WHITE);

		// tabbedPane.addTab("Tab 2", createJDesktopPane());
		tabbedPane.setBackgroundPainterAt(tabbedPane.getTabCount() - 1, tp2);
		tabbedPane.setSelectedForegroundAt(tabbedPane.getTabCount() - 1, Color.WHITE);

		// tabbedPane.addTab("Tab 3", createJFlash());
		// tabbedPane.setBackgroundPainterAt(tabbedPane.getTabCount() - 1, tp3);
		// tabbedPane.setSelectedForegroundAt(tabbedPane.getTabCount() - 1,
		// Color.WHITE);
		return tabbedPane;
	}

	private JComponent createJDesktopPane() {
		desktopPane = new WebDesktopPane();
		desktopPane.setOpaque(false);
		createAccountsIcon(desktopPane);
		createEntriesIcon(desktopPane);
		createTrialBalanceIcon(desktopPane);
		return desktopPane;
	}

	private void createAccountsIcon(final WebDesktopPane desktopPane) {
		accountsInternalFrame = new AccountsInternalFrame();

		accountsInternalFrame.setFrameIcon(new ImageIcon(getClass().getResource("/icons/frame.png")));

		final WebButton internalFrameIcon = new WebButton("Accounts",
				new ImageIcon(getClass().getResource("/icons/webframe.png")));
		internalFrameIcon.setRolloverDecoratedOnly(true);
		internalFrameIcon.setHorizontalTextPosition(WebButton.CENTER);
		internalFrameIcon.setVerticalTextPosition(WebButton.BOTTOM);
		internalFrameIcon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				if (internalFrameIcon.getClientProperty(DesktopPaneIconMoveAdapter.DRAGGED_MARK) != null) {
					return;
				}
				if (accountsInternalFrame.isClosed()) {
					if (accountsInternalFrame.getParent() == null) {
						desktopPane.add(accountsInternalFrame);
					}
					accountsInternalFrame.open();
					accountsInternalFrame.setIcon(false);
				} else {
					accountsInternalFrame.setIcon(!accountsInternalFrame.isIcon());
				}
			}
		});
		final DesktopPaneIconMoveAdapter ma1 = new DesktopPaneIconMoveAdapter();
		internalFrameIcon.addMouseListener(ma1);
		internalFrameIcon.addMouseMotionListener(ma1);
		internalFrameIcon.setBounds(25, 25, 100, 75);
		desktopPane.add(internalFrameIcon);
		accountsInternalFrame.close();
	}

	private void createEntriesIcon(final WebDesktopPane desktopPane) {
		entriesTransInternalFrame = new EntriesTransInternalFrame();
		entriesTransInternalFrame.setFrameIcon(new ImageIcon(getClass().getResource("/icons/frame.png")));

		final WebButton internalFrameIcon = new WebButton("Entries",
				new ImageIcon(getClass().getResource("/icons/webframe.png")));
		internalFrameIcon.setRolloverDecoratedOnly(true);
		internalFrameIcon.setHorizontalTextPosition(WebButton.CENTER);
		internalFrameIcon.setVerticalTextPosition(WebButton.BOTTOM);
		internalFrameIcon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				if (internalFrameIcon.getClientProperty(DesktopPaneIconMoveAdapter.DRAGGED_MARK) != null) {
					return;
				}
				if (entriesTransInternalFrame.isClosed()) {
					if (entriesTransInternalFrame.getParent() == null) {
						desktopPane.add(entriesTransInternalFrame);
					}
					entriesTransInternalFrame.open();
					entriesTransInternalFrame.setIcon(false);
				} else {
					entriesTransInternalFrame.setIcon(!entriesTransInternalFrame.isIcon());
				}
			}
		});
		final DesktopPaneIconMoveAdapter ma1 = new DesktopPaneIconMoveAdapter();
		internalFrameIcon.addMouseListener(ma1);
		internalFrameIcon.addMouseMotionListener(ma1);
		internalFrameIcon.setBounds(120, 25, 100, 75);
		desktopPane.add(internalFrameIcon);
		entriesTransInternalFrame.close();
	}

	private void createTrialBalanceIcon(final WebDesktopPane desktopPane) {
		trialBalanceInternalFrame = new TrialBalanceInternalFrame();
		trialBalanceInternalFrame.setFrameIcon(new ImageIcon(getClass().getResource("/icons/frame.png")));

		final WebButton internalFrameIcon = new WebButton("Trial Balance",
				new ImageIcon(getClass().getResource("/icons/webframe.png")));
		internalFrameIcon.setRolloverDecoratedOnly(true);
		internalFrameIcon.setHorizontalTextPosition(WebButton.CENTER);
		internalFrameIcon.setVerticalTextPosition(WebButton.BOTTOM);
		internalFrameIcon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				if (internalFrameIcon.getClientProperty(DesktopPaneIconMoveAdapter.DRAGGED_MARK) != null) {
					return;
				}
				if (trialBalanceInternalFrame.isClosed()) {
					if (trialBalanceInternalFrame.getParent() == null) {
						desktopPane.add(trialBalanceInternalFrame);
					}
					trialBalanceInternalFrame.open();
					trialBalanceInternalFrame.setIcon(false);
				} else {
					trialBalanceInternalFrame.setIcon(!trialBalanceInternalFrame.isIcon());
				}
			}
		});
		final DesktopPaneIconMoveAdapter ma1 = new DesktopPaneIconMoveAdapter();
		internalFrameIcon.addMouseListener(ma1);
		internalFrameIcon.addMouseMotionListener(ma1);
		internalFrameIcon.setBounds(220, 25, 100, 75);
		desktopPane.add(internalFrameIcon);
		trialBalanceInternalFrame.close();
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

	private void menuItemAccountsActionPerformed() {
		// new AccountsInternalFrame().setVisible(true);
	}

	private void webLinkLabelAccountsMouseClicked() {

		if (accountsInternalFrame.isClosed()) {
			if (accountsInternalFrame.getParent() == null) {
				desktopPane.add(accountsInternalFrame);
			}
			accountsInternalFrame.open();
			accountsInternalFrame.setIcon(false);
		} else {
			accountsInternalFrame.setIcon(!accountsInternalFrame.isIcon());
		}

	}

	private void webLinkLabelEntriesMouseClicked() {

		if (entriesTransInternalFrame.isClosed()) {
			if (entriesTransInternalFrame.getParent() == null) {
				desktopPane.add(entriesTransInternalFrame);
			}
			entriesTransInternalFrame.open();
			entriesTransInternalFrame.setIcon(false);
		} else {
			entriesTransInternalFrame.setIcon(!entriesTransInternalFrame.isIcon());
		}

	}

	private void webLinkLabelReportsMouseClicked() {
		if (trialBalanceInternalFrame.isClosed()) {
			if (trialBalanceInternalFrame.getParent() == null) {
				desktopPane.add(trialBalanceInternalFrame);
			}
			trialBalanceInternalFrame.open();
			trialBalanceInternalFrame.setIcon(false);
		} else {
			trialBalanceInternalFrame.setIcon(!trialBalanceInternalFrame.isIcon());
		};
	}
}

class DesktopPaneIconMoveAdapter extends MouseAdapter {

	public static final String DRAGGED_MARK = "was.dragged";

	private boolean dragging = false;
	private Point startPoint = null;
	private Rectangle startBounds = null;

	@Override
	public void mousePressed(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) {
			dragging = true;
			startPoint = e.getLocationOnScreen();
			startBounds = e.getComponent().getBounds();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (dragging) {
			e.getComponent().setBounds(new Rectangle(startBounds.x + e.getLocationOnScreen().x - startPoint.x,
					startBounds.y + e.getLocationOnScreen().y - startPoint.y, startBounds.width, startBounds.height));
			if (e.getComponent() instanceof JComponent) {
				((JComponent) e.getComponent()).putClientProperty(DRAGGED_MARK, true);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e) && dragging) {
			Rectangle bounds = e.getComponent().getBounds();

			Container parent = e.getComponent().getParent();
			boolean setBounds = false;
			for (int i = 25; i < parent.getWidth(); i += 125) {
				for (int j = 25; j < parent.getHeight(); j += 100) {
					Rectangle cell = new Rectangle(i, j, 100, 75);
					if (cell.intersects(bounds)) {
						Rectangle intersection = cell.intersection(bounds);
						if (intersection.width * intersection.height >= bounds.width * bounds.height / 8) {
							e.getComponent().setBounds(cell);
							setBounds = true;
							break;
						}
					}
				}
				if (setBounds) {
					break;
				}
			}

			if (e.getComponent() instanceof JComponent) {
				((JComponent) e.getComponent()).putClientProperty(DRAGGED_MARK, null);
			}
			dragging = false;
		}
	}
}
