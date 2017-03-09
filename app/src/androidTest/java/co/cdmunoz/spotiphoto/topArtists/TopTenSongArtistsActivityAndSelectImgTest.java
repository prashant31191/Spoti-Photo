package co.cdmunoz.spotiphoto.topArtists;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.cdmunoz.spotiphoto.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TopTenSongArtistsActivityAndSelectImgTest {

  @Rule
  public ActivityTestRule<TopArtistsActivity> mActivityTestRule = new ActivityTestRule<>(TopArtistsActivity.class);

  @Test
  public void topTenSongArtistsActivityAndSelectImgTest() throws InterruptedException {
    ViewInteraction recyclerView = onView(
            allOf(withId(R.id.top_artists_list_container),
                    withParent(allOf(withId(R.id.top_artists_list_swipe),
                            withParent(withId(R.id.main_container)))),
                    isDisplayed()));
    Thread.sleep(2000);
    recyclerView.perform(actionOnItemAtPosition(4, click()));

    ViewInteraction recyclerView2 = onView(
            allOf(withId(R.id.top_ten_songs_list_container),
                    withParent(withId(R.id.top_ten_songs_list_swipe)),
                    isDisplayed()));
    Thread.sleep(2000);
    recyclerView2.perform(actionOnItemAtPosition(6, click()));

    onView(withId(R.id.commomImageView)).check(ViewAssertions.matches(isDisplayed()));

  }

}
