/**
 * @Copyrights G. Vaidhyanathan
 */
package org.ucp.report;

import java.awt.event.ActionEvent;

import javax.swing.JComponent;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.table.control.actions.AbstractXMLAction;

/**
 * @author G. Vaidhyanathan
 */
@SuppressWarnings("serial")
public class GenerateReportAction extends AbstractXMLAction {
  
  private final UCPReport report;
  
  private static GenerateReportAction instance;
  
  /**
   * @param operation
   */
  private GenerateReportAction(UCPReport report_p) {
    super("generate.action", Messages.getInstance(), null); //$NON-NLS-1$
    this.report= report_p;
  }
  
  public static final GenerateReportAction getInstance(final UCPReport report_p) {
    if(instance == null) {
      instance = new GenerateReportAction(report_p);
    }
    return instance;
  }

  /*
   * (non-Javadoc)
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    doSaveOperation((JComponent) e.getSource());
  }

  /**
   * @return File Filter for the save dialog
   */
  @Override
  protected FileNameExtensionFilter getFileFilter() {
    return new FileNameExtensionFilter("HTML Document", "html"); //$NON-NLS-1$ //$NON-NLS-2$
  }

  @Override
  protected void saveFile(String fileName, JComponent component) {
    report.setReportStream(fileName);
    report.generateReport();
  }
}
