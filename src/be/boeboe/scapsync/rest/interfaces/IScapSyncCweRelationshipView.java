/**
 * 
 */
package be.boeboe.scapsync.rest.interfaces;


/**
 * Interface to represent a ScapSync CWE Relationship View
 * 
 * @author boeboe
 */
public interface IScapSyncCweRelationshipView {

  /**
   * Get the (relative) URL from this CWE Relationship View.
   * @return url the URL from this CWE Relationship View
   */
  public String getUrl();
  
  /**
   * Get the Id from this CWE Relationship View.
   * @return id the Id from this CWE Relationship View
   */
  public String getId();  
}