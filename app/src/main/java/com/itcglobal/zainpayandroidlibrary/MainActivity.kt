package com.itcglobal.zainpayandroidlibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.itcglobal.zainpay_android_library.ZainpayAndroidLibrary

class MainActivity : AppCompatActivity() {
    private lateinit var utilLibrary: ZainpayAndroidLibrary
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Zainpay Endpoints and Functionalities 19 + 3 = 22

        //Virtual Account Endpoints
        //1. Create virtual accounts ++
        //2. Check virtual acc balance ++
        //3. Deposit transaction Verification ++
        //4. Transfer fund ++
        //5. Bank list ++
        //6. Transfer Name Enquiry ++
        //7. Create Zainbox +
        //8. Get all Zainboxes +
        //9. Get all Zainbox Accounts +
        //10. Update Virtual Account Status - confirm endpoint + token ?
        //11. All Virtual Account Balance of a Zainbox - confirm endpoint + token ?
        //12. Virtual Account Transactions - zainboxCodeName /virtual-account/wallet/transactions/7965332109 + acc No
        //13. Create Settlement -  {{ baseurl }}/zainbox/settlement?zainboxCode=7eOQfvaVHvcds05eWH4t ?  + zainboxCode
        //14. Get Settlement +
        //15. Transfer Verification - /virtual-account/wallet/transaction/verify/svxgdtyGDHt67 + txnRef
        //16. Zainbox Transactions History - https://sandbox.zainpay.ng/zainbox/transactions/THbfnDvK5o zainboxCode
        //17. Total Payment Collected By Zainbox - confirm endpoint ? + zainboxCode
        //18. Zainbox Profile and Current Billing Plan - confirm endpoint ? + zainboxCode
        //19. Merchant Transactions - /zainbox/transactions?count=10 - int or string

        // Card Endpoints
        //20. Initialize Payment +
        //xx. Test Cards ++
        //21. Get Card Payment Status - confirm endpoint + token

        var tokenPayload = utilLibrary.generateAuth()

        // Test Library Functionalities
        //var newJson = utilLibrary.getBanList()


        //var accountPayload = utilMethods.createVirtualAcc(add params)
        //var balancePayload = utilMethods.getAccBalance(params)
        //var verifyDepositePayload = utilMethods.getDepositVerification(params)
        //var transferFundPayload = utilMethods.fundTransfer(params)
        //var bankListPayload = utilMethods.getBanList()
        //var nameEnquiryPayload = utilMethods.getNameEnquiry(params)


    }
}