/**
 * 
 */
package be.boeboe.scapsync.rest.interfaces;

/**
 * Interface to represent a ScapSync Facet
 * @author boeboe
 */
public interface IScapSyncSearchFacet {
  
  /**
   * Get the selected state of the Facet.
   * @return boolean true if the facet is selected. False if not selected
   */
  public boolean getSelected();
  
  /**
   * Get the name of the Facet.
   * @return name the name of the Facet
   */
  public String getName();
  
  /**
   * Get the relative URL of the Facet.
   * @return url the URL of the Facet
   */
  public String getUrl();
}
