package jackpack.kotlin.databindingdemo.views.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import jackpack.kotlin.databindingdemo.BR
import jackpack.kotlin.databindingdemo.databinding.UserListItemBinding
import jackpack.kotlin.databindingdemo.datas.remotes.model.UserVO
import jackpack.kotlin.databindingdemo.views.callbacks.CustomClickListener

/**
 * Created by Mg Kaung on 5/2/2020.
 */

class UserListAdapter(context: Context) : PagedListAdapter<UserVO, UserListAdapter.DataViewHolder>(diffCallback) ,
    CustomClickListener {
    private val mContext: Context ?=context
    companion object {

        private val diffCallback = object : DiffUtil.ItemCallback<UserVO>() {
            override fun areItemsTheSame(oldItem: UserVO, newItem: UserVO): Boolean =
                oldItem.user_id == newItem.user_id

            override fun areContentsTheSame(oldItem: UserVO, newItem: UserVO): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = UserListItemBinding.inflate(inflater)
        return DataViewHolder(binding)
    }

     override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val userVO: UserVO? = getItem(position)
         userVO?.let { holder.bind(it) }
         holder.binding.setItemClickListener(this)
    }


    inner class DataViewHolder(val binding: UserListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserVO) {
            binding.setVariable(BR.user,item)
            binding.executePendingBindings();
        }
    }

    override fun cardClicked(userdata: UserVO?) {
        Toast.makeText(mContext,"Selected item is = ${userdata?.name}",Toast.LENGTH_LONG).show()
    }
}