package jackpack.kotlin.databindingdemo.datas.local.daos


import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import jackpack.kotlin.databindingdemo.datas.remotes.model.UserVO

/**
 * Created by Mg Kaung on 5/3/2020.
 */
@Dao
interface UserDao {

    @Query("SELECT * from user_table ORDER BY user_id ASC")
    fun getUserDataFromRoom():  DataSource.Factory<Int,UserVO>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insert(userdata: UserVO)

    @Query("DELETE FROM user_table")
     fun deleteAll()

}