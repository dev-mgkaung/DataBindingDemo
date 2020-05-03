package jackpack.kotlin.databindingdemo.datas.remotes.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "user_table")
data class UserVO(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    val user_id: Int,
    @ColumnInfo(name = "age")
    @SerializedName("age")
    val age: String,
    @ColumnInfo(name = "email")
    @SerializedName("email")
    val email: String,
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String,
    @ColumnInfo(name = "imageUrl")
    @SerializedName("imageUrl")
    val imageUrl: String
)
