/**
 * 
 */
package be.boeboe.scapsync.rest.cwe;

import org.json.JSONException;
import org.json.JSONObject;

import be.boeboe.scapsync.rest.interfaces.IScapSyncCweDemonstrativeExample;

/**
 * Rest Implementation of a ScapSync CWE Demonstrative Example
 * 
 * Example json:
 * { "body" : "Code example ...", 
 *   "intro" : "This example takes an IP address ..." }
 * @author boeboe
 */
public class ScapSyncCweDemonstrativeExampleRest implements IScapSyncCweDemonstrativeExample {
  private static final String BODY = "body";
  private static final String INTRO = "intro";

  private String fBody;
  private String fIntro;

  public ScapSyncCweDemonstrativeExampleRest(JSONObject scapSyncCweDemonstrativeExampleRest) {
    super();
    try {
      fBody = scapSyncCweDemonstrativeExampleRest.has(BODY) ?  scapSyncCweDemonstrativeExampleRest.getString(BODY): null;
      fIntro = scapSyncCweDemonstrativeExampleRest.getString(INTRO);
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweDemonstrativeExample#getBody()
   */
  @Override
  public String getBody() {
    return fBody;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweDemonstrativeExample#getIntro()
   */
  @Override
  public String getIntro() {
    return fIntro;
  }
}
