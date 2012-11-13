/**
 * 
 */
package be.boeboe.scapsync.rest.interfaces;

/**
 * Interface to represent a ScapSync CWE Alternate Term
 * @author boeboe
 */
public interface IScapSyncCweAlternateTerm {

  /**
   * Get the Name from this CWE Alternate Term.
   * @return name the Name from this CWE Alternate Term
   */
  public String getName();

  /**
   * Get the Description from this CWE Alternate Term.
   * @return description the Description from this CWE Alternate Term
   */
  public String getDescription();
}
