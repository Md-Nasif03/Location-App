👋 Hi, I’m MD NASIF <br>
👀 I’m interested in Android development <br>
🌱 I’m currently learning Android development using Jetpack compose in Kotlin <br>
📫 Gmail: mdnasif03@gmail.com <br>
😄 Pronouns: he/him<br>
<br>
# Location App
This Android application demonstrates how to request location permissions, get the user's current location, and display the location as an address. 
The app uses the Fused Location Provider API and the Geocoder API to retrieve and translate the location data.<br>
# Features:
Request location permissions from the user.<br>
Get the user's current location using the Fused Location Provider API.<br>
Translate the location coordinates into a human-readable address using the Geocoder API.<br>
Display the address on the screen.<br>
# Project Structure:
The project consists of the following files:<br>
MainActivity.kt: This is the main activity of the app. It sets up the UI and handles user interaction.<br>
MyApp.kt: This composable function defines the main layout of the app.<br>
LocationDisplay.kt: This composable function displays the location and provides a button to get the location.<br>
locationViewModel.kt: This ViewModel class holds the location data and provides a method to update the location.<br>
LocationUtils.kt: This class handles the location permission checks, location updates, and address translation.<br>
LocationData.kt: This data class represents a location with latitude and longitude coordinates.<br>
dimens.xml: This file contains the dimension resources used in the app.<br>
strings.xml: This file contains the string resources used in the app.
