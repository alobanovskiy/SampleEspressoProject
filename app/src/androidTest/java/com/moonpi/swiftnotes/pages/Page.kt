package com.moonpi.swiftnotes.pages

import android.support.test.espresso.Espresso
import ru.tinkoff.allure.step

open class Page {

    companion object {
        inline fun <reified T : Page> on(): T {
            return Page().on()
        }
    }

    inline fun <reified T : Page> on(): T {
        val page = T::class.constructors.first().call()
        page.verify()
        return page
    }

    open fun verify(): Page {
        // Each subpage should have its default assurances here
        return this
    }

    fun back(): Page {
        step("вернуться назад") {
            Espresso.pressBack()
            return this
        }
    }
}