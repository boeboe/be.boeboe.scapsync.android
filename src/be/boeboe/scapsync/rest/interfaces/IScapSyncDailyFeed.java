/**
 * 
 */
package be.boeboe.scapsync.rest.interfaces;

import java.util.Date;

/**
 * @author vanbosb
 *
 */
public interface IScapSyncDailyFeed {
  
  /**
   * Get the current date of this ScapSync Daily feed.
   * @return date the current date of this daily feed
   */
  Date getCurrentDate();

  /**
   * Get new items announced on the ScapSync feed.
   * @return IScapSyncFeed[] the feed on new items
   */
  public IScapSyncFeed[] getNewItems();

  /**
   * Get modified items announced on the ScapSync feed.
   * @return IScapSyncFeed[] the feed on changed items
   */
  public IScapSyncFeed[] getChangedItems();

  /**
   * Get statistics on the ScapSync todays feed
   * @return IScapSyncFeedCount[] statistics on todays feed
   */
  public IScapSyncFeedCount[] getFeedStats(); 
}
