package com.example.baitaptet
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.baitaptet.databinding.FragmentRestaurantsBinding
import com.example.baitaptet.screen.restaurants.Image
import com.example.baitaptet.viewmodel.RestaurantsViewModel



class RestaurantsFragment : Fragment() {

    lateinit var binding: FragmentRestaurantsBinding
    lateinit var adapter: ImageAdapter
    lateinit var viewModel: RestaurantsViewModel
    private var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar?.show()
        (activity as AppCompatActivity?)!!.supportActionBar?.title = "My restaurants"

    }
    override fun onDestroy() {
        super.onDestroy()
        (activity as AppCompatActivity?)!!.supportActionBar?.hide()
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


        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_option, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_profile -> {
                findNavController().navigate(R.id.action_restaurantsFragment_to_profileFragment)
                true
            }
            R.id.action_toggle_layout_manager -> {
                layoutManager = if (layoutManager is LinearLayoutManager) {
                    GridLayoutManager(requireContext(), 2)
                } else {
                    LinearLayoutManager(requireContext())
                }
                binding.rvImage.layoutManager = layoutManager
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
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
