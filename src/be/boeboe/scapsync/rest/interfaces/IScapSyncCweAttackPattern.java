/**
 * 
 */
package be.boeboe.scapsync.rest.interfaces;

import java.net.URI;

/**
 * Interface to represent a ScapSync CWE Attack Pattern
 * @author boeboe
 */
public interface IScapSyncCweAttackPattern {

  /**
   * Get the URL from this CWE Attack Pattern.
   * @return url the URL from this CWE Attack Pattern
   */
  public URI getUrl();

  /**
   * Get the ID from this CWE Attack Pattern.
   * @return id the ID from this CWE Attack Pattern
   */
  public String getId();
}
