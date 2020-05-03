package jackpack.kotlin.databindingdemo.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import jackpack.kotlin.databindingdemo.datas.local.dbs.UserRoomDatabase
import jackpack.kotlin.databindingdemo.datas.local.repository.LocalRepository
import jackpack.kotlin.databindingdemo.datas.remotes.model.UserVO
import jackpack.kotlin.databindingdemo.datas.remotes.repository.RemoteRepository
import jackpack.kotlin.databindingdemo.datas.remotes.responses.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * Created by Mg Kaung on 5/2/2020.
 */
class SimpleViewModel(private val dataRepository :RemoteRepository, application: Application) : AndroidViewModel(application) {

    //offline RoomDatabase
    private val repository: LocalRepository
    val userData: LiveData<List<UserVO>>


    init {
        val userDao = UserRoomDatabase.getDatabase(application, viewModelScope).userDao()
        repository = LocalRepository(userDao)
        userData = repository.allUsersData
    }

    fun insert(usertable: UserVO) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(usertable)
    }


    fun onRefresh()
    {
        Log.e("Refresh..","fffffffffff");
       getUsers()
    }


    //online Retrofit2
    fun getUsers() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {

            for (item: UserVO in dataRepository.getUsers()) {
                insert(item)
            }
            emit(Resource.success(data =dataRepository.getUsers()))

        } catch (exception: Exception) {

            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}
