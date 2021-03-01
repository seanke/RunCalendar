package com.seanke.runcalendar.ui.main.plan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.fragment.app.activityViewModels
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.CalendarMonth
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.MonthHeaderFooterBinder
import com.seanke.runcalendar.Status
import com.seanke.runcalendar.data.model.PlannedActivity
import com.seanke.runcalendar.ui.calendar.DayViewContainer
import com.seanke.runcalendar.databinding.FragmentPlanBinding
import com.seanke.runcalendar.ui.calendar.MonthFooterViewContainer
import com.seanke.runcalendar.ui.calendar.MonthHeaderViewContainer
import com.seanke.runcalendar.ui.main.MainViewModel
import java.time.YearMonth
import java.time.temporal.WeekFields
import java.util.*

class PlanFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private var _binding: FragmentPlanBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentPlanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.calendarView.dayBinder = object : DayBinder<DayViewContainer> {
            // Called only when a new container is needed.
            override fun create(view: View) = DayViewContainer(view)

            // Called every time we need to reuse a container.
            override fun bind(container: DayViewContainer, day: CalendarDay) {
                container.textView.text = day.date.dayOfMonth.toString()
            }
        }

        binding.calendarView.monthHeaderBinder = object : MonthHeaderFooterBinder<MonthHeaderViewContainer> {
            override fun create(view: View) = MonthHeaderViewContainer(view)
            override fun bind(container: MonthHeaderViewContainer, month: CalendarMonth) {
                (month.yearMonth.month.name.toLowerCase().capitalize() + " " + month.year).also { container.textView.text = it }
            }
        }

        binding.calendarView.monthFooterBinder = object : MonthHeaderFooterBinder<MonthFooterViewContainer> {
            override fun create(view: View) = MonthFooterViewContainer(view)
            override fun bind(container: MonthFooterViewContainer, month: CalendarMonth) {
                container.textView.text = "-------------"
            }
        }

        val currentMonth = YearMonth.now()
        val firstMonth = currentMonth.minusMonths(10)
        val lastMonth = currentMonth.plusMonths(10)
        val firstDayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek
        binding.calendarView.setup(firstMonth, lastMonth, firstDayOfWeek)
        binding.calendarView.scrollToMonth(currentMonth)

        setupObserver()
    }

    private fun setupObserver() {
        mainViewModel.getPlannedActivities().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    loadCalendar(it.data!!)
                }
                Status.LOADING -> {
                }
                Status.ERROR -> {
                    //Handle Error
                }
            }
        })
    }

    private fun loadCalendar(data: List<PlannedActivity>) {
        binding.calendarView
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null;
    }
}