package be.boeboe.scapsync.rest.interfaces;

import java.util.Date;

/**
 * Interface to represent a ScapSync CPE
 * @author boeboe
 */
public interface IScapSyncCpeDetails {

  /**
   * Get Status from this CPE.
   * @return status the Status of this CPE
   */
  public String getStatus();

  /**
   * Get Name from this CPE.
   * @return name the Name of this CPE
   */
  public String getName();

  /**
   * Get Version Count from this CPE.
   * @return version_count the Version Count of this CPE
   */
  public String getVersionCount();

  /**
   * Get Upstream Modified Date from this CPE.
   * @return upstream_modified the Upstream Modified Date of this CPE
   */
  public Date getUpstreamModfied();

  /**
   * Get Titles from this CPE.
   * @return String[] the Titles of this CPE
   */
  public String[] getTitles();

  /**
   * Get the Version URL from this CPE.
   * @return version_url the Version UR of this CPE
   */
  public String getVersionUrl(); 
}
