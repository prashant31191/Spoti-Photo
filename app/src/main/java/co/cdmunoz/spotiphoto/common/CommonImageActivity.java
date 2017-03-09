package co.cdmunoz.spotiphoto.common;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.cdmunoz.spotiphoto.R;
import co.cdmunoz.spotiphoto.util.Constants;

/**
 * Activity for detailed song image
 * <p>
 * User: cdmunoz
 * Date: 3/7/17
 * Time: 6:39 PM
 */
public class CommonImageActivity extends AppCompatActivity implements CommonImageView {

  @BindView(R.id.commomImageView)
  SubsamplingScaleImageView detailedImageView;
  @BindView(R.id.commonImageProgressBar)
  ProgressBar progressBar;

  private CommonImagePresenter presenter;

  public static Intent getIntent(Context context, String url) {
    Intent intent = new Intent(context, CommonImageActivity.class);
    intent.putExtra(Constants.EXTRA_PARAM_IMG_URL, url);
    return intent;
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.common_image_detail_activity);
    ButterKnife.bind(this);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeButtonEnabled(true);

    presenter = new CommonImagePresenter(this);
    presenter.getImageFromUrl(getIntent().getStringExtra(Constants.EXTRA_PARAM_IMG_URL));

  }

  @Override
  public void showProgress() {
    progressBar.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideProgress() {
    progressBar.setVisibility(View.GONE);
  }

  @Override
  public void showError(String message) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void showImage(Bitmap bitmap) {
    detailedImageView.setImage(ImageSource.bitmap(bitmap));
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }
}
