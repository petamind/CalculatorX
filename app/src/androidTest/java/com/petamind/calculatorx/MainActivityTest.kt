package com.petamind.calculatorx


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        val button = onView(
            allOf(
                withText("7"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.table),
                        2
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        button.perform(click())

        val textView = onView(
            allOf(
                withId(R.id.result_textview), withText("7"),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("7")))

        val button2 = onView(
            allOf(
                withText("9"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.table),
                        2
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        button2.perform(click())

        val textView2 = onView(
            allOf(
                withId(R.id.result_textview), withText("79"),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("79")))

        val button3 = onView(
            allOf(
                withText("5"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.table),
                        3
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        button3.perform(click())

        val textView3 = onView(
            allOf(
                withId(R.id.result_textview), withText("79"),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("795")))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
