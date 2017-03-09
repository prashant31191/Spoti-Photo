package co.cdmunoz.spotiphoto.topTenSongs;

import co.cdmunoz.spotiphoto.model.SpotifySongsResponse;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Class implementing the presenter layer for artist's top ten songs feature
 * <p>
 * User: cdmunoz
 * Date: 3/7/17
 * Time: 5:55 PM
 */
public class TopTenSongsPresenter {

  private TopTenSongsView view;
  private TopTenSongsInteractor topTenSongsInteractor;

  public TopTenSongsPresenter(TopTenSongsView view) {
    this.view = view;
    topTenSongsInteractor = new TopTenSongsInteractor();
  }

  public void getTopTenSongs(String artistId) {
    if (null != view) view.showProgress();
    topTenSongsInteractor.getTopTenSongsByArtist(artistId).subscribeOn(Schedulers.newThread()).observeOn(
            AndroidSchedulers.mainThread()).subscribe(new Subscriber<SpotifySongsResponse>() {
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
      public void onNext(SpotifySongsResponse spotifySongsResponse) {
        view.hideProgress();
        view.showSongs(spotifySongsResponse.getTracks());
      }
    });
  }

  public void destroy() {
    view = null;
  }
}
