package com.example.vendor_android.fragments

import Product
import ProductListAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import android.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vendor_android.R
import com.example.vendor_android.adapters.AccountAdapter

class ProductsFragment : Fragment() {
    private lateinit var productAdapter: ProductListAdapter
    private lateinit var productList: List<Product>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        productList = listOf(
            Product(R.drawable.pizza, "Pizza", "₹349", "₹399", "completed", true),
            Product(R.drawable.biryani, "Veg Biryani", "₹99", "₹149", "pending", false),
            Product(R.drawable.chicken, "Chicken Biryani", "₹149", "₹299", "completed", true)
        )


        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        productAdapter = ProductListAdapter(productList)
        recyclerView.adapter = productAdapter
    }

}