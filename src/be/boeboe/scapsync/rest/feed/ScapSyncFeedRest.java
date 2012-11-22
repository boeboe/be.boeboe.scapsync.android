/**
 * 
 */
package be.boeboe.scapsync.rest.feed;

import org.json.JSONException;
import org.json.JSONObject;

import be.boeboe.scapsync.rest.interfaces.IScapSyncFeed;

/**
 * Rest Implementation of a ScapSync Feed
 * 
 * Example json:
 * { "count" : 266, "doc_type" : "cce", "title" : "CCE-4909-8 DEPRECATED.",
 *   "url" : "/cce/CCE-4909-8?version=21c48903fce39af9584a9738493d8748",
 *   "version_history_url" : "/versions/cce/CCE-4909-8", "new" : false,
 *   "version_key" : "CCE-4909-8","doc_id" : "21c489ce39af9584a9738493d8748" }
 * @author boeboe
 */
public class ScapSyncFeedRest implements IScapSyncFeed {
  private static final String DOC_TYPE = "doc_type";
  private static final String COUNT = "count";
  private static final String TITLE = "title";
  private static final String URL = "url";
  private static final String VERSION_HISTORY_URL = "version_history_url";
  private static final String NEW = "new";
  private static final String VERSION_KEY = "version_key";
  private static final String DOC_ID = "doc_id";
  
  private String fDocType;
  private int fCount;
  private String fTitle;
  private String fUrl;
  private String fVersionHistoryUrl;
  private boolean fNew;
  private String fVersionKey;
  private String fDocId;

  public ScapSyncFeedRest(JSONObject scapSyncFeedRest) {
    super();
    try {
      fDocType = scapSyncFeedRest.getString(DOC_TYPE);
      fCount = scapSyncFeedRest.getInt(COUNT);
      fTitle = scapSyncFeedRest.getString(TITLE);
      fUrl = scapSyncFeedRest.getString(URL);
      fVersionHistoryUrl = scapSyncFeedRest.has(VERSION_HISTORY_URL) ? scapSyncFeedRest.getString(VERSION_HISTORY_URL): null;
      fNew = Boolean.parseBoolean(scapSyncFeedRest.getString(NEW));
      fVersionKey = scapSyncFeedRest.getString(VERSION_KEY);
      fDocId = scapSyncFeedRest.getString(DOC_ID);
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncFeed#getDocType()
   */
  @Override
  public String getDocType() {
    return fDocType;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncFeed#getCount()
   */
  @Override
  public int getCount() {
    return fCount;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncFeed#getTitle()
   */
  @Override
  public String getTitle() {
    return fTitle;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncFeed#getUrl()
   */
  @Override
  public String getUrl() {
    return fUrl;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncFeed#isNew()
   */
  @Override
  public boolean isNew() {
    return fNew;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncFeed#getVersionKey()
   */
  @Override
  public String getVersionKey() {
    return fVersionKey;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncFeed#getDocId()
   */
  @Override
  public String getDocId() {
    return fDocId;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncFeed#getVersionHistoryUrl()
   */
  @Override
  public String getVersionHistoryUrl() {
    return fVersionHistoryUrl;
  }

  @Override
  public String toString() {
    return "ScapSyncFeedRest [fDocType=" + fDocType + ", fCount=" + fCount
        + ", fTitle=" + fTitle + ", fUrl=" + fUrl + ", fVersionHistoryUrl="
        + fVersionHistoryUrl + ", fNew=" + fNew + ", fVersionKey="
        + fVersionKey + ", fDocId=" + fDocId + "]";
  }
}
