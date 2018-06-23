/**
 * @Copyrights G. Vaidhyanathan
 */
package org.ucp;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import org.i18n.swing.I18NJMenu;
import org.i18n.swing.I18NJMenuItem;
import org.i18n.swing.locale.components.LocaleAction;
import org.i18n.swing.util.DialogUtil;
import org.table.control.img.ImageRegistry;

/**
 * @author G. Vaidhyanathan
 */
@SuppressWarnings("serial")
public class UCPMenuBar extends JMenuBar {
	private final Action generateReportAction;
	private final JMenu fileMenu;
	private final JMenuItem reportItem;
	private final JMenu helpMenu;
	private final JMenuItem menuAbout;
	private final JMenuItem menuHelpFile;
	private final JMenu settingsMenu;
	private final JMenuItem languagesMenuItem;
	private final Messages menuMessages = Messages.getInstance();

	public UCPMenuBar(Action generateAction) {
		generateReportAction = generateAction;
		fileMenu = new I18NJMenu("app.file.menu", menuMessages); //$NON-NLS-1$
		reportItem = new JMenuItem(generateReportAction);

		helpMenu = new I18NJMenu("app.help.menu", menuMessages); //$NON-NLS-1$
		menuAbout = new I18NJMenuItem("about.action", menuMessages); //$NON-NLS-1$
		menuHelpFile = new I18NJMenuItem("open.help.file", menuMessages); //$NON-NLS-1$

		settingsMenu = new I18NJMenu("app.settings.menu", menuMessages); //$NON-NLS-1$
		languagesMenuItem = new JMenuItem(); //$NON-NLS-1$

		// Populate the menus after the frame is painted
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				populateMenus();
			}
		});
	}

	/**
	 * Populate the menus
	 */
	private void populateMenus() {
		fileMenu.setMnemonic(KeyStroke
				.getKeyStroke(menuMessages.getString("app.file.menu.keystroke")).getKeyCode()); //$NON-NLS-1$
		add(fileMenu);
		fileMenu.add(reportItem);

		helpMenu.setMnemonic(KeyStroke
				.getKeyStroke(menuMessages.getString("app.help.menu.keystroke")).getKeyCode()); //$NON-NLS-1$
		add(helpMenu);

		menuAbout.addActionListener(new ActionListener() {

			@SuppressWarnings("synthetic-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				showAboutDialog();
			}

		});

		menuHelpFile.addActionListener(new ActionListener() {

			@SuppressWarnings("synthetic-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				openHelpFile();
			}

		});
		helpMenu.add(menuHelpFile);
		helpMenu.add(menuAbout);

		settingsMenu.setMnemonic(KeyStroke
				.getKeyStroke(menuMessages.getString("app.settings.menu.keystroke")).getKeyCode()); //$NON-NLS-1$
		add(settingsMenu);

		languagesMenuItem.setAction(new LocaleAction(DialogUtil.getWindow(this)));
		settingsMenu.add(languagesMenuItem);
	}

	private void openHelpFile() {
		try {
			File file = new File(System.getProperty("user.home") + File.separator + "help-file.html"); //$NON-NLS-1$//$NON-NLS-2$
			InputStream in = UCPMenuBar.class.getResourceAsStream("UCP-help.html"); //$NON-NLS-1$
			OutputStream out = new FileOutputStream(file);
			byte buf[] = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			out.flush();
			out.close();
			in.close();
			Desktop.getDesktop().open(file);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void showAboutDialog() {
		JLabel lbl = new JLabel(Messages.getInstance().getString("about.msg")); //$NON-NLS-1$
		JOptionPane.showMessageDialog(DialogUtil.getWindow(this), lbl, Messages.getInstance().getString(
				"about.title"), //$NON-NLS-1$
				JOptionPane.INFORMATION_MESSAGE, ImageRegistry.getIcon(ImageRegistry.THUMBS_ICON));
	}
}
