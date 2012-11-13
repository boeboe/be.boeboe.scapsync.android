package be.boeboe.scapsync.rest.interfaces;

import java.util.Date;

/**
 * Interface to represent a ScapSync CVE
 * @author boeboe
 */
public interface IScapSyncCveDetails {
  
  /**
   * Get CVSS (Common Vulnerability Scoring System) from this CVE.
   * @return IScapSyncCvss the CVSS of this CVE
   */
  public IScapSyncCvss getCvss();

  /**
   * Get CVSS (Common Vulnerability Scoring System) Vector from this CVE.
   * @return vector the CVSS vector of this CVE
   */
  public String getCvssVector();
  
  /**
   * Get CVSS (Common Vulnerability Scoring System) Base Score from this CVE.
   * @return double the CVSS Base Score of this CVE
   */
  public double getCvssBaseScore();
  
  /**
   * Get Version Count from this CVE.
   * @return int the Version Count of this CVE
   */
  public int getVersionCount();
  
  /**
   * Get Upstream Modified Date from this CVE.
   * @return date the Upstream Modified Date of this CVE
   */
  public Date getUpstreamModifiedDate();

  /**
   * Get Upstream Published Date from this CVE.
   * @return date the Upstream Published Date of this CVE
   */
  public Date getUpstreamPublishedDate();
  
  /**
   * Get Summary from this CVE.
   * @return summary the Summary of this CVE
   */
  public String getSummary();
  
  /**
   * Get References from this CVE.
   * @return IScapSyncReference an array with all references of this CVE
   */
  public IScapSyncCveReference[] getRefences();
  
  /**
   * Get Version URL (relative) from this CVE.
   * @return String a relative URL towards the Version
   */
  public String getVersionUrl();
  
}
