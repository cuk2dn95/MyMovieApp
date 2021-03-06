package com.example.truongquockhanh.mymovieapp.feature.popular


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.truongquockhanh.mymovieapp.R
import com.example.truongquockhanh.mymovieapp.base.BaseFragment
import com.example.truongquockhanh.mymovieapp.constant.NetworkState
import com.example.truongquockhanh.mymovieapp.databinding.FragmentListPopularMovieBinding
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_list_popular_movie.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 *
 */
class ListPopularMovieFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: ListPopularMovieViewModel
    private lateinit var movieAdapter: ListPopularViewAdapter

    lateinit var binding: FragmentListPopularMovieBinding

    override val netWorkState: LiveData<NetworkState>
        get() = viewModel.netWorkState

    override fun showLoading() {
        progress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress.visibility = View.GONE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_popular_movie, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListPopularMovieViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieAdapter = ListPopularViewAdapter()
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = movieAdapter
        binding.viewModel = viewModel
        viewModel.listMovie.observe(this, Observer {
            movieAdapter.submitList(it)
        })

    }
}
