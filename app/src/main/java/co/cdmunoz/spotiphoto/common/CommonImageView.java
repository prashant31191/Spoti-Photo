package co.cdmunoz.spotiphoto.common;


import android.graphics.Bitmap;

interface CommonImageView {

  void showProgress();

  void hideProgress();

  void showError(String message);

  void showImage(Bitmap bitmap);
}
