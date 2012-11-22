package be.boeboe.scapsync.rest.feed;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import be.boeboe.scapsync.rest.ScapSyncUtils;
import be.boeboe.scapsync.rest.interfaces.IScapSyncDailyFeed;
import be.boeboe.scapsync.rest.interfaces.IScapSyncFeed;
import be.boeboe.scapsync.rest.interfaces.IScapSyncFeedCount;

public class ScapSyncDailyFeedRest implements IScapSyncDailyFeed {
  private static final String CURRENT_DATE = "current_date";
  private static final String VERSIONS = "versions";
  private static final String CHANGED_ITEMS = "changed_items";
  private static final String NEW_ITEMS = "new_items";
  private static final String COUNTS = "counts";

  private Date fCurrentDate;
  private IScapSyncFeedCount[] fFeedStats;
  private IScapSyncFeed[] fScapSyncNewFeeds;
  private IScapSyncFeed[] fScapSyncChangedFeeds;

  public ScapSyncDailyFeedRest(JSONObject scapSyncDailyFeedRest) {
    super();
    try {
      fCurrentDate = ScapSyncUtils.getDate(scapSyncDailyFeedRest.getString(CURRENT_DATE));
      fFeedStats = ScapSyncUtils.getObjectArray(scapSyncDailyFeedRest.getJSONArray(COUNTS), ScapSyncFeedCountRest.class);
      fScapSyncNewFeeds = ScapSyncUtils.getObjectArray(scapSyncDailyFeedRest.getJSONObject(VERSIONS).getJSONArray(NEW_ITEMS), ScapSyncFeedRest.class);
      fScapSyncChangedFeeds = ScapSyncUtils.getObjectArray(scapSyncDailyFeedRest.getJSONObject(VERSIONS).getJSONArray(CHANGED_ITEMS), ScapSyncFeedRest.class);
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncDailyFeed#getCurrentDate()
   */
  @Override
  public Date getCurrentDate() {
    return fCurrentDate;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncDailyFeed#getNewItems()
   */
  @Override
  public IScapSyncFeed[] getNewItems() {
    return fScapSyncNewFeeds;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncDailyFeed#getChangedItems()
   */
  @Override
  public IScapSyncFeed[] getChangedItems() {
    return fScapSyncChangedFeeds;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncDailyFeed#getFeedStats()
   */
  @Override
  public IScapSyncFeedCount[] getFeedStats() {
    return fFeedStats;
  }
}
