package com.example.foodtracking.Databases.Calendar

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "calendar_table")
class CalendarItem: Serializable {
    @PrimaryKey(autoGenerate = true)
    var id = 0
    var date: String = ""
    var mealId: Int = 0

    constructor()

    constructor(date: String, mealId: Int) {
        this.date = date
        this.mealId = mealId
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}