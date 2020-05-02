package jackpack.kotlin.databindingdemo.datas.responses

/**
 * Created by Mg Kaung on 5/2/2020.
 */


data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): Resource<T> = Resource(status = Status.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): Resource<T> =
            Resource(status = Status.ERROR, data = data, message = message)

        fun <T> loading(data: T?): Resource<T> = Resource(status = Status.LOADING, data = data, message = null)
    }
}
enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

// A generic class that contains data and status about loading this data.
//sealed class Resource<T>(
//    val data: T? = null,
//    val errorCode: Int? = null
//) {
//    class Success<T>(data: T) : Resource<T>(data)
//    class Loading<T>(data: T? = null) : Resource<T>(data)
//    class DataError<T>(errorCode: Int) : Resource<T>(null, errorCode)
//}