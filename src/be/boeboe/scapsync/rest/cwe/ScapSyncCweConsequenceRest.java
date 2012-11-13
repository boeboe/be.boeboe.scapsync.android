/**
 * 
 */
package be.boeboe.scapsync.rest.cwe;

import org.json.JSONException;
import org.json.JSONObject;

import be.boeboe.scapsync.rest.interfaces.IScapSyncCweConsequence;

/**
 * Rest Implementation of ScapSync CWE Consequence
 * 
 * Example json:
 * { "impact" : "Execute unauthorized ...", "scope" : "Integrity, Conf...",
 * "notes" : "If the memory accessible by the attacker ..." }
 * @author boeboe
 */
public class ScapSyncCweConsequenceRest implements IScapSyncCweConsequence {
  private static final String IMPACT = "impact";
  private static final String SCOPE = "scope";
  private static final String NOTES = "notes";

  private String fImpact;
  private String fScope;
  private String fNotes;

  public ScapSyncCweConsequenceRest(JSONObject scapSyncCweConsequenceRest) {
    super();
    try {
      fImpact = scapSyncCweConsequenceRest.getString(IMPACT);
      fScope = scapSyncCweConsequenceRest.getString(SCOPE);
      fNotes = scapSyncCweConsequenceRest.has(NOTES) ? scapSyncCweConsequenceRest.getString(NOTES): null;
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweConsequence#getImpact()
   */
  @Override
  public String getImpact() {
    return fImpact;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweConsequence#getScope()
   */
  @Override
  public String getScope() {
    return fScope;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweConsequence#getNotes()
   */
  @Override
  public String getNotes() {
    return fNotes;
  }
}
