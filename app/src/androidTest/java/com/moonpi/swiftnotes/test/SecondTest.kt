package com.moonpi.swiftnotes.test

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.RootMatchers.isDialog
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.runner.AndroidJUnit4
import android.support.test.uiautomator.UiDevice
import com.moonpi.swiftnotes.MainActivity
import com.moonpi.swiftnotes.R
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
class SecondTest : AbstractSwiftnotesTest() {

    @get:Rule
    val rule = SwiftnotesRule(MainActivity::class.java, false)

    @Test
    @DisplayName("Проверка открытия страницы создания")
    fun newNoteHints() {
        rule.launchActivity()
        step("Проверяем отображение страницы") {
            onView(withId(R.id.newNote)).perform(click())
            onView(allOf(withId(R.id.titleEdit), isDisplayed())).check(matches(withHint("Title")))
                    .perform(typeText("test title"))
            onView(allOf(withId(R.id.bodyEdit), isDisplayed())).check(matches(withHint("Note")))
                    .perform(typeText("test"))
            deviceScreenshot("page_display")
            Espresso.pressBack()
            Espresso.pressBack()
            onView(withText("Save changes?"))
                    .inRoot(isDialog())
                    .check(matches(isDisplayed()))
            onView(withText("YES"))
                    .inRoot(isDialog())
                    .check(matches(isDisplayed()))
                    .perform(click())
            onView(withId(R.layout.list_view_note))
            onView(withId(R.id.titleView)).check(matches(withText("test title")))
            onView(withId(R.id.bodyView)).check(matches(withText("test")))
        }
    }
}
