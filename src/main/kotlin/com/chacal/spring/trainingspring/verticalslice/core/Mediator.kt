package com.chacal.spring.trainingspring.verticalslice.core

interface Mediator {
    fun<T> send(request: Request<T>): T
}