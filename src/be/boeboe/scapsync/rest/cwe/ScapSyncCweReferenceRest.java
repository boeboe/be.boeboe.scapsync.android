/**
 * 
 */
package be.boeboe.scapsync.rest.cwe;

import java.net.URI;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import be.boeboe.scapsync.rest.ScapSyncUtils;
import be.boeboe.scapsync.rest.interfaces.IScapSyncCweReference;

/**
 * Rest Implementation of a ScapSync CWE Reference
 * 
 * Example json:
 * { "edition" : "2nd Edition", 
 *   "section" : "Chapter 5, ...",
 *   "title" : "Writing Secure Code",
 *   "publication_date" : "2002-01-01T00:00:00Z",
 *   "author" : [ "M. Howard", "D. LeBlanc" ] }
 * { "url" : "http://msdn.microsoft.com/en-us/library/ms647466.aspx",
 *   "title" : "Using the Strsafe.h Functions",
 *   "author" : [ "Microsoft" ] }
 * { "date" : "2010-03-09T00:00:00Z",
 *   "url" : "http://blogs.sans.org/appsecstreetfighter/...",
 *   "title" : "Top 25 Series - Rank 7 - Path Traversal",
 *   "author" : [ "Johannes Ullrich" ] }
 * @author boeboe
 */
public class ScapSyncCweReferenceRest implements IScapSyncCweReference {
  private static final String URL = "url";
  private static final String TITLE = "title";
  private static final String EDITION = "edition";
  private static final String SECTION = "section";
  private static final String DATE = "date";
  private static final String PUBLICATION_DATE = "publication_date";
  private static final String AUTOR = "author";

  private URI fUrl;
  private String fTitle;
  private String fEdition;
  private String fSection;
  private Date fDate;
  private Date fPublicationDate;
  private String[] fAutors;

  public ScapSyncCweReferenceRest(JSONObject scapSyncCweReferenceRest) {
    super();
    try { 
      fUrl = scapSyncCweReferenceRest.has(URL) ? URI.create(scapSyncCweReferenceRest.getString(URL)): null;
      fTitle = scapSyncCweReferenceRest.has(TITLE) ? scapSyncCweReferenceRest.getString(TITLE): null;
      fEdition = scapSyncCweReferenceRest.has(EDITION) ? scapSyncCweReferenceRest.getString(EDITION): null;
      fSection = scapSyncCweReferenceRest.has(SECTION) ? scapSyncCweReferenceRest.getString(SECTION): null;
      fDate = scapSyncCweReferenceRest.has(DATE) ? ScapSyncUtils.getDate(scapSyncCweReferenceRest.getString(DATE)): null;
      fPublicationDate = scapSyncCweReferenceRest.has(PUBLICATION_DATE) ? ScapSyncUtils.getDate(scapSyncCweReferenceRest.getString(PUBLICATION_DATE)): null;
      
      fAutors = scapSyncCweReferenceRest.has(AUTOR) ? ScapSyncUtils.getStringArray(scapSyncCweReferenceRest.getJSONArray(AUTOR)): null;
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweReference#getUrl()
   */
  @Override
  public URI getUrl() {
    return fUrl;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweReference#getTitle()
   */
  @Override
  public String getTitle() {
    return fTitle;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweReference#getEdition()
   */
  @Override
  public String getEdition() {
    return fEdition;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweReference#getSection()
   */
  @Override
  public String getSection() {
    return fSection;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweReference#getDate()
   */
  @Override
  public Date getDate() {
    return fDate;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweReference#getPublicationDate()
   */
  @Override
  public Date getPublicationDate() {
    return fPublicationDate;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweReference#getAuthors()
   */
  @Override
  public String[] getAuthors() {
    return fAutors;
  }
}
