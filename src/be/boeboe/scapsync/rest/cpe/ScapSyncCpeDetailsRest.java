package be.boeboe.scapsync.rest.cpe;

import java.util.Arrays;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import be.boeboe.scapsync.rest.ScapSyncUtils;
import be.boeboe.scapsync.rest.interfaces.IScapSyncCpeDetails;

/**
 * Rest Implementation of a ScapSync CPE
 * 
 * Example json:
 * { "status" : "draft", "name" : "cpe:/a:openssl:openssl:0.9.6k",
 *   "version_count" : 1, "upstream_modified" : "2008-03-25T19:16:29.693Z",
 *   "titles" : [ "[English] OpenSSL Project OpenSSL 0.9.6k" ],
 *   "version_url" : "/versions/cpe/cpe:/a:openssl:openssl:0.9.6k" }
 * @author boeboe
 */
public class ScapSyncCpeDetailsRest implements IScapSyncCpeDetails {
  private static final String STATUS = "status";
  private static final String NAME = "name";
  private static final String VERSION_COUNT = "version_count";
  private static final String UPSTREAM_MODIFIED = "upstream_modified";
  private static final String TITLES = "titles";
  private static final String VERSION_URL = "version_url";
  
  private String fStatus;
  private String fName;
  private String fVersionCount;
  private Date fUpstreamModified;
  private String[] fTitles;
  private String fVersionUrl;
  
  public ScapSyncCpeDetailsRest(JSONObject scapSyncCpeDetailsRest) {
    super();
    try {
      fStatus = scapSyncCpeDetailsRest.getString(STATUS);
      fName = scapSyncCpeDetailsRest.getString(NAME);
      fVersionCount = scapSyncCpeDetailsRest.getString(VERSION_COUNT);
      fUpstreamModified = ScapSyncUtils.getDate(scapSyncCpeDetailsRest.getString(UPSTREAM_MODIFIED));
      fVersionUrl = scapSyncCpeDetailsRest.getString(VERSION_URL);

      fTitles = ScapSyncUtils.getStringArray(scapSyncCpeDetailsRest.getJSONArray(TITLES));
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCpeDetails#getStatus()
   */
  @Override
  public String getStatus() {
    return fStatus;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCpeDetails#getName()
   */
  @Override
  public String getName() {
    return fName;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCpeDetails#getVersionCount()
   */
  @Override
  public String getVersionCount() {
    return fVersionCount;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCpeDetails#getUpstreamModfied()
   */
  @Override
  public Date getUpstreamModfied() {
    return fUpstreamModified;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCpeDetails#getTitles()
   */
  @Override
  public String[] getTitles() {
    return fTitles;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCpeDetails#getVersionUrl()
   */
  @Override
  public String getVersionUrl() {
    return fVersionUrl;
  }

  @Override
  public String toString() {
    return "ScapSyncCpeDetailsRest [fStatus=" + fStatus + ", fName=" + fName
        + ", fVersionCount=" + fVersionCount + ", fUpstreamModified="
        + fUpstreamModified + ", fTitles=" + Arrays.toString(fTitles)
        + ", fVersionUrl=" + fVersionUrl + "]";
  }
}
