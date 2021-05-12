package com.moonpi.swiftnotes.test

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.longClick
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.RootMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.runner.AndroidJUnit4
import com.moonpi.swiftnotes.MainActivity
import com.moonpi.swiftnotes.R.id.action_delete
import com.moonpi.swiftnotes.pages.ActivityEdit
import com.moonpi.swiftnotes.pages.ActivityMain
import com.moonpi.swiftnotes.rule.SwiftnotesRule
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.tinkoff.allure.android.deviceScreenshot
import ru.tinkoff.allure.annotations.DisplayName


@RunWith(AndroidJUnit4::class)
@DisplayName("Создание заметки")
class FourthTest : AbstractSwiftnotesTest() {

    @get:Rule
    val rule = SwiftnotesRule(MainActivity::class.java, false)
    private val title = "title for test"
    private val body = "test message"

    @Test
    @DisplayName("Проверка удаления заметки")
    fun checkNoteDeletion() {
        rule.launchActivity()
        ActivityMain()
                .clickAddNote()
                .typeTitle(title)
                .typeBody(body)
                .back()
                .back()
        ActivityEdit()
                .isDialogPresent("Save changes?")
                .dialogAction("YES")
        onView(withText(title)).perform(longClick())
        onView(withId(action_delete)).perform(click())
        onView(withText("Selected notes will be deleted!"))
                .inRoot(RootMatchers.isDialog())
                .check(matches(isDisplayed()))
        onView(withText("ОК"))
                .inRoot(RootMatchers.isDialog())
                .check(matches(isDisplayed()))
                .perform(click())
        onView(allOf(withText("Press \'+\' to add new note")))
                .check(matches(isDisplayed()))
    }
}
