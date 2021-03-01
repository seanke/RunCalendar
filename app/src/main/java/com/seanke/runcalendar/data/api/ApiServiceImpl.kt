package com.seanke.runcalendar.data.api

import com.rx2androidnetworking.Rx2AndroidNetworking
import com.seanke.runcalendar.data.model.PlannedActivity
import io.reactivex.Single

class ApiServiceImpl : ApiService {
    override fun getPlannedActivities(): Single<List<PlannedActivity>> {
        var plannedActivities =  Rx2AndroidNetworking.get("https://6039b78dd2b9430017d24528.mockapi.io/plannedActivity")
            .build()
            .getObjectListSingle(PlannedActivity::class.java)
        return plannedActivities
    }
}