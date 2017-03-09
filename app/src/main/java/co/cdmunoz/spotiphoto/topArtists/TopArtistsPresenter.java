package co.cdmunoz.spotiphoto.topArtists;

import co.cdmunoz.spotiphoto.model.SpotifyArtistsResponse;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Class implementing the presenter layer
 * <p>
 * User: cdmunoz
 * Date: 3/6/17
 * Time: 8:37 PM
 */

public class TopArtistsPresenter {

  private TopArtistsView view;
  private TopArtistsInteractor topArtistsInteractor;

  public TopArtistsPresenter(TopArtistsView view) {
    this.view = view;
    this.topArtistsInteractor = new TopArtistsInteractor();
  }

  public void getTopArtists(String numberTopArtists, String countryTopArtists) {
    if (null != view) view.showProgress();
    topArtistsInteractor.getTopArtists(numberTopArtists, countryTopArtists)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<SpotifyArtistsResponse>() {
              @Override
              public void onCompleted() {
                view.hideProgress();
              }

              @Override
              public void onError(Throwable e) {
                view.hideProgress();
                view.showError(e.getMessage());
              }

              @Override
              public void onNext(SpotifyArtistsResponse spotifyArtistsResponse) {
                view.hideProgress();
                view.showList(spotifyArtistsResponse.getArtists().getItems());
              }
            });
  }

  public void onDestroy() {
    view = null;
  }
}

