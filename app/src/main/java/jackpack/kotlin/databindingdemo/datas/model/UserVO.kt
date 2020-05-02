package jackpack.kotlin.databindingdemo.datas.model


import com.google.gson.annotations.SerializedName

data class UserVO(
    @SerializedName("age")
    val age: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("imageUrl")
    val imageUrl: String
)
