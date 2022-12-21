# Zainpay Android SDK

<p align="center">  
   <img title="Zainpay" height="100" src="https://github.com/shahidsani/zainpay-flutter-sdk/blob/main/zainpay.png" width="50%"/>
</p>  

## Table of Contents
1. [Introduction](#introduction)
2. [Requirements](#requirements)
3. [Installation](#installation)
4. [Usage](#usage)
5. [License](#License)
6. [Reference](#Reference)

## Introduction
For your Android development, this Android SDK includes a helper class that will assist in providing global api call settings. <br/>
Additionally, methods are offered to assist in overwriting the globally set configurations inside the request object that has been instantiated.<br/>
The idea of overriding is brought to you for safe usage of this SDK within **async** environment.

**Virtual Account Endpoints**

1. Create virtual accounts
2. Check balance
3. Verify deposit transaction
4. Transfer fund
5. Bank list
6. Transfer Name Enquiry
7. Create Zainbox
8. Get all Zainboxes
9. Get all Zainbox Accounts
10. Update Virtual Account Status
11. All Virtual Account Balance of a Zainbox
12. Virtual Account Transactions
13. Create Settlement
14. Get Settlement
15. Transfer Verification
16. Zainbox Transactions History
17. Total Payment Collected By Zainbox
18. Zainbox Profile and Current Billing Plan
19. Merchant Transactions

**Card Endpoints**

20. Initialize Payment
21. Test Cards
22. Get Card Payment Status

## Requirements

1. Android Kotlin `minsdk=21` and `targetsdk=33`
2. Implemented Kotlin version `>= 1.7.10`
3. Implemented Gradle version `= 7.3.0` 

## Installation

1. Add the dependency to your project. In your `build.gradle`, file 
```
implementation 'com.github.ismailukman:zainpay_android_sdk:1.3'
```

## Usage

To create a virtual account in zainbox, your function expect required constructor.

    fun createVirtualAcc(token: String, bank_type: String, first_name: String, surname: String,
        email: String, mobile_number: String, dob: String, gender: String,
        address: String, title:String,state:String, zainbox_code:String)

        {
            "bankType": "wemaBank",
            "firstName": "Lukman",
            "surname": "Shuaib",
            "email": "luk@gmail.com",
            "mobileNumber": "09067876540",
            "dob": "12-08-1996",
            "gender": "M",
            "address": "Jamai",
            "title": "Mr",
            "state": "Kogi",
            "zainboxCode": "THbfnDvK5o"
        }

### Handling the response

Server response for this method returns a json Object with a `reply.code`.

        {
            "code": "00",
            "data": {
            "accountName": "Shuaib Lukman ",
            "accountNumber": "4426764771",
            "bankName": "wemaBank",
            "email": "luk@gmail.com"
            },
            "description": "successful",
            "status": "200 OK"
        }

        //val server_response = jsonObject?.toString(5)
        //Log.d("createVirtualAcc Response:", server_response.toString())

#### Note:

1. `reply.code` can be null or an error response code from server .
2. You need to confirm the transaction is successful. Ensure that the txRef, amount, and status are correct and successful.
3. Be sure to verify the transaction details before providing value.

## License

By contributing to the Zainpay Android library, you agree that your contributions will be licensed under its [MIT license](/License.md)
 

## Reference
Copyright (c) [Official Zainpay API Documentation](https://zainpay.ng/developers)
