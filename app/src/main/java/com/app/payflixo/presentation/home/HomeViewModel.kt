package com.app.payflixo.presentation.home


import androidx.lifecycle.ViewModel
import com.app.payflixo.R
import com.app.payflixo.presentation.home.model.ServiceCategory
import com.app.payflixo.presentation.home.model.ServiceItem

class HomeViewModel : ViewModel() {

    val banking = listOf(
        ServiceItem("AEPS Aadhaar Pay", R.drawable.ic_fingerprint),
        ServiceItem("MATM", R.drawable.ic_atm),
        ServiceItem("DMT", R.drawable.ic_transfer),
        ServiceItem("Credit Card", R.drawable.ic_credit),
        ServiceItem("Account", R.drawable.ic_account),
        ServiceItem("Loan", R.drawable.ic_loan)
    )

    val recharge = listOf(
        ServiceItem("Mobile Recharge", R.drawable.ic_fingerprint),
        ServiceItem("DTH Recharge", R.drawable.ic_fingerprint),
        ServiceItem("Electricity Bill", R.drawable.ic_fingerprint),
        ServiceItem("Water Bill", R.drawable.ic_fingerprint),
        ServiceItem("Gas Bill", R.drawable.ic_fingerprint)
    )

    val travel = listOf(
        ServiceItem("Bus Booking", R.drawable.ic_bus),
        ServiceItem("Flight Booking", R.drawable.ic_flight),
        ServiceItem("Hotel Booking", R.drawable.ic_fingerprint)
    )

    fun getServices(category: ServiceCategory) = when (category) {
        ServiceCategory.BANKING -> banking
        ServiceCategory.RECHARGE -> recharge
        ServiceCategory.TRAVEL -> travel
    }

}
