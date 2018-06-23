/**
 * @Copyrights G. Vaidhyanathan
 */
package org.ucp.pf;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.i18n.swing.I18NJLabel;
import org.i18n.swing.IconButton;
import org.i18n.swing.util.DialogUtil;
import org.table.control.ControlConstants;
import org.table.control.img.ImageRegistry;

/**
 * @author G. Vaidhyanathan
 */
@SuppressWarnings("serial")
public class ProductivityFactorPanel extends JPanel implements ItemListener {
  private Messages pfMessages = Messages.getInstance();
  private final JLabel productivityLabel;
  private final JComboBox cboProductivity;
  private final JButton helpAction;
  private final ProductivityFactor productivityFactor;

  public ProductivityFactorPanel(ProductivityFactor productivityFactor_p) {
    setLayout(new BorderLayout());

    productivityLabel = new I18NJLabel("productivity.label", pfMessages); //$NON-NLS-1$

    productivityFactor = productivityFactor_p;
    productivityLabel.setFont(ControlConstants.FONT);

    cboProductivity = new JComboBox(getProductivities());
    cboProductivity.setFont(ControlConstants.FONT);
    cboProductivity.insertItemAt(ControlConstants.EMPTY_STRING, 0);
    cboProductivity.setSelectedItem(ControlConstants.EMPTY_STRING);
    cboProductivity.setPrototypeDisplayValue("XXXXXXX"); //$NON-NLS-1$
    cboProductivity.addItemListener(this);

    helpAction = new IconButton(ImageRegistry.getIcon(ImageRegistry.HELP_ICON));

    JPanel tmp = new JPanel();
    tmp.add(productivityLabel);
    tmp.add(cboProductivity);

    add(tmp, BorderLayout.WEST);
    JPanel east = new JPanel();
    east.add(helpAction);
    add(east, BorderLayout.CENTER);

    helpAction.addActionListener(new ActionListener() {
      @SuppressWarnings("synthetic-access")
      @Override
      public void actionPerformed(ActionEvent e) {
        DialogUtil.showInformationDialog(DialogUtil.getWindow(ProductivityFactorPanel.this), pfMessages
            .getString("productivity.msg")); //$NON-NLS-1$
      }
    });
  }

  @SuppressWarnings("boxing")
  private Integer[] getProductivities() {
    int lowerLimit = Integer.parseInt(pfMessages.getString("productivity.lower.limit")); //$NON-NLS-1$
    int upperLimit = Integer.parseInt(pfMessages.getString("productivity.upper.limit")); //$NON-NLS-1$
    Integer[] productivities = new Integer[upperLimit - lowerLimit + 1];
    for (int i = 0; i < productivities.length; i++) {
      productivities[i] = i + lowerLimit;
    }
    return productivities;
  }

  /*
   * (non-Javadoc)
   * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
   */
  @SuppressWarnings("boxing")
  @Override
  public void itemStateChanged(ItemEvent e) {
    if (e.getStateChange() == ItemEvent.SELECTED) {
      if (e.getItem() == ControlConstants.EMPTY_STRING) {
        productivityFactor.setProductivity(0);
        return;
      }
      productivityFactor.setProductivity((Integer) cboProductivity.getSelectedItem());
    }
  }
}
