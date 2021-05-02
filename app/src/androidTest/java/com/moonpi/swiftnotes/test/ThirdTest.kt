package com.moonpi.swiftnotes.test

import android.bluetooth.BluetoothClass
import android.support.test.InstrumentationRegistry
import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.espresso.Espresso.*
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.runner.AndroidJUnit4
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.moonpi.swiftnotes.MainActivity
import com.moonpi.swiftnotes.R
import com.moonpi.swiftnotes.rule.SwiftnotesRule
import com.moonpi.swiftnotes.util.targetContext
import com.schibsted.spain.barista.rule.BaristaRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.tinkoff.allure.annotations.DisplayName
import ru.tinkoff.allure.step


@RunWith(AndroidJUnit4::class)
@DisplayName("Создание заметки")
class ThirdTest : AbstractSwiftnotesTest() {

    @get:Rule
    val rule = SwiftnotesRule(MainActivity::class.java, false)

    @Test
    @DisplayName("Проверка открытия страницы создания")
    fun newNoteHints() {
        rule.launchActivity()
        step("Проверяем отображение страницы") {
            openActionBarOverflowOrOptionsMenu(targetContext)
            onView(withText("Backup notes")).check(matches(isDisplayed()))
            onView(withText("Restore notes")).check(matches(isDisplayed()))
            onView(withText("Rate app")).check(matches(isDisplayed()))
            pressBack()
            onView(withId(R.id.newNote)).perform(ViewActions.click())
            openActionBarOverflowOrOptionsMenu(targetContext)
            onView(withText("Note font size")).check(matches(isDisplayed()))
        }
    }
}
