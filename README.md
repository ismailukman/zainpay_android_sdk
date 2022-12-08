# Zainpay Android SDK

## Installation

```
implementation 'com.github.ismailukman:zainpay_android_sdk:-f34e1541eb-1'
```

# Usage

This SDK ships with helper class that will help provide global api calls settings for your Android development.<br/>
Further more, methods are provided to help overwrite the globally set configurations within the instantiated request
object.<br/>
The idea of overriding is brought to you for safe usage of this SDK within **async** environment.

**Example: Global settings**

 1. Create virtual accounts 
 2. Check balance 
 3. Verify deposit transaction 
 4. Transfer fund 
 5. Bank list 
 6. Transfer Name Enquiry 

```kotlin/java
use Zainpay\SDK\Engine;

```
