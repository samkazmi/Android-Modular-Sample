package samkazmi.example.repositories.usecases

import samkazmi.example.repositories.utils.ParseErrors
import samkazmi.example.datainterfaces.models.Message
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class BaseUsecase(private val parseErrors: ParseErrors) {

    protected suspend fun handleException(
        e: Exception,
        onError: (message: Message) -> Unit
    ) {
        val error = withContext(Dispatchers.IO) {
            parseErrors.parseInternalErrors(e)
        }
        println("onError:  ${Thread.currentThread()}")
        onError(error)
    }

    protected suspend fun handleError(
        code: Int, errorBody: String,
        onError: (message: Message) -> Unit
    ) {
        val error = withContext(Dispatchers.IO) {
            println("creating error method:  ${Thread.currentThread()}")
            parseErrors.parseServerErrors(code, errorBody)
        }
        println("onError:  ${Thread.currentThread()}")
        onError(error)
    }
}