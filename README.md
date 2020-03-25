# Android-AzureAD-MFA
Description: Android (Kotlin) client application using WebView to authenticate a user to Azure Active Directory with MFA

# Goal
Create a native Android client to automate the authenticatng a user to Azure Active Directory with MFA. Since the Android WebView
does not support FIDO2, the initial phase is to enable TOTP as the MFA for the users but have this client automate the retrieval of 
the 6-digit OTP code from a YubiKey and paste it into the OTP field during authentication to Azure Active Directory using this login: https://myprofile.microsoft.com

# Azure Active Directory Authentication flow
There is no API for authentication so the users are reuqired to navigate the web interface. The current flow looks like this:

![alt text](AzureAD-Flow.png "Azure AD Authentication Flow on Android")

