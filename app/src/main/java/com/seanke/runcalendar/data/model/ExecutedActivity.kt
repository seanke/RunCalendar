package com.seanke.runcalendar.data.model

import java.time.OffsetDateTime
import java.util.*

data class ExecutedActivity(val id: UUID, var startTime: OffsetDateTime)
