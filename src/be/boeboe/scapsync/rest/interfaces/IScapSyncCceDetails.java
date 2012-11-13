/**
 * 
 */
package be.boeboe.scapsync.rest.interfaces;

import java.util.Date;

/**
 * Interface to represent a ScapSync CCE
 * @author boeboe
 */
public interface IScapSyncCceDetails {

  /**
   * Get Description from this CCE.
   * @return description the Description of this CCE
   */
  public String getDescription();

  /**
   * Get Parameters from this CCE.
   * @return parameters the Parameters of this CCE
   */
  public void getParameters();

  /**
   * Get Deprecated state from this CCE.
   * @return boolean the Deprecated state of this CCE
   */
  public boolean getDeprecated();

  /**
   * Get Mechanisms from this CCE.
   * @return mechanisms the Mechanisms of this CCE
   */
  public void getMechanisms();

  /**
   * Get VersionCount from this CCE.
   * @return versionCount the VersionCount of this CCE
   */
  public int getVersionCount();

  /**
   * Get UpstreamModified Date from this CCE.
   * @return upstreamModifieddate the UpstreamModified Date of this CCE
   */
  public Date getUpstreamModified();

  /**
   * Get Platform from this CCE.
   * @return platform the Platform of this CCE
   */
  public String getPlatform();

  /**
   * Get Description from this CCE.
   * @return description the Description of this CCE
   */
  public String getVersionUrl();
}
