package be.boeboe.scapsync.rest;

import java.net.URI;

import org.json.JSONException;
import org.json.JSONObject;

import be.boeboe.scapsync.rest.cce.ScapSyncCceDetailsRest;
import be.boeboe.scapsync.rest.cpe.ScapSyncCpeDetailsRest;
import be.boeboe.scapsync.rest.cve.ScapSyncCveDetailsRest;
import be.boeboe.scapsync.rest.cwe.ScapSyncCweDetailsRest;
import be.boeboe.scapsync.rest.feed.ScapSyncDailyFeedRest;
import be.boeboe.scapsync.rest.interfaces.IScapSyncCceDetails;
import be.boeboe.scapsync.rest.interfaces.IScapSyncCpeDetails;
import be.boeboe.scapsync.rest.interfaces.IScapSyncCveDetails;
import be.boeboe.scapsync.rest.interfaces.IScapSyncCweDetails;
import be.boeboe.scapsync.rest.interfaces.IScapSyncDailyFeed;
import be.boeboe.scapsync.rest.interfaces.IScapSyncHandle;
import be.boeboe.scapsync.rest.interfaces.IScapSyncSearchOrderFilter;
import be.boeboe.scapsync.rest.interfaces.IScapSyncSearchResultType;
import be.boeboe.scapsync.rest.interfaces.IScapSyncStats;
import be.boeboe.scapsync.rest.stats.ScapSyncStatsRest;

/**
 * Rest Implementation of a ScapSyncHandle.
 * @author boeboe
 */
public class ScapSyncHandle implements IScapSyncHandle {
  public static final URI SCAP_SYNC_BASE_URL = URI.create("http://scapsync.com");
  
  private static final String SEARCH_URL = "search_url";
  private static final String STATS_URL = "stats_url";
  private static final String DAILY_FEED = "daily_feed_url";
  private static final String CCE_URL_PATTERN = "cce_url_pattern";
  private static final String CPE_URL_PATTERN = "cpe_url_pattern";
  private static final String CVE_URL_PATTERN = "cve_url_pattern";
  private static final String CWE_URL_PATTERN = "cwe_url_pattern";

  private URI fSearchUri;
  private URI fDailyFeedUri;
  private URI fStatsUri;
  private URI fCceUri;
  private URI fCpeUri;
  private URI fCveUri;
  private URI fCweUri;

  public ScapSyncHandle() {
    JSONObject jsonMain = ScapSyncUtils.execRestGet(SCAP_SYNC_BASE_URL);
    
    try {
      fSearchUri = URI.create(jsonMain.getString(SEARCH_URL));
      fDailyFeedUri = URI.create(jsonMain.getString(DAILY_FEED));
      fStatsUri = URI.create(jsonMain.getString(STATS_URL));
      
      String cceUrlPattern = jsonMain.getString(CCE_URL_PATTERN);
      String cpeUrlPattern = jsonMain.getString(CPE_URL_PATTERN);
      String cveUrlPattern = jsonMain.getString(CVE_URL_PATTERN);
      String cweUrlPattern = jsonMain.getString(CWE_URL_PATTERN);
      
      fCceUri = URI.create(SCAP_SYNC_BASE_URL + cceUrlPattern.replace("/id", "/"));
      fCpeUri = URI.create(SCAP_SYNC_BASE_URL + cpeUrlPattern.replace("/id", "/"));
      fCveUri = URI.create(SCAP_SYNC_BASE_URL + cveUrlPattern.replace("/id", "/"));
      fCweUri = URI.create(SCAP_SYNC_BASE_URL + cweUrlPattern.replace("/id", "/"));
    } catch (JSONException e) {
      throw new RuntimeException("Problem fetching base url patterns", e);
    }
  }
  
  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncHandle#searchAll(java.lang.String)
   */
  public ScapSyncSearch search(String searchItem, IScapSyncSearchResultType type, IScapSyncSearchOrderFilter sorting) {
    URI queryUri = URI.create(fSearchUri + "?");

    if (null != type) {
      queryUri =  URI.create(queryUri + IScapSyncSearchResultType.PREFIX.getText() + type.getText());
    }

    if (null != sorting) {
      queryUri = URI.create(queryUri + IScapSyncSearchOrderFilter.PREFIX.getText() + sorting.getText());
    }

    System.out.println(queryUri);
    return new ScapSyncSearch(queryUri, searchItem);
  }


  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncHandle#getCceDetails(java.lang.String)
   */
  public IScapSyncCceDetails getCceDetails(String id) {
    URI detailsUri = URI.create(fCceUri + id);
    JSONObject jsonDetailsResult = ScapSyncUtils.execRestGet(detailsUri);
    return new ScapSyncCceDetailsRest(jsonDetailsResult);
  }
  
  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncHandle#getCpeDetails(java.lang.String)
   */
  public IScapSyncCpeDetails getCpeDetails(String id) {
    URI detailsUri = URI.create(fCpeUri + id);
    JSONObject jsonDetailsResult = ScapSyncUtils.execRestGet(detailsUri);
    return new ScapSyncCpeDetailsRest(jsonDetailsResult);
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncHandle#getCveDetails(java.lang.String)
   */
  public IScapSyncCveDetails getCveDetails(String id) {
    URI detailsUri = URI.create(fCveUri + id);
    JSONObject jsonDetailsResult = ScapSyncUtils.execRestGet(detailsUri);
    return new ScapSyncCveDetailsRest(jsonDetailsResult);
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncHandle#getCweDetails(java.lang.String)
   */
  public IScapSyncCweDetails getCweDetails(String id) {
    URI detailsUri = URI.create(fCweUri + id);
    JSONObject jsonDetailsResult = ScapSyncUtils.execRestGet(detailsUri);
    return new ScapSyncCweDetailsRest(jsonDetailsResult);
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncHandle#getStatistics()
   */
  @Override
  public IScapSyncStats getStatistics() {
    IScapSyncStats stats = new ScapSyncStatsRest(ScapSyncUtils.execRestGet(fStatsUri));
    return stats;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncHandle#getDailyFeed()
   */
  @Override
  public IScapSyncDailyFeed getDailyFeed() {
    IScapSyncDailyFeed dailyFeeds = new ScapSyncDailyFeedRest(ScapSyncUtils.execRestGet(fDailyFeedUri));
    return dailyFeeds;
  }
}
