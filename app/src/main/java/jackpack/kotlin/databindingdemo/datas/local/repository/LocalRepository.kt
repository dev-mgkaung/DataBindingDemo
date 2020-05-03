package jackpack.kotlin.databindingdemo.datas.local.repository

import androidx.lifecycle.LiveData
import jackpack.kotlin.databindingdemo.datas.local.daos.UserDao
import jackpack.kotlin.databindingdemo.datas.remotes.model.UserVO

/**
 * Created by Mg Kaung on 5/3/2020.
 */
class LocalRepository(private val userDao: UserDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allUsersData: LiveData<List<UserVO>> = userDao.getUserDataFromRoom()

    suspend fun insert(userdata: UserVO) {
        userDao.insert(userdata)
    }
}