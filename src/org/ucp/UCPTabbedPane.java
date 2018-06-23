/**
 * @Copyrights G. Vaidhyanathan
 */
package org.ucp;

import java.util.Locale;

import javax.swing.JTabbedPane;

import org.i18n.swing.locale.LocaleListener;
import org.i18n.swing.locale.LocaleRegistry;
import org.table.control.XMLOperations;
import org.table.control.view.ConfigurableTable;

/**
 * @author G. Vaidhyanathan
 *
 */
@SuppressWarnings("serial")
public class UCPTabbedPane extends JTabbedPane implements LocaleListener {
  
  @SuppressWarnings("unchecked")
  private final XMLOperations[] operations;
  
  @SuppressWarnings("unchecked")
  public UCPTabbedPane(XMLOperations[] xmlOperations) {
    operations = xmlOperations;
    
    for (XMLOperations operation : operations) {
      addTab(operation.getMessages().getString(operation.getShortTitle()), new ConfigurableTable(operation));
    }
    
    LocaleRegistry.getRegistry().addLocaleListener(this);
  }

  /* (non-Javadoc)
   * @see org.i18n.swing.locale.LocaleListener#localeChanged(java.util.Locale)
   */
  @Override
  public void localeChanged(Locale newLocale) {
    int length = operations.length;
    for(int i = 0; i < length; i++) {
      final XMLOperations<?> op = operations[i];
      setTitleAt(i, op.getMessages().getString(op.getShortTitle()));
    }
  }
}
