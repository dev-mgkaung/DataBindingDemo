package jackpack.kotlin.databindingdemo.viewmodels

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.PagedList
import jackpack.kotlin.databindingdemo.datas.local.dbs.UserRoomDatabase
import jackpack.kotlin.databindingdemo.datas.local.repository.LocalRepository
import jackpack.kotlin.databindingdemo.datas.remotes.model.UserVO
import jackpack.kotlin.databindingdemo.datas.remotes.repository.RemoteRepository
import jackpack.kotlin.databindingdemo.datas.remotes.responses.Resource
import androidx.paging.toLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * Created by Mg Kaung on 5/2/2020.
 */
class SimpleViewModel(private val dataRepository :RemoteRepository, application: Application) : AndroidViewModel(application) {

    //offline RoomDatabase
    private val repository: LocalRepository

    init {
        val userDao = UserRoomDatabase.getDatabase(application, viewModelScope).userDao()
        repository = LocalRepository(userDao)

    }

    val userDataList: LiveData<PagedList<UserVO>> = UserRoomDatabase.getDatabase(application, viewModelScope).userDao().getUserDataFromRoom().toLiveData(pageSize = 10)

    fun insert(usertable: UserVO) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(usertable)
    }

    fun onRefresh()
    {
        //Swipe Refresh listener
        getUsersDataFromServer()
    }

    //online Retrofit2
    fun getUsersDataFromServer() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
           try {
            for (item: UserVO in dataRepository.getUsersDataFromServer()) {
                insert(item)   //insert online data to RoomDB
            }
            emit(Resource.success(data =dataRepository.getUsersDataFromServer()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}
