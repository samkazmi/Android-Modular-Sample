package samkazmi.example.datainterfaces.models

data class AuthHeader(var sid: Int? = null, var stoken: String? = null) {
    fun checkNull(): Boolean {
        return sid == null || stoken == null
    }
}
