/**
 * 
 */
package be.boeboe.scapsync.rest.interfaces;

/**
 *  Interface to represent a ScapSync Search
 * @author boeboe
 */
public interface IScapSyncSearch {

  /**
   * Get the Relative Search URL of this search.
   * @return page the Relative Search URL of this search
   */
  public String getSearchUrl();

  /**
   * Get the number of rows per page in this search.
   * @return int the number of rows in this search
   */
  public int getRowsPerPage();

  /**
   * Get the Starting Row of this search.
   * @return int the number of the Starting Row of this search
   */
  public int getStartRow();

  /**
   * Get the Ending Row of this search.
   * @return int the number of the Ending Row of this search
   */
  public int getEndRow();
  
  /**
   * Get the Total Rows of this search.
   * @return int the number of Total Rows of this search
   */
  public int getTotalRows(); 

  /**
   * Get the number of the Current Page of this search.
   * @return int the Current Page of this search
   */
  public int getCurrentPage(); 

  /**
   * Get the Sort Fields of this search.
   * @return sortFields[] and array containing the available Sort Fields
   */
  public IScapSyncSearchSortField[] getSortFields();
  
  /**
   * Get the Facets of this search.
   * @return IScapSyncSearchFacet[] an array containing the available Facets
   */
  public IScapSyncSearchFacet[] getFacets();
  
  /**
   * Get the Results of this search.
   * @return IScapSyncSearchResult[] an array containing the available Results
   */
  public IScapSyncSearchResult[] getResults();

  /**
   * Get the Pages of this search.
   * @return IScapSyncSearchPage[] an array containing the available Pages
   */
  public IScapSyncSearchPage[] getPages();

}
