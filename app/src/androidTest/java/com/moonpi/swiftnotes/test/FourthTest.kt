package com.moonpi.swiftnotes.test

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.RootMatchers
import android.support.test.espresso.matcher.RootMatchers.isDialog
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.runner.AndroidJUnit4
import android.support.test.uiautomator.UiDevice
import com.moonpi.swiftnotes.MainActivity
import com.moonpi.swiftnotes.R
import com.moonpi.swiftnotes.pages.ActivityMain
import com.moonpi.swiftnotes.rule.SwiftnotesRule
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.tinkoff.allure.android.deviceScreenshot
import ru.tinkoff.allure.annotations.DisplayName
import ru.tinkoff.allure.step


@RunWith(AndroidJUnit4::class)
@DisplayName("Создание заметки")
class FourthTest : AbstractSwiftnotesTest() {

    @get:Rule
    val rule = SwiftnotesRule(MainActivity::class.java, false)
    private val title = "title for test"
    private val body = "test message"

    @Test
    @DisplayName("Проверка удаления заметки")
    fun newNoteHints() {
        rule.launchActivity()
        step("Проверяем отображение страницы") {
            ActivityMain()
                    .clickAddNote()
                    .typeTitle(title)
                    .typeBody(body)
            deviceScreenshot("page_display")
            pressBack()
            pressBack()
            onView(withText("YES"))
                    .inRoot(isDialog())
                    .check(matches(isDisplayed()))
                    .perform(click())
            onView(withText(title)).perform(longClick())
            onView(withId(R.id.action_delete)).perform(click())
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
}
