package com.moonpi.swiftnotes.pages

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.RootMatchers
import android.support.test.espresso.matcher.ViewMatchers
import com.moonpi.swiftnotes.R
import org.hamcrest.CoreMatchers

class ActivityEdit : Page() {
    fun typeTitle(title: String): ActivityEdit {
        onView(CoreMatchers.allOf(ViewMatchers.withId(R.id.titleEdit), ViewMatchers.isDisplayed()))
                .check(ViewAssertions.matches(ViewMatchers.withHint("Title")))
                .perform(ViewActions.typeText(title))
        return this
    }

    fun typeBody(body: String): Page {
        onView(CoreMatchers.allOf(ViewMatchers.withId(R.id.bodyEdit), ViewMatchers.isDisplayed()))
                .check(ViewAssertions.matches(ViewMatchers.withHint("Note")))
                .perform(ViewActions.typeText(body))
        return this
    }

    fun saveDialogAction(shouldBeSaved: Boolean): Page {
        onView(ViewMatchers.withText("Save changes?"))
                .inRoot(RootMatchers.isDialog())
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        return if (shouldBeSaved) {
            onView(ViewMatchers.withText("YES"))
                    .inRoot(RootMatchers.isDialog())
                    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                    .perform(ViewActions.click())
            this
        } else {
            onView(ViewMatchers.withText("NO"))
                    .inRoot(RootMatchers.isDialog())
                    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                    .perform(ViewActions.click())
            ActivityMain()
        }
    }

    fun deleteNoteDialogAction(shouldBeDeleted: Boolean): Page {
        onView(ViewMatchers.withText("Selected notes will be deleted!"))
                .inRoot(RootMatchers.isDialog())
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        return if (shouldBeDeleted) {
            onView(ViewMatchers.withText("ОК"))
                    .inRoot(RootMatchers.isDialog())
                    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                    .perform(ViewActions.click())
            this
        } else {
            onView(ViewMatchers.withText("Отмена"))
                    .inRoot(RootMatchers.isDialog())
                    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                    .perform(ViewActions.click())
            ActivityMain()
        }
    }
}