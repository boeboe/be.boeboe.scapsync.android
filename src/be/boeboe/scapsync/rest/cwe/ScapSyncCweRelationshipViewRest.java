/**
 * 
 */
package be.boeboe.scapsync.rest.cwe;

import org.json.JSONException;
import org.json.JSONObject;

import be.boeboe.scapsync.rest.interfaces.IScapSyncCweRelationshipView;


/**
 * Rest Implementation of a ScapSync CWE Relationship View
 * 
 * @author boeboe
 */
public class ScapSyncCweRelationshipViewRest  implements IScapSyncCweRelationshipView  {
  private static final String URL = "url";
  private static final String ID = "id";

  private String fUrl;
  private String fId;
  
  public ScapSyncCweRelationshipViewRest(JSONObject scapSyncCweRelationshipViewRest) {
    super();
    try {
      fUrl = scapSyncCweRelationshipViewRest.getString(URL);
      fId = scapSyncCweRelationshipViewRest.getString(ID);
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweRelationship.IScapSyncCweRelationshipView#getUrl()
   */
  @Override
  public String getUrl() {
    return fUrl;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweRelationship.IScapSyncCweRelationshipView#getId()
   */
  @Override
  public String getId() {
    return fId;
  }
}
