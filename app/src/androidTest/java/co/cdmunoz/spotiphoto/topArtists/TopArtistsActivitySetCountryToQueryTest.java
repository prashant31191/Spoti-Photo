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

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TopArtistsActivitySetCountryToQueryTest {

  @Rule
  public ActivityTestRule<TopArtistsActivity> mActivityTestRule = new ActivityTestRule<>(TopArtistsActivity.class);

  @Test
  public void topArtistsActivitySetCountryToQueryTest() throws InterruptedException {
    openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

    ViewInteraction appCompatTextView = onView(
            allOf(withId(R.id.title), withText("Settings"), isDisplayed()));
    appCompatTextView.perform(click());

    ViewInteraction linearLayout = onView(
            allOf(childAtPosition(
                    allOf(withId(android.R.id.list),
                            withParent(withClassName(is("android.widget.LinearLayout")))),
                    1),
                    isDisplayed()));
    linearLayout.perform(click());

    ViewInteraction checkedTextView = onView(
            allOf(withId(android.R.id.text1), withText("ES"),
                    childAtPosition(
                            allOf(withClassName(is("com.android.internal.app.AlertController$RecycleListView")),
                                    withParent(withClassName(is("android.widget.FrameLayout")))),
                            4),
                    isDisplayed()));
    checkedTextView.perform(click());

    ViewInteraction appCompatImageButton = onView(
            allOf(withContentDescription("Navigate up"),
                    withParent(withId(R.id.toolbar)),
                    isDisplayed()));
    appCompatImageButton.perform(click());

    ViewInteraction recyclerView = onView(withId(R.id.top_artists_list_container));
    Thread.sleep(2000);
    recyclerView.check(matches(isDisplayed()));

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
