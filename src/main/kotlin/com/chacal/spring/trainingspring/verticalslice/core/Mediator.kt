package com.chacal.spring.trainingspring.verticalslice.core

interface Mediator {
    operator fun<T: Any> invoke(request: Request<T>): T
}