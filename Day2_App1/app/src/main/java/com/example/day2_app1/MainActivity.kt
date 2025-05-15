package com.example.day2_app1

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import android.location.LocationProvider
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresPermission
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.net.toUri
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority

class MainActivity : AppCompatActivity() {

    private lateinit var fusedClient : FusedLocationProviderClient
    private val LOCATION_ID = 1
    lateinit var tvLongitude : TextView
    lateinit var tvLatitude : TextView
    lateinit var tvTextLocation : TextView
    lateinit var btnSMS : Button
    lateinit var openMapBtn : Button

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    override fun onStart() {
        super.onStart()
        val locationManager : LocationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        val provider : LocationProvider? = locationManager.getProvider(LocationManager.GPS_PROVIDER)
        val isGPSEnabled : Boolean = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if(!isGPSEnabled){
            enableLocationSettings()
        }
        else{
            getFreshLocation()
        }
    }

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvLongitude = findViewById(R.id.tvLongitude)
        tvLatitude = findViewById(R.id.tvLatitude)
        tvTextLocation = findViewById(R.id.tvTextLocation)
        btnSMS = findViewById(R.id.btnSMS)
        openMapBtn = findViewById(R.id.btnMAP)
//        val location = locationResult.lastLocation
        btnSMS.setOnClickListener {
            openSmsView(tvTextLocation.text.toString(),"01220591158")
        }

        getFreshLocation()

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ){
            // pop up a dialog to ask for permission
            ActivityCompat.requestPermissions(this@MainActivity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                LOCATION_ID)

            return
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_ID) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // permission granted
                Log.i("Permission", "granted")
            } else {
                // permission denied
                Log.i("Permission", "denied")
            }
        }
    }

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    fun getFreshLocation() {
        fusedClient = LocationServices.getFusedLocationProviderClient(this)
        fusedClient.requestLocationUpdates(
            LocationRequest.Builder(0).apply {
                setPriority(Priority.PRIORITY_HIGH_ACCURACY)
            }.build(),
            object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    super.onLocationResult(locationResult)
                    val location = locationResult.lastLocation
                    tvLongitude.text = location?.longitude.toString()
                    tvLatitude.text = location?.latitude.toString()
                    getLocation(location!!.longitude, location.latitude)
                    openMapBtn.setOnClickListener {
                        openMapView(
                            latitude = location?.latitude ?: 0.0,
                            longitude = location?.longitude ?: 0.0,
                            address = tvTextLocation.text.toString()
                        ) }
                }
            },
            Looper.myLooper())

    }

    private fun enableLocationSettings() {
        val settingsIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        startActivity(settingsIntent)
    }

    private fun getLocation(longitude: Double, latitude: Double) {
        val geocoder = Geocoder(this)
        try {
            val addressList = geocoder.getFromLocation(latitude, longitude, 1)
            if (addressList != null && addressList.isNotEmpty()) {
                val address = addressList[0].getAddressLine(0)
                tvTextLocation.text = "Address: $address\nLongitude: $longitude\nLatitude: $latitude"
            } else {
                tvTextLocation.text = "Location not found\nLongitude: $longitude\nLatitude: $latitude"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            tvTextLocation.text = "Geocoder failed\nLongitude: $longitude\nLatitude: $latitude"
        }

    }


    private fun openSmsView(address: String, phoneNumber: String) {
        try {
            val smsUri = "smsto:$phoneNumber".toUri()
            val smsIntent = Intent(Intent.ACTION_SENDTO, smsUri).apply {
                putExtra("sms_body", address)
            }
            startActivity(smsIntent)
        } catch (e: Exception) {
            Log.e("SMS", "Error opening SMS view: ${e.message}")
            Toast.makeText(this, "Failed to open SMS app", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openMapView(latitude: Double, longitude: Double, address: String) {
        try {
            val intent = Intent(this, MapActivity::class.java).apply {
                putExtra("latitude", latitude)
                putExtra("longitude", longitude)
                putExtra("address", address)
            }
            startActivity(intent)
        } catch (e: Exception) {
            Log.e("Map", "Error opening Map view: ${e.message}")
            Toast.makeText(this, "Failed to open Map", Toast.LENGTH_SHORT).show()
        }
    }

}