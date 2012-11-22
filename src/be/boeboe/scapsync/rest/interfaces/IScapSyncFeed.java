/**
 * 
 */
package be.boeboe.scapsync.rest.interfaces;

/**
 * Interface to represent a ScapSync Feed Item
 * @author vanbosb
 */
public interface IScapSyncFeed {
  
  /**
   * The document type of this feed item.
   * @return type the document type of this feed item
   */
  public String getDocType();

  /**
   * The count of this feed item.
   * @return count the count of this feed item
   */
  public int getCount();

  /**
   * The title of this feed item.
   * @return title the title of this feed item
   */
  public String getTitle();

  /**
   * The url (relative) of this feed item.
   * @return url the url of this feed item
   */
  public String getUrl();

  /**
   * The version history url (relative) of this feed item.
   * @return url the version history url of this feed item
   */
  public String getVersionHistoryUrl();

  /**
   * Is this feed item new or not.
   * @return boolean true if the feed item is new
   */
  public boolean isNew();

  /**
   * The version key of this feed item.
   * @return version_key the version key of this feed item
   */
  public String getVersionKey();

  /**
   * The doc id of this feed item.
   * @return doc_id the doc id of this feed item
   */
  public String getDocId();

}