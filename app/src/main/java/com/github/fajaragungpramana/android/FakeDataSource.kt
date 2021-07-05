package com.github.fajaragungpramana.android

object FakeDataSource {

    fun getListDay(page: Int) = listOf(
        Day("Monday"),
        Day("Tuesday"),
        Day("Wednesday"),
        Day("Thursday"),
        Day("Friday"),
        Day("Saturday"),
        Day("Sunday"),
    )

}