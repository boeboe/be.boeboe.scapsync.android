package be.boeboe.scapsync.rest;

import java.net.URI;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import be.boeboe.scapsync.rest.cce.ScapSyncCceDetailsRest;
import be.boeboe.scapsync.rest.cpe.ScapSyncCpeDetailsRest;
import be.boeboe.scapsync.rest.cve.ScapSyncCveDetailsRest;
import be.boeboe.scapsync.rest.cwe.ScapSyncCweDetailsRest;
import be.boeboe.scapsync.rest.interfaces.IScapSyncCceDetails;
import be.boeboe.scapsync.rest.interfaces.IScapSyncCpeDetails;
import be.boeboe.scapsync.rest.interfaces.IScapSyncCveDetails;
import be.boeboe.scapsync.rest.interfaces.IScapSyncCweDetails;
import be.boeboe.scapsync.rest.interfaces.IScapSyncSearchResult;
import be.boeboe.scapsync.rest.interfaces.IScapSyncSearchResultType;
import be.boeboe.scapsync.rest.interfaces.IScapSyncSearcher;
import be.boeboe.scapsync.rest.interfaces.IScapSyncStats;
import be.boeboe.scapsync.rest.stats.ScapSyncStatsRest;

/**
 * Rest Implementation of a ScapSyncSearcher.
 * @author boeboe
 */
public class ScapSyncSearcher implements IScapSyncSearcher {
  public static final URI SCAP_SYNC_BASE_URL = URI.create("http://scapsync.com");
  private static final String SEARCH_PATTERN = "search_url";
  private static final String STATS_PATTERN = "stats_url";
  
  
  private URI fSearchBaseUri;
  private URI fQueryAllBaseUri;
  private URI fQueryCceBaseUri;
  private URI fQueryCpeBaseUri;
  private URI fQueryCveBaseUri;
  private URI fQueryCweBaseUri;
  private URI fStatsUri;

  public ScapSyncSearcher() {
    JSONObject jsonMain = ScapSyncUtils.execRestGet(SCAP_SYNC_BASE_URL);
    
    try {
      fSearchBaseUri = URI.create(jsonMain.getString(SEARCH_PATTERN));
      fQueryAllBaseUri = URI.create(fSearchBaseUri + "?q=");
      fQueryCceBaseUri = URI.create(fSearchBaseUri + "?solrDocumentType=cce&q=");
      fQueryCpeBaseUri = URI.create(fSearchBaseUri + "?solrDocumentType=cpe&q=");
      fQueryCveBaseUri = URI.create(fSearchBaseUri + "?solrDocumentType=cve&q=");
      fQueryCweBaseUri = URI.create(fSearchBaseUri + "?solrDocumentType=cwe&q=");

      fStatsUri = URI.create(jsonMain.getString(STATS_PATTERN));
    } catch (JSONException e) {
      throw new RuntimeException("Problem fetching base url patterns", e);
    }
  }

  public String getfSearchBaseUri() {
    return fSearchBaseUri.toString();
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncSearcher#searchAll(java.lang.String)
   */
  public ScapSyncSearch searchAll(String searchItem) {
    ScapSyncSearch search = new ScapSyncSearch(fQueryAllBaseUri, searchItem);
    return search;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncSearcher#searchCce(java.lang.String)
   */
  public ScapSyncSearch searchCce(String searchItem) {
    return new ScapSyncSearch(fQueryCceBaseUri, searchItem);
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncSearcher#searchCpe(java.lang.String)
   */
  public ScapSyncSearch searchCpe(String searchItem) {
    return new ScapSyncSearch(fQueryCpeBaseUri, searchItem);
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncSearcher#searchCve(java.lang.String)
   */
  public ScapSyncSearch searchCve(String searchItem) {
    return new ScapSyncSearch(fQueryCveBaseUri, searchItem);
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncSearcher#searchCwe(java.lang.String)
   */
  public ScapSyncSearch searchCwe(String searchItem) {
    return new ScapSyncSearch(fQueryCweBaseUri, searchItem);
  }


  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncSearcher#searchCce(java.lang.String)
   */
  public IScapSyncCceDetails getCceDetails(IScapSyncSearchResult searchResult) {
    if (searchResult.getType() != IScapSyncSearchResultType.TYPE_CCE) {
      return null;
    } else {
      URI detailsUri = URI.create(SCAP_SYNC_BASE_URL + searchResult.getUrl());
      JSONObject jsonDetailsResult = ScapSyncUtils.execRestGet(detailsUri);
      return new ScapSyncCceDetailsRest(jsonDetailsResult);
    }
  }
  
  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncSearcher#getCpeDetails(
   *          be.boeboe.scapsync.rest.interfaces.IScapSyncSearchResult)
   */
  public IScapSyncCpeDetails getCpeDetails(IScapSyncSearchResult searchResult) {
    if (searchResult.getType() != IScapSyncSearchResultType.TYPE_CPE) {
      return null;
    } else {
      URI detailsUri = URI.create(SCAP_SYNC_BASE_URL + searchResult.getUrl());
      JSONObject jsonDetailsResult = ScapSyncUtils.execRestGet(detailsUri);
      return new ScapSyncCpeDetailsRest(jsonDetailsResult);
    }
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncSearcher#getCveDetails(
   *          be.boeboe.scapsync.rest.interfaces.IScapSyncSearchResult)
   */
  public IScapSyncCveDetails getCveDetails(IScapSyncSearchResult searchResult) {
    if (searchResult.getType() != IScapSyncSearchResultType.TYPE_CVE) {
      return null;
    } else {
      URI detailsUri = URI.create(SCAP_SYNC_BASE_URL + searchResult.getUrl());
      JSONObject jsonDetailsResult = ScapSyncUtils.execRestGet(detailsUri);
      return new ScapSyncCveDetailsRest(jsonDetailsResult);
    }
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncSearcher#getCweDetails(
   *        be.boeboe.scapsync.rest.interfaces.IScapSyncSearchResult)
   */
  public IScapSyncCweDetails getCweDetails(IScapSyncSearchResult searchResult) {
    if (searchResult.getType() != IScapSyncSearchResultType.TYPE_CWE) {
      return null;
    } else {
      URI detailsUri = URI.create(SCAP_SYNC_BASE_URL + searchResult.getUrl());
      JSONObject jsonDetailsResult = ScapSyncUtils.execRestGet(detailsUri);
      return new ScapSyncCweDetailsRest(jsonDetailsResult);
    }
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncSearcher#close()
   */
  @Override
  public void close() {
    
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncSearcher#getStatistics()
   */
  @Override
  public Map<String, Integer> getStatistics() {
    IScapSyncStats stats = new ScapSyncStatsRest(ScapSyncUtils.execRestGet(fStatsUri));
    return stats.getStatistics();
  }
}
