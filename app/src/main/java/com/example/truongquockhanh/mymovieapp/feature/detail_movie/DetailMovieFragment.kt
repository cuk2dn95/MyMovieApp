package com.example.truongquockhanh.mymovieapp.feature.detail_movie


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.truongquockhanh.mymovieapp.R
import com.example.truongquockhanh.mymovieapp.base.BaseFragment
import com.example.truongquockhanh.mymovieapp.constant.NetworkState
import com.example.truongquockhanh.mymovieapp.data.ViewModelFactory
import com.example.truongquockhanh.mymovieapp.databinding.FragmentDetailMovieBinding
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_detail_movie.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 *
 */
class DetailMovieFragment : BaseFragment() {

    lateinit var binding: FragmentDetailMovieBinding
    lateinit var viewModel: DetailMovieViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_detail_movie,
            container,
            false
        )
        arguments?.let {
            viewModel.getMovieById(DetailMovieFragmentArgs.fromBundle(bundle = it).idMovie)
        }

        viewModel.movie.observe(this, Observer {
            if (it != null) {
                binding.movie = it
            }
        })

        return binding.root
    }

    override val netWorkState: LiveData<NetworkState>
        get() = viewModel.netWorkState

    override fun showLoading() {
        progress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress.visibility = View.GONE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailMovieViewModel::class.java)
    }

}
