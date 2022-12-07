package com.itcglobal.zainpayandroidlibrary;

import android.content.Context;
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences;
import android.util.Patterns;
import java.text.DecimalFormat

public class PersistentVariables(context:Context)  {

    private val prefName = "SharedPrefs"
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(prefName, MODE_PRIVATE)!!

    fun formatNumberCurrency(number: String): String {
        val formatter = DecimalFormat("###,###,##0.00")
        return formatter.format(number.toDouble())
    }
    fun formatNumber(number: String): String {
        val formatter = DecimalFormat("###,###,###")
        return formatter.format(number.toDouble())
    }

    fun emailValidate(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun save(KEY_NAME: String, text: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(KEY_NAME, text)
        editor.apply()
    }

    fun save(KEY_NAME: String, value: Int) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putInt(KEY_NAME, value)
        editor.apply()
    }

    fun save(KEY_NAME: String, value: Float) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putFloat(KEY_NAME, value)
        editor.apply()
    }

    fun save(KEY_NAME: String, value: Double) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putFloat(KEY_NAME, value.toFloat())
        editor.apply()
    }

    fun save(KEY_NAME: String, status: Boolean) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean(KEY_NAME, status)
        editor.apply()
    }

    fun getValueString(KEY_NAME: String): String? {
        return sharedPreferences.getString(KEY_NAME, null)
    }

    fun getValueInt(KEY_NAME: String): Int {
        return sharedPreferences.getInt(KEY_NAME, 0)
    }

    fun getValueFloat(KEY_NAME: String): Float {
        return sharedPreferences.getFloat(KEY_NAME, 0.00F)
    }

    fun getValueBoolean(KEY_NAME: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(KEY_NAME, defaultValue)

    }

    fun clearSharedAll() {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    fun removeValue(KEY_NAME: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.remove(KEY_NAME)
        editor.apply()
    }

    fun String.capitalizeWords() = this.split(" ").joinToString(" ") { it.capitalize() }.trimEnd()

//    fun main() {
//        val str = "The quick brown fox jumps over the lazy dog"
//        print(str.capitalizeWords())
//    }


}
