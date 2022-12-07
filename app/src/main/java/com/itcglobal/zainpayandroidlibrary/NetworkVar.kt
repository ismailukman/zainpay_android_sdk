package com.itcglobal.zainpayandroidlibrary

class NetworkVar {

    var baseUrl = "https://sandbox.zainpay.ng"

    var urlCreateVirtualAcc = "$baseUrl/virtual-account/create/request"
    var urlCheckBalance = "$baseUrl/virtual-account/wallet/balance/7965869053"
    var urlVerifyTransaction = "$baseUrl/virtual-account/wallet/deposit/verify/Qaa1m44r0de1h61668"
    var urlTransferFund = "$baseUrl/bank/transfer/"
    var urlBankList = "$baseUrl/bank/list"
    var urlTransferNameEnquiry = "$baseUrl/bank/name-enquiry?bankCode=000013&accountNumber=0011242735"
    var urlAuth = "$baseUrl/merchant/auth/token/request"

//    URL Variables
    //"${central.urlMerchantDeleteAgent}?agentId=$userId"

}
