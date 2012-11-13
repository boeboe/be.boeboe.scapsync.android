/**
 * 
 */
package be.boeboe.scapsync.rest.search;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import be.boeboe.scapsync.rest.ScapSyncUtils;
import be.boeboe.scapsync.rest.interfaces.IScapSyncSearchResult;
import be.boeboe.scapsync.rest.interfaces.IScapSyncSearchResultType;

/**
 * Rest Implementation of a ScapSync Result
 * 
 * Example json:
 * {"id":"CVE-2009-2629",
 *      "summary_text":"CWE-119 through 0.5.37, 0.6.x before...",
 *      "title_text":"Buffer underflow in...",
 *      "type":"cve",
 *      "url":"/cve/CVE-2009-2629",
 *      "modified":"2009-12-19T06:56:44Z"}
 * @author boeboe
 */
public class ScapSyncSearchResultRest implements IScapSyncSearchResult {
  private static final String ID = "id";
  private static final String SUMMARY_TEXT = "summary_text";
  private static final String TITLE_TEXT = "title_text";
  private static final String TYPE = "type";
  private static final String URL = "url";
  private static final String MODIFIED_DATE = "modified";

  private String fId;
  private String fSummaryText;
  private String fTitleText;
  private String fUrl;
  private IScapSyncSearchResultType fType;
  private Date fModifiedDate;
  
  public ScapSyncSearchResultRest(JSONObject scapSyncSearchResultRest) {
    super();
    try {
      fId = scapSyncSearchResultRest.getString(ID);
      fSummaryText = scapSyncSearchResultRest.getString(SUMMARY_TEXT);
      fTitleText = scapSyncSearchResultRest.getString(TITLE_TEXT);
      fUrl = scapSyncSearchResultRest.getString(URL);
      fType = IScapSyncSearchResultType.fromString(scapSyncSearchResultRest.getString(TYPE));
      fModifiedDate = ScapSyncUtils.getDate(scapSyncSearchResultRest.getString(MODIFIED_DATE));
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncSearchResult#getId()
   */
  @Override
  public String getId() {
    return fId;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncSearchResult#getSummaryText()
   */
  @Override
  public String getSummaryText() {
    return fSummaryText;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncSearchResult#getTitleText()
   */
  @Override
  public String getTitleText() {
    return fTitleText;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncSearchResult#getUrl()
   */
  @Override
  public String getUrl() {
    return fUrl;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncSearchResult#getType()
   */
  @Override
  public IScapSyncSearchResultType getType() {
    return fType;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncSearchResult#getModifiedDate()
   */
  @Override
  public Date getModifiedDate() {
    return fModifiedDate;
  }

  @Override
  public String toString() {
    return "ScapSyncSearchResultRest [fId=" + fId + ", fSummaryText="
        + fSummaryText + ", fTitleText=" + fTitleText + ", fUrl=" + fUrl
        + ", fType=" + fType + ", fModifiedDate=" + fModifiedDate + "]";
  }
}
