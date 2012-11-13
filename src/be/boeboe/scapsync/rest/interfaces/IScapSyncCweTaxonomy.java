/**
 * 
 */
package be.boeboe.scapsync.rest.interfaces;

/**
 * Interface to represent a ScapSync CWE Taxonomy
 * @author boeboe
 */
public interface IScapSyncCweTaxonomy {

  /**
   * Get the Taxonomy from this CWE Taxonomy.
   * @return taxonomy the Taxonomy from this CWE Taxonomy
   */
  public String getTaxonomy();

  /**
   * Get the Name from this CWE Taxonomy.
   * @return name the Name from this CWE Taxonomy
   */
  public String getName();

  /**
   * Get the Fit from this CWE.
   * @return fit the Fit from this CWE Taxonomy
   */
  public String getFit();

  /**
   * Get the ID from this CWE Taxonomy.
   * @return notes the ID from this CWE Taxonomy
   */
  public String getId();
}
