package com.genxsol.quiltertest.core.common.schedulers

import io.reactivex.rxjava3.core.Scheduler

interface SchedulerProvider {
    val io: Scheduler
    val computation: Scheduler
    val main: Scheduler
}

