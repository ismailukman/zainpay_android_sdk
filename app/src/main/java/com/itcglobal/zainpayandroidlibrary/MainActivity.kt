package com.itcglobal.zainpayandroidlibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    private lateinit var utilMethods: NetworkUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Current Functionalities
        //1. Create virtual accounts ++
        //2. Check balance ++
        //3. Verify deposit transaction ++
        //4. Transfer fund ++
        //5. Bank list ++
        //6. Transfer Name Enquiry ++

        var tokenPayload = utilMethods.generateAuth()

        //var accountPayload = utilMethods.createVirtualAcc(add params)
        //var balancePayload = utilMethods.getAccBalance(params)
        //var verifyDepositePayload = utilMethods.getDepositVerification(params)
        //var transferFundPayload = utilMethods.fundTransfer(params)
        //var bankListPayload = utilMethods.getBanList()
        //var nameEnquiryPayload = utilMethods.getNameEnquiry(params)


    }
}