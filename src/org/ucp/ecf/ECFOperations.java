/**
 * @Copyrights G. Vaidhyanathan
 */
package org.ucp.ecf;

import java.io.InputStream;

import org.i18n.swing.locale.AbstractI18NMessages;
import org.table.control.AbstractXMLOperations;
import org.table.control.XMLTableDataModel;

/**
 * @author G. Vaidhyanathan
 */
public class ECFOperations extends AbstractXMLOperations<EnvironmentFactor> {
  private EnvironmentComplexityFactors environmentComplexityFactors;

  @Override
  public InputStream getTemplateFile() {
    String fileName = "environment-complexity-factors.xml"; //$NON-NLS-1$
    return ECFOperations.class.getResourceAsStream(fileName);
  }

  /**
   * @return the fileTypes
   */
  public EnvironmentComplexityFactors getEnvironmentComplexityFactors() {
    if (environmentComplexityFactors == null) {
      ObjectFactory factory = new ObjectFactory();
      environmentComplexityFactors = factory.createEnvironmentComplexityFactors();
    }
    return environmentComplexityFactors;
  }

  /*
   * (non-Javadoc)
   * @see org.table.control.XMLOperations#loadXML(java.lang.String)
   */
  @Override
  public void loadXML(String fileName) throws Exception {
    environmentComplexityFactors = (EnvironmentComplexityFactors) unmarshal(fileName);
    notifyDataChanged();
  }

  /*
   * (non-Javadoc)
   * @see org.table.control.XMLOperations#getTableElement()
   */
  @Override
  public XMLTableDataModel<EnvironmentFactor> getTableDataModel() {
    return getEnvironmentComplexityFactors();
  }

  /*
   * (non-Javadoc)
   * @see org.table.control.XMLOperations#getSchemaFile()
   */
  @Override
  public InputStream getSchemaFile() {
    String fileName = "environment-complexity-factors.xsd"; //$NON-NLS-1$
    return ECFOperations.class.getResourceAsStream(fileName);
  }

  /*
   * (non-Javadoc)
   * @see org.table.control.XMLOperations#getTransformFile()
   */
  @Override
  public InputStream getTransformFile() {
    String fileName = "environment-complexity-factors.xslt"; //$NON-NLS-1$
    return ECFOperations.class.getResourceAsStream(fileName);
  }

  /*
   * (non-Javadoc)
   * @see org.table.control.XMLOperations#getHelpFile()
   */
  @Override
  public InputStream getHelpFile() {
    String fileName = "ecf-help.html"; //$NON-NLS-1$
    return ECFOperations.class.getResourceAsStream(fileName);
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
