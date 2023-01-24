package com.ju.telemedicineju.data.model.input

data class Patient(
    var Patient_ID: String,
    var Full_Name: String,
    var Gender: String,
    var Date_of_Birth: String,
    var Phone_Number: String,
    var Email: String,
    var Address: String,
    var Post_Office: String,
    var Police_Station: String,
    var District: String,
    var Profile_Photo: String,
    var latLong: LatLong

)