package com.itcglobal.zainpay_android_library

class NetworkVar {

    var baseUrl = "https://sandbox.zainpay.ng"
    //var baseUrl = "https://api.zainpay.ng"

    var urlAuth = "$baseUrl/merchant/auth/token/request"

    var urlCreateVirtualAcc = "$baseUrl/virtual-account/create/request"
    var urlCheckBalance = "$baseUrl/virtual-account/wallet/balance/7965869053"
    var urlVerifyDepositTransaction = "$baseUrl/virtual-account/wallet/deposit/verify/Qaa1m44r0de1h61668"
    var urlTransferFund = "$baseUrl/bank/transfer/"
    var urlBankList = "$baseUrl/bank/list"
    var urlTransferNameEnquiry = "$baseUrl/bank/name-enquiry?bankCode=000013&accountNumber=0011242735"

    var urlCreateZainbox = "$baseUrl/zainbox/create/request"
    var urlGetAllZainboxes = "$baseUrl/zainbox/list"
    var urlGetZainboxAcc = "$baseUrl/zainbox/virtual-accounts"
    var urlUpdateVirtAccStatus = "$baseUrl/virtual-account/change/account/status"
    var urlGetVirtualAccBalInZainbox = "$baseUrl/zainbox/accounts/balance"
    var urlGetVirtualAccTransactions = "$baseUrl/virtual-account/wallet/transactions"
    var urlCreateSettlement = "$baseUrl/zainbox/settlement"
    var urlGetSettlement = "$baseUrl/zainbox/settlement"
    var urlGetTransferVerification = "$baseUrl/virtual-account/wallet/transaction/verify"
    var urlGetZainboxTnxHistory = "$baseUrl/zainbox/transactions"
    var urlGetTotalPaymentZainbox = "$baseUrl/zainbox/transfer/deposit/summary"
    var urlGetZainboxProfil = "$baseUrl/zainbox/profile"
    var urlGetMerchantTxn = "$baseUrl/zainbox/transactions"

    //Card Endpoints
    var urlInitialPayment = "$baseUrl/zainbox/card/initialize/payment"
    var urlGetTestCard = "$baseUrl/virtual-account/wallet/deposit/verify"
    var urlGetCardPaymentStatus = "$baseUrl/virtual-account/wallet/deposit/verify"


//    URL Variables
    //"${central.urlMerchantDeleteAgent}?agentId=$userId"

}
