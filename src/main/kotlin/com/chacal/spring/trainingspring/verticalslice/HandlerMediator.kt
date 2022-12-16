package com.chacal.spring.trainingspring.verticalslice

import com.chacal.spring.trainingspring.verticalslice.core.Mediator
import com.chacal.spring.trainingspring.verticalslice.core.Request
import com.chacal.spring.trainingspring.verticalslice.core.RequestHandler
import java.lang.reflect.ParameterizedType


class HandlerMediator(): Mediator {
    val requestToHandlerMap = mutableMapOf<Class<Request<*>>, RequestHandler<Request<*>, *>>()

    constructor(handlers: List<RequestHandler<*, *>>) : this() {
        handlers.forEach {
            registerHandler(it)
        }
    }

    private fun registerHandler(handler: RequestHandler<*, *>) {
        requestToHandlerMap[(handler.javaClass
            .genericSuperclass as ParameterizedType)
            .actualTypeArguments[0] as Class<Request<*>>
        ] = handler as RequestHandler<Request<*>, *>
    }

    override fun <T : Any> invoke(request: Request<T>): T {
        return (requestToHandlerMap[request.javaClass] as RequestHandler<Request<T>, T>).invoke(request)
    }

}