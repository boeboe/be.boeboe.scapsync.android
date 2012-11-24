/**
 * 
 */
package be.boeboe.scapsync.rest.interfaces;

import be.boeboe.scapsync.rest.ScapSyncSearch;

/**
 * Interface that represents a ScapSyncHandle.
 * @author boeboe
 *
 */
public interface IScapSyncHandle {

  /**
   * Search for CCE's, CPE's, CVE's and CWE's.
   * @param searchItem the item to search for, null not allowed
   * @param type the type of search, all types if null
   * @param sorting the sorting order of the results, no order if null
   * @return 
   */
  public ScapSyncSearch search(String searchItemm, IScapSyncSearchResultType type, IScapSyncSearchOrderFilter sorting);

  /**
   * Get the full details of a CCE (Common Configuration Enumeration).
   * @param id the ID of the CCE
   * @return IScapSyncCceDetails the full details of this CCE
   */
  public IScapSyncCceDetails getCceDetails(String id);
  
  /**
   * Get the full details of a CPE (Common Platform Enumeration).
   * @param id the ID of the CPE
   * @return IScapSyncCpeDetails the full details of this CPE
   */
  public IScapSyncCpeDetails getCpeDetails(String id);
  
  /**
   * Get the full details of a CVE (Common Vulnerabilities and Exposures).
   * @param id the ID of the CVE
   * @return IScapSyncCveDetails the full details of this CVE
   */
  public IScapSyncCveDetails getCveDetails(String id);
  
  /**
   * Get the full details of a CWE (Common Weakness Enumeration).
   * @param id the ID of the CWE
   * @return IScapSyncCweDetails the full details of this CWE
   */
  public IScapSyncCweDetails getCweDetails(String id);
  
  /**
   * Get full statistics of the available SCAP data.
   * @return IScapSyncStats the full details of this CWE
   */
  
  public IScapSyncStats getStatistics();

  /**
   * Get the daily feeds from ScapSync.
   * @return IScapSyncDailyFeed the daily feeds from ScapSync
   */
  public IScapSyncDailyFeed getDailyFeed();
}
