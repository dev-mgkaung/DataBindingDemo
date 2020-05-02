package jackpack.kotlin.databindingdemo.datas.apis

import jackpack.kotlin.databindingdemo.datas.model.UserVO
import jackpack.kotlin.databindingdemo.utilities.GET_USERS
import retrofit2.http.GET


/**
 * Created by Mg Kaung on 5/2/2020.
 */
interface ApiService {

    @GET(GET_USERS)
    suspend  fun getUsers() : List<UserVO>

}