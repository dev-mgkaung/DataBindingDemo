package jackpack.kotlin.databindingdemo.datas.local.repository

import jackpack.kotlin.databindingdemo.datas.local.daos.UserDao
import jackpack.kotlin.databindingdemo.datas.remotes.model.UserVO

/**
 * Created by Mg Kaung on 5/3/2020.
 */
class LocalRepository(private val userDao: UserDao) {

    suspend fun insert(userdata: UserVO) {
        userDao.insert(userdata)
    }
}