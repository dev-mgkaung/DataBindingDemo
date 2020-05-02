package jackpack.kotlin.databindingdemo.views.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import jackpack.kotlin.databindingdemo.datas.apis.ApiHelper
import jackpack.kotlin.databindingdemo.datas.repository.DataRepository
import jackpack.kotlin.databindingdemo.viewmodels.SimpleViewModel

/**
 * Created by Mg Kaung on 5/2/2020.
 */
class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SimpleViewModel::class.java)) {
            return SimpleViewModel(DataRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}
