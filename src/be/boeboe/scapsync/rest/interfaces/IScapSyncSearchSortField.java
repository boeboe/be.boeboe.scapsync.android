/**
 * 
 */
package be.boeboe.scapsync.rest.interfaces;

/**
 * Interface to represent a ScapSync Sort Field
 * @author boeboe
 */
public interface IScapSyncSearchSortField {

  /**
   * Get the selected state of the Sort Field.
   * @return boolean true if sort field selected. False if not selected.
   */
  public boolean getSelected();
  
  /**
   * Get the name of the Sort Field.
   * @return name the name of the sort field.
   */
  public String getName();
}
