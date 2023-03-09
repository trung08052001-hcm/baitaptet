package com.example.baitaptet.screen.restaurants

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baitaptet.ImageAdapter
import com.example.baitaptet.OnImageItemListener
import com.example.baitaptet.R
import com.example.baitaptet.databinding.ActivityRestaurantsBinding
import com.example.baitaptet.viewmodel.RestaurantsViewModel


class RestaurantsActivity : AppCompatActivity() {
    lateinit var binding: ActivityRestaurantsBinding
    lateinit var adapter: ImageAdapter
    lateinit var viewModel: RestaurantsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_restaurants)
        viewModel = ViewModelProvider(this)[RestaurantsViewModel::class.java]

        setUpRecyclerView()
        setUpButtonLoad()

        registerDataEvent()
        registerLoadingView()
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setTitle("Restaurants")
    }
    override fun onCreateOptionsMenu(menu: Menu) :Boolean{
        menuInflater.inflate(R.menu.menu_option,menu)
        return true

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_toggle_layout_manager -> {
                val layoutManager = if (binding.rvImage.layoutManager is LinearLayoutManager) {
                    GridLayoutManager(this, 2)
                } else {
                    LinearLayoutManager(this)
                }
                binding.rvImage.layoutManager = layoutManager
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
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