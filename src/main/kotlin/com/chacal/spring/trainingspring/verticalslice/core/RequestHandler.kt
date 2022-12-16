package com.chacal.spring.trainingspring.verticalslice.core

interface RequestHandler<TRequest: Request<TResponse>, TResponse>{
    operator fun invoke(request: TRequest): TResponse
}