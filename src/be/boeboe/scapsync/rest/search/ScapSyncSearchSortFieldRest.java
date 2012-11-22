/**
 * 
 */
package be.boeboe.scapsync.rest.search;

import org.json.JSONException;
import org.json.JSONObject;

import be.boeboe.scapsync.rest.interfaces.IScapSyncSearchSortField;

/**
 * Rest Implementation of a ScapSync Sort Field
 * 
 * Example json:
 * {"selected":false,"name":"Modification Date (Newest First)"}
 * @author boeboe
 */
public class ScapSyncSearchSortFieldRest implements IScapSyncSearchSortField {
  private static final String SELECTED = "selected";
  private static final String NAME = "name";

  private boolean fSelected;
  private String fName;
  
  public ScapSyncSearchSortFieldRest(JSONObject scapSyncSearchSortFieldRest) {
    super();
    try {
      fSelected = Boolean.parseBoolean(scapSyncSearchSortFieldRest.getString(SELECTED));
      fName = scapSyncSearchSortFieldRest.getString(NAME);
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncSearchSortField#getSelected()
   */
  @Override
  public boolean getSelected() {
    return fSelected;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncSearchSortField#getName()
   */
  @Override
  public String getName() {
    return fName;
  }
}
