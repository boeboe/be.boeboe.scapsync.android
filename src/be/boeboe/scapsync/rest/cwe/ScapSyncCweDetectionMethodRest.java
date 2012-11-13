/**
 * 
 */
package be.boeboe.scapsync.rest.cwe;

import org.json.JSONException;
import org.json.JSONObject;

import be.boeboe.scapsync.rest.interfaces.IScapSyncCweDetectionMethod;

/**
 * Rest Implementation of ScapSync CWE Detection Method
 * 
 * Example json:
 * { "notes" : "Detection ...", "effectiveness" : "High", 
 *   "name" : "Automated Static Analysis",
 *   "description" : "This weakness ..."}
 * @author boeboe
 */
public class ScapSyncCweDetectionMethodRest implements IScapSyncCweDetectionMethod {
  private static final String NOTES = "notes";
  private static final String EFFECTIVENESS = "effectiveness";
  private static final String NAME = "name";
  private static final String DESCRIPTION = "description";
  
  private String fNotes;
  private String fEffectiveness;
  private String fName;
  private String fDescription;
  
  public ScapSyncCweDetectionMethodRest(JSONObject scapSyncCweDetectionMethodRest) {
    super();
    try {
      fNotes = scapSyncCweDetectionMethodRest.has(NOTES) ? scapSyncCweDetectionMethodRest.getString(NOTES): null;
      fEffectiveness = scapSyncCweDetectionMethodRest.has(EFFECTIVENESS) ? scapSyncCweDetectionMethodRest.getString(EFFECTIVENESS): null;
      fName = scapSyncCweDetectionMethodRest.getString(NAME);
      fDescription = scapSyncCweDetectionMethodRest.getString(DESCRIPTION);
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweDetectionMethod#getNotes()
   */
  @Override
  public String getNotes() {
    return fNotes;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweDetectionMethod#getEffectiveness()
   */
  @Override
  public String getEffectiveness() {
    return fEffectiveness;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweDetectionMethod#getName()
   */
  @Override
  public String getName() {
    return fName;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweDetectionMethod#getDescription()
   */
  @Override
  public String getDescription() {
    return fDescription;
  }
}
