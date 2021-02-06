package com.seanke.runcalendar.entities

import java.time.OffsetDateTime
import java.util.*

data class Activity(val id: UUID, var startTime: OffsetDateTime)
