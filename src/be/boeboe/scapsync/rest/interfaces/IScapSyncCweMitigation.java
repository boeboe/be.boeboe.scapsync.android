/**
 * 
 */
package be.boeboe.scapsync.rest.interfaces;

/**
 * Interface to represent a ScapSync CWE Mitigation
 * @author boeboe
 */
public interface IScapSyncCweMitigation {

  /**
   * Get the Phase from this CWE Mitigation.
   * @return phase the Phase from this CWE Mitigation
   */
  public String getPhase();

  /**
   * Get the Effectiveness from this CWE Mitigation.
   * @return effectiveness the Effectiveness from this CWE Mitigation
   */
  public String getEffectiveness();

  /**
   * Get the Notes from this CWE Mitigation.
   * @return notes the Notes from this CWE Mitigation
   */
  public String getNotes();

  /**
   * Get the Description from this CWE Mitigation.
   * @return description the Description from this CWE Mitigation
   */
  public String getDescription();

  /**
   * Get the Strategy from this CWE Mitigation.
   * @return strategy the Strategy from this CWE Mitigation
   */
  public String getStrategy();

}
