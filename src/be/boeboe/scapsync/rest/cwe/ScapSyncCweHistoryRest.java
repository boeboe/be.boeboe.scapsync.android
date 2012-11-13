package be.boeboe.scapsync.rest.cwe;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import be.boeboe.scapsync.rest.ScapSyncUtils;
import be.boeboe.scapsync.rest.interfaces.IScapSyncCweHistory;

/**
 * Rest Implementation of a ScapSync CWE History
 * 
 * Example json:
 * { "date" : "2008-07-01T00:00:00Z", "organization" : "Cigital",
 *   "modifier" : "Eric Dalci", "comment" : "updated Time_of_Introduction" }
 * @author boeboe
 */
public class ScapSyncCweHistoryRest implements IScapSyncCweHistory {
  private static final String DATE = "date";
  private static final String ORGANIZATION = "organization";
  private static final String MODIFIER = "modifier";
  private static final String COMMENT = "comment";

  private Date fDate;
  private String fOrganization;
  private String fModifier;
  private String fComment;

  public ScapSyncCweHistoryRest(JSONObject scapSyncCweHistoryRest) {
    super();
    try {
      fDate = ScapSyncUtils.getDate(scapSyncCweHistoryRest.getString(DATE));
      fOrganization = scapSyncCweHistoryRest.has(ORGANIZATION) ?scapSyncCweHistoryRest.getString(ORGANIZATION): null;
      fModifier = scapSyncCweHistoryRest.has(MODIFIER) ? scapSyncCweHistoryRest.getString(MODIFIER): null;
      fComment = scapSyncCweHistoryRest.getString(COMMENT);
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweHistory#getDate()
   */
  @Override
  public Date getDate() {
    return fDate;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweHistory#getOrganization()
   */
  @Override
  public String getOrganization() {
    return fOrganization;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweHistory#getModifier()
   */
  @Override
  public String getModifier() {
    return fModifier;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweHistory#getComment()
   */
  @Override
  public String getComment() {
    return fComment;
  }
}
