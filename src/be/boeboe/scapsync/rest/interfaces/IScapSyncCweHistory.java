/**
 * 
 */
package be.boeboe.scapsync.rest.interfaces;

import java.util.Date;

/**
 * Interface to represent a ScapSync CWE History
 * @author boeboe
 */
public interface IScapSyncCweHistory {
  
  /**
   * Get the Date from this CWE History.
   * @return date the Date from this CWE History
   */
  public Date getDate();

  /**
   * Get the Organization from this CWE History.
   * @return organization the Organization from this CWE History
   */
  public String getOrganization();

  /**
   * Get the Modifier from this CWE History.
   * @return modifier the Modifier from this CWE History
   */
  public String getModifier();

  /**
   * Get the Comment from this CWE History.
   * @return comment the Comment from this CWE History
   */
  public String getComment();
}
