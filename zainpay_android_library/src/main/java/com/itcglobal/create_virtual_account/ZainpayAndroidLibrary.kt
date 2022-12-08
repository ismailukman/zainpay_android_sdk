package com.itcglobal.create_virtual_account

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import com.itcglobal.zainpayandroidlibrary.NetworkVar
import com.itcglobal.zainpayandroidlibrary.PersistentVariables
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
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
            .url("${central.urlVerifyTransaction}/$txnRef")
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


}