package com.example.baitaptet
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.baitaptet.databinding.FragmentRestaurantsBinding
import com.example.baitaptet.screen.restaurants.Image
import com.example.baitaptet.viewmodel.RestaurantsViewModel



class RestaurantsFragment : Fragment() {

    lateinit var binding: FragmentRestaurantsBinding
    lateinit var adapter: ImageAdapter
    lateinit var viewModel: RestaurantsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRestaurantsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[RestaurantsViewModel::class.java]


        setUpRecyclerView()
        setUpButtonLoad()
        registerDataEvent()
        registerLoadingView()
        (binding.toolbar as? AppCompatActivity)?.setSupportActionBar(binding.toolbar)
        binding.toolbar.title = "Restaurants"
        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_option, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_profile -> {

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUpRecyclerView() {
        binding.rvImage.layoutManager = LinearLayoutManager(requireContext())

        adapter = ImageAdapter(onImageClickListener)
        binding.rvImage.adapter = adapter
    }

    private val onImageClickListener = object : OnImageItemListener {
        override fun onClickItem(item: Image) {
            viewModel.handleItemWhenClicked(item)
            Toast.makeText(requireContext(), "on click item", Toast.LENGTH_SHORT).show();
        }

        override fun onLongClick(item: Image) {
            viewModel.handleItemWhenLongClicked(item)
            Toast.makeText(requireContext(), "on click long item", Toast.LENGTH_SHORT).show();
        }

    }

    private fun setUpButtonLoad() {
        binding.btnLoad.setOnClickListener {
            viewModel.loadData()
        }
    }

    private fun registerDataEvent() {
        viewModel.listOfIdol.observe(viewLifecycleOwner, Observer { data ->
            run {
                adapter.submitList(data)
            }
        })
    }

    private fun registerLoadingView() {
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            run {
                binding.progressBar.visibility =
                    if (isLoading) View.VISIBLE else
                        View.INVISIBLE
            }
        }
    }


}
