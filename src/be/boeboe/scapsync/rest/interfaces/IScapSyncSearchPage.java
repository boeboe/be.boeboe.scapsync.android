package be.boeboe.scapsync.rest.interfaces;

/**
 * Interface to represent a ScapSync Search Page
 * @author boeboe
 */
public interface IScapSyncSearchPage {

  /**
   * Get the integer name/id from the Page.
   * @return name a number representing the page name
   */
  public int getName();
  
  /**
   * Get the relative URL towards the page. 
   * @return the url to get the page
   */
  public String getUrl();
}
