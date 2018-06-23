/**
 * @Copyrights G. Vaidhyanathan
 */
package org.ucp.tcf;

import java.io.InputStream;

import org.i18n.swing.locale.AbstractI18NMessages;
import org.table.control.AbstractXMLOperations;
import org.table.control.XMLTableDataModel;

/**
 * @author G. Vaidhyanathan
 */
public class TCFOperations extends AbstractXMLOperations<TechnicalFactor> {
  private TechnicalComplexityFactors technicalComplexityFactors;

  @Override
  public InputStream getTemplateFile() {
    String fileName = "technical-complexity-factors.xml"; //$NON-NLS-1$
    return TCFOperations.class.getResourceAsStream(fileName);
  }

  /**
   * @return the fileTypes
   */
  public TechnicalComplexityFactors getTechnicalComplexityFactors() {
    if (technicalComplexityFactors == null) {
      ObjectFactory factory = new ObjectFactory();
      technicalComplexityFactors = factory.createTechnicalComplexityFactors();
    }
    return technicalComplexityFactors;
  }

  /*
   * (non-Javadoc)
   * @see org.table.control.XMLOperations#loadXML(java.lang.String)
   */
  @Override
  public void loadXML(String fileName) throws Exception {
    technicalComplexityFactors = (TechnicalComplexityFactors) unmarshal(fileName);
    notifyDataChanged();
  }

  /*
   * (non-Javadoc)
   * @see org.table.control.XMLOperations#getTableElement()
   */
  @Override
  public XMLTableDataModel<TechnicalFactor> getTableDataModel() {
    return getTechnicalComplexityFactors();
  }

  /*
   * (non-Javadoc)
   * @see org.table.control.XMLOperations#getSchemaFile()
   */
  @Override
  public InputStream getSchemaFile() {
    String fileName = "technical-complexity-factors.xsd"; //$NON-NLS-1$
    return TCFOperations.class.getResourceAsStream(fileName);
  }

  /*
   * (non-Javadoc)
   * @see org.table.control.XMLOperations#getTransformFile()
   */
  @Override
  public InputStream getTransformFile() {
    String fileName = "technical-complexity-factors.xslt"; //$NON-NLS-1$
    return TCFOperations.class.getResourceAsStream(fileName);
  }

  /*
   * (non-Javadoc)
   * @see org.table.control.XMLOperations#getHelpFile()
   */
  @Override
  public InputStream getHelpFile() {
    String fileName = "tcf-help.html"; //$NON-NLS-1$
    return TCFOperations.class.getResourceAsStream(fileName);
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
