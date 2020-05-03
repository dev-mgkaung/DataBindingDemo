package jackpack.kotlin.databindingdemo.datas.apis

/**
 * Created by Mg Kaung on 5/2/2020.
 */
class ApiHelper(private val apiService: ApiService) {

    suspend fun getUsersDataFromServer() = apiService.getUsersDataFromServer()

}