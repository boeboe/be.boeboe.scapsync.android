/**
 * 
 */
package be.boeboe.scapsync.rest.interfaces;

/**
 * Interface to represent a ScapSync Feed Count
 * @author vanbosb
 */
public interface IScapSyncFeedCount {

  /**
   * The document type of this feed count.
   * @return doc_type the document type of this feed count
   */
  public String getDocType();

  /**
   * The type of this feed count.
   * @return type the type of this feed count
   */
  public String getType();

  /**
   * The changed count of this feed count.
   * @return changed_count the changed count of this feed count
   */
  public int getChangedCount();

  /**
   * The new count of this feed item.
   * @return new_count the new count of this feed count
   */
  public int getNewCount();
}
