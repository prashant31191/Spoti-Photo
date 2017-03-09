package co.cdmunoz.spotiphoto.topTenSongs;

import co.cdmunoz.spotiphoto.model.SpotifySongsResponse;
import co.cdmunoz.spotiphoto.rest.ApiClient;
import co.cdmunoz.spotiphoto.rest.ApiInterface;
import rx.Observable;

/**
 * Implementation of top ten songs interactor
 * <p>
 * User: cdmunoz
 * Date: 3/7/17
 * Time: 5:57 PM
 */

public class TopTenSongsInteractor {

  public Observable<SpotifySongsResponse> getTopTenSongsByArtist(String artistId) {
    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
    return apiInterface.getTopTenSongsByArtist(artistId, "GB");
  }
}
