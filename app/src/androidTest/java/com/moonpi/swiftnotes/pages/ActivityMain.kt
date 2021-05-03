package com.moonpi.swiftnotes.pages

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.RootMatchers
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import com.moonpi.swiftnotes.R
import org.hamcrest.CoreMatchers

class ActivityMain : Page() {
    override fun verify(): Page {
        onView(withId(R.layout.activity_main))
        return this
    }

    public fun clickAddNote(): ActivityEdit {
        onView(ViewMatchers.withId(R.id.newNote))
                .perform(ViewActions.click())
        onView(CoreMatchers.allOf(withId(R.id.titleEdit), isDisplayed()))
                .check(matches(withHint("Title")))
        onView(CoreMatchers.allOf(withId(R.id.bodyEdit), isDisplayed()))
                .check(matches(withHint("Note")))
        return ActivityEdit()
    }

}