/**
 * 
 */
package be.boeboe.scapsync.rest.interfaces;

import java.net.URI;
import java.util.Date;

/**
 * Interface to represent a ScapSync CWE Reference
 * @author boeboe
 */
public interface IScapSyncCweReference {

  /**
   * Get the URL from this CWE Reference.
   * @return url the URL from this CWE Reference
   */
  public URI getUrl();
  
  /**
   * Get the Title from this CWE Reference.
   * @return title the Title from this CWE Reference
   */
  public String getTitle();
  
  /**
   * Get the Edition from this CWE Reference.
   * @return edition the Edition from this CWE Reference
   */
  public String getEdition();
  
  /**
   * Get the Section from this CWE Reference.
   * @return section the Section from this CWE Reference
   */
  public String getSection();

  /**
   * Get the Date from this CWE Reference.
   * @return date the Date from this CWE Reference
   */
  public Date getDate();

  /**
   * Get the PublicationDate from this CWE Reference.
   * @return publicationDate the PublicationDate from this CWE Reference
   */
  public Date getPublicationDate();

  /**
   * Get the Authors from this CWE Reference.
   * @return stringArray the Authors from this CWE Reference
   */
  public String[] getAuthors();
}
