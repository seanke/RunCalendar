package com.seanke.runcalendar.ui.main.execution

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.seanke.runcalendar.R

class ExecutionFragment : Fragment() {

    private lateinit var executionViewModel: ExecutionViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        executionViewModel =
                ViewModelProvider(this).get(ExecutionViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_analyse, container, false)

        return root
    }
}