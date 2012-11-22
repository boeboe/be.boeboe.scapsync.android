/**
 * 
 */
package be.boeboe.scapsync.rest.search;

import org.json.JSONException;
import org.json.JSONObject;

import be.boeboe.scapsync.rest.interfaces.IScapSyncSearchFacet;

/**
 * Rest Implementation of a ScapSync Facet
 * 
 * Example json:
 * { "values":[{"selected":true,"count":11,
 *   "name":"Vulnerability","url":"/search?q=nginx"}],
 *   "name":"Type"}
 * @author boeboe
 */
public class ScapSyncSearchFacetRest implements IScapSyncSearchFacet {
  private static final String SELECTED = "selected";
  protected static String NAME = "name";
  private static final String URL = "url";
  protected static String VALUES = "values";

  private boolean fSelected;
  private String fName;
  private String fUrl;
  public ScapSyncSearchFacetRest(JSONObject scapSyncSearchFacetRest) {
    super();
    try {
      fSelected = Boolean.parseBoolean(scapSyncSearchFacetRest.getString(SELECTED));
      fName = scapSyncSearchFacetRest.getString(NAME);
      fUrl = scapSyncSearchFacetRest.getString(URL);
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
  
  public ScapSyncSearchFacetRest(JSONObject scapSyncSearchFacetRest, String baseName) {
    this(scapSyncSearchFacetRest);
    fName = baseName + " " + fName;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncSearchFacet#getSelected()
   */
  @Override
  public boolean getSelected() {
    return fSelected;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncSearchFacet#getName()
   */
  @Override
  public String getName() {
    return fName;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncSearchFacet#getUrl()
   */
  @Override
  public String getUrl() {
    return fUrl;
  }

  @Override
  public String toString() {
    return "ScapSyncSearchFacetRest [fSelected=" + fSelected + ", fName="
        + fName + ", fUrl=" + fUrl + "]";
  }
}
