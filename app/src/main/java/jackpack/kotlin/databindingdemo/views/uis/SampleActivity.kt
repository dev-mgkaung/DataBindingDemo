package jackpack.kotlin.databindingdemo.views.uis

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import jackpack.kotlin.databindingdemo.R
import jackpack.kotlin.databindingdemo.databinding.ActivitySampleBinding
import jackpack.kotlin.databindingdemo.datas.apis.ApiHelper
import jackpack.kotlin.databindingdemo.datas.apis.RetrofitBuilder
import jackpack.kotlin.databindingdemo.datas.remotes.responses.Status
import jackpack.kotlin.databindingdemo.viewmodels.SimpleViewModel
import jackpack.kotlin.databindingdemo.views.adapters.UserListAdapter
import jackpack.kotlin.databindingdemo.views.base.ViewModelFactory
import kotlinx.android.synthetic.main.activity_sample.*


class SampleActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySampleBinding

    private val viewModel by lazy { ViewModelProviders.of(this, ViewModelFactory(ApiHelper(RetrofitBuilder.apiService),application)).get(SimpleViewModel::class.java) }

    private lateinit var adapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDataBinding()
        setupObservers()
    }

    private fun setupDataBinding()
    {
        //Databing setUp
           binding= DataBindingUtil.setContentView(this, R.layout.activity_sample)
           binding.lifecycleOwner = this
           adapter = UserListAdapter(applicationContext)
           binding.myAdapter = adapter
           binding.viewModel=viewModel

        ///Data Show By RoomDB With Paging Library
           viewModel.userDataList.observe(this, Observer {
               isVisibleView(true)
               adapter.submitList(it)
           })

        //Dark Theme Change Logic With AppCompatDelegate
           binding.setOnDarkModeChange { buttonView, isChecked ->
            println("buttonView = [$buttonView], isChecked = [$isChecked]")
               when(isChecked)
               {
                   true -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                   else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
               }
           }
    }

    private fun setupObservers() {

        //data fetch from server
        viewModel.getUsersDataFromServer().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> { swipeRefresh.isRefreshing=false; }
                    Status.ERROR -> { swipeRefresh.isRefreshing=true; }
                    Status.LOADING -> { swipeRefresh.isRefreshing=true; }
                }
            }
        })

    }

    fun isVisibleView(condition: Boolean)
    {
        when(condition) {
            false ->
            {   swipeRefresh.isRefreshing=true
                recyclerView.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
            }
            true ->
            {   swipeRefresh.isRefreshing=false
                recyclerView.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            }
        }
    }
}
