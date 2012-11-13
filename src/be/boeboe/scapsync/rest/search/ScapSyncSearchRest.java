/**
 * 
 */
package be.boeboe.scapsync.rest.search;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import be.boeboe.scapsync.rest.ScapSyncUtils;
import be.boeboe.scapsync.rest.interfaces.IScapSyncSearch;
import be.boeboe.scapsync.rest.interfaces.IScapSyncSearchFacet;
import be.boeboe.scapsync.rest.interfaces.IScapSyncSearchPage;
import be.boeboe.scapsync.rest.interfaces.IScapSyncSearchResult;
import be.boeboe.scapsync.rest.interfaces.IScapSyncSearchSortField;

/**
 * Rest Implementation of a ScapSync Search
 * 
 * Example JSON:
 * { "rows_per_page" : 10, "sort_fields" : [ { ... } ], "start_row" : 0,
 *   "total_rows" : 11, "facets" : [ { ... } ], "current_page" : 0,
 *   "results" : [ { ... } ], "end_row" : 10, "search_url" : "/search",
 *   "pages" : [ { ... } ] }
 * @author boeboe
 */
public class ScapSyncSearchRest implements IScapSyncSearch {
  private static final String ROWS_PER_PAGE = "rows_per_page";
  private static final String SORT_FIELDS = "sort_fields";
  private static final String START_ROW = "start_row";
  private static final String TOTAL_ROWS = "total_rows";
  private static final String FACETS = "facets";
  private static final String CURRENT_PAGE = "current_page";
  private static final String RESULS = "results";
  private static final String END_ROW = "end_row";
  private static final String SEARCH_URL = "search_url";
  private static final String PAGES = "pages";

  private int fRowsPerPage;
  private IScapSyncSearchSortField[] fSortFields;
  private int fStartRow;
  private int fTotalRows;
  private IScapSyncSearchFacet[] fFacets;
  private int fCurrentPage;
  private IScapSyncSearchResult[] fResults;
  private int fEndRow;
  private String fSearchUrl;
  private IScapSyncSearchPage[] fPages;
  
  public ScapSyncSearchRest(JSONObject scapSyncSearchRest) {
    super();
    try {
      fRowsPerPage = scapSyncSearchRest.getInt(ROWS_PER_PAGE);
      fStartRow = scapSyncSearchRest.getInt(START_ROW);
      fTotalRows = scapSyncSearchRest.getInt(TOTAL_ROWS);
      fCurrentPage = scapSyncSearchRest.getInt(CURRENT_PAGE);
      fEndRow = scapSyncSearchRest.getInt(END_ROW);
      fSearchUrl = scapSyncSearchRest.getString(SEARCH_URL);

      fSortFields = ScapSyncUtils.getObjectArray(scapSyncSearchRest.getJSONArray(SORT_FIELDS), ScapSyncSearchSortFieldRest.class);
      fResults = ScapSyncUtils.getObjectArray(scapSyncSearchRest.getJSONArray(RESULS), ScapSyncSearchResultRest.class);
      fPages = ScapSyncUtils.getObjectArray(scapSyncSearchRest.getJSONArray(PAGES), ScapSyncSearchPageRest.class);

      List<IScapSyncSearchFacet> facetList = new ArrayList<IScapSyncSearchFacet>();
      JSONArray facetGroups = scapSyncSearchRest.getJSONArray(FACETS);
      for (int i = 0; i< facetGroups.length(); i++) {
        JSONObject thisFacetGroup = facetGroups.getJSONObject(i);
        String baseFacetName = thisFacetGroup.getString(ScapSyncSearchFacetRest.NAME);
        JSONArray facets = thisFacetGroup.getJSONArray(ScapSyncSearchFacetRest.VALUES);
        for ( int j = 0 ; j < facets.length(); j++) {
          facetList.add(new ScapSyncSearchFacetRest(facets.getJSONObject(j), baseFacetName));
        }
      }
      fFacets = facetList.toArray(new IScapSyncSearchFacet[facetList.size()]);

    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncSearch#getSearchUrl()
   */
  @Override
  public String getSearchUrl() {
    return fSearchUrl;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncSearch#getRowsPerPage()
   */
  @Override
  public int getRowsPerPage() {
    return fRowsPerPage;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncSearch#getStartRow()
   */
  @Override
  public int getStartRow() {
    return fStartRow;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncSearch#getEndRow()
   */
  @Override
  public int getEndRow() {
    return fEndRow;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncSearch#getTotalRows()
   */
  @Override
  public int getTotalRows() {
    return fTotalRows;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncSearch#getCurrentPage()
   */
  @Override
  public int getCurrentPage() {
    return fCurrentPage;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncSearch#getSortFields()
   */
  @Override
  public IScapSyncSearchSortField[] getSortFields() {
    return fSortFields;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncSearch#getFacets()
   */
  @Override
  public IScapSyncSearchFacet[] getFacets() {
    return fFacets;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncSearch#getResults()
   */
  @Override
  public IScapSyncSearchResult[] getResults() {
    return fResults;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncSearch#getPages()
   */
  @Override
  public IScapSyncSearchPage[] getPages() {
    return fPages;
  }
}
