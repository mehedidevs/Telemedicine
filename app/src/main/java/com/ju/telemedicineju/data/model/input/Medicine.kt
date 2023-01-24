package com.ju.telemedicineju.data.model.input

data class Medicine(
    var Medicine_ID: String,
    var Group_Name: String,
    var Brand_Name: String,
    var Price: String,

    var latLong: LatLong
)