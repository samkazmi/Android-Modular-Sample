package samkazmi.example.repositories.remote.client

import samkazmi.example.datainterfaces.models.AuthHeader
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class SessionAuth @Inject
internal constructor() : Interceptor {

    private var authHeader = AuthHeader(-1, null)

    fun setAuthHeader(authHeader: AuthHeader) {
        this.authHeader = authHeader
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader(sidParamName, if (authHeader.sid == null) "" else authHeader.sid.toString())
            .addHeader(stokenParamName,  if (authHeader.stoken == null) "" else authHeader.stoken.toString())
            .build()
        return chain.proceed(request)
    }

    companion object {

        private val location = "header"
        private val sidParamName = "sid"
        private val stokenParamName = "stoken"
    }
}
