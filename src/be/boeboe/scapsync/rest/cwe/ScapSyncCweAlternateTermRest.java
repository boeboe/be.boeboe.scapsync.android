/**
 * 
 */
package be.boeboe.scapsync.rest.cwe;

import org.json.JSONException;
import org.json.JSONObject;

import be.boeboe.scapsync.rest.interfaces.IScapSyncCweAlternateTerm;

/**
 * Rest implementation of ScapSync CWE Alternate Term
 * 
 * Example json:
 * { "name" : "Memory Corruption", "description" : "The generic term ..." }
 * @author boeboe
 */
public class ScapSyncCweAlternateTermRest implements IScapSyncCweAlternateTerm {
  private static final String NAME = "name";
  private static final String DESCRIPTION = "description";

  private String fName;
  private String fDescription;

  public ScapSyncCweAlternateTermRest(JSONObject scapSyncCweAlternateTermRest) {
    super();
    try {
      fName = scapSyncCweAlternateTermRest.getString(NAME);
      fDescription = scapSyncCweAlternateTermRest.has(DESCRIPTION) ? scapSyncCweAlternateTermRest.getString(DESCRIPTION): null;
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweAlternateTerm#getName()
   */
  @Override
  public String getName() {
    return fName;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweAlternateTerm#getDescription()
   */
  @Override
  public String getDescription() {
    return fDescription;
  }
}
