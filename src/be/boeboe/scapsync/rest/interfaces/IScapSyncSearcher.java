/**
 * 
 */
package be.boeboe.scapsync.rest.interfaces;

import java.util.Map;

import be.boeboe.scapsync.rest.ScapSyncSearch;

/**
 * Interface that represents a ScapSyncSearcher.
 * @author boeboe
 *
 */
public interface IScapSyncSearcher {
  
  /**
   * Close down the searcher (release HttpClient handle).
   */
  public void close();
  
  
  /**
   * Search for CCE's (Common Configuration Enumeration) or misconfigurations.
   * CCE provides unique identifiers to system configuration issues in order
   * to facilitate fast and accurate correlation of configuration data across
   * multiple information sources and tools. For example, CCE Identifiers can
   * be used to associate checks in configuration assessment tools with
   * statements in configuration best-practice.
   * @param searchItem the item to search for
   * @return 
   */  
  public ScapSyncSearch searchCce(String searchItem);
  
  /**
   * Search for CPE's (Common Platform Enumeration).
   * CPE is a structured naming scheme for information technology systems,
   * software, and packages. Based upon the generic syntax for Uniform
   * Resource Identifiers (URI), CPE includes a formal name format, a method
   * for checking names against a system, and a description format for
   * binding text and tests to a name. 
   * @param searchItem the item to search for
   * @return 
   */
  public ScapSyncSearch searchCpe(String searchItem);

  /**
   * Search for CVE's (Common Vulnerabilities and Exposures) or security
   * related software flaws. CVE is a dictionary of publicly known information
   * security vulnerabilities and exposures. CVE’s common identifiers enable
   * data exchange between security products and provide a baseline index point
   * for evaluating coverage of tools and services.
   * @param searchItem the item to search for
   */
  public ScapSyncSearch searchCve(String searchItem);

  /**
   * Search for CWE's (Common Weakness Enumeration). The Common Weakness
   * Enumeration Specification (CWE) provides a common language of discourse
   * for discussing, finding and dealing with the causes of software security
   * vulnerabilities as they are found in code, design, or system architecture.
   * Each individual CWE represents a single vulnerability type.
   * @param searchItem the item to search for
   * @return 
   */
  public ScapSyncSearch searchCwe(String searchItem);

  /**
   * Search for CCE's, CPE's, CVE's and CWE's.
   * @param searchItem the item to search for
   * @return 
   */
  public ScapSyncSearch searchAll(String searchItem);
  
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
}
