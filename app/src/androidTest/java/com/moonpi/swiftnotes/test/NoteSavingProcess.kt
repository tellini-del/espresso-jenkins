package com.moonpi.swiftnotes.test

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.Espresso.*
import android.support.test.espresso.action.ViewActions.typeText
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
@DisplayName("Проверка сохранения заметки")

class NoteSavingProcess : AbstractSwiftnotesTest() {

    @get:Rule
    val rule = SwiftnotesRule(MainActivity::class.java, false)

    @Test
    @DisplayName("Проверка сохранения заметки")

    fun noteSaving(){
        rule.launchActivity()

        step("Создать заметку") {
            onView(withId(R.id.newNote)).perform(click())
        }
        step("Заполнить поля Название и Текст") {
            onView(withId(R.id.titleEdit)).perform(typeText("ASsaaadaasd"))
            onView(withId(R.id.bodyEdit)).perform(typeText("Text inside"))
        }
        step("Нажать назад в тулбаре и сохранить заметку") {
            closeSoftKeyboard()
            pressBack()
            onView(withText(R.string.dialog_save_changes)).check(matches(withText("Save changes?")))
            onView(withText(R.string.yes_button)).perform(click())
        }
        step("Проверить сохранение заметки") {
            onView(withId(R.id.titleView)).check(matches(withText("ASsaaadaasd")))
        }
    }
}