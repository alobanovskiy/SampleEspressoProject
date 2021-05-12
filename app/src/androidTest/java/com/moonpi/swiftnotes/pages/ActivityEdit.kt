package com.moonpi.swiftnotes.pages

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.RootMatchers.isDialog
import android.support.test.espresso.matcher.ViewMatchers.*
import com.moonpi.swiftnotes.R.id.bodyEdit
import com.moonpi.swiftnotes.R.id.titleEdit
import org.hamcrest.CoreMatchers.allOf
import ru.tinkoff.allure.android.deviceScreenshot
import ru.tinkoff.allure.step

class ActivityEdit : Page() {

    fun typeTitle(title: String): ActivityEdit {
        step("ввод заголовка ноды") {
            onView(allOf(withId(titleEdit), isDisplayed()))
                    .check(matches(withHint("Title")))
                    .perform(typeText(title))
            deviceScreenshot("page_display")
            return this
        }
    }

    fun typeBody(body: String): ActivityEdit {
        step("ввод тела ноды") {
            onView(allOf(withId(bodyEdit), isDisplayed()))
                    .check(matches(withHint("Note")))
                    .perform(typeText(body))
            deviceScreenshot("page_display")
            return this
        }
    }

    fun isDialogPresent(dialogTitle: String): ActivityEdit {
        step("проверка показан ли диалог" + dialogTitle) {
            onView(withText(dialogTitle))
                    .inRoot(isDialog())
                    .check(matches(isDisplayed()))
            deviceScreenshot("page_display")
            return this
        }
    }

    fun dialogAction(buttonName: String): ActivityEdit {
        step("провести действие" + buttonName + "на диалоговом окне") {
            onView(withText(buttonName))
                    .inRoot(isDialog())
                    .check(matches(isDisplayed()))
                    .perform(click())
            deviceScreenshot("page_display")
            return this
        }
    }

}