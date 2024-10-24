package com.devon.hisaabkitaab.utils

import org.koin.android.BuildConfig

object Constants {
    const val BASE_URL = "https://api.openbrewerydb.org"
    const val SPLASH_TIME: Long = 2000
    const val TAG = "Benefit-TAG"

    object PreferenceKeys {
        const val FIREBASE_TOKEN = "FIREBASE_TOKEN"
        const val LOGIN_RESPONSE = "LOGIN_RESPONSE"
    }

    object RetrofitConstants {
        const val RETROFIT_METHOD_POST = "post"
        const val RETROFIT_METHOD_GET = "get"
    }

    object Message {
        const val COMING_SOON = "Coming soon"
        const val LAST_7_DAYS = "Last 7 Days"
        const val TOP_5_CUSTOMER = "Top 5 Customer"
        const val TOP_5_CUSTOMER_SHIPMENT_WISE = "Top 5 Customer Shipment Wise"
        const val TOP_5_CUSTOMER_REVENUE_WISE = "Top 5 Customer Revenue Wise"
        const val TOP_5_CUSTOMER_WEIGHT_WISE = "Top 5 Customer Weight Wise"
    }

    object ErrorMessage {
        const val REQUIRED = "Required"
        const val pleaseEnterCNNumber = "Please enter CN Number"
    }

    object IntentKeys {
        const val TRACK_ORDER = "TRACK_ORDER"
        const val CN_NUMBER = "CN_NUMBER"
    }

    object EndPoint{

        const val breweries = "/breweries"
    }

}