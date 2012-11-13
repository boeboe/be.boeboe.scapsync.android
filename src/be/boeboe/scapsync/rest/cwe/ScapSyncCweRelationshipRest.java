/**
 * 
 */
package be.boeboe.scapsync.rest.cwe;

import org.json.JSONException;
import org.json.JSONObject;

import be.boeboe.scapsync.rest.ScapSyncUtils;
import be.boeboe.scapsync.rest.interfaces.IScapSyncCweRelationship;
import be.boeboe.scapsync.rest.interfaces.IScapSyncCweRelationshipView;

/**
 * Rest Implementation of a ScapSync CWE Relationship
 * 
 * Example json:
 * { "url" : "/cwe/CWE-118", "target_form" : "Weakness", 
 *   "target_id" : "CWE-118", "nature" : "is a child of",
 *   "views" : [ { "url" : "/cwe/CWE-1000", "id" : "CWE-1000" },
 *               { "url" : "/cwe/CWE-699", "id" : "CWE-699" } ] }
 * @author boeboe
 */
public class ScapSyncCweRelationshipRest implements IScapSyncCweRelationship {
  private static final String URL = "url";
  private static final String TARGET_FORM = "target_form";
  private static final String TARGET_ID = "target_id";
  private static final String NATURE = "nature";
  private static final String VIEWS = "views";

  private String fUrl;
  private String fTargetForm;
  private String fTargetId;
  private String fNature;
  private IScapSyncCweRelationshipView[] fViews;

  public ScapSyncCweRelationshipRest(JSONObject scapSyncCweRelationshipRest) {
    super();
    try {
      fUrl = scapSyncCweRelationshipRest.getString(URL);
      fTargetForm = scapSyncCweRelationshipRest.getString(TARGET_FORM);
      fTargetId = scapSyncCweRelationshipRest.getString(TARGET_ID);
      fNature = scapSyncCweRelationshipRest.getString(NATURE);

      fViews = scapSyncCweRelationshipRest.has(VIEWS) ? ScapSyncUtils.getObjectArray(scapSyncCweRelationshipRest.getJSONArray(VIEWS), ScapSyncCweRelationshipViewRest.class): null;
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweRelationship#getUrl()
   */
  @Override
  public String getUrl() {
    return fUrl;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweRelationship#getTargetForm()
   */
  @Override
  public String getTargetForm() {
    return fTargetForm;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweRelationship#getNature()
   */
  @Override
  public String getNature() {
    return fNature;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweRelationship#getTargetId()
   */
  @Override
  public String getTargetId() {
    return fTargetId;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweRelationship#getViews()
   */
  @Override
  public IScapSyncCweRelationshipView[] getViews() {
    return fViews;
  }
}
