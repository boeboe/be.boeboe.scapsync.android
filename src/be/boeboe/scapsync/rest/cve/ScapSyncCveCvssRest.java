/**
 * 
 */
package be.boeboe.scapsync.rest.cve;

import org.json.JSONException;
import org.json.JSONObject;

import be.boeboe.scapsync.rest.interfaces.IScapSyncCvss;

/**
 * Rest Implementation of a ScapSync Cvss (Common Vulnerability Scoring System)
 * 
 * Example json:
 * { "Access Complexity" : "Low",
 *   "Confidentiality Impact" : "Partial",
 *   "Access Vector" : "Local",
 *   "Authentication" : "None",
 *   "Integrity Impact" : "None",
 *   "Availability Impact" : "None" }
 * @author boeboe
 */
public class ScapSyncCveCvssRest implements IScapSyncCvss {
  private static final String CVSS_ACCES_VECTOR = "Access Vector";
  private static final String CVSS_ACCES_COMPLEXITY = "Access Complexity";
  private static final String CVSS_AUTHENTICATION = "Authentication";
  private static final String CVSS_CONF_IMPACT = "Confidentiality Impact";
  private static final String CVSS_INTEG_IMPACT = "Integrity Impact";
  private static final String CVSS_AVAIL_IMPACT = "Availability Impact";
  
  private String fCvssAccessVector;
  private String fCvssAccessComplexity;
  private String fAuthentication;
  private String fConfImpact;
  private String fIntegImpact;
  private String fAvailImpact;

  public ScapSyncCveCvssRest(JSONObject scapSyncCvssRest) {
    super();
    try {
      fCvssAccessVector = scapSyncCvssRest.getString(CVSS_ACCES_VECTOR);
      fCvssAccessComplexity = scapSyncCvssRest.getString(CVSS_ACCES_COMPLEXITY);
      fAuthentication = scapSyncCvssRest.getString(CVSS_AUTHENTICATION);
      fConfImpact = scapSyncCvssRest.getString(CVSS_CONF_IMPACT);
      fIntegImpact = scapSyncCvssRest.getString(CVSS_INTEG_IMPACT);
      fAvailImpact = scapSyncCvssRest.getString(CVSS_AVAIL_IMPACT);
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCvss#getAccessVector()
   */
  @Override
  public String getAccessVector() {
    return fCvssAccessVector;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCvss#getAccessComplexity()
   */
  @Override
  public String getAccessComplexity() {
    return fCvssAccessComplexity;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCvss#getAuthentication()
   */
  @Override
  public String getAuthentication() {
    return fAuthentication;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCvss#getConfImpact()
   */
  @Override
  public String getConfImpact() {
    return fConfImpact;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCvss#getIntegImpact()
   */
  @Override
  public String getIntegImpact() {
    return fIntegImpact;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCvss#getAvailImpact()
   */
  @Override
  public String getAvailImpact() {
    return fAvailImpact;
  }

  @Override
  public String toString() {
    return "ScapSyncCveCvssRest [fCvssAccessVector=" + fCvssAccessVector
        + ", fCvssAccessComplexity=" + fCvssAccessComplexity
        + ", fAuthentication=" + fAuthentication + ", fConfImpact="
        + fConfImpact + ", fIntegImpact=" + fIntegImpact + ", fAvailImpact="
        + fAvailImpact + "]";
  }
}
