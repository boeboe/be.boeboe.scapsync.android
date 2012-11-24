/**
 * 
 */
package be.boeboe.scapsync.rest.interfaces;

import java.util.Map;

/**
 * Interface to represent ScapSync Statistics
 * @author vanbosb
 *
 */
public interface IScapSyncStats {

  /**
   * Get all SCAP statistics available (key, value).
   * @return hashMap Containing the statistics in key value pairs
   */  
  public Map<String, Integer> getStatistics();

  /**
   * Get the labels from the ScapSync statistics data.
   * @return stringArray the labels from the ScapSync statistics data
   */  
  public String[] getLabels();


  /**
   * Get the title from the ScapSync statistics data.
   * @return string the title from the ScapSync statistics data
   */  
  public String getTitle();
}
