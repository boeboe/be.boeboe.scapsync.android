/**
 * 
 */
package be.boeboe.scapsync.rest;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author boeboe
 *
 */
public class ScapSyncUtils {
  
  /**
   * getDate is a helper method to convert ScapSync Date Strings to java Date
   * objects. 
   *  Example: "2009-12-19T06:56:44Z" or "2012-06-09T03:14:52.417000Z" or
   *           "2008-03-25T19:16:29.693Z"
   * @param scapSyncDate
   * @return date the a Java Date object
   */
  public static Date getDate(String scapSyncDate) {
    String correctionPattern1 = "(.*)(\\.\\d{6})([Z])";
    String correctionPattern2 = "(.*)(\\.\\d{3})([Z])";
    if (scapSyncDate.matches(correctionPattern1)) {
      scapSyncDate = scapSyncDate.replaceAll(correctionPattern1, "$1$3");
    } else if (scapSyncDate.matches(correctionPattern2)) {
      scapSyncDate = scapSyncDate.replaceAll(correctionPattern2, "$1$3");
    }

    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    Date date = new Date();
    try {
      date = formatter.parse(scapSyncDate);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return date;
  }
  
  /**
   * A helper method to convert one dimensional JSONArrays to StringArrays.
   * @param jsonArray the JSONArray to convert
   * @return stringArray the converted StringArray
   * @throws JSONException exception during JSON parsing
   */
  public static String[] getStringArray(JSONArray jsonArray) throws JSONException {
    String[] stringArray = new String[jsonArray.length()];
    for ( int i = 0 ; i < jsonArray.length(); i++) {
       stringArray[i] = jsonArray.getString(i);
    }
    return stringArray;
  }

  /**
   * A helper method to convert multi dimensional JSONArrays to ObjectArrays
   * of Class clazz.
   * @param jsonArray the JSONArray to convert
   * @return clazzArray and array of Objects from type clazz
   * @throws JSONException exception during JSON parsing
   */
  @SuppressWarnings("unchecked")
  public static <K> K[] getObjectArray(JSONArray jsonArray, Class<K> clazz) {
    K[] result = (K[])Array.newInstance(clazz,jsonArray.length());
    for ( int i = 0 ; i < jsonArray.length(); i++) {
      try {
        result[i] = clazz.getConstructor(JSONObject.class).newInstance(jsonArray.getJSONObject(i));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return result;
  }
}
