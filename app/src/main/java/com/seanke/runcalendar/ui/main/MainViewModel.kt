package com.seanke.runcalendar.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seanke.runcalendar.Resource
import com.seanke.runcalendar.data.api.ApiServiceImpl
import com.seanke.runcalendar.data.model.PlannedActivity
import com.seanke.runcalendar.data.repository.MainRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel() : ViewModel() {

    private val plannedActivities = MutableLiveData<Resource<List<PlannedActivity>>>()
    private val compositeDisposable = CompositeDisposable()
    private val mainRepository = MainRepository(ApiServiceImpl())

    init {
        fetchPlannedActivities()
    }

    private fun fetchPlannedActivities() {
        plannedActivities.postValue(Resource.loading(null))
        compositeDisposable.add(
            mainRepository.getPlannedActivities()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ plannedActivitiesList ->
                    plannedActivities.postValue(Resource.success(plannedActivitiesList))
                }, { throwable ->
                    plannedActivities.postValue(Resource.error("Something went wrong", null))
                })
        )
    }

    fun getPlannedActivities(): LiveData<Resource<List<PlannedActivity>>>{
        return plannedActivities
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}