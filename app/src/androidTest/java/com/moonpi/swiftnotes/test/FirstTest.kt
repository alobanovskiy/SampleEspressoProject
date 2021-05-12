package com.moonpi.swiftnotes.test

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.runner.AndroidJUnit4
import com.moonpi.swiftnotes.MainActivity
import com.moonpi.swiftnotes.pages.ActivityEdit
import com.moonpi.swiftnotes.pages.ActivityMain
import com.moonpi.swiftnotes.rule.SwiftnotesRule
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.tinkoff.allure.annotations.DisplayName


@RunWith(AndroidJUnit4::class)
class FirstTest : AbstractSwiftnotesTest() {

    @get:Rule
    val rule = SwiftnotesRule(MainActivity::class.java, false)

    @Test
    @DisplayName("Проверка экрана создания заметки")
    fun newNoteHints() {
        rule.launchActivity()
        ActivityMain()
                .clickAddNote()
        pressBack()
        pressBack()
        ActivityEdit()
                .isDialogPresent("Save changes?")
                .dialogAction("No")
        onView(allOf(withText("Press \'+\' to add new note")))
                .check(matches(isDisplayed()))
    }
}
