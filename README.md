- 👋 Hi, I’m MD NASIF 
- 👀 I’m interested in Android development 
- 🌱 I’m currently learning Android development using Jetpack compose in Kotlin 
- 📫 Gmail: mdnasif03@gmail.com 
- 😄 Pronouns: he/him
<br>
# Location App
This Android application demonstrates how to request location permissions, get the user's current location, and display the location as an address. 
The app uses the Fused Location Provider API and the Geocoder API to retrieve and translate the location data.
# Features:
- Request location permissions from the user.
- Get the user's current location using the Fused Location Provider API.
- Translate the location coordinates into a human-readable address using the Geocoder API.
- Display the address on the screen.
# Project Structure:
The project consists of the following files:
- MainActivity.kt: This is the main activity of the app. It sets up the UI and handles user interaction.
- MyApp.kt: This composable function defines the main layout of the app.
- LocationDisplay.kt: This composable function displays the location and provides a button to get the location.
- locationViewModel.kt: This ViewModel class holds the location data and provides a method to update the location.
- LocationUtils.kt: This class handles the location permission checks, location updates, and address translation.
- LocationData.kt: This data class represents a location with latitude and longitude coordinates.
- dimens.xml: This file contains the dimension resources used in the app.
- strings.xml: This file contains the string resources used in the app.
