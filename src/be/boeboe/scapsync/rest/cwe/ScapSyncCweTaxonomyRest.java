/**
 * 
 */
package be.boeboe.scapsync.rest.cwe;

import org.json.JSONException;
import org.json.JSONObject;

import be.boeboe.scapsync.rest.interfaces.IScapSyncCweTaxonomy;

/**
 * Rest Implementation of a ScapSync CWE Taxonomy
 * 
 * Example json:
 * { "taxonomy" : "PLOVER", "name" : "Path Traversal" }
 * { "taxonomy" : "OWASP Top Ten 2007",
 *   "name" : "Insecure Direct Object Reference",
 *   "fit" : "CWE_More_Specific", "id" : "A4" }
 * @author boeboe
 */
public class ScapSyncCweTaxonomyRest implements IScapSyncCweTaxonomy {
  private static final String TAXONOMY = "taxonomy";
  private static final String NAME = "name";
  private static final String FIT = "fit";
  private static final String ID = "id";

  private String fTaxonomy;
  private String fName;
  private String fFit;
  private String fId;

  public ScapSyncCweTaxonomyRest(JSONObject scapSyncCweTaxonomyRest) {
    super();
    try {
      fTaxonomy = scapSyncCweTaxonomyRest.getString(TAXONOMY);
      fName = scapSyncCweTaxonomyRest.getString(NAME);
      fFit = scapSyncCweTaxonomyRest.has(FIT) ? scapSyncCweTaxonomyRest.getString(FIT): null;
      fId = scapSyncCweTaxonomyRest.has(ID) ? scapSyncCweTaxonomyRest.getString(ID): null;
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweTaxonomy#getTaxonomy()
   */
  @Override
  public String getTaxonomy() {
    return fTaxonomy;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweTaxonomy#getName()
   */
  @Override
  public String getName() {
    return fName;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweTaxonomy#getFit()
   */
  @Override
  public String getFit() {
    return fFit;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweTaxonomy#getId()
   */
  @Override
  public String getId() {
    return fId;
  }
}
