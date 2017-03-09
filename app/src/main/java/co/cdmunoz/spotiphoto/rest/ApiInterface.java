package co.cdmunoz.spotiphoto.rest;

import co.cdmunoz.spotiphoto.model.SpotifyArtistsResponse;
import co.cdmunoz.spotiphoto.model.SpotifySongsResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Interface with all the endpoints
 * <p>
 * User: cdmunoz
 * Date: 3/6/17
 * Time: 10:05 PM
 */

public interface ApiInterface {

  @GET("search")
  Observable<SpotifyArtistsResponse> getTopTenArtists(
          @Query(value = "q", encoded = true) String query, @Query("type") String type,
          @Query("market") String market, @Query("limit") int limit);

  @GET("artists/{id}/top-tracks")
  Observable<SpotifySongsResponse> getTopTenSongsByArtist(
          @Path("id") String artistId, @Query("country") String country);
}
