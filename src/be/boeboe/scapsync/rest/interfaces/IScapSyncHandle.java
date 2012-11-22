/**
 * 
 */
package be.boeboe.scapsync.rest.interfaces;

import java.util.Map;

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
   * @param searchResult the search result containing generic CCE data
   * @return IScapSyncCceDetails the full details of this CCE
   */
  // public IScapSyncCceDetails getCceDetails(IScapSyncSearchResult searchResult);
  
  /**
   * Get the full details of a CPE (Common Platform Enumeration).
   * @param searchResult the search result containing generic CPE data
   * @return IScapSyncCpeDetails the full details of this CPE
   */
  public IScapSyncCpeDetails getCpeDetails(IScapSyncSearchResult searchResult);
  
  /**
   * Get the full details of a CVE (Common Vulnerabilities and Exposures).
   * @param searchResult the search result containing generic CVE data
   * @return IScapSyncCveDetails the full details of this CVE
   */
  public IScapSyncCveDetails getCveDetails(IScapSyncSearchResult searchResult);
  
  /**
   * Get the full details of a CWE (Common Weakness Enumeration).
   * @param searchResult the search result containing generic CWE data
   * @return IScapSyncCweDetails the full details of this CWE
   */
  public IScapSyncCweDetails getCweDetails(IScapSyncSearchResult searchResult);
  
  /**
   * Get full statistics of the available SCAP data.
   * @param searchResult the search result containing generic CWE data
   * @return IScapSyncCweDetails the full details of this CWE
   */
  
  public Map<String, Integer> getStatistics();

  /**
   * Get the daily feeds from ScapSync.
   * @return IScapSyncDailyFeed the daily feeds from ScapSync
   */
  public IScapSyncDailyFeed getDailyFeed();
}
