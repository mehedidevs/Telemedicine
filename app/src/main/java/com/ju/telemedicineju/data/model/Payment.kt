package com.ju.telemedicineju.data.model

data class Payment(
    var Payment_ID: String,
    var Patient_ID: String,
    var Payment_Against: String,
    var Medicine_Purchase_ID: String,
    var Diagnosis_ID: String,
    var Appointment_ID: String,
    var Amount: String,
    var Payment_Method: String
)

