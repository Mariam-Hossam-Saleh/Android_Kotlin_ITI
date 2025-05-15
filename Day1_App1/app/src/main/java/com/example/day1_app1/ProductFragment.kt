package com.example.day1_app1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.day1_app1.model.Product

class ProductFragment : Fragment(), OnProductClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: RecyclerAdapter
    private var isLandscape = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)

        isLandscape = arguments?.getBoolean("isLandscape") ?: false

        recyclerAdapter = RecyclerAdapter(emptyList(), this)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        return view
    }

    @SuppressLint("IdleBatteryChargingConstraints")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
//            .setRequiresCharging(true)
//            .setRequiresBatteryNotLow(true)
//            .setRequiresDeviceIdle(true)
            .build()
        val myWorkRequest : WorkRequest = OneTimeWorkRequestBuilder<MyWorker>()
            .setConstraints(constraints)
            .build()
        WorkManager.getInstance(requireContext()).enqueue(myWorkRequest)

        WorkManager.getInstance(requireContext()).getWorkInfoByIdLiveData(myWorkRequest.id).observe( viewLifecycleOwner)
        {
            workinfo->

                if(workinfo?.state == WorkInfo.State.SUCCEEDED){
                    Log.i("MyWorker" , "hffvgbhnjmkjhgfghjmh")
                    recyclerAdapter.products = MyWorker.fetchedData.products
                    recyclerAdapter.onupdate()
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
}
