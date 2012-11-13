/**
 * 
 */
package be.boeboe.scapsync.rest.cwe;

import java.net.URI;

import org.json.JSONException;
import org.json.JSONObject;

import be.boeboe.scapsync.rest.interfaces.IScapSyncCweAttackPattern;

/**
 * Rest Implementation of ScapSync CWE Attack Pattern
 * 
 * Example json:
 * { "url" : "http://capec.mitre.org/data/definitions/139.html", 
 *   "id" : "CAPEC-139" }
 * @author boeboe
 */
public class ScapSyncCweAttackPatternRest implements IScapSyncCweAttackPattern {
  private static final String URL = "url";
  private static final String ID = "id";

  private URI fUrl;
  private String fId;
  
  public ScapSyncCweAttackPatternRest(JSONObject scapSyncCweAttackPatternRest) {
    super();
    try {
      fUrl = URI.create(scapSyncCweAttackPatternRest.getString(URL));
      fId = scapSyncCweAttackPatternRest.getString(ID);
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweAttackPattern#getUrl()
   */
  @Override
  public URI getUrl() {
    return fUrl;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweAttackPattern#getId()
   */
  @Override
  public String getId() {
    return fId;
  }
}
