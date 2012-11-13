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
   * Get all SCAP statistics available.
   * @return hashMap Containing the statistics in key value pairs
   */  
  public Map<String, Integer> getStatistics();

}
