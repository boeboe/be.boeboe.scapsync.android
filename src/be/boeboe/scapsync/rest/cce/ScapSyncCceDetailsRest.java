/**
 * 
 */
package be.boeboe.scapsync.rest.cce;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import be.boeboe.scapsync.rest.ScapSyncUtils;
import be.boeboe.scapsync.rest.interfaces.IScapSyncCceDetails;

/**
 * Rest Implementation of a ScapSync CVE
 * 
 * Example json:
 * { "description" : "DEPRECATED.", "parameters" : [ ], "deprecated" : true,
 *   "mechanisms" : [ ], "version_count" : 244, 
 *   "upstream_modified" : "2009-04-30T00:00:00Z", "platform" : "solaris9",
 *    "references" : [ ], "version_url" : "/versions/cce/CCE-4923-9" }
 * @author boeboe
 */
public class ScapSyncCceDetailsRest implements IScapSyncCceDetails {
  private static final String DESCRIPTION = "description";
  //private static final String PARAMETERS = "parameters";
  private static final String DEPRICATED = "deprecated";
  //private static final String MECHANISMS = "mechanisms";
  private static final String VERSION_COUNT = "version_count";
  private static final String UPSTREAM_MODIFIED = "upstream_modified";
  //private static final String REFERENCES = "references";
  private static final String PLATFORM = "platform";
  private static final String VERSION_URL = "version_url";
  
  private String fDescription;
  private boolean fDepricated;
  private int fVersionCount;;
  private Date fUpstreamModified;
  private String fPlatform;
  private String fVersionUrl;
  
  public ScapSyncCceDetailsRest(JSONObject scapSyncCceDetailsRest) {
    try {
      fDescription = scapSyncCceDetailsRest.getString(DESCRIPTION);
      fDepricated = Boolean.parseBoolean(scapSyncCceDetailsRest.getString(DEPRICATED));
      fVersionCount = scapSyncCceDetailsRest.getInt(VERSION_COUNT);
      fUpstreamModified = ScapSyncUtils.getDate(scapSyncCceDetailsRest.getString(UPSTREAM_MODIFIED));
      fPlatform = scapSyncCceDetailsRest.getString(PLATFORM);
      fVersionUrl = scapSyncCceDetailsRest.getString(VERSION_URL);
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCceDetails#getDescription()
   */
  @Override
  public String getDescription() {
    return fDescription;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCceDetails#getParameters()
   */
  @Override
  public void getParameters() {
    
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCceDetails#getDeprecated()
   */
  @Override
  public boolean getDeprecated() {
    return fDepricated;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCceDetails#getMechanisms()
   */
  @Override
  public void getMechanisms() {    
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCceDetails#getVersionCount()
   */
  @Override
  public int getVersionCount() {
    return fVersionCount;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCceDetails#getUpstreamModified()
   */
  @Override
  public Date getUpstreamModified() {
    return fUpstreamModified;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCceDetails#getPlatform()
   */
  @Override
  public String getPlatform() {
    return fPlatform;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCceDetails#getVersionUrl()
   */
  @Override
  public String getVersionUrl() {
    return fVersionUrl;
  }

}
