package com.moonpi.swiftnotes.test

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.runner.AndroidJUnit4
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.moonpi.swiftnotes.MainActivity
import com.moonpi.swiftnotes.pages.ActivityMain
import com.moonpi.swiftnotes.rule.SwiftnotesRule
import com.moonpi.swiftnotes.util.targetContext
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.tinkoff.allure.annotations.DisplayName


@RunWith(AndroidJUnit4::class)
@DisplayName("Создание заметки")
class ThirdTest : AbstractSwiftnotesTest() {

    @get:Rule
    val rule = SwiftnotesRule(MainActivity::class.java, false)

    @Test
    @DisplayName("Проверка пунктов меню в тулбаре")
    fun checkToolbars() {
        rule.launchActivity()
        openActionBarOverflowOrOptionsMenu(targetContext)
        onView(withText("Backup notes"))
                .check(matches(isDisplayed()))
        onView(withText("Restore notes"))
                .check(matches(isDisplayed()))
        onView(withText("Rate app"))
                .check(matches(isDisplayed()))
        ActivityMain()
                .back()
        ActivityMain()
                .clickAddNote()
        openActionBarOverflowOrOptionsMenu(targetContext)
        onView(withText("Note font size"))
                .check(matches(isDisplayed()))
    }
}
