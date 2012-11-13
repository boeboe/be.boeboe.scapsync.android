/**
 * 
 */
package be.boeboe.scapsync.rest.cwe;

import org.json.JSONException;
import org.json.JSONObject;

import be.boeboe.scapsync.rest.ScapSyncUtils;
import be.boeboe.scapsync.rest.interfaces.IScapSyncCwePlatforms;

/**
 * Rest Implementation of a ScapSync CWE Platform
 * 
 * Example json:
 * { "languages" : [ "Languages...", "C (Often)", ... ],
 *   "notes" : "It is possible ..." }
 * @author boeboe
 */
public class ScapSyncCwePlatformsRest implements IScapSyncCwePlatforms {
  private static final String LANGUAGES = "languages";
  private static final String NOTES = "notes";

  private String[] fLanguages;
  private String fNotes;

  public ScapSyncCwePlatformsRest(JSONObject scapSyncCwePlatformsRest) {
    super();
    try {
      fNotes = scapSyncCwePlatformsRest.has(NOTES) ? scapSyncCwePlatformsRest.getString(NOTES): null;
      
      fLanguages = ScapSyncUtils.getStringArray(scapSyncCwePlatformsRest.getJSONArray(LANGUAGES));
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCwePlatforms#getLanguages()
   */
  @Override
  public String[] getLanguages() {
    return fLanguages;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCwePlatforms#getNotes()
   */
  @Override
  public String getNotes() {
    return fNotes;
  }
}
