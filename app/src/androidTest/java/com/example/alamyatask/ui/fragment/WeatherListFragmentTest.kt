package com.example.alamyatask.ui.fragment

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.alamyatask.MainActivity
import com.example.alamyatask.R
import com.example.alamyatask.data.FakeWeather_Data
import com.example.alamyatask.data.network.response.ListItem
import junit.framework.TestCase
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.alamyatask.ui.adapter.WeatherAdapter
import org.junit.*
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4ClassRunner::class)
class WeatherListFragmentTest {

    val LIST_ITEM_IN_TEST = 3
    val MOVIE_IN_TEST = FakeWeather_Data.weatheritems[LIST_ITEM_IN_TEST]

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)




        @Test
        fun a_test_isListFragmentVisible_onAppLaunch() {
            onView(withId(R.id.allweatheRecycler)).check(matches(isDisplayed()))

            // onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())))
        }



        @Test
        fun test_backNavigation_toMovieListFragment() {

            // Click list item #LIST_ITEM_IN_TEST
            onView(withId(R.id.allweatheRecycler))
                .perform(
                    actionOnItemAtPosition<WeatherAdapter.WeatherVHolder>(
                        LIST_ITEM_IN_TEST,
                        click()
                    )
                )

            // Confirm nav to DetailFragment and display title
            onView(withId(R.id.weathertitle)).check(matches(withText(MOVIE_IN_TEST.weather!![0]!!.main)))

            pressBack()

            // Confirm MovieListFragment in view
            onView(withId(R.id.allweatheRecycler)).check(matches(isDisplayed()))
        }

    }

