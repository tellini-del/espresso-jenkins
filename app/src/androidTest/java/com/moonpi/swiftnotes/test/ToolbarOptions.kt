package com.moonpi.swiftnotes.test

import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.espresso.Espresso.*
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import com.moonpi.swiftnotes.MainActivity
import com.moonpi.swiftnotes.rule.SwiftnotesRule
import org.junit.Rule
import org.junit.Test
import com.moonpi.swiftnotes.R
import org.junit.runner.RunWith
import ru.tinkoff.allure.annotations.DisplayName
import ru.tinkoff.allure.step
import java.util.regex.Pattern.matches

@RunWith(AndroidJUnit4::class)
@DisplayName("Проверка пунктов меню в тулбаре")
class ToolbarOptions: AbstractSwiftnotesTest() {

    @get:Rule
    val rule = SwiftnotesRule(MainActivity::class.java, false)

    @Test
    @DisplayName("Проверка пунктов меню в тулбаре")
    fun toolbarOptions() {
        rule.launchActivity()

        step("Открыть меню ЕЩЕ") {
            openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext())
        }
        step("Проверка пункта Backup в меню ЕЩЕ") {
            onView(withText(R.string.action_backup)).check(matches(withText("Backup notes")))
        }
        step("Проверка пункта Restore в меню ЕЩЕ") {
            onView(withText(R.string.action_restore)).check(matches(withText("Restore notes")))
        }
        step("Проверка пункта Rate в меню ЕЩЕ") {
            onView(withText(R.string.action_rate_app)).check(matches(withText("Rate app")))
        }
        step("Создать заметку") {
            pressBack()
            onView(withId(R.id.newNote)).perform(click())
        }
        step("Открыть меню ЕЩЕ") {
            openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext())
        }
        step("Проверка пункта Note font size в меню ЕЩЕ") {
            onView(withText(R.string.action_font_size)).check(matches(withText("Note font size")))
        }
        step("Проверка пункта Hide note body in list в меню ЕЩЕ") {
            onView(withText(R.string.action_hide_body)).check(matches(withText("Hide note body in list")))
            pressBack()
        }
    }
}