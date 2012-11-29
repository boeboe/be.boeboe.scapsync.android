/**
 * 
 */
package be.boeboe.scapsync.rest;

import android.annotation.SuppressLint;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author boeboe
 *
 */
@SuppressLint("SimpleDateFormat")
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
  
  public static JSONObject execRestGet(URI uri) {
    final DefaultHttpClient httpClient;
    httpClient = new DefaultHttpClient();

//    HttpHost proxy = new HttpHost("127.0.0.1", 3128);
//    httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);

    HttpGet request = new HttpGet(uri);
    request.addHeader("Accept", "application/json");

    try {
      HttpResponse response = httpClient.execute(request);
      if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
        throw new RuntimeException("Unexpected server response "
           + response.getStatusLine() + " for " + request.getRequestLine());
      }

      InputStream inputStream = response.getEntity().getContent();
      InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
      StringBuilder stringBuilder = new StringBuilder();

      String line;
      while ((line = bufferedReader.readLine()) != null) {
        stringBuilder.append(line);
      }

      String output = stringBuilder.toString();
      JSONObject json = new JSONObject(output);
      httpClient.getConnectionManager().shutdown();
      return json;
    } catch (IOException e) {
      httpClient.getConnectionManager().shutdown();
      System.out.println("Problem reading remote response for " + request.getRequestLine());
      return new JSONObject();
    } catch (JSONException e) {
      httpClient.getConnectionManager().shutdown();
      System.out.println("Problem JSONParsing remote response for " + request.getRequestLine());
      return new JSONObject();
    }
  }
}
