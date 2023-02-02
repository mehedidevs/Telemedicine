package com.ju.telemedicineju.model.input

data class LocalPractitioner(
    var  Practitioner_ID : String? = "",
    var Full_Name: String? = "",
    var Gender: String? = "",
    var Qualification: String? = "",
    var Phone_Number: String? = "",
    var Email: String? = "",
    var Address: String? = "",
    var Chamber_Location_Map: String? = "",
    var Profile_Photo: String? = "",
    var latLong: LatLong = LatLong("","")

)
