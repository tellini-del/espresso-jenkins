package com.moonpi.swiftnotes.test

import android.app.AlertDialog
import android.app.Dialog
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.Espresso.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.RootMatchers.isDialog
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.runner.AndroidJUnit4

import com.moonpi.swiftnotes.R
import com.moonpi.swiftnotes.MainActivity
import com.moonpi.swiftnotes.rule.SwiftnotesRule
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Rule
import org.junit.runner.RunWith
import ru.tinkoff.allure.annotations.DisplayName
import org.junit.Test
import ru.tinkoff.allure.step


@RunWith(AndroidJUnit4::class)
@DisplayName("Проверка экрана создания заметки")

class CheckScreenState : AbstractSwiftnotesTest() {

    @get:Rule
    val rule = SwiftnotesRule(MainActivity::class.java, false)

    @Test
    @DisplayName("1 test")
    fun checkingScreenState() {
        rule.launchActivity()

        step("Проверка хинта заголовка новой заметки") {
            onView(withId(R.id.newNote)).perform(click())
            onView(allOf(withId(R.id.titleEdit), isDisplayed())).check(matches(withHint("Title")))
        }

        step("Провека хинта текста Note") {
            onView(allOf(withId(R.id.bodyEdit), isDisplayed())).check(matches(withHint("Note")))
        }

        step("Переход по кнопке назад на тулбаре и проверка диалога") {
            closeSoftKeyboard()
            pressBack()
            onView(allOf(withText(R.string.dialog_save_changes), isDisplayed())).check(matches(
                withText("Save changes?")))
            onView(withText(R.string.yes_button)).check(matches(withText("Yes")))
            onView(withText(R.string.no_button)).check(matches(withText("No")))
        }

        step("Возвращаемся на главный экран") {
            onView(withText(R.string.no_button)).perform(click())
            onView(withText(R.string.app_name)).check(matches(withText("Swiftnotes")))
        }



    }

}