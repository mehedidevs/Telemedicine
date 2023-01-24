package com.ju.telemedicineju.model.diagonsis

data class Diagnosis(
    var Diagnosis_ID: String,
    var Diagnosis_Center_ID: String,
    var Patient_ID: String,
    var Diagnosis_Service_ID: String,
    var Preferred_Date: String,
    var Total_Diagnosis_Charge: String
)