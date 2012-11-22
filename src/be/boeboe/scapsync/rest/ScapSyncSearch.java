/**
 * 
 */
package be.boeboe.scapsync.rest;

import java.net.URI;
import java.util.LinkedList;

import org.json.JSONObject;

import be.boeboe.scapsync.rest.interfaces.IScapSyncSearch;
import be.boeboe.scapsync.rest.interfaces.IScapSyncSearchPage;
import be.boeboe.scapsync.rest.search.ScapSyncSearchRest;

/**
 * @author boeboe
 *
 */
public class ScapSyncSearch extends Thread {
  
  private static IScapSyncSearch fFirstResult;
  private LinkedList<ScapSyncSearchListener> searchListeners = new LinkedList<ScapSyncSearchListener>();
  
  
  public ScapSyncSearch(URI baseUri, String searchItem) {
    URI queryUri = URI.create(baseUri + "&q=" + searchItem + "&n=100");
    JSONObject jsonFirstResult = ScapSyncUtils.execRestGet( queryUri);
    fFirstResult = new ScapSyncSearchRest(jsonFirstResult);
  }
  
  private void search(URI pageUri) {
    JSONObject res = ScapSyncUtils.execRestGet(pageUri);

    for(ScapSyncSearchListener listener: searchListeners){
      ScapSyncSearchRest result = new ScapSyncSearchRest(res);
      listener.resultReceived(result.getResults());
    }
  }

  public void run(){   
    for (IScapSyncSearchPage page : fFirstResult.getPages() ) {
      URI pageUri = URI.create(ScapSyncHandle.SCAP_SYNC_BASE_URL + page.getUrl());
      search(pageUri);
    }
  }      

  //LISTENER STUFF        
  public void addSearchListener(ScapSyncSearchListener listener){
    searchListeners.add(listener);
  }
 
  public boolean removeSearchListener(ScapSyncSearchListener listener){
    return searchListeners.remove(listener);
  }
}
