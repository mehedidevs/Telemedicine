package com.ju.telemedicineju.model.input

data class DiagnosisCenter(
    var Diagnosis_Center_ID: String? = "",
    var Name: String? = "",
    var Phone_Number: String? = "",
    var Email: String? = "",
    var Address: String? = "",
    var Post_Office: String? = "",
    var Police_Station: String? = "",
    var District: String? = "",
    var Profile_Photo: String? = "",
    var latLong: LatLong = LatLong("","")

)
