package co.cdmunoz.spotiphoto.topTenSongs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import co.cdmunoz.spotiphoto.R;
import co.cdmunoz.spotiphoto.model.SpotifyTrack;
import co.cdmunoz.spotiphoto.util.Constants;

/**
 * Activity for artist's songs
 * <p>
 * User: cdmunoz
 * Date: 3/7/17
 * Time: 5:42 PM
 */
public class TopTenSongsActivity extends AppCompatActivity implements TopTenSongsView {

  @BindView(R.id.top_ten_songs_progressBar)
  ProgressBar progressBar;
  @BindView(R.id.top_ten_songs_list_swipe)
  SwipeRefreshLayout refreshLayout;
  @BindView(R.id.top_ten_songs_list_container)
  RecyclerView topSongsList;
  @BindString(R.string.top_ten_songs_title) String topTenSongsTitle;

  private TopTenSongsPresenter presenter;
  private boolean swipeRefresh;

  public static Intent getIntent(Context context, String artistId, String artistName) {
    Intent intent = new Intent(context, TopTenSongsActivity.class);
    intent.putExtra(Constants.EXTRA_PARAM_ARTIST_ID, artistId);
    intent.putExtra(Constants.EXTRA_PARAM_ARTIST_NAME, artistName);
    return intent;
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.top_ten_songs_activity);
    ButterKnife.bind(this);
    setTitle(getIntent().getStringExtra(Constants.EXTRA_PARAM_ARTIST_NAME) + "'s " +topTenSongsTitle);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeButtonEnabled(true);

    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    topSongsList.setLayoutManager(linearLayoutManager);

    presenter = new TopTenSongsPresenter(this);
    presenter.getTopTenSongs(getIntent().getStringExtra(Constants.EXTRA_PARAM_ARTIST_ID));

    refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        swipeRefresh = true;
        refreshLayout.setRefreshing(false);
        presenter.getTopTenSongs(getIntent().getStringExtra(Constants.EXTRA_PARAM_ARTIST_ID));
      }
    });
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
  public void showSongs(List<SpotifyTrack> tracks) {
    TopTenSongsAdapter adapter = new TopTenSongsAdapter(this, tracks);
    topSongsList.setAdapter(adapter);
    adapter.notifyDataSetChanged();
    if (swipeRefresh) refreshLayout.setRefreshing(false);
  }

  @Override
  protected void onDestroy() {
    presenter.destroy();
    super.onDestroy();
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
