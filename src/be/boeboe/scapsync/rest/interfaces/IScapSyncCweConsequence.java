/**
 * 
 */
package be.boeboe.scapsync.rest.interfaces;

/**
 * Interface to represent a ScapSync CWE Consequence
 * @author boeboe
 */
public interface IScapSyncCweConsequence {

  /**
   * Get the Impact from this CWE Consequence.
   * @return taxonomy the Impact from this CWE Consequence
   */
  public String getImpact();

  /**
   * Get the Scope from this CWE Consequence.
   * @return scope the Scope from this CWE Consequence
   */
  public String getScope();

  /**
   * Get the Notes from this CWE Consequence.
   * @return notes the Notes from this CWE Consequence
   */
  public String getNotes();
}
