package com.seanke.runcalendar.ui.calendar

import android.view.View
import android.widget.TextView
import com.kizitonwose.calendarview.ui.ViewContainer
import com.seanke.runcalendar.R

class MonthFooterViewContainer(view: View) : ViewContainer(view) {
    val textView: TextView = view.findViewById(R.id.footerTextView)
}