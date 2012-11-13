/**
 * 
 */
package be.boeboe.scapsync.rest.search;

import org.json.JSONException;
import org.json.JSONObject;

import be.boeboe.scapsync.rest.interfaces.IScapSyncSearchPage;

/**
 * Rest Implementation of a ScapSync Search Page
 * 
 * Example json:
 * {"name":1,"url":"/search?q=nginx&s=0&solrDocumentType=cve&n=10"}
 * @author boeboe
 */
public class ScapSyncSearchPageRest implements IScapSyncSearchPage {
  private static final String NAME = "name";
  private static final String URL = "url";

  private int fName;
  private String fUrl;
  
  public ScapSyncSearchPageRest(JSONObject scapSyncSearchPageRest) {
    super();
    try {
      fName = scapSyncSearchPageRest.getInt(NAME);
      fUrl = scapSyncSearchPageRest.getString(URL);
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncSearchPage#getName()
   */
  @Override
  public int getName() {
    return fName;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncSearchPage#getUrl()
   */
  @Override
  public String getUrl() {
    return fUrl;
  }
}
