/**
 * 
 */
package be.boeboe.scapsync.rest;

import be.boeboe.scapsync.rest.interfaces.IScapSyncSearchResult;

/**
 * @author boeboe
 *
 */
public interface ScapSyncSearchListener {
  public void resultReceived(IScapSyncSearchResult[] searchResults);
}
