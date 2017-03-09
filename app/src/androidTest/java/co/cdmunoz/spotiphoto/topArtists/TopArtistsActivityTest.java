package co.cdmunoz.spotiphoto.topArtists;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.cdmunoz.spotiphoto.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TopArtistsActivityTest {

  @Rule
  public ActivityTestRule<TopArtistsActivity> mActivityTestRule = new ActivityTestRule<>(TopArtistsActivity.class);

  @Test
  public void topArtistsActivityTest() throws InterruptedException {
    ViewInteraction recyclerView = onView(withId(R.id.top_artists_list_container));
    Thread.sleep(2000);
    recyclerView.check(matches(isDisplayed()));
    onView(withId(R.id.top_artists_list_container)).check(matches(hasElements()));
  }

  public static Matcher<View> hasElements() {
    return new TypeSafeMatcher<View>() {
      @Override
      public boolean matchesSafely(final View view) {
        RecyclerView recyclerView = (RecyclerView) view;
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        return adapter.getItemCount() > 0;
      }

      @Override
      public void describeTo(final Description description) {
        description.appendText("RecyclerView should have items");
      }
    };
  }

}
