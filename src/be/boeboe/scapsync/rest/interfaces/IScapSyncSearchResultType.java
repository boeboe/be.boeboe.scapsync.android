package be.boeboe.scapsync.rest.interfaces;

public enum IScapSyncSearchResultType {
  /** result type CCE. */
  TYPE_CCE("cce"),
  TYPE_CCE_RESOURCE("cce_resource"),
  TYPE_CPE("cpe"),
  TYPE_CVE("cve"),
  TYPE_CWE("cwe");

  private String fScapSyncSearchResultType;

  IScapSyncSearchResultType(String scapSyncSearchResultType) {
    fScapSyncSearchResultType = scapSyncSearchResultType;
  }

  public String getText() {
    return this.fScapSyncSearchResultType;
  }

  public static IScapSyncSearchResultType fromString(String text) {
    if (text != null) {
      for (IScapSyncSearchResultType b : IScapSyncSearchResultType.values()) {
        if (text.equalsIgnoreCase(b.fScapSyncSearchResultType)) {
          return b;
        }
      }
    }
    return null;
  }
}
