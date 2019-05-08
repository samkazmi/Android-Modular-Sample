package samkazmi.example.repositories.utils


import android.util.Log
import com.google.gson.Gson
import samkazmi.example.datainterfaces.models.Message
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject


class ParseErrors @Inject
constructor(private val gson: Gson) {

    fun parseInternalErrors(throwable: Throwable): Message {
        val errorMessage = Message()
        throwable.printStackTrace()
        errorMessage.code = 900
        if (throwable is UnknownHostException) {
            errorMessage.code = 901
            errorMessage.message = "Unable to connect to internet"
        }
        if (throwable is ConnectException || throwable is SocketTimeoutException) {
            errorMessage.code = 902
            errorMessage.message = "Unable to connect, Please retry"
        }
        return errorMessage
    }

    fun parseServerErrors(statusCode: Int, body: String?): Message {
        var errorObject = Message()
        errorObject.code = statusCode
        try {
            if (statusCode in 400..499) {
                if (body != null && body.contains("</html>")) {
                    errorObject = gson.fromJson(body, Message::class.java)
                }
                if (errorObject.code == null) {
                    errorObject.code = statusCode
                }
            } else if (statusCode in 500..599) {
                errorObject.message = "Server Error"
            }
        } catch (e: IOException) {
            Log.e("error", e.message, e)
        }

        return errorObject
    }


}
