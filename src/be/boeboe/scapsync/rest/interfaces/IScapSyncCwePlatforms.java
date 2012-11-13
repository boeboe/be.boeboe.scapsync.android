/**
 * 
 */
package be.boeboe.scapsync.rest.interfaces;

/**
 * Interface to represent a ScapSync CWE Platform
 * @author boeboe
 */
public interface IScapSyncCwePlatforms {

  /**
   * Get the Names from this CWE Platforms.
   * @return name the Name from this CWE Platforms
   */
  public String[] getLanguages();

  /**
   * Get the Notes from this CWE Platforms.
   * @return notes the Notes from this CWE Platforms
   */
  public String getNotes();
}
