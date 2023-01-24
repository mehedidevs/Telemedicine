package com.ju.telemedicineju.model

data class MedicinePurchase(
    var Medicine_Purchase_ID: String,
    var Patient_ID: String,
    var Medicine_ID: String,
    var Pharmacy_ID: String,
    var Quantity: String,
    var Price: String
)

