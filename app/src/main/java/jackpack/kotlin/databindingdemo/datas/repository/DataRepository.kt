package jackpack.kotlin.databindingdemo.datas.repository

import jackpack.kotlin.databindingdemo.datas.apis.ApiHelper

/**
 * Created by Mg Kaung on 5/2/2020.
 */

class DataRepository(private val apiHelper: ApiHelper) {

    suspend fun getUsers() = apiHelper.getUsers()
}