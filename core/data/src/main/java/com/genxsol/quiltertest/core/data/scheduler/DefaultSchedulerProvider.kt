package com.genxsol.quiltertest.core.data.scheduler

import com.genxsol.quiltertest.core.common.schedulers.SchedulerProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultSchedulerProvider @Inject constructor() : SchedulerProvider {
    override val io: Scheduler = Schedulers.io()
    override val computation: Scheduler = Schedulers.computation()
    override val main: Scheduler = AndroidSchedulers.mainThread()
}

