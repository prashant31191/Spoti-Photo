package co.cdmunoz.spotiphoto.topArtists;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import co.cdmunoz.spotiphoto.R;
import co.cdmunoz.spotiphoto.model.SpotifyArtistsItems;
import co.cdmunoz.spotiphoto.util.Utility;

public class TopArtistsActivity extends AppCompatActivity implements TopArtistsView {

  @BindView(R.id.top_artists_list_container)
  RecyclerView topArtistsList;
  @BindView(R.id.top_artists_list_swipe)
  SwipeRefreshLayout refreshLayout;
  @BindView(R.id.top_artists_progressBar)
  ProgressBar topArtistsProgressBar;
  @BindString(R.string.top_artists_title)
  String topArtistsTitle;
  @BindView(R.id.btnStartDemo)
  Button btnStartDemo;

  private TopArtistsAdapter adapter;
  private List<SpotifyArtistsItems> spotifyArtists;
  private TopArtistsPresenter presenter;
  private boolean swipeRefresh;
  private String preferredNumberTopArtists;
  private String preferredCountryTopArtists;
  private String preferredCountryLabelTopArtists;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.top_artists_activity);
    ButterKnife.bind(this);

    GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
    topArtistsList.setHasFixedSize(false);
    topArtistsList.setLayoutManager(gridLayoutManager);

    preferredNumberTopArtists = Utility.getPreferredTopArtistsNumber(this);
    preferredCountryTopArtists = Utility.getPreferredTopArtistsCountry(this);

    presenter = new TopArtistsPresenter(this);
    presenter.getTopArtists(preferredNumberTopArtists, preferredCountryTopArtists);
    preferredCountryLabelTopArtists = Utility.getCountryDescriptionFromValue(this, preferredCountryTopArtists);

    refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        swipeRefresh = true;
        refreshLayout.setRefreshing(false);
        presenter.getTopArtists(preferredNumberTopArtists, preferredCountryTopArtists);
      }
    });

    btnStartDemo.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(TopArtistsActivity.this,ActAnimationList.class));
      }
    });

  }

  @Override
  public void showProgress() {
    topArtistsList.setVisibility(View.GONE);
    topArtistsProgressBar.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideProgress() {
    topArtistsProgressBar.setVisibility(View.GONE);
    topArtistsList.setVisibility(View.VISIBLE);
  }

  @Override
  public void showError(String message) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    refreshLayout.setRefreshing(false);
  }

  @Override
  public void showList(List<SpotifyArtistsItems> topArtists) {
    spotifyArtists = topArtists;
    adapter = new TopArtistsAdapter(this, spotifyArtists);
    topArtistsList.setAdapter(adapter);
    adapter.notifyDataSetChanged();
    if (swipeRefresh) refreshLayout.setRefreshing(false);
  }

  @Override
  protected void onResume() {
    super.onResume();
    String number = Utility.getPreferredTopArtistsNumber(this);
    if (number != null && !number.equals(preferredNumberTopArtists)) {
      preferredNumberTopArtists = number;
    }
    String country = Utility.getPreferredTopArtistsCountry(this);
    if (country != null && !country.equals(preferredCountryTopArtists)) {
      preferredCountryTopArtists = country;
    }
    presenter.getTopArtists(number, country);

    String countryLabel = Utility.getCountryDescriptionFromValue(this, country);
    setTitle(topArtistsTitle + " " + countryLabel);
  }

  @Override
  protected void onDestroy() {
    presenter.onDestroy();
    super.onDestroy();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main_top_artists_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_settings) {
      startActivity(new Intent(this, TopArtistsSettingsActivity.class));
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
