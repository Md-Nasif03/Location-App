package com.example.locationapp


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class locationViewModel:ViewModel(){

    //update location data
    private val _location= mutableStateOf<LocationData?>(null)
    var location: State<LocationData?> = _location

    fun updateLocation(locationData: LocationData){
        _location.value=locationData
    }
}