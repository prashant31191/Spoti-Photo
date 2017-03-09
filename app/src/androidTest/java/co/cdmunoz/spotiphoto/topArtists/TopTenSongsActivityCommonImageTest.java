package co.cdmunoz.spotiphoto.topArtists;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.cdmunoz.spotiphoto.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.doubleClick;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TopTenSongsActivityCommonImageTest {

  @Rule
  public ActivityTestRule<TopArtistsActivity> mActivityTestRule = new ActivityTestRule<>(TopArtistsActivity.class);

  @Test
  public void topTenSongsActivityCommonImageTest() throws InterruptedException {
    ViewInteraction recyclerView = onView(
            allOf(withId(R.id.top_artists_list_container),
                    withParent(allOf(withId(R.id.top_artists_list_swipe),
                            withParent(withId(R.id.main_container)))),
                    isDisplayed()));

    Thread.sleep(2000);
    recyclerView.perform(actionOnItemAtPosition(2, click()));

    ViewInteraction recyclerView2 = onView(
            allOf(withId(R.id.top_ten_songs_list_container),
                    withParent(withId(R.id.top_ten_songs_list_swipe)),
                    isDisplayed()));
    Thread.sleep(2000);
    recyclerView2.perform(actionOnItemAtPosition(3, click()));

    ViewInteraction view = onView(
            allOf(withId(R.id.commomImageView),
                    childAtPosition(
                            allOf(withId(R.id.content),
                                    childAtPosition(
                                            withId(android.R.id.content),
                                            0)),
                            0),
                    isDisplayed()));
    Thread.sleep(2000);
    onView(withId(R.id.commomImageView)).perform(doubleClick());

  }

  private static Matcher<View> childAtPosition(
          final Matcher<View> parentMatcher, final int position) {

    return new TypeSafeMatcher<View>() {
      @Override
      public void describeTo(Description description) {
        description.appendText("Child at position " + position + " in parent ");
        parentMatcher.describeTo(description);
      }

      @Override
      public boolean matchesSafely(View view) {
        ViewParent parent = view.getParent();
        return parent instanceof ViewGroup && parentMatcher.matches(parent)
                && view.equals(((ViewGroup) parent).getChildAt(position));
      }
    };
  }
}
