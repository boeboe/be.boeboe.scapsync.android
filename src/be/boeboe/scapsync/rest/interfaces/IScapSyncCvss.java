package be.boeboe.scapsync.rest.interfaces;

/**
 * Interface to represent a ScapSync Cvss (Common Vulnerability Scoring System)
 * @author boeboe
 */
public interface IScapSyncCvss {

  /**
   * Get CVSS AccessVector from this CVE.
   *    Metric: AV = AccessVector (Related exploit range)
   *    Possible Values: L = Local access, A = Adjacent network, N = Network
   * @return String the CVSS AccessVector of this CVE
   */  
  public String getAccessVector();

  /**
   * Get CVSS AccessComplexity from this CVE.
   *    Metric: AC = AccessComplexity (Required attack complexity)
   *    Possible Values: H = High, M = Medium, L = Low
   * @return String the CVSS AccessComplexity of this CVE
   */  
  public String getAccessComplexity();

  /**
   * Get CVSS Authentication from this CVE.
   *    Metric: Au = Authentication (Level of authentication needed to exploit)
   *    Possible Values: N= None required, S= Requires single instance,
   *                     M= Requires multiple instances
   * @return String the CVSS Authentication of this CVE
   */  
  public String getAuthentication();

  /**
   * Get CVSS ConfImpact from this CVE.
   *    Metric: C = ConfImpact (Confidentiality impact)
   *    Possible Values: N = None, P = Partial, C = Complete
   * @return String the CVSS ConfImpact of this CVE
   */  
  public String getConfImpact();

  /**
   * Get CVSS IntegImpact from this CVE.
   *    Metric: I = IntegImpact (Integrity impact)
   *    Possible Values: N = None, P = Partial, C = Complete
   * @return String the CVSS IntegImpact of this CVE
   */  
  public String getIntegImpact();

  /**
   * Get CVSS AvailImpact from this CVE.
   *    Metric: A = AvailImpact (Availability impact)
   *    Possible Values: N = None, P = Partial, C = Complete
   * @return String the CVSS AvailImpact of this CVE
   */  
  public String getAvailImpact();
  
}
