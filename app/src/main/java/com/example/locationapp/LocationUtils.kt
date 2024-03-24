package com.example.locationapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Looper
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import java.util.Locale

class LocationUtils(
    context:Context
) {
     // get and show the location
    //it retrieve the location
    private val _fusedLocationClient:FusedLocationProviderClient=
        LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")//use this because we can not use the fun without permission
    // update the location data in viewModel
    fun requestLocationUpdates(viewModel: locationViewModel){
        val locationCallback=object: LocationCallback(){
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                locationResult.lastLocation?.let {
                    val location = LocationData(it.latitude,it.longitude)
                    viewModel.updateLocation(location)
                }
            }
        }
        //build the location request
        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,1000).build()

        // now we fused the data
        _fusedLocationClient.requestLocationUpdates(locationRequest,locationCallback, Looper.getMainLooper())
    }




    fun hasLocationPermission(context: Context):Boolean{
        return ContextCompat.checkSelfPermission(context,
            Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED
                &&
                ContextCompat.checkSelfPermission(context,
                    Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED
    }

    //translate the lat-lng to address
    fun reverseGeocodeLocation(locationData: LocationData,context: Context):String{
        /* The Geocoder object is used to convert geographic coordinates
        (latitude and longitude) into human-readable addresses.*/
        val geocoder = Geocoder(context, Locale.getDefault())
        
        //The LatLng object represents a geographical location using latitude and longitude coordinates.
        val coordinate = LatLng(locationData.latitude,locationData.longitude)

        /* The getFromLocation() method of the Geocoder object takes the latitude and
        longitude coordinates and returns a list of addresses */
        val address:MutableList<Address>?=
            geocoder.getFromLocation(coordinate.latitude,coordinate.longitude,1)

        return if (address?.isNotEmpty()==true){
            address[0].getAddressLine(0)
        }else{
            "Address not found"
        }
    }


}