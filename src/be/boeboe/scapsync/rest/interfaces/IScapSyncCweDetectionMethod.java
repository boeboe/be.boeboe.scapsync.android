/**
 * 
 */
package be.boeboe.scapsync.rest.interfaces;

/**
 * Interface to represent a ScapSync CWE Detection Method
 * @author boeboe
 */
public interface IScapSyncCweDetectionMethod {

  /**
   * Get the Notes from this CWE Detection Method.
   * @return notes the Notes from this CWE Detection Method
   */
  public String getNotes();

  /**
   * Get the Effectiveness from this CWE Detection Method.
   * @return effectiveness the Effectiveness from this CWE Detection Method
   */
  public String getEffectiveness();

  /**
   * Get the Name from this CWE Detection Method.
   * @return name the Name from this CWE Detection Method
   */
  public String getName();

  /**
   * Get the Description from this CWE Detection Method.
   * @return description the Description from this CWE Detection Method
   */
  public String getDescription();
}
