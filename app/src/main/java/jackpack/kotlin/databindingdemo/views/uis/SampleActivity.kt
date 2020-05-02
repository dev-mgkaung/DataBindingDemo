package jackpack.kotlin.databindingdemo.views.uis

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import jackpack.kotlin.databindingdemo.R
import jackpack.kotlin.databindingdemo.databinding.ActivitySampleBinding
import jackpack.kotlin.databindingdemo.datas.apis.ApiHelper
import jackpack.kotlin.databindingdemo.datas.apis.RetrofitBuilder
import jackpack.kotlin.databindingdemo.datas.model.UserVO
import jackpack.kotlin.databindingdemo.datas.responses.Status
import jackpack.kotlin.databindingdemo.viewmodels.SimpleViewModel
import jackpack.kotlin.databindingdemo.views.adapters.UserListAdapter
import jackpack.kotlin.databindingdemo.views.base.ViewModelFactory
import kotlinx.android.synthetic.main.activity_sample.*


class SampleActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySampleBinding
    // Obtain ViewModel from ViewModelProviders
    private val viewModel by lazy { ViewModelProviders.of(this, ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))).get(SimpleViewModel::class.java) }

    private lateinit var adapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDataBinding()
        setupObservers()
    }

    private fun setupDataBinding()
    {
           binding= DataBindingUtil.setContentView(this, R.layout.activity_sample)
           binding.lifecycleOwner = this  // use Fragment.viewLifecycleOwner for fragments
           adapter = UserListAdapter(applicationContext, arrayListOf())
           binding.myAdapter = adapter
    }


    private fun setupObservers() {
        viewModel.getUsers().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { users -> retrieveList(users) }
                    }
                    Status.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(users: List<UserVO>) {
        adapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }
    }
}
