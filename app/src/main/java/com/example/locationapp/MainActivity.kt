package com.example.locationapp

import android.Manifest
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.locationapp.ui.theme.LocationAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            LocationAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp(viewModel: locationViewModel= viewModel()){
    val context= LocalContext.current
    val locationUtils=LocationUtils(context)
    LocationDisplay(viewModel,locationUtils = locationUtils, context =context )
}

@Composable
fun LocationDisplay(
    viewModel: locationViewModel,
    locationUtils: LocationUtils,
    context: Context
){

    val location =viewModel.location.value
    // locationDate to convert it to address
    val address=location?.let {
        locationUtils.reverseGeocodeLocation(location,context)
    }


    // request for permission. permission pop up shows
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()) {
        // Check if both permissions were granted
        if (it[Manifest.permission.ACCESS_FINE_LOCATION]==true
            || it[Manifest.permission.ACCESS_COARSE_LOCATION]==true
            ){
            //I have access the location
            locationUtils.requestLocationUpdates(viewModel)
        }else{
            // Check if a rationale is required
            /*
            A rationale is a logical argument that explains why a particular action or decision is being taken.
            It provides the reasons and justifications for a course of action.
            A rationale is often used to persuade others to accept or understand a particular point of view or decision.
             */
            val rationalRequired=ActivityCompat.shouldShowRequestPermissionRationale(
                context as MainActivity,
                Manifest.permission.ACCESS_FINE_LOCATION
            )|| ActivityCompat.shouldShowRequestPermissionRationale(
                context as MainActivity,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            // Display a toast message based on whether a rationale is required
            if (rationalRequired){
                Toast.makeText(context,"Location permission is required",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(context,"go to the setting" ,Toast.LENGTH_LONG).show()

            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (location!=null){
            Text(text = "Address:${address}")
        }else{
            Text(text = "Location not found")
        }

        Button(onClick = {
            if (locationUtils.hasLocationPermission(context)){
                locationUtils.requestLocationUpdates(viewModel)

            }else{
                //request permission
                requestPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }
        }) {
            Text(text = "Get location")
        }
    }
}

