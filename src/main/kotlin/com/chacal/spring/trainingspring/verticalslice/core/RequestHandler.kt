package com.chacal.spring.trainingspring.verticalslice.core

interface RequestHandler<in TRequest: Request<TResponse>, out TResponse> where TResponse: Any {
    operator fun invoke(request: TRequest): TResponse
}