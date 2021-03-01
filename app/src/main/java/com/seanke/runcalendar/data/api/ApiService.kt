package com.seanke.runcalendar.data.api

import com.seanke.runcalendar.data.model.PlannedActivity
import io.reactivex.Single

interface ApiService {
    fun getPlannedActivities(): Single<List<PlannedActivity>>
}