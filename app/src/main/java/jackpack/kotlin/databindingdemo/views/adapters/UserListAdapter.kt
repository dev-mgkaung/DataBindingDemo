package jackpack.kotlin.databindingdemo.views.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import jackpack.kotlin.databindingdemo.BR
import jackpack.kotlin.databindingdemo.databinding.UserListItemBinding
import jackpack.kotlin.databindingdemo.datas.remotes.model.UserVO
import jackpack.kotlin.databindingdemo.views.callbacks.CustomClickListener

/**
 * Created by Mg Kaung on 5/2/2020.
 */
class UserListAdapter(private val contexts: Context,private val users: ArrayList<UserVO>) : RecyclerView.Adapter<UserListAdapter.DataViewHolder>() ,
    CustomClickListener {
    private val context: Context? = contexts
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = UserListItemBinding.inflate(inflater)
        return DataViewHolder(binding)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
        holder.binding.setItemClickListener(this)
    }

    fun addUsers(users: List<UserVO>) {
        this.users.apply {
            clear()
            addAll(users)
        }

    }
    inner class DataViewHolder(val binding: UserListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserVO) {
            binding.setVariable(BR.user,item)
            binding.executePendingBindings();
        }
    }

    override fun cardClicked(users: UserVO?) {
        Toast.makeText(context, "You clicked " + users?.name,
            Toast.LENGTH_LONG).show();
    }
}