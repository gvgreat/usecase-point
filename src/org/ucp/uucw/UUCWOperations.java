/**
 * @Copyrights G. Vaidhyanathan
 */
package org.ucp.uucw;

import java.io.InputStream;

import org.i18n.swing.locale.AbstractI18NMessages;
import org.table.control.AbstractXMLOperations;
import org.table.control.XMLTableDataModel;

/**
 * @author G. Vaidhyanathan
 */
public class UUCWOperations extends AbstractXMLOperations<UsecaseWeight> {
  private UnadjustedUsecaseWeights unadjustedUsecaseWeights;

  @Override
  public InputStream getTemplateFile() {
    String fileName = "unadjusted-usecase-weights.xml"; //$NON-NLS-1$
    return UUCWOperations.class.getResourceAsStream(fileName);
  }

  /**
   * @return the fileTypes
   */
  public UnadjustedUsecaseWeights getUnadjustedUsecaseWeights() {
    if (unadjustedUsecaseWeights == null) {
      ObjectFactory factory = new ObjectFactory();
      unadjustedUsecaseWeights = factory.createUnadjustedUsecaseWeights();
    }
    return unadjustedUsecaseWeights;
  }

  /*
   * (non-Javadoc)
   * @see org.table.control.XMLOperations#loadXML(java.lang.String)
   */
  @Override
  public void loadXML(String fileName) throws Exception {
    unadjustedUsecaseWeights = (UnadjustedUsecaseWeights) unmarshal(fileName);
    notifyDataChanged();
  }

  /*
   * (non-Javadoc)
   * @see org.table.control.XMLOperations#getTableElement()
   */
  @Override
  public XMLTableDataModel<UsecaseWeight> getTableDataModel() {
    return getUnadjustedUsecaseWeights();
  }

  /*
   * (non-Javadoc)
   * @see org.table.control.XMLOperations#getSchemaFile()
   */
  @Override
  public InputStream getSchemaFile() {
    String fileName = "unadjusted-usecase-weights.xsd"; //$NON-NLS-1$
    return UUCWOperations.class.getResourceAsStream(fileName);
  }

  /*
   * (non-Javadoc)
   * @see org.table.control.XMLOperations#getTransformFile()
   */
  @Override
  public InputStream getTransformFile() {
    String fileName = "unadjusted-usecase-weights.xslt"; //$NON-NLS-1$
    return UUCWOperations.class.getResourceAsStream(fileName);
  }

  /*
   * (non-Javadoc)
   * @see org.table.control.XMLOperations#getHelpFile()
   */
  @Override
  public InputStream getHelpFile() {
    String fileName = "uucw-help.html"; //$NON-NLS-1$
    return UUCWOperations.class.getResourceAsStream(fileName);
  }

  /**
   * @return the Short title for the operations (Acronym or abbreviation)
   */
  public String getShortTitle() {
    return "operation.short.title"; //$NON-NLS-1$
  }

  /**
   * @return the title for the operations
   */
  public String getTitle() {
    return "operation.title"; //$NON-NLS-1$
  }

  /**
   * Messages file used for Internationalization
   * @return AbstractI18NMessages
   */
  @Override
  public AbstractI18NMessages getMessages() {
    return Messages.getInstance();
  }
}
