package com.example.day6_app2

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.day6_app2.databinding.FragmentProductBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ProductFragment : Fragment(), OnProductClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: RecyclerAdapter
    private var isLandscape = false
    private  lateinit var binding : FragmentProductBinding

    lateinit var productList : List<Product>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductBinding.inflate(inflater, container, false)
        recyclerView = binding.recyclerView

        isLandscape = arguments?.getBoolean("isLandscape") ?: false

        recyclerAdapter = RecyclerAdapter(emptyList(), this)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

    @SuppressLint("IdleBatteryChargingConstraints")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val myProductDao : ProductDAO = ProductDatabase.getInstance(requireContext()).getProductDao()
        if(isConnected()) {
            lifecycleScope.launch(Dispatchers.IO) {
                try {
                    val response = RetrofitHelper.service.getAllProducts()
                    if (response.isSuccessful) {
                        productList = response.body()?.products ?: emptyList()
                        myProductDao.insertAllProducts(productList)
                        withContext(Dispatchers.Main) {
                            recyclerAdapter.products = productList
                            recyclerAdapter.onupdate()
                        }
                    } else {
                        Log.i("TAG", response.errorBody().toString())
                    }
                } catch (th: Throwable) {
                    Log.i("TAG", th.message ?: "Error")
                    th.printStackTrace()
                }
            }
        }
        else{
            lifecycleScope.launch(Dispatchers.IO) {
                try {
                    productList = myProductDao.getAllProductsFromDB()
                    withContext(Dispatchers.Main) {
                        Toast.makeText(requireContext(), "No Internet. Showing offline data.", Toast.LENGTH_SHORT).show()
                        recyclerAdapter.products = productList
                        recyclerAdapter.onupdate()
                    }

                } catch (th: Throwable) {
                    Log.i("TAG", th.message ?: "Error")
                    th.printStackTrace()
                }
            }
        }
    }

    override fun onProductClick(product: Product) {
        if(isLandscape) {
            (requireActivity() as ActivityA).showProductDetails(product)
        }
        else{
            val intent = Intent(requireContext(), ActivityB::class.java)
            intent.putExtra("product", product)
            startActivity(intent)
        }
    }

    private fun isConnected(): Boolean {
        val cm = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }
}
