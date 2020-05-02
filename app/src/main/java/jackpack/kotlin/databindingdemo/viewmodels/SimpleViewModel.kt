package jackpack.kotlin.databindingdemo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import jackpack.kotlin.databindingdemo.datas.repository.DataRepository
import jackpack.kotlin.databindingdemo.datas.responses.Resource
import kotlinx.coroutines.Dispatchers

/**
 * Created by Mg Kaung on 5/2/2020.
 */
class SimpleViewModel(private val dataRepository :DataRepository) : ViewModel() {

    fun getUsers() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = dataRepository.getUsers()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}