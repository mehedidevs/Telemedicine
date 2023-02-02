package com.ju.telemedicineju.model.input

data class Medicine(
    var Medicine_ID: String? = "",
    var Medicine_Name: String? = "",
    var Group_Name: String? = "",
    var Brand_Name: String? = "",
    var Price: String? = "",
    var Medicine_Photo: String? = "",
    var latLong: LatLong = LatLong("","")
)