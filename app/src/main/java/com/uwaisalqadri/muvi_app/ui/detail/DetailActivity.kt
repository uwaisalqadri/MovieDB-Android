package com.uwaisalqadri.muvi_app.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.uwaisalqadri.muvi_app.R
import com.uwaisalqadri.muvi_app.databinding.ActivityDetailBinding
import com.uwaisalqadri.muvi_app.utils.showToast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by viewBinding()
    private val viewModel: DetailViewModel by viewModels()

    private val castAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intent = intent?.extras?.get("movieId")
        Timber.d(intent.toString())

        with(viewModel) {
            getDetailCredits(intent.toString())

            castData.observe(this@DetailActivity) { casts ->
                casts.map {
                    castAdapter.add(CastItem(it))
                }
            }
            messageData.observe(this@DetailActivity) {
                showToast(it)
            }
            showProgressBar.observe(this@DetailActivity) {
                //
            }
        }

        with(binding.rvDetailCast) {
            adapter = castAdapter
            layoutManager = LinearLayoutManager(this@DetailActivity, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }

        binding.apply {
            btnBack.setOnClickListener {
                finish()
            }
        }
    }
}