package ru.blackmirrror.fooddelivery.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.blackmirrror.fooddelivery.R
import ru.blackmirrror.fooddelivery.databinding.FragmentMenuBinding


class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding
    private val viewModel by viewModel<MenuViewModel>()

    private lateinit var mealAdapter: MealAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var bannerAdapter: BannerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleRecyclers()
        handleSpinner()
        observeData()
    }

    private fun handleRecyclers() {
        mealAdapter = MealAdapter()
        binding.rvFood.adapter = mealAdapter
        bannerAdapter = BannerAdapter()
        binding.rvBanners.adapter = bannerAdapter
        categoryAdapter = CategoryAdapter()
        categoryAdapter.onCategoryItemClickListener = { category ->
            category.strCategory?.let { viewModel.getMealsByCategory(it) }
            viewModel.changeCategoryState(category)
        }
        binding.rvCategories.adapter = categoryAdapter
    }

    private fun handleSpinner() {
        val spinnerItems = resources.getStringArray(R.array.spinner_city_items)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinnerItems)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCities.adapter = adapter
    }

    private fun observeData() {
        viewModel.loading.observe(viewLifecycleOwner) {
            (binding.pbMenu.visibility).apply {
                if (it) View.VISIBLE
                else View.GONE
            }
        }
        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
        viewModel.banners.observe(viewLifecycleOwner) {
            bannerAdapter.submitList(it)
        }
        viewModel.categories.observe(viewLifecycleOwner) {
            categoryAdapter.submitList(it)
        }
        viewModel.meals.observe(viewLifecycleOwner) {
            mealAdapter.submitList(it)
        }
    }
}