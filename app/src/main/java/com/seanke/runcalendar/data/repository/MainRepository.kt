package com.seanke.runcalendar.data.repository

import com.seanke.runcalendar.data.api.ApiService
import com.seanke.runcalendar.data.model.PlannedActivity
import io.reactivex.Single

class MainRepository(private val apiService: ApiService) {

    fun getPlannedActivities(): Single<List<PlannedActivity>> {
        return apiService.getPlannedActivities()
    }

}