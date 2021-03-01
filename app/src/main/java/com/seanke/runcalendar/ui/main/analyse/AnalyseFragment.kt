package com.seanke.runcalendar.ui.main.analyse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.seanke.runcalendar.R

class AnalyseFragment : Fragment() {

    private lateinit var analyseViewModel: AnalyseViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        analyseViewModel =
                ViewModelProvider(this).get(AnalyseViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_execution, container, false)

        return root
    }
}