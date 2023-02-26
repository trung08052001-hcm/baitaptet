package com.example.baitaptet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baitaptet.databinding.ActivityRestaurantsBinding

class RestaurantsActivity : AppCompatActivity() {
    lateinit var binding: ActivityRestaurantsBinding
    lateinit var adapter: ImageAdapter
    lateinit var viewModel: MainVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_restaurants)
        viewModel = ViewModelProvider(this)[MainVM::class.java]

        setUpRecyclerView()
        setUpButtonLoad()

        registerDataEvent()
        registerLoadingView()
    }

    private fun setUpRecyclerView() {
        binding.rvImage.layoutManager = LinearLayoutManager(this);

        adapter = ImageAdapter(onImageClickListener)
        binding.rvImage.adapter = adapter
    }

    private val onImageClickListener  = object : OnImageItemListener {
        override fun onClickItem(item: Image) {
            viewModel.handleItemWhenClicked(item)
            Toast.makeText(this@RestaurantsActivity, "on click item", Toast.LENGTH_SHORT).show();
        }

        override fun onLongClick(item: Image) {
            viewModel.handleItemWhenLongClicked(item)
            Toast.makeText(this@RestaurantsActivity, "on click long item", Toast
                .LENGTH_SHORT).show();
        }

    }


    private fun setUpButtonLoad() {
        binding.btnLoad.setOnClickListener {
            viewModel.loadData()
        }
    }

    private fun registerDataEvent() {
        viewModel.listOfIdol.observe(this, Observer { data ->
            run {
                adapter.submitList(data)
            }
        })
    }

    private fun registerLoadingView() {
        viewModel.isLoading.observe(this) { isLoading ->
            run {
                binding.progressBar.visibility =
                    if (isLoading) View.VISIBLE else
                        View.INVISIBLE
            }
        }
    }

}