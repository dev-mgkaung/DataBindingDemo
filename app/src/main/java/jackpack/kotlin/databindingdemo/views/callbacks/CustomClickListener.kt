package jackpack.kotlin.databindingdemo.views.callbacks

import jackpack.kotlin.databindingdemo.datas.model.UserVO

/**
 * Created by Mg Kaung on 5/2/2020.
 */
open interface CustomClickListener {
    fun cardClicked(f: UserVO?)
}