//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in
// JDK 6
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2010.03.10 at 04:57:47 PM IST
//

package org.ucp.tcf;

import java.util.Comparator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref=&quot;{}factor&quot;/&gt;
 *         &lt;element name=&quot;description&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot;/&gt;
 *         &lt;element ref=&quot;{}weight&quot;/&gt;
 *         &lt;element ref=&quot;{}complexity&quot;/&gt;
 *         &lt;element ref=&quot;{}calculated-factor&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "factor", "description", "weight", "complexity", "calculatedFactor" })
@XmlRootElement(name = "technical-factor")
public class TechnicalFactor implements Comparator<TechnicalFactor>, Comparable<TechnicalFactor> {

  @XmlElement(required = true)
  protected String factor;
  @XmlElement(required = true)
  protected String description;
  protected double weight;
  protected double complexity;
  @XmlElement(name = "calculated-factor")
  protected double calculatedFactor;

  /**
   * Gets the value of the factor property.
   * @return possible object is {@link String }
   */
  public String getFactor() {
    return factor;
  }

  /**
   * Sets the value of the factor property.
   * @param value
   *          allowed object is {@link String }
   */
  public void setFactor(String value) {
    this.factor = value;
  }

  /**
   * Gets the value of the description property.
   * @return possible object is {@link String }
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the value of the description property.
   * @param value
   *          allowed object is {@link String }
   */
  public void setDescription(String value) {
    this.description = value;
  }

  /**
   * Gets the value of the weight property.
   */
  public double getWeight() {
    return weight;
  }

  /**
   * Sets the value of the weight property.
   */
  public void setWeight(double value) {
    this.weight = value;
  }

  /**
   * Gets the value of the complexity property.
   */
  public double getComplexity() {
    return complexity;
  }

  /**
   * Sets the value of the complexity property.
   */
  public void setComplexity(double value) {
    this.complexity = value;
  }

  /**
   * @return the calculatedFactor
   */
  public double getCalculatedFactor() {
    // this is calculated dynamically
    setCalculatedFactor(weight * complexity);
    return calculatedFactor;
  }

  /**
   * @param calculatedFactor
   *          the calculatedFactor to set
   */
  public void setCalculatedFactor(double value) {
    this.calculatedFactor = value;
  }

  /*
   * (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof TechnicalFactor) {
      TechnicalFactor that = (TechnicalFactor) obj;
      return getFactor().equals(that.getFactor());
    }
    return super.equals(obj);
  }

  /**
   * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
   */
  @SuppressWarnings("boxing")
  @Override
  public int compare(TechnicalFactor o1, TechnicalFactor o2) {
    Integer f1 = Integer.parseInt(o1.getFactor().substring(1));
    Integer f2 = Integer.parseInt(o2.getFactor().substring(1));

    return f1.compareTo(f2);
  }

  /**
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  @Override
  public int compareTo(TechnicalFactor o) {
    return compare(this, o);
  }

  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return getFactor() + "-" + getDescription(); //$NON-NLS-1$
  }
}
