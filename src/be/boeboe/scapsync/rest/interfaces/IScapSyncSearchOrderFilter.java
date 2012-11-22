package be.boeboe.scapsync.rest.interfaces;

public enum IScapSyncSearchOrderFilter {
  SORT_NEWEST("-modified_ts"),
  SORT_OLDEST("modified_ts"),
  SORT_RELEVANT("most_relevant"),
  PREFIX("&r=");
  
  private String fScapSyncSearchFilter;

  IScapSyncSearchOrderFilter(String scapSyncSearchFilter) {
    fScapSyncSearchFilter = scapSyncSearchFilter;
  }

  public String getText() {
    return this.fScapSyncSearchFilter;
  }

  public static IScapSyncSearchOrderFilter fromString(String text) {
    if (text != null) {
      for (IScapSyncSearchOrderFilter b : IScapSyncSearchOrderFilter.values()) {
        if (text.equalsIgnoreCase(b.fScapSyncSearchFilter)) {
          return b;
        }
      }
    }
    return null;
  }
}
