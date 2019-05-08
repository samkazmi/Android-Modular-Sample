package samkazmi.example.base

import android.app.Application
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    private var job = Job()
    protected var viewModelScope: CoroutineScope = CoroutineScope(Dispatchers.Main + job)

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

    fun getString(@StringRes idRes: Int): String =
            getApplication<Application>().getString(idRes)
}