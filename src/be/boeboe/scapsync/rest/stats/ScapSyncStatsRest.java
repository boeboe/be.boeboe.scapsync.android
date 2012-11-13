/**
 * 
 */
package be.boeboe.scapsync.rest.stats;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import be.boeboe.scapsync.rest.interfaces.IScapSyncStats;

/**
 * Rest Implementation of a ScapSync Statistics
 * 
 * Example json:
 * { "chart" : { 
 *      "data" : {
 *         "rows" : [ { "c" : [ { "v" : "Configurations" }, { "v" : 11241 } ]
 *                 }, { "c" : [ { "v" : "Products" }, { "v" : 64265 } ]
 *                 }, { "c" : [ { "v" : "Vulnerabilities" }, { "v" : 54333 } ]
 *                 }, { "c" : [ { "v" : "Weaknesses" }, { "v" : 909 } ]
 *                 } ],
 *         "cols" : [ { "type" : "string", 
 *                      "id" : "type", 
 *                      "label" : "Content Type"
 *                 }, {
 *                      "type" : "number",
 *                      "id" : "records",
 *                      "label" : "Number Of Records"
 *                 } ]
 *               }
 *              ...
 *             }
 * }
 * @author boeboe
 */
public class ScapSyncStatsRest implements IScapSyncStats {
  private static final String CHART = "chart";
  private static final String DATA = "data";
  private static final String ROWS = "rows";
  private static final String COUNT = "c";
  private static final String VALUE = "v";

  Map<String,Integer> fStatistics = new HashMap<String,Integer>();
  

  public ScapSyncStatsRest(JSONObject scapSyncStatsRest) {
    super();
    try {
      JSONArray rows = scapSyncStatsRest.getJSONObject(CHART).getJSONObject(DATA).getJSONArray(ROWS);

      for (int i = 0 ; i < rows.length(); i++) {
        JSONArray row = rows.getJSONObject(i).getJSONArray(COUNT);
        String scope = row.getJSONObject(0).getString(VALUE);
        fStatistics.put(scope, row.getJSONObject(1).getInt(VALUE));
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncStats#getStatistics()
   */
  @Override
  public Map<String,Integer> getStatistics() {
    return fStatistics;
  }
}
