package be.boeboe.scapsync.rest.interfaces;

import java.net.URI;

/**
 * Interface to represent a ScapSync Reference
 * @author boeboe
 */
public interface IScapSyncCveReference {
  
  /**
   * Get the ID of the Reference.
   * @return id the string with the ID of this Reference
   */
  public String getId();
  
  /**
   * Get the source of the Reference.
   * @return source the source of the Reference
   */
  public String getSource();
  
  /**
   * Get the URL of the Reference.
   * @return url the URL of the Reference
   */
  public URI getUrl();
}
