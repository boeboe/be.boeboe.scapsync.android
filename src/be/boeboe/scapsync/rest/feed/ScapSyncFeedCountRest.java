/**
 * 
 */
package be.boeboe.scapsync.rest.feed;

import org.json.JSONException;
import org.json.JSONObject;

import be.boeboe.scapsync.rest.interfaces.IScapSyncFeedCount;

/**
 * Rest Implementation of a ScapSync Feed Count
 * 
 * Example json:
 * { "doc_type" : "cce", "type" : "Configuration",
 *   "changed_count" : 4, "new_count" : 0 }
 * @author boeboe
 */
public class ScapSyncFeedCountRest implements IScapSyncFeedCount {
  private static final String DOC_TYPE = "doc_type";
  private static final String TYPE = "type";
  private static final String CHANGED_COUNT = "changed_count";
  private static final String NEW_COUNT = "new_count";
  
  private String fDocType;
  private String fType;
  private int fChangedCount;
  private int fNewCount;

  public ScapSyncFeedCountRest(JSONObject scapSyncFeedCountRest) {
    super();
    try {
      fDocType = scapSyncFeedCountRest.getString(DOC_TYPE);
      fType = scapSyncFeedCountRest.getString(TYPE);
      fChangedCount = scapSyncFeedCountRest.getInt(CHANGED_COUNT);
      fNewCount = scapSyncFeedCountRest.getInt(NEW_COUNT);
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
  
  
  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncFeedCount#getDocType()
   */
  @Override
  public String getDocType() {
    return fDocType;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncFeedCount#getType()
   */
  @Override
  public String getType() {
    return fType;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncFeedCount#getChangedCount()
   */
  @Override
  public int getChangedCount() {
    return fChangedCount;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncFeedCount#getNewCount()
   */
  @Override
  public int getNewCount() {
    return fNewCount;
  }
}
