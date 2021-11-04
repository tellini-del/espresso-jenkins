package com.moonpi.swiftnotes.test

import android.support.test.espresso.Espresso.*
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.longClick
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.runner.AndroidJUnit4
import com.moonpi.swiftnotes.MainActivity
import com.moonpi.swiftnotes.rule.SwiftnotesRule
import com.moonpi.swiftnotes.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.tinkoff.allure.annotations.DisplayName
import ru.tinkoff.allure.step

@RunWith(AndroidJUnit4::class)
@DisplayName("Удаление заметки")
class DeleteNote: AbstractSwiftnotesTest() {

    @get:Rule
    val rule = SwiftnotesRule(MainActivity::class.java, false)

    @Test
    @DisplayName("Удаление заметки")
    fun deleteNote(){
        rule.launchActivity()

        step("Создать заметку"){
            onView(withId(R.id.newNote)).perform(click())
            onView(withId(R.id.titleEdit)).perform(typeText("Name bleat"))
            onView(withId(R.id.bodyEdit)).perform(typeText("Body bleat"))
            closeSoftKeyboard()
            pressBack()
            onView(withText("Yes")).perform(click())
        }
        step("Лонгтап на заметке"){
            onView(withText("Name bleat")).perform(longClick())
        }
        step("Удаление заметки"){
            onView(withId(R.id.action_delete)).perform(click())
            onView(withText("OK")).perform(click())
        }
        step("Заметка не отображается в списке") {
            onView(withText("Name bleat")).check(doesNotExist())
        }
    }

}