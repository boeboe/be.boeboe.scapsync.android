/**
 * 
 */
package be.boeboe.scapsync.rest.cwe;

import org.json.JSONException;
import org.json.JSONObject;

import be.boeboe.scapsync.rest.interfaces.IScapSyncCweMitigation;

/**
 * Rest Implementation of a ScapSync CWE Mitigation
 * 
 * Example json:
 * { "phase" : "Requirements", "description" : "Use a language...",
 *   "strategy" : "Language Selection" }
 * { "phase" : "Build and Compilation", "notes" : "This is not ...",
 *   "effectiveness" : "Defense in Depth", "description" : "Run or ...",
 *   "strategy" : "Compilation or Build Hardening" }
 * @author boeboe
 */
public class ScapSyncCweMitigationRest implements IScapSyncCweMitigation {
  private static final String PHASE = "phase";
  private static final String EFFECTIVENESS = "effectiveness";
  private static final String NOTES = "notes";
  private static final String DESCRIPTION = "description";
  private static final String STRATEGY = "strategy";

  private String fPhase;
  private String fEffectiveness;
  private String fNotes;
  private String fDescription;
  private String fStrategy;

  public ScapSyncCweMitigationRest(JSONObject scapSyncCweMitigationRest) {
    super();
    try {
      fPhase = scapSyncCweMitigationRest.has(PHASE) ? scapSyncCweMitigationRest.getString(PHASE): null;
      fEffectiveness = scapSyncCweMitigationRest.has(EFFECTIVENESS) ? scapSyncCweMitigationRest.getString(EFFECTIVENESS): null;
      fNotes = scapSyncCweMitigationRest.has(NOTES) ? scapSyncCweMitigationRest.getString(NOTES): null;
      fDescription = scapSyncCweMitigationRest.getString(DESCRIPTION);
      fStrategy = scapSyncCweMitigationRest.has(STRATEGY) ? scapSyncCweMitigationRest.getString(STRATEGY): null;
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweMitigation#getPhase()
   */
  @Override
  public String getPhase() {
    return fPhase;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweMitigation#getEffectiveness()
   */
  @Override
  public String getEffectiveness() {
    return fEffectiveness;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweMitigation#getNotes()
   */
  @Override
  public String getNotes() {
    return fNotes;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweMitigation#getDescription()
   */
  @Override
  public String getDescription() {
    return fDescription;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweMitigation#getStrategy()
   */
  @Override
  public String getStrategy() {
    return fStrategy;
  }

}
