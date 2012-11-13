/**
 * 
 */
package be.boeboe.scapsync.rest.cwe;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import be.boeboe.scapsync.rest.ScapSyncUtils;
import be.boeboe.scapsync.rest.interfaces.IScapSyncCweAlternateTerm;
import be.boeboe.scapsync.rest.interfaces.IScapSyncCweAttackPattern;
import be.boeboe.scapsync.rest.interfaces.IScapSyncCweConsequence;
import be.boeboe.scapsync.rest.interfaces.IScapSyncCweDemonstrativeExample;
import be.boeboe.scapsync.rest.interfaces.IScapSyncCweDetails;
import be.boeboe.scapsync.rest.interfaces.IScapSyncCweDetectionMethod;
import be.boeboe.scapsync.rest.interfaces.IScapSyncCweHistory;
import be.boeboe.scapsync.rest.interfaces.IScapSyncCweMitigation;
import be.boeboe.scapsync.rest.interfaces.IScapSyncCweObservedExample;
import be.boeboe.scapsync.rest.interfaces.IScapSyncCwePlatforms;
import be.boeboe.scapsync.rest.interfaces.IScapSyncCweReference;
import be.boeboe.scapsync.rest.interfaces.IScapSyncCweRelationship;
import be.boeboe.scapsync.rest.interfaces.IScapSyncCweTaxonomy;

/**
 * Rest Implementation of a ScapSync CWE
 * 
 * Example json:
 * { "observed_examples" : [ { ... } ], "detection_methods" : [ { ... } ],
 *   "attack_patterns" : [ { ... } ], "mitigations" : [ { ... } ],
 *   "upstream_modified" : "2012-05-11T00:00:00Z", "cwe_id" : "CWE-119",
 *   "references" : [ {...} ], "cwe_type" : "Weakness",
 *   "affected_resources" : [ "Memory" ], "description" : "Certain ...",
 *   "version_count" : 1, "summary" : "The software performs ...",
 *   "version_url" : "/versions/cwe/CWE-119",
 *   "time_of_introduction" : [ "Architecture and Design", ... ],
 *   "likelihood_of_exploit" : "High", "history" : [ { ... } ],
 *   "taxonomy_mappings" : [ { ... } ], "consequences" : [ { ... } ],
 *   "relationships" : [ { ...} ], "alternate_terms" : [ { ... } ],
 *   "platforms" : { ... }, "demonstrative_examples" : [ { ... } ] }
 * @author boeboe
 */
public class ScapSyncCweDetailsRest implements IScapSyncCweDetails {
  private static final String OBSERVED_EXAMPLES = "observed_examples";
  private static final String DETECTION_METHODS = "detection_methods";
  private static final String ATTACK_PATTERNS = "attack_patterns";
  private static final String MITIGATIONS = "mitigations";
  private static final String REFERENCES = "references";
  private static final String TAXONOMY_MAPPINGS = "taxonomy_mappings";
  private static final String CONSEQUENCES = "consequences";
  private static final String HISTORY = "history";
  private static final String RELATIONSHIPS = "relationships";
  private static final String ALTERNATE_TERMS = "alternate_terms";
  private static final String PLATFORMS = "platforms";
  private static final String DEMONSTRATIVE_EXAMPLES = "demonstrative_examples";
  
  private static final String UPSTREAM_MODIFIED = "upstream_modified";
  private static final String CWE_ID = "cwe_id";
  private static final String CWE_TYPE = "cwe_type";
  private static final String AFFECTED_RESOURCES = "affected_resources";
  private static final String DESCRIPTION = "description";
  private static final String VERSION_COUNT = "version_count";
  private static final String SUMMARY = "summary";
  private static final String VERSION_URL = "version_url";
  private static final String TIME_OF_INTRODUCTION = "time_of_introduction";
  private static final String LIKELIHOOD_OF_EXPLOIT = "likelihood_of_exploit";
  
  private IScapSyncCweObservedExample[] fObservedExamples;
  private IScapSyncCweDetectionMethod[] fDetectionMethods;
  private IScapSyncCweAttackPattern[] fAttackPatterns;
  private IScapSyncCweMitigation[] fMitigations;
  private IScapSyncCweReference[] fReferences;
  private IScapSyncCweTaxonomy[] fTaxonomyMappings;
  private IScapSyncCweConsequence[] fConsequences;
  private IScapSyncCweHistory[] fHistrories;
  private IScapSyncCweRelationship[] fRelationships;
  private IScapSyncCweDemonstrativeExample[] fDemonstrativeExamples;
  private IScapSyncCweAlternateTerm[] fAlternateTerms;
  
