/**
 * 
 */
package be.boeboe.scapsync.rest.cwe;

import org.json.JSONException;
import org.json.JSONObject;

import be.boeboe.scapsync.rest.interfaces.IScapSyncCweObservedExample;

/**
 * Rest Implementation of a ScapSync CWE Observed Example
 * 
 * Example json:
 * { "url" : "/cve/CVE-2009-2550", "description" : "Classic stack-based ...",
 *   "cve_id" : "CVE-2009-2550" }
 * @author boeboe
 */
public class ScapSyncCweObservedExampleRest implements IScapSyncCweObservedExample {
  private static final String URL = "url";
  private static final String DESCRIPTION = "description";
  private static final String CVE_ID = "cve_id";
  
  private String furl;
  private String fDescription;
  private String fCveId;
  
  public ScapSyncCweObservedExampleRest(JSONObject scapSyncObservedCweExampleRest) {
    super();
    try {
      furl = scapSyncObservedCweExampleRest.getString(URL);
      fDescription = scapSyncObservedCweExampleRest.getString(DESCRIPTION);
      fCveId = scapSyncObservedCweExampleRest.getString(CVE_ID);
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweObservedExample#getUrl()
   */
  @Override
  public String getUrl() {
    return furl;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweObservedExample#getDescription()
   */
  @Override
  public String getDescription() {
    return fDescription;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweObservedExample#getCveId()
   */
  @Override
  public String getCveId() {
    return fCveId;
  }
}
