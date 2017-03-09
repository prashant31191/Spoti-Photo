package co.cdmunoz.spotiphoto.common;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.InputStream;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

public class CommonImagePresenter {

  private static final String LOG_TAG = "CommonImagePresenter";

  private CommonImageView view;

  public CommonImagePresenter(CommonImageView view) {
    this.view = view;
  }

  public void getImageFromUrl(final String url) {
    if (null != view) view.showProgress();
    Observable.fromCallable(new Func0<Bitmap>() {
      @Override
      public Bitmap call() {
        Bitmap imageToShow = null;
        try {
          InputStream in = new java.net.URL(url).openStream();
          imageToShow = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
          Log.e(LOG_TAG, "Error while decoding the image: " + e.getMessage());
          e.printStackTrace();
        }
        return imageToShow;
      }
    }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<Bitmap>() {
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
              public void onNext(Bitmap bitmap) {
                view.hideProgress();
                view.showImage(bitmap);
              }
            });
  }
}