  private Date fUpstreamModified;
  private String fCweId;
  private String fCweType;
  private String[] fAffectedResources;
  private String fDescription;
  private int fVersionCount;
  private String fSummary;
  private String fVersionUrl;
  private String[] fTimesOfIntroduction;
  private String fLikelihoodOfExploit;
  private IScapSyncCwePlatforms fPlatforms;
  
  public ScapSyncCweDetailsRest(JSONObject scapSyncCweDetailsRest) {
    super();
    try {
      fUpstreamModified = ScapSyncUtils.getDate(scapSyncCweDetailsRest.getString(UPSTREAM_MODIFIED));
      fCweId = scapSyncCweDetailsRest.getString(CWE_ID);
      fCweType = scapSyncCweDetailsRest.getString(CWE_TYPE);
      fDescription = scapSyncCweDetailsRest.has(DESCRIPTION) ? scapSyncCweDetailsRest.getString(DESCRIPTION) : null; 
      fVersionCount = scapSyncCweDetailsRest.getInt(VERSION_COUNT);
      fSummary = scapSyncCweDetailsRest.getString(SUMMARY);
      fVersionUrl = scapSyncCweDetailsRest.getString(VERSION_URL);
      fLikelihoodOfExploit = scapSyncCweDetailsRest.has(LIKELIHOOD_OF_EXPLOIT) ? scapSyncCweDetailsRest.getString(LIKELIHOOD_OF_EXPLOIT) : null;

      fObservedExamples = scapSyncCweDetailsRest.has(OBSERVED_EXAMPLES) ? ScapSyncUtils.getObjectArray(scapSyncCweDetailsRest.getJSONArray(OBSERVED_EXAMPLES), ScapSyncCweObservedExampleRest.class): null;
      fDetectionMethods = scapSyncCweDetailsRest.has(DETECTION_METHODS) ? ScapSyncUtils.getObjectArray(scapSyncCweDetailsRest.getJSONArray(DETECTION_METHODS), ScapSyncCweDetectionMethodRest.class): null;
      fAttackPatterns = scapSyncCweDetailsRest.has(ATTACK_PATTERNS) ? ScapSyncUtils.getObjectArray(scapSyncCweDetailsRest.getJSONArray(ATTACK_PATTERNS), ScapSyncCweAttackPatternRest.class): null;
      fMitigations = scapSyncCweDetailsRest.has(MITIGATIONS) ? ScapSyncUtils.getObjectArray(scapSyncCweDetailsRest.getJSONArray(MITIGATIONS), ScapSyncCweMitigationRest.class): null;
      fReferences = scapSyncCweDetailsRest.has(REFERENCES) ? ScapSyncUtils.getObjectArray(scapSyncCweDetailsRest.getJSONArray(REFERENCES), ScapSyncCweReferenceRest.class): null;
      fTaxonomyMappings = scapSyncCweDetailsRest.has(TAXONOMY_MAPPINGS) ? ScapSyncUtils.getObjectArray(scapSyncCweDetailsRest.getJSONArray(TAXONOMY_MAPPINGS), ScapSyncCweTaxonomyRest.class): null;
      fConsequences = scapSyncCweDetailsRest.has(CONSEQUENCES) ? ScapSyncUtils.getObjectArray(scapSyncCweDetailsRest.getJSONArray(CONSEQUENCES), ScapSyncCweConsequenceRest.class): null;
      fHistrories = scapSyncCweDetailsRest.has(HISTORY) ? ScapSyncUtils.getObjectArray(scapSyncCweDetailsRest.getJSONArray(HISTORY), ScapSyncCweHistoryRest.class): null;
      fRelationships = scapSyncCweDetailsRest.has(RELATIONSHIPS) ? ScapSyncUtils.getObjectArray(scapSyncCweDetailsRest.getJSONArray(RELATIONSHIPS), ScapSyncCweRelationshipRest.class): null;
      fAlternateTerms = scapSyncCweDetailsRest.has(ALTERNATE_TERMS) ? ScapSyncUtils.getObjectArray(scapSyncCweDetailsRest.getJSONArray(ALTERNATE_TERMS), ScapSyncCweAlternateTermRest.class): null;
      fDemonstrativeExamples = scapSyncCweDetailsRest.has(DEMONSTRATIVE_EXAMPLES) ? ScapSyncUtils.getObjectArray(scapSyncCweDetailsRest.getJSONArray(DEMONSTRATIVE_EXAMPLES), ScapSyncCweDemonstrativeExampleRest.class): null;

      fAffectedResources = scapSyncCweDetailsRest.has(AFFECTED_RESOURCES) ? ScapSyncUtils.getStringArray(scapSyncCweDetailsRest.getJSONArray(AFFECTED_RESOURCES)): null;
      fTimesOfIntroduction = scapSyncCweDetailsRest.has(TIME_OF_INTRODUCTION) ? ScapSyncUtils.getStringArray(scapSyncCweDetailsRest.getJSONArray(TIME_OF_INTRODUCTION)): null;

      fPlatforms = scapSyncCweDetailsRest.has(PLATFORMS) ? new ScapSyncCwePlatformsRest(scapSyncCweDetailsRest.getJSONObject(PLATFORMS)): null;
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweDetails#getObservedExamples()
   */
  @Override
  public IScapSyncCweObservedExample[] getObservedExamples() {
    return fObservedExamples;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweDetails#getDetectionMethods()
   */
  @Override
  public IScapSyncCweDetectionMethod[] getDetectionMethods() {
    return fDetectionMethods;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweDetails#getAttackPattern
   */
  @Override
  public IScapSyncCweAttackPattern[] getAttackPatterns() {
    return fAttackPatterns;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweDetails#getMitigations
   */
  @Override
  public IScapSyncCweMitigation[] getMitigations() {
    return fMitigations;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweDetails#getReferences
   */
  @Override
  public IScapSyncCweReference[] getReferences() {
    return fReferences;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweDetails#getTaxonomyMappings
   */
  @Override
  public IScapSyncCweTaxonomy[] getTaxonomyMappings() {
    return fTaxonomyMappings;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweDetails#getConsequences
   */
  @Override
  public IScapSyncCweConsequence[] getConsequences() {
    return fConsequences;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweDetails#getHistory
   */
  @Override
  public IScapSyncCweHistory[] getHistory() {
    return fHistrories;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweDetails#getRelationships
   */
  @Override
  public IScapSyncCweRelationship[] getRelationships() {
    return fRelationships;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweDetails#getAlternateTerms()
   */
  @Override
  public IScapSyncCweAlternateTerm[] getAlternateTerms() {
    return fAlternateTerms;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweDetails#getDemonstrativeExamples()
   */
  @Override
  public IScapSyncCweDemonstrativeExample[] getDemonstrativeExamples() {
    return fDemonstrativeExamples;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweDetails#getUpstreamModified()
   */
  @Override
  public Date getUpstreamModified() {
    return fUpstreamModified;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweDetails#getCweId()
   */
  @Override
  public String getCweId() {
    return fCweId;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweDetails#getCweType()
   */
  @Override
  public String getCweType() {
    return fCweType;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweDetails#getCweAffectedResources()
   */
  @Override
  public String[] getCweAffectedResources() {
    return fAffectedResources;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweDetails#getCweDescription()
   */
  @Override
  public String getCweDescription() {
    return fDescription;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweDetails#getCweVersionCount()
   */
  @Override
  public int getCweVersionCount() {
    return fVersionCount;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweDetails#getCweSummary()
   */
  @Override
  public String getCweSummary() {
    return fSummary;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweDetails#getCweVersionUrl()
   */
  @Override
  public String getCweVersionUrl() {
    return fVersionUrl;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweDetails#getCweTimesOfIntroduction()
   */
  @Override
  public String[] getCweTimesOfIntroduction() {
    return fTimesOfIntroduction;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweDetails#getCweLikelihoodOfExploit()
   */
  @Override
  public String getCweLikelihoodOfExploit() {
    return fLikelihoodOfExploit;
  }

  /**
   * @see be.boeboe.scapsync.rest.interfaces.IScapSyncCweDetails#getPlatforms()
   */
  @Override
  public IScapSyncCwePlatforms getPlatforms() {
    return fPlatforms;
  }
}
