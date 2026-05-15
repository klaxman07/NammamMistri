package com.nammamistri.data.model

data class Worker(

    val name: String,

    val dailyWage: Int,

    val daysWorked: Int
) {

    fun totalSalary(): Int {

        return dailyWage * daysWorked
    }
}