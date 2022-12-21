package com.itcglobal.zainpay_android_library

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class ZainpayAndroidLibrary(private val context: Context) {

    private var urlApp = ""
    val shared = PersistentVariables(context)
    private val central = NetworkVar() //APIVarSandBox()
    private val mediaType: MediaType = "application/json; charset=utf-8".toMediaType()
    private val client = OkHttpClient()


    @RequiresApi(Build.VERSION_CODES.M)
    fun isOnline(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if(capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    //Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    //Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    //Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }

    private fun checkNetworkConnection(context: Context): Boolean {
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo
        val isConnected: Boolean = networkInfo?.isConnected() ?: false
        if (networkInfo != null && isConnected) {
            // show "Connected" & type of network "WIFI or MOBILE"
            //networkInfo.typeName
        } else {
            // show "Not Connected"
        }
        return isConnected
    }

    @Throws(JSONException::class)
    fun buildAuth(): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.accumulate("username","test")
        jsonObject.accumulate("secret", "yZCDMaXJbssdkvXnissPtsv")
        return jsonObject
    }

    @Throws(JSONException::class)
    fun buildCreateVirtualAcc(bank_type: String, first_name: String, surname: String,
                              email: String, mobile_number: String, dob: String, gender: String,
                              address: String, title:String, state:String, zainbox_code:String): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.accumulate("bankType", bank_type )
        jsonObject.accumulate("firstName",  first_name)
        jsonObject.accumulate("surname",  surname)
        jsonObject.accumulate("email", email)
        jsonObject.accumulate("mobileNumber", mobile_number)
        jsonObject.accumulate("dob",  dob)
        jsonObject.accumulate("gender",  gender)
        jsonObject.accumulate("address",  address)
        jsonObject.accumulate("title",  title)
        jsonObject.accumulate("state", state)
        jsonObject.accumulate("zainboxCode", zainbox_code)
        return jsonObject
    }


    @Throws(JSONException::class)
    fun buildFundTransfer(dest_accNo: String, dest_bankCode: String, amount: String, source_accNo: String,
                          source_bankCode: String, zainbox_code: String, txnRef: String, narration: String): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.accumulate("destinationAccountNumber", dest_accNo )
        jsonObject.accumulate("destinationBankCode",  dest_bankCode)
        jsonObject.accumulate("amount",  amount)
        jsonObject.accumulate("sourceAccountNumber", source_accNo)
        jsonObject.accumulate("sourceBankCode", source_bankCode)
        jsonObject.accumulate("zainboxCode",  zainbox_code)
        jsonObject.accumulate("txnRef",  txnRef)
        jsonObject.accumulate("narration",  narration)
        return jsonObject
    }

    @Throws(JSONException::class)
    fun buildCreateZainbox(name: String, tag: String, callbackurl: String, email: String): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.accumulate("name", name )
        jsonObject.accumulate("tags",  tag)
        jsonObject.accumulate("callbackUrl",  callbackurl)
        jsonObject.accumulate("email", email)
        return jsonObject
    }

    fun buildUpdateVirtAccStatus(zainboxCode: String, accNo: String, status: String): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.accumulate("zainboxCode", zainboxCode )
        jsonObject.accumulate("accountNumber",  accNo)
        jsonObject.accumulate("status",  status)
        return jsonObject
    }
