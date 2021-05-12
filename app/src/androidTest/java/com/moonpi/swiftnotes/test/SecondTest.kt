package com.moonpi.swiftnotes.test

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.runner.AndroidJUnit4
import com.moonpi.swiftnotes.MainActivity
import com.moonpi.swiftnotes.R.id.bodyView
import com.moonpi.swiftnotes.R.id.titleView
import com.moonpi.swiftnotes.pages.ActivityEdit
import com.moonpi.swiftnotes.pages.ActivityMain
import com.moonpi.swiftnotes.rule.SwiftnotesRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.tinkoff.allure.annotations.DisplayName


@RunWith(AndroidJUnit4::class)
@DisplayName("Создание заметки")
class SecondTest : AbstractSwiftnotesTest() {

    @get:Rule
    val rule = SwiftnotesRule(MainActivity::class.java, false)
    private val title = "title for test"
    private val body = "test message"

    @Test
    @DisplayName("Проверка сохранения заметки")
    fun saveNewNote() {
        rule.launchActivity()
        ActivityMain()
                .clickAddNote()
                .typeTitle(title)
                .typeBody(body)
        pressBack()
        pressBack()
        ActivityEdit()
                .isDialogPresent("Save changes?")
                .dialogAction("YES")
        onView(withId(titleView)).check(matches(withText(title)))
        onView(withId(bodyView)).check(matches(withText(body)))
    }
}
