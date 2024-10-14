package com.example.myapplication.fragments

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vendor_android.R
import com.example.vendor_android.adapters.ProductAdapter


class ProductFragment : Fragment() {


    private lateinit var productAdapter: ProductAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val productList = listOf(
            Pair("Banner(s)", "Add your Banners here"),
            Pair("Product(s)", "Add your Products here"),
            Pair("Add-On(s)", "Add your Products here"),
            Pair("Upload Merchant Details", "Upload Store Details"),
            Pair("Product Category", "Product Category"),
            Pair("Coupon(s)", "Generate Coupon")
        )

        productAdapter = ProductAdapter(productList) { title ->
            // Handle click events
            // Navigate to the appropriate activity based on the title
        }

        recyclerView.adapter = productAdapter
    }



}