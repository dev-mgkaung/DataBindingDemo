package jackpack.kotlin.databindingdemo.views.uis

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import jackpack.kotlin.databindingdemo.R
import jackpack.kotlin.databindingdemo.databinding.ActivitySampleBinding
import jackpack.kotlin.databindingdemo.datas.apis.ApiHelper
import jackpack.kotlin.databindingdemo.datas.apis.RetrofitBuilder
import jackpack.kotlin.databindingdemo.datas.remotes.model.UserVO
import jackpack.kotlin.databindingdemo.datas.remotes.responses.Status
import jackpack.kotlin.databindingdemo.viewmodels.SimpleViewModel
import jackpack.kotlin.databindingdemo.views.adapters.UserListAdapter
import jackpack.kotlin.databindingdemo.views.base.ViewModelFactory
import kotlinx.android.synthetic.main.activity_sample.*


class SampleActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySampleBinding
    // Obtain ViewModel from ViewModelProviders
    private val viewModel by lazy { ViewModelProviders.of(this, ViewModelFactory(ApiHelper(RetrofitBuilder.apiService),application)).get(SimpleViewModel::class.java) }

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
           binding.viewModel=viewModel

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

        //online mode
        viewModel.getUsers().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
//                            isVisibleView(true)
//                            resource.data?.let { users ->
//                                retrieveList(users)
//                            }
                        swipeRefresh.isRefreshing=false;
                        Toast.makeText(applicationContext,"Success",Toast.LENGTH_LONG).show();
                        Log.e("Success Data","fetch");
                    }
                    Status.ERROR -> {
                     //   isVisibleView(false)
                        swipeRefresh.isRefreshing=true;
                        Log.e("Error Data","fetch");
                        Toast.makeText(applicationContext,"Error",Toast.LENGTH_LONG).show();

                    }
                    Status.LOADING -> {
                    //    isVisibleView(false)
                        swipeRefresh.isRefreshing=true;
                        Log.e("Loading Data","fetch");
                        Toast.makeText(applicationContext,"Loading",Toast.LENGTH_LONG).show();

                    }
                }
            }
        })

        //offline mode (First Time data fetch from api , then we inserted data into room database. Now we show our offline data with recyclerview
        viewModel.userData.observe(this, Observer { users ->
            isVisibleView(true)
            users?.let {  retrieveList(users) }
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
    private fun retrieveList(users: List<UserVO>) {
        adapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }
    }
}
