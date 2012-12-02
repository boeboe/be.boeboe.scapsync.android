/**
 * 
 */
package be.boeboe.scapsync.rest.cve;

import java.util.Arrays;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import be.boeboe.scapsync.rest.ScapSyncUtils;
import be.boeboe.scapsync.rest.interfaces.IScapSyncCveDetails;
import be.boeboe.scapsync.rest.interfaces.IScapSyncCveReference;
import be.boeboe.scapsync.rest.interfaces.IScapSyncCvss;

/**
 * Rest Implementation of a ScapSync CVE
 * 
 * Example json:
 * { "cvss_vector" : "A:P/AC:L/I:P/C:P/Au:N/AV:N", "cvss_base_score" : 7.5, 
 *   "version_count" : 1, "upstream_modified" : "2009-12-19T06:56:44.377000Z",
 *   "summary" : "Buffer underflow in src/http/ngx_http_parse.c ...",
 *   "references" : [ { ... } ], 
 *   "upstream_published" : "2009-09-15T22:30:00.233000Z",
 *   "assessments" : [ ], "cwe" : { ... }, "cvss" : { ... },
 *   "version_url" : "/versions/cve/CVE-2009-2629" }
 * @author boeboe
 */
public class ScapSyncCveDetailsRest implements IScapSyncCveDetails {
  private static final String CVSS = "cvss";
  private static final String CVSS_VECTOR = "cvss_vector";
  private static final String CVSS_BASE_SCORE = "cvss_base_score";
  private static final String VERSION_COUNT = "version_count";
  private static final String UPSTREAM_MODIFIED = "upstream_modified";
  private static final String UPSTREAM_PUBLISHED = "upstream_published";
  private static final String SUMMARY = "summary";
  private static final String REFERENCES = "references";
  private static final String VERSION_URL = "version_url";
  
  private IScapSyncCvss fCvss;
  private String fCvssVector;
  private float fCvssBaseScore;
  private int fVersionCount;
  private Date fUpstreamModified;
  private Date fUpstreamPublished;
  private String fSummary;
  private IScapSyncCveReference[] fReferences;
  private String fVersionUrl;
  
  public ScapSyncCveDetailsRest(JSONObject scapSyncCveDetailsRest) {
    super();
    try {
      fCvss = scapSyncCveDetailsRest.has(CVSS) ? new ScapSyncCveCvssRest(scapSyncCveDetailsRest.getJSONObject(CVSS)) : null;
      fCvssVector = scapSyncCveDetailsRest.has(CVSS_VECTOR) ? scapSyncCveDetailsRest.getString(CVSS_VECTOR): null;
      fCvssBaseScore = scapSyncCveDetailsRest.has(CVSS_BASE_SCORE) ? (float) scapSyncCveDetailsRest.getDouble(CVSS_BASE_SCORE): 0;
      fVersionCount = scapSyncCveDetailsRest.getInt(VERSION_COUNT);
      fUpstreamModified = ScapSyncUtils.getDate(scapSyncCveDetailsRest.getString(UPSTREAM_MODIFIED));
      fUpstreamPublished = ScapSyncUtils.getDate(scapSyncCveDetailsRest.getString(UPSTREAM_PUBLISHED));
      fSummary = scapSyncCveDetailsRest.getString(SUMMARY);
      fVersionUrl = scapSyncCveDetailsRest.getString(VERSION_URL);
      fReferences = ScapSyncUtils.getObjectArray(scapSyncCveDetailsRest.getJSONArray(REFERENCES), ScapSyncCveReferenceRest.class);
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCveDetails#getCvss()
   */
  @Override
  public IScapSyncCvss getCvss() {
    return fCvss;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCveDetails#getCvssVector()
   */
  @Override
  public String getCvssVector() {
    return fCvssVector;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCveDetails#getCvssBaseScore()
   */
  @Override
  public float getCvssBaseScore() {
    return fCvssBaseScore;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCveDetails#getVersionCount()
   */
  @Override
  public int getVersionCount() {
    return fVersionCount;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCveDetails#getUpstreamModifiedDate()
   */
  @Override
  public Date getUpstreamModifiedDate() {
    return fUpstreamModified;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCveDetails#getUpstreamPublishedDate()
   */
  @Override
  public Date getUpstreamPublishedDate() {
    return fUpstreamPublished;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCveDetails#getSummary()
   */
  @Override
  public String getSummary() {
    return fSummary;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCveDetails#getRefences()
   */
  @Override
  public IScapSyncCveReference[] getRefences() {
    return fReferences;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCveDetails#getVersionUrl()
   */
  @Override
  public String getVersionUrl() {
    return fVersionUrl;
  }

  @Override
  public String toString() {
    return "ScapSyncCveRest [fCvssVector=" + fCvssVector + ", fCvssBaseScore="
        + fCvssBaseScore + ", fVersionCount=" + fVersionCount
        + ", fUpstreamModified=" + fUpstreamModified + ", fUpstreamPublished="
        + fUpstreamPublished + ", fSummary=" + fSummary + ", fReferences="
        + Arrays.toString(fReferences) + "]";
  }
}
