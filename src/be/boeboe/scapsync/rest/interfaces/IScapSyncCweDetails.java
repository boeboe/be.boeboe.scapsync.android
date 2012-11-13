/**
 * 
 */
package be.boeboe.scapsync.rest.interfaces;

import java.util.Date;

/**
 * Interface to represent a ScapSync CWE
 * 
 * @author boeboe
 */
public interface IScapSyncCweDetails {

  /**
   * Get an array of Observed Examples from this CWE.
   * @return array of IScapSyncObservedCweExample's for this CWE
   */
  public IScapSyncCweObservedExample[] getObservedExamples();

  /**
   * Get an array of Detection Methods from this CWE.
   * @return array of IScapSyncCweDetectionMethod's for this CWE
   */
  public IScapSyncCweDetectionMethod[] getDetectionMethods();

  /**
   * Get an array of Attack Patterns from this CWE.
   * @return array of IScapSyncCweAttackPattern's for this CWE
   */
  public IScapSyncCweAttackPattern[] getAttackPatterns();

  /**
   * Get an array of Mitigations from this CWE.
   * @return array of IScapSyncCweMitigations's for this CWE
   */
  public IScapSyncCweMitigation[] getMitigations();

  /**
   * Get an array of References from this CWE.
   * @return array of IScapSyncCweReference's for this CWE
   */
  public IScapSyncCweReference[] getReferences();

  /**
   * Get an array of Taxonomy Mappings from this CWE.
   * @return array of IScapSyncCweTaxonomy's for this CWE
   */
  public IScapSyncCweTaxonomy[] getTaxonomyMappings();

  /**
   * Get an array of Consequences from this CWE.
   * @return array of IScapSyncCweConsequence's for this CWE
   */
  public IScapSyncCweConsequence[] getConsequences();

  /**
   * Get an array of Histories from this CWE.
   * @return array of IScapSyncCweHistory's for this CWE
   */
  public IScapSyncCweHistory[] getHistory();

  /**
   * Get an array of Relationships from this CWE.
   * @return array of IScapSyncCweRelationship's for this CWE
   */
  public IScapSyncCweRelationship[] getRelationships();

  /**
   * Get an array of Alternate Terms from this CWE.
   * @return array of IScapSyncCweAlternateTerm's for this CWE
   */
  public IScapSyncCweAlternateTerm[] getAlternateTerms();

  /**
   * Get the Platforms from this CWE.
   * @return platforms an IScapSyncCwePlatforms for this CWE
   */
  public IScapSyncCwePlatforms getPlatforms();

  /**
   * Get an array of Demonstrative Examples from this CWE.
   * @return array of IScapSyncCweDemonstrativeExample's for this CWE
   */
  public IScapSyncCweDemonstrativeExample[] getDemonstrativeExamples();

  /**
   * Get the Upstream Modified Date from this CWE.
   * @return date the Upstream Modified Date from this CWE
   */
  public Date getUpstreamModified();

  /**
   * Get the ID from this CWE.
   * @return notes the CWE ID from this CWE
   */
  public String getCweId();

  /**
   * Get the Type from this CWE.
   * @return type the CWE Type from this CWE
   */
  public String getCweType();

  /**
   * Get the Affected Resources from this CWE.
   * @return stringArray the CWE Affected Resources from this CWE
   */
  public String[] getCweAffectedResources();

  /**
   * Get the Description from this CWE.
   * @return description the CWE Description from this CWE
   */
  public String getCweDescription();

  /**
   * Get the VersionCount from this CWE.
   * @return int the CWE VersionCount from this CWE
   */
  public int getCweVersionCount();

  /**
   * Get the Summary from this CWE.
   * @return summary the CWE Summary from this CWE
   */
  public String getCweSummary();

  /**
   * Get the (relative) Version Url from this CWE.
   * @return url the CWE Version Url from this CWE
   */
  public String getCweVersionUrl();

  /**
   * Get the Times Of Introduction from this CWE.
   * @return stringArray an array containing the Times Of Introduction from
   * this CWE
   */
  public String[] getCweTimesOfIntroduction();

  /**
   * Get the Likelihood Of Exploit from this CWE.
   * @return likelihood the Likelihood Of Exploit from this CWE
   */
  public String getCweLikelihoodOfExploit();

}
