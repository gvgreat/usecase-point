/**
 * @Copyrights G. Vaidhyanathan
 */
package org.ucp.uaw;

import java.io.InputStream;

import org.i18n.swing.locale.AbstractI18NMessages;
import org.table.control.AbstractXMLOperations;
import org.table.control.XMLTableDataModel;

/**
 * @author G. Vaidhyanathan
 */
public class UAWOperations extends AbstractXMLOperations<ActorWeight> {
  private UnadjustedActorWeights unadjustedActorWeights;

  @Override
  public InputStream getTemplateFile() {
    String fileName = "unadjusted-actor-weights.xml"; //$NON-NLS-1$
    return UAWOperations.class.getResourceAsStream(fileName);
  }

  /**
   * @return the fileTypes
   */
  public UnadjustedActorWeights getUnadjustedActorWeights() {
    if (unadjustedActorWeights == null) {
      ObjectFactory factory = new ObjectFactory();
      unadjustedActorWeights = factory.createUnadjustedActorWeights();
    }
    return unadjustedActorWeights;
  }

  /*
   * (non-Javadoc)
   * @see org.table.control.XMLOperations#loadXML(java.lang.String)
   */
  @Override
  public void loadXML(String fileName) throws Exception {
    unadjustedActorWeights = (UnadjustedActorWeights) unmarshal(fileName);
    notifyDataChanged();
  }

  /*
   * (non-Javadoc)
   * @see org.table.control.XMLOperations#getTableElement()
   */
  @Override
  public XMLTableDataModel<ActorWeight> getTableDataModel() {
    return getUnadjustedActorWeights();
  }

  /*
   * (non-Javadoc)
   * @see org.table.control.XMLOperations#getSchemaFile()
   */
  @Override
  public InputStream getSchemaFile() {
    String fileName = "unadjusted-actor-weights.xsd"; //$NON-NLS-1$
    return UAWOperations.class.getResourceAsStream(fileName);
  }

  /*
   * (non-Javadoc)
   * @see org.table.control.XMLOperations#getTransformFile()
   */
  @Override
  public InputStream getTransformFile() {
    String fileName = "unadjusted-actor-weights.xslt"; //$NON-NLS-1$
    return UAWOperations.class.getResourceAsStream(fileName);
  }

  /*
   * (non-Javadoc)
   * @see org.table.control.XMLOperations#getHelpFile()
   */
  @Override
  public InputStream getHelpFile() {
    String fileName = "uaw-help.html"; //$NON-NLS-1$
    return UAWOperations.class.getResourceAsStream(fileName);
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
