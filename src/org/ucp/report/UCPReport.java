/**
 * @Copyrights G. Vaidhyanathan
 */
package org.ucp.report;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

import javax.xml.bind.JAXBException;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.table.control.XMLOperations;
import org.ucp.pf.ProductivityFactor;
import org.ucp.uaw.UAWOperations;
import org.ucp.uucw.UUCWOperations;

/**
 * @author G. Vaidhyanathan
 */
@SuppressWarnings("serial")
public class UCPReport implements Serializable {
  @SuppressWarnings("unchecked")
  private final XMLOperations[] reportOperations;
  private final TransformerFactory transformerFactory = TransformerFactory.newInstance();
  private OutputStream reportStream;
  private final ProductivityFactor productivityFactor;

  @SuppressWarnings("unchecked")
  public UCPReport(final XMLOperations[] operations_p, ProductivityFactor productivityFactor_p) {
    this.reportOperations = operations_p;
    this.productivityFactor = productivityFactor_p;
  }

  /**
   * @return the reportStream
   */
  public OutputStream getReportStream() {
    return reportStream;
  }

  @SuppressWarnings("unchecked")
  public void generateReport() {
    for (XMLOperations operation : reportOperations) {
      Transformer transformer;
      try {
        transformer = transformerFactory.newTransformer(new StreamSource(operation.getTransformFile()));
        transformer.transform(new JAXBSource(operation.getContext(), operation.getTableDataModel()),
                              new StreamResult(reportStream));
      } catch (TransformerConfigurationException ex) {
        ex.printStackTrace();
      } catch (TransformerException ex) {
        ex.printStackTrace();
      } catch (JAXBException ex) {
        ex.printStackTrace();
      }
    }
    try {
      reportStream.write(prepareProductivityFactor().getBytes());
      reportStream.write(prepareTotalUCP().getBytes());
      reportStream.flush();
      reportStream.close();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  /**
   * @param reportStream
   *          the reportStream to set
   */
  public void setReportStream(String reportFileName) {
    try {
      reportStream = new FileOutputStream(reportFileName);
    } catch (FileNotFoundException ex) {
      ex.printStackTrace();
    }
  }

  @SuppressWarnings("nls")
  private String prepareProductivityFactor() {
    StringBuilder builder = new StringBuilder();
    builder.append("<font face=\"Verdana\" size=\"1\">");
    builder.append("<br />");
    builder.append("<hr />");
    builder.append("<h2> Productivity Factor = ");
    builder.append(productivityFactor.getProductivity());
    builder.append("</h2>");
    builder.append("</font>");
    return builder.toString();
  }

  @SuppressWarnings( { "nls" })
  private String prepareTotalUCP() {
    StringBuilder builder = new StringBuilder();
    builder.append("<font face=\"Verdana\" size=\"1\">");
    builder.append("<br />");
    builder.append("<hr />");
    builder.append("<h2>");
    builder.append(getUCP());
    builder.append("</h2> <hr /> <br />");
    builder.append("</font>");
    return builder.toString();
  }
  
  public String getUCP() {
    StringBuilder builder = new StringBuilder();
    builder.append("UCP (TCP * ECF * UUCP (UUCW+UAW) * PF) = "); //$NON-NLS-1$
    builder.append(getTotalUCP());
    builder.append(" hours"); //$NON-NLS-1$
    return builder.toString();
  }

  @SuppressWarnings( { "unchecked", "boxing" })
  private double getTotalUCP() {
    double totalUCP = 1;
    double totalUUCP = 0;
    for (XMLOperations oper : reportOperations) {
      double calculatedValue = oper.getTableDataModel().getCalculatedValue();
      Class operClass = oper.getClass();
      if(operClass == UUCWOperations.class || operClass == UAWOperations.class) {
        totalUUCP += calculatedValue;
      } else {
        totalUCP *= calculatedValue;
      }
    }
    totalUCP *= totalUUCP;
    totalUCP *= productivityFactor.getProductivity();
    return totalUCP;
  }
}
