package com.shamilov.payda.domain.executor

import io.reactivex.Scheduler

/**
 * Created by Shamilov on 15.07.2020
 */
interface SchedulerProvider {
    fun ui(): Scheduler
    fun io(): Scheduler
    fun computation(): Scheduler
}