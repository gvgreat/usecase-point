/**
 * @Copyrights G. Vaidhyanathan
 */
package org.ucp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Locale;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.i18n.swing.I18NAbstractAction;
import org.i18n.swing.locale.LocaleListener;
import org.i18n.swing.locale.LocaleRegistry;
import org.i18n.swing.util.DialogUtil;
import org.table.control.ControlConstants;
import org.table.control.XMLOperations;
import org.table.control.img.ImageRegistry;
import org.ucp.ecf.ECFOperations;
import org.ucp.pf.ProductivityFactor;
import org.ucp.pf.ProductivityFactorPanel;
import org.ucp.report.GenerateReportAction;
import org.ucp.report.UCPReport;
import org.ucp.tcf.TCFOperations;
import org.ucp.uaw.UAWOperations;
import org.ucp.uucw.UUCWOperations;

/**
 * @author t_vaidhyanathan
 */
public class UCPApplication {

	@SuppressWarnings("unchecked")
	private final XMLOperations[] operations;
	private final ProductivityFactorPanel pfPanel;
	private final UCPReport report;
	private final Action generateReport;
	private final Action estimate;
	private final Messages ucpMsg = Messages.getInstance();
	private final ProductivityFactor productivityFactor = new ProductivityFactor();
	private final JFrame frame;
	private final LocaleListener localeListener = new LocaleListener() {

		@SuppressWarnings("synthetic-access")
		@Override
		public void localeChanged(Locale newLocale) {
			frame.setTitle(ucpMsg.getString("frame.title")); //$NON-NLS-1$
		}

	};

	static {
		setLocale();
	}

	@SuppressWarnings("boxing")
	private UCPApplication() {
		operations = getOperations();
		pfPanel = new ProductivityFactorPanel(productivityFactor);
		report = new UCPReport(operations, productivityFactor);

		generateReport = GenerateReportAction.getInstance(report);
		estimate = new EstimateAction();

		productivityFactor.addAction(generateReport);
		productivityFactor.addAction(estimate);
		productivityFactor.setProductivity(0);

		frame = new JFrame(ucpMsg.getString("frame.title")); //$NON-NLS-1$

		LocaleRegistry.getRegistry().addLocaleListener(localeListener);
	}

	@SuppressWarnings("unchecked")
	private XMLOperations[] getOperations() {
		return new XMLOperations[] { new TCFOperations(), new ECFOperations(), new UUCWOperations(),
				new UAWOperations() };
	}

	/**
	 * @return
	 */
	@SuppressWarnings("boxing")
	private Container prepareContentPane() {
		JPanel panel = new JPanel(new BorderLayout());

		panel.add(pfPanel, BorderLayout.NORTH);
		panel.add(new UCPTabbedPane(operations));

		JPanel southPanel = new JPanel(new BorderLayout());
		JPanel tmp = new JPanel();

		JButton generateReportAction = new JButton(generateReport);
		generateReportAction.setIcon(ImageRegistry.getIcon(ImageRegistry.REPORT_ICON));

		JButton estimateAction = new JButton(estimate);

		tmp.add(estimateAction);
		tmp.add(generateReportAction);
		southPanel.add(tmp, BorderLayout.EAST);

		panel.add(southPanel, BorderLayout.SOUTH);

		int width = Integer.parseInt(ucpMsg.getString("app.pref.width")); //$NON-NLS-1$
		int height = Integer.parseInt(ucpMsg.getString("app.pref.height")); //$NON-NLS-1$
		panel.setPreferredSize(new Dimension(width, height));
		return panel;
	}

	/**
   * 
   */
	@SuppressWarnings("boxing")
	protected void showEstimatedEffort() {
		if (!isProductivitySelected()) {
			DialogUtil.showErrorDialog(DialogUtil.getWindow(pfPanel), ucpMsg
					.getString("productivity.error.msg")); //$NON-NLS-1$
			return;
		}
		StringBuilder builder = new StringBuilder();
		builder.append(report.getUCP());
		JLabel lbl = new JLabel(builder.toString());
		lbl.setForeground(new Color(0, 0, 106));
		lbl.setFont(ControlConstants.BOLD_FONT);
		JOptionPane.showMessageDialog(DialogUtil.getWindow(pfPanel), lbl, ucpMsg
				.getString("total.estimated.effort"), //$NON-NLS-1$
				JOptionPane.INFORMATION_MESSAGE);
	}

	@SuppressWarnings("boxing")
	private boolean isProductivitySelected() {
		return (productivityFactor.getProductivity() > 0);
	}

	private void createAndShowGUI() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(new UCPMenuBar(generateReport));
		frame.setContentPane(prepareContentPane());
		frame.pack();
		frame.setVisible(true);
	}

	@SuppressWarnings("serial")
	private class EstimateAction extends I18NAbstractAction {

		@SuppressWarnings("synthetic-access")
		public EstimateAction() {
			super("estimate.action", ucpMsg); //$NON-NLS-1$
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			showEstimatedEffort();
		}
	}

	/**
	 * @param args
	 * @throws UnsupportedLookAndFeelException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		final UCPApplication application = new UCPApplication();
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("synthetic-access")
			@Override
			public void run() {
				application.createAndShowGUI();
			}
		});
	}

	/**
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private static void setLocale() {
		Locale locale = null;
		try {
			FileInputStream fis = new FileInputStream("preferred-locale"); //$NON-NLS-1$
			ObjectInputStream ois = new ObjectInputStream(fis);
			locale = (Locale) ois.readObject();
			ois.close();
			fis.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		UIManager.getDefaults().setDefaultLocale(locale == null ? Locale.ENGLISH : locale);
	}
}
