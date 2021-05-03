package com.moonpi.swiftnotes.test

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.RootMatchers.isDialog
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.runner.AndroidJUnit4
import android.support.test.uiautomator.UiDevice
import com.moonpi.swiftnotes.MainActivity
import com.moonpi.swiftnotes.R
import com.moonpi.swiftnotes.pages.ActivityEdit
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
class SecondTest : AbstractSwiftnotesTest() {

    @get:Rule
    val rule = SwiftnotesRule(MainActivity::class.java, false)
    private val title = "title for test"
    private val body = "test message"

    @Test
    @DisplayName("Проверка сохранения заметки")
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
            ActivityEdit()
                    .saveDialogAction(true)
            onView(withId(R.id.titleView)).check(matches(withText(title)))
            onView(withId(R.id.bodyView)).check(matches(withText(body)))
        }
    }
}
