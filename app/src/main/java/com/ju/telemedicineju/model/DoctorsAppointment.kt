package com.ju.telemedicineju.model

data class DoctorsAppointment(
    var Appointment_ID: String,
    var Patient_ID: String,
    var Doctor_ID: String,
    var Preferred_Date: String,
    var Preferred_Time: String,
    var Doctor_Fee: String,
)