//
//    {
//        "name": "new-daily-settlement3",
//        "zainboxCode": "7eOQfvaVHvcds05eWH4t",
//        "scheduleType": "T1",
//        "schedulePeriod": "Daily",
//        "settlementAccountList":
//        [
//            {
//                "accountNumber":"1234567890",
//                "bankCode":"0009",
//                "percentage": "20"
//            }, {
//            "accountNumber":"1234567890",
//            "bankCode":"0009",
//            "percentage": "80"
//        }],
//        "status": true
//    }

    fun buildCreateSettlement(name:String,zainboxCode: String, scheduleType: String, schedulePeriod: String, accNo1:String,
                              bankCode1:String, percent1:String, status:String): JSONObject {
        val jsonObject = JSONObject()
        val bodyArray = JSONArray()

        jsonObject.accumulate("name", name )
        jsonObject.accumulate("zainboxCode",  zainboxCode)
        jsonObject.accumulate("scheduleType",  scheduleType)
        jsonObject.accumulate("schedulePeriod",  schedulePeriod)

        val element1 = JSONObject()
        element1.accumulate("accountNumber", accNo1)
        element1.accumulate("bankCode", bankCode1)
        element1.accumulate("percentage", percent1)

        bodyArray.put(element1)
        jsonObject.put("settlementAccountList", bodyArray)
        jsonObject.accumulate("status",  status)

        return jsonObject
    }

    fun buildCreateSettlement(name:String,zainboxCode: String, scheduleType: String, schedulePeriod: String, accNo1:String,
                              bankCode1:String, percent1:String, accNo2:String, bankCode2:String, percent2:String, status:String): JSONObject {
        val jsonObject = JSONObject()
        val bodyArray = JSONArray()

        jsonObject.accumulate("name", name )
        jsonObject.accumulate("zainboxCode",  zainboxCode)
        jsonObject.accumulate("scheduleType",  scheduleType)
        jsonObject.accumulate("schedulePeriod",  schedulePeriod)

        val element1 = JSONObject()
        element1.accumulate("accountNumber", accNo1)
        element1.accumulate("bankCode", bankCode1)
        element1.accumulate("percentage", percent1)
        val element2 = JSONObject()
        element2.accumulate("accountNumber", accNo2)
        element2.accumulate("bankCode", bankCode2)
        element2.accumulate("percentage", percent2)

        bodyArray.put(element1)
        bodyArray.put(element2)
        jsonObject.put("settlementAccountList", bodyArray)
        jsonObject.accumulate("status",  status)

        return jsonObject
    }


    fun buildInitializePayment(amount:String,txnRef: String, mobileNumber: String, zainboxCode: String, emailAddress:String,
                               callbackurl:String, ): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.accumulate("amount", amount )
        jsonObject.accumulate("txnRef",  txnRef)
        jsonObject.accumulate("mobileNumber",  mobileNumber)
        jsonObject.accumulate("zainboxCode",  zainboxCode)
        jsonObject.accumulate("emailAddress",  emailAddress)
        jsonObject.accumulate("callBackUrl",  callbackurl)
        return jsonObject
    }

    fun generateAuth() : JSONObject? {
        val jsonObject: JSONObject?
        urlApp = central.urlAuth
        val myJSONObject = buildAuth()
        val requestBody = myJSONObject.toString().toRequestBody(mediaType)
        val request = Request.Builder()
            //.addHeader("Authorization", "Bearer $token")
            .method("POST", requestBody)
            .url(urlApp)
            .build()

        jsonObject = try {
            val reply = client.newCall(request).execute()
            val result = reply.body!!.string()
            when (reply.code) {
                201 -> {
                    JSONObject(result)
                }
                200 -> {
                    JSONObject(result)
                }
                else -> {
                    JSONObject(result)
                }
            }
        } catch (e: IOException){
            e.printStackTrace()
            null
        }
        return jsonObject
    }

    fun createVirtualAcc(token: String, bank_type: String, first_name: String, surname: String,
                         email: String, mobile_number: String, dob: String, gender: String,
                         address: String, title:String,state:String, zainbox_code:String) : JSONObject? {
        val jsonObject: JSONObject?
        urlApp = central.urlCreateVirtualAcc
        val myJSONObject = buildCreateVirtualAcc(bank_type, first_name, surname, email, mobile_number, dob, gender, address, title, state, zainbox_code)
        val requestBody = myJSONObject.toString().toRequestBody(mediaType)
        val request = Request.Builder()
            .addHeader("Authorization", "Bearer $token")
            .method("POST", requestBody)
            .url(urlApp)
            .build()
        //Log.d("create virtual acc", "${urlApp}$id")
        jsonObject = try {
            val reply = client.newCall(request).execute()
            val result = reply.body!!.string()
            when (reply.code) {
                201 -> {
                    JSONObject(result)
                }
                200 -> {
                    JSONObject(result)
                }
                else -> {
                    JSONObject(result)
                }
            }
        } catch (e: IOException){
            e.printStackTrace()
            null
        }
        return jsonObject
    }


    fun getAccBalance(token: String, accNo:String) : JSONObject? {
        val jsonObject: JSONObject?
        val request = Request.Builder()
            .addHeader("Authorization", "Bearer $token")
            .method("GET", null)
            .url("${central.urlCheckBalance}/$accNo")
            .build()
        //Log.d("Get Tax URL", central.url_taxOffice)
        jsonObject = try {
            val reply = client.newCall(request).execute()
            val result = reply.body!!.string()
            when (reply.code) {
                201 -> {
                    JSONObject(result)
                }
                200 -> {
                    JSONObject(result)
                }
                else -> {
                    JSONObject(result)
                }
            }
        } catch (e: IOException){
            e.printStackTrace()
            null
            //Log.e("IOException:", e)
        }
        //val message = jsonObject?.toString(5)
        //Log.d("Get TaxOffice:", message.toString())
        return jsonObject
    }

    fun getDepositVerification(token: String, txnRef: String) : JSONObject? {
        val jsonObject: JSONObject?
        val request = Request.Builder()
            .addHeader("Authorization", "Bearer $token")
            .method("GET", null)
            .url("${central.urlVerifyDepositTransaction}/$txnRef")
            .build()
        //Log.d("urlVerifyTransaction", central.urlVerifyTransaction)
        jsonObject = try {
            val reply = client.newCall(request).execute()
            val result = reply.body!!.string()
            when (reply.code) {
                201 -> {
                    JSONObject(result)
                }
                200 -> {
                    JSONObject(result)
                }
                else -> {
                    JSONObject(result)
                }
            }
        } catch (e: IOException){
            e.printStackTrace()
            null
            //Log.e("IOException:", e)
        }
        //val message = jsonObject?.toString(5)
        return jsonObject
    }

    fun fundTransfer(token: String, dest_accNo: String, dest_bankCode: String, amount: String, source_accNo: String,
                     source_bankCode: String, zainbox_code: String, txnRef: String, narration: String) : JSONObject? {
        val jsonObject: JSONObject?
        urlApp = central.urlTransferFund
        val myJSONObject = buildFundTransfer(dest_accNo, dest_bankCode, amount, source_accNo, source_bankCode, zainbox_code, txnRef, narration)
        val requestBody = myJSONObject.toString().toRequestBody(mediaType)
        val request = Request.Builder()
            .addHeader("Authorization", "Bearer $token")
            .method("POST", requestBody)
            .url(urlApp)
            .build()
        //Log.d("create virtual acc", "${urlApp}$id")
        jsonObject = try {
            val reply = client.newCall(request).execute()
            val result = reply.body!!.string()
            when (reply.code) {
                201 -> {
                    JSONObject(result)
                }
                200 -> {
                    JSONObject(result)
                }
                else -> {
                    JSONObject(result)
                }
            }
        } catch (e: IOException){
            e.printStackTrace()
            null
        }
        return jsonObject
    }

    fun getBanList() : JSONObject? {
        val jsonObject: JSONObject?
        val request = Request.Builder()
            .method("GET", null)
            .url(central.urlBankList)
            .build()
        //Log.d("Get Tax URL", central.url_taxOffice)
        jsonObject = try {
            val reply = client.newCall(request).execute()
            val result = reply.body!!.string()
            when (reply.code) {
                201 -> {
                    JSONObject(result)
                }
                200 -> {
                    JSONObject(result)
                }
                else -> {
                    JSONObject(result)
                }
            }
        } catch (e: IOException){
            e.printStackTrace()
            null
            //Log.e("IOException:", e)
        }
        //val message = jsonObject?.toString(5)
        return jsonObject
    }


    fun getNameEnquiry(token: String, accNo: String, bankCode: String) : JSONObject? {
        val jsonObject: JSONObject?
        val request = Request.Builder()
            .addHeader("Authorization", "Bearer $token")
            .method("GET", null)
            .url("${central.urlTransferNameEnquiry}?bankCode=$bankCode&accountNumber=$accNo")
            .build() //"${central.urlMerchantDeleteAgent}?agentId=$userId"
        //Log.d("Get Tax URL", central.url_taxOffice)
        jsonObject = try {
            val reply = client.newCall(request).execute()
            val result = reply.body!!.string()
            when (reply.code) {
                201 -> {
                    JSONObject(result)
                }
                200 -> {
                    JSONObject(result)
                }
                else -> {
                    JSONObject(result)
                }
            }
        } catch (e: IOException){
            e.printStackTrace()
            null
            //Log.e("IOException:", e)
        }
        //val message = jsonObject?.toString(5)
        return jsonObject
    }


    fun createZainbox(token: String, name: String, tag: String, callbackurl: String, email: String) : JSONObject? {
        val jsonObject: JSONObject?
        urlApp = central.urlCreateZainbox
        val myJSONObject = buildCreateZainbox(name, tag, callbackurl, email)
        val requestBody = myJSONObject.toString().toRequestBody(mediaType)
        val request = Request.Builder()
            .addHeader("Authorization", "Bearer $token")
            .method("POST", requestBody)
            .url(urlApp)
            .build()
        //Log.d("create virtual acc", "${urlApp}$id")
        jsonObject = try {
            val reply = client.newCall(request).execute()
            val result = reply.body!!.string()
            when (reply.code) {
                201 -> {
                    JSONObject(result)
                }
                200 -> {
                    JSONObject(result)
                }
                else -> {
                    JSONObject(result)
                }
            }
        } catch (e: IOException){
            e.printStackTrace()
            null
        }
        return jsonObject
    }

    fun getAllZainboxes(token: String) : JSONObject? {
        val jsonObject: JSONObject?
        val request = Request.Builder()
            .addHeader("Authorization", "Bearer $token")
            .method("GET", null)
            .url(central.urlGetAllZainboxes)
            .build()
        jsonObject = try {
            val reply = client.newCall(request).execute()
            val result = reply.body!!.string()
            when (reply.code) {
                201 -> {
                    JSONObject(result)
                }
                200 -> {
                    JSONObject(result)
                }
                else -> {
                    JSONObject(result)
                }
            }
        } catch (e: IOException){
            e.printStackTrace()
            null
            //Log.e("IOException:", e)
        }
        //val message = jsonObject?.toString(5)
        //Log.d("GetAllZainboxes:", message.toString())
        return jsonObject
    }


    fun getZainboxeAcc(token: String, zainboxCodeName:String) : JSONObject? {
        val jsonObject: JSONObject?
        val request = Request.Builder()
            .addHeader("Authorization", "Bearer $token")
            .method("GET", null)
            .url("${central.urlGetZainboxAcc}/$zainboxCodeName")
            .build()
        jsonObject = try {
            val reply = client.newCall(request).execute()
            val result = reply.body!!.string()
            when (reply.code) {
                201 -> {
                    JSONObject(result)
                }
                200 -> {
                    JSONObject(result)
                }
                else -> {
                    JSONObject(result)
                }
            }
        } catch (e: IOException){
            e.printStackTrace()
            null
            //Log.e("IOException:", e)
        }
        //val message = jsonObject?.toString(5)
        //Log.d("GetAllZainboxes:", message.toString())
        return jsonObject
    }

    fun updateVirtAccStatus(token: String, zainboxCode: String, accNo: String, status: String) : JSONObject? {
        val jsonObject: JSONObject?
        urlApp = central.urlUpdateVirtAccStatus
        val myJSONObject = buildUpdateVirtAccStatus(zainboxCode, accNo, status)
        val requestBody = myJSONObject.toString().toRequestBody(mediaType)
        val request = Request.Builder()
            .addHeader("Authorization", "Bearer $token")
            .method("POST", requestBody)
            .url(urlApp)
            .build()
        //Log.d("create virtual acc", "${urlApp}$id")
        jsonObject = try {
            val reply = client.newCall(request).execute()
            val result = reply.body!!.string()
            when (reply.code) {
                201 -> {
                    JSONObject(result)
                }
                200 -> {
                    JSONObject(result)
                }
                else -> {
                    JSONObject(result)
                }
            }
        } catch (e: IOException){
            e.printStackTrace()
            null
        }
        return jsonObject
    }

    //zainbox/accounts/balance/THbfnDvK5o

    fun getVirtualAccBalInZainbox(token: String, zainboxCodeName:String) : JSONObject? {
        val jsonObject: JSONObject?
        val request = Request.Builder()
            .addHeader("Authorization", "Bearer $token")
            .method("GET", null)
            .url("${central.urlGetVirtualAccBalInZainbox}/$zainboxCodeName")
            .build()
        jsonObject = try {
            val reply = client.newCall(request).execute()
            val result = reply.body!!.string()
            when (reply.code) {
                201 -> {
                    JSONObject(result)
                }
                200 -> {
                    JSONObject(result)
                }
                else -> {
                    JSONObject(result)
                }
            }
        } catch (e: IOException){
            e.printStackTrace()
            null
            //Log.e("IOException:", e)
        }
        //val message = jsonObject?.toString(5)
        //Log.d("getVirtualAccBalInZainbox:", message.toString())
        return jsonObject
    }

    fun getVirtualAccTransactions(token: String, zainboxCodeName:String) : JSONObject? {
        val jsonObject: JSONObject?
        val request = Request.Builder()
            .addHeader("Authorization", "Bearer $token")
            .method("GET", null)
            .url("${central.urlGetVirtualAccTransactions}/$zainboxCodeName")
            .build()
        jsonObject = try {
            val reply = client.newCall(request).execute()
            val result = reply.body!!.string()
            when (reply.code) {
                201 -> {
                    JSONObject(result)
                }
                200 -> {
                    JSONObject(result)
                }
                else -> {
                    JSONObject(result)
                }
            }
        } catch (e: IOException){
            e.printStackTrace()
            null
            //Log.e("IOException:", e)
        }
        //val message = jsonObject?.toString(5)
        //Log.d("getVirtualAccBalInZainbox:", message.toString())
        return jsonObject
    }


    fun createSettlement(token: String, name: String, zainboxCode: String, scheduleType: String, schedulePeriod: String,
                         accountNumber:String, bankCode:String, percentage:String, status: String) : JSONObject? {
        val jsonObject: JSONObject?
        urlApp = central.urlCreateSettlement
        val myJSONObject = buildCreateSettlement(name, zainboxCode, scheduleType, schedulePeriod,accountNumber, bankCode, percentage, status)
        val requestBody = myJSONObject.toString().toRequestBody(mediaType)
        val request = Request.Builder()
            .addHeader("Authorization", "Bearer $token")
            .method("POST", requestBody)
            .url(urlApp)
            .build()
        //Log.d("create virtual acc", "${urlApp}$id")
        jsonObject = try {
            val reply = client.newCall(request).execute()
            val result = reply.body!!.string()
            when (reply.code) {
                201 -> {
                    JSONObject(result)
                }
                200 -> {
                    JSONObject(result)
                }
                else -> {
                    JSONObject(result)
                }
            }
        } catch (e: IOException){
            e.printStackTrace()
            null
        }
        return jsonObject
    }

    fun createSettlement(token: String, name: String, zainboxCode: String, scheduleType: String, schedulePeriod: String,
                         accountNumber:String, bankCode:String, percentage:String, accountNumber2:String, bankCode2:String, percentage2:String,
                         status: String) : JSONObject? {
        val jsonObject: JSONObject?
        urlApp = central.urlCreateSettlement
        val myJSONObject = buildCreateSettlement(name, zainboxCode, scheduleType, schedulePeriod,accountNumber, bankCode, percentage,
            accountNumber2, bankCode2, percentage2, status)
        val requestBody = myJSONObject.toString().toRequestBody(mediaType)
        val request = Request.Builder()
            .addHeader("Authorization", "Bearer $token")
            .method("POST", requestBody)
            .url(urlApp)
            .build()
        //Log.d("create virtual acc", "${urlApp}$id")
        jsonObject = try {
            val reply = client.newCall(request).execute()
            val result = reply.body!!.string()
            when (reply.code) {
                201 -> {
                    JSONObject(result)
                }
                200 -> {
                    JSONObject(result)
                }
                else -> {
                    JSONObject(result)
                }
            }
        } catch (e: IOException){
            e.printStackTrace()
            null
        }
        return jsonObject
    }

    ///zainbox/settlement?zainboxCode=7eOQfvaVHvcds05eWH4t
    fun getSettlement(token: String, zainboxCode:String) : JSONObject? {
        val jsonObject: JSONObject?
        val request = Request.Builder()
            .addHeader("Authorization", "Bearer $token")
            .method("GET", null)
            .url("${central.urlGetSettlement}?zainboxCode=$zainboxCode")
            .build()
        jsonObject = try {
            val reply = client.newCall(request).execute()
            val result = reply.body!!.string()
            when (reply.code) {
                201 -> {
                    JSONObject(result)
                }
                200 -> {
                    JSONObject(result)
                }
                else -> {
                    JSONObject(result)
                }
            }
        } catch (e: IOException){
            e.printStackTrace()
            null
            //Log.e("IOException:", e)
        }
        //val message = jsonObject?.toString(5)
        //Log.d("getSettlement:", message.toString())
        return jsonObject
    }

    fun getTransferVerification(token: String, txnRef:String) : JSONObject? {
        val jsonObject: JSONObject?
        val request = Request.Builder()
            .addHeader("Authorization", "Bearer $token")
            .method("GET", null)
            .url("${central.urlGetTransferVerification}/$txnRef")
            .build()
        jsonObject = try {
            val reply = client.newCall(request).execute()
            val result = reply.body!!.string()
            when (reply.code) {
                201 -> {
                    JSONObject(result)
                }
                200 -> {
                    JSONObject(result)
                }
                else -> {
                    JSONObject(result)
                }
            }
        } catch (e: IOException){
            e.printStackTrace()
            null
            //Log.e("IOException:", e)
        }
        //val message = jsonObject?.toString(5)
        //Log.d("getVirtualAccBalInZainbox:", message.toString())
        return jsonObject
    }

    fun getZainboxTnxHistory(token: String, zainboxCode:String) : JSONObject? {
        val jsonObject: JSONObject?
        val request = Request.Builder()
            .addHeader("Authorization", "Bearer $token")
            .method("GET", null)
            .url("${central.urlGetZainboxTnxHistory}/$zainboxCode")
            .build()
        jsonObject = try {
            val reply = client.newCall(request).execute()
            val result = reply.body!!.string()
            when (reply.code) {
                201 -> {
                    JSONObject(result)
                }
                200 -> {
                    JSONObject(result)
                }
                else -> {
                    JSONObject(result)
                }
            }
        } catch (e: IOException){
            e.printStackTrace()
            null
            //Log.e("IOException:", e)
        }
        //val message = jsonObject?.toString(5)
        //Log.d("getVirtualAccBalInZainbox:", message.toString())
        return jsonObject
    }

     // /zainbox/transfer/deposit/summary/THbfnDvK5o?dateFrom=2022-02&dateTo=2022-03
    fun getTotalPayementZainbox(token: String, zainboxCode:String, dateFrom:String, dateTo:String) : JSONObject? {
        val jsonObject: JSONObject?
        val request = Request.Builder()
            .addHeader("Authorization", "Bearer $token")
            .method("GET", null)
            .url("${central.urlGetTotalPaymentZainbox}/${zainboxCode}?dateFrom=$dateFrom&dateTo=$dateTo")
            .build()
        jsonObject = try {
            val reply = client.newCall(request).execute()
            val result = reply.body!!.string()
            when (reply.code) {
                201 -> {
                    JSONObject(result)
                }
                200 -> {
                    JSONObject(result)
                }
                else -> {
                    JSONObject(result)
                }
            }
        } catch (e: IOException){
            e.printStackTrace()
            null
            //Log.e("IOException:", e)
        }
        //val message = jsonObject?.toString(5)
        //Log.d("getVirtualAccBalInZainbox:", message.toString())
        return jsonObject
    }

    // /zainbox/profile/THbfnDvK5o
    fun getZainboxProfile(token: String, zainboxCode:String) : JSONObject? {
        val jsonObject: JSONObject?
        val request = Request.Builder()
            .addHeader("Authorization", "Bearer $token")
            .method("GET", null)
            .url("${central.urlGetZainboxProfil}/$zainboxCode")
            .build()
        jsonObject = try {
            val reply = client.newCall(request).execute()
            val result = reply.body!!.string()
            when (reply.code) {
                201 -> {
                    JSONObject(result)
                }
                200 -> {
                    JSONObject(result)
                }
                else -> {
                    JSONObject(result)
                }
            }
        } catch (e: IOException){
            e.printStackTrace()
            null
            //Log.e("IOException:", e)
        }
        //val message = jsonObject?.toString(5)
        //Log.d("getVirtualAccBalInZainbox:", message.toString())
        return jsonObject
    }

    // /zainbox/transactions?count=10
     fun getMerchantTransaction(token: String, count:String) : JSONObject? {
         val jsonObject: JSONObject?
         val request = Request.Builder()
             .addHeader("Authorization", "Bearer $token")
             .method("GET", null)
             .url("${central.urlGetMerchantTxn}?count=$count")
             .build()
         jsonObject = try {
             val reply = client.newCall(request).execute()
             val result = reply.body!!.string()
             when (reply.code) {
                 201 -> {
                     JSONObject(result)
                 }
                 200 -> {
                     JSONObject(result)
                 }
                 else -> {
                     JSONObject(result)
                 }
             }
         } catch (e: IOException){
             e.printStackTrace()
             null
             //Log.e("IOException:", e)
         }
         //val message = jsonObject?.toString(5)
         //Log.d("getVirtualAccBalInZainbox:", message.toString())
         return jsonObject
     }

    fun initialCardPayment(token: String, amount: String, txnRef: String, mobileNumber: String, zainboxCode:String, email:String, callbackurl:String) : JSONObject? {
        val jsonObject: JSONObject?
        urlApp = central.urlInitialPayment
        val myJSONObject = buildInitializePayment(amount, txnRef, mobileNumber, zainboxCode, email, callbackurl)
        val requestBody = myJSONObject.toString().toRequestBody(mediaType)
        val request = Request.Builder()
            .addHeader("Authorization", "Bearer $token")
            .method("POST", requestBody)
            .url(urlApp)
            .build()
        //Log.d("create virtual acc", "${urlApp}$id")
        jsonObject = try {
            val reply = client.newCall(request).execute()
            val result = reply.body!!.string()
            when (reply.code) {
                201 -> {
                    JSONObject(result)
                }
                200 -> {
                    JSONObject(result)
                }
                else -> {
                    JSONObject(result)
                }
            }
        } catch (e: IOException){
            e.printStackTrace()
            null
        }
        return jsonObject
    }

    // /virtual-account/wallet/deposit/verify/{txnRef}

    fun getCardPaymentStatus(token: String, txnRef:String) : JSONObject? {
        val jsonObject: JSONObject?
        val request = Request.Builder()
            .addHeader("Authorization", "Bearer $token")
            .method("GET", null)
            .url("${central.urlGetCardPaymentStatus}/$txnRef")
            .build()
        jsonObject = try {
            val reply = client.newCall(request).execute()
            val result = reply.body!!.string()
            when (reply.code) {
                201 -> {
                    JSONObject(result)
                }
                200 -> {
                    JSONObject(result)
                }
                else -> {
                    JSONObject(result)
                }
            }
        } catch (e: IOException){
            e.printStackTrace()
            null
            //Log.e("IOException:", e)
        }
        //val message = jsonObject?.toString(5)
        //Log.d("getVirtualAccBalInZainbox:", message.toString())
        return jsonObject
    }



}