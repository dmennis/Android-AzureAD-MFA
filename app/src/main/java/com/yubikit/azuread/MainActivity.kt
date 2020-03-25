package com.yubikit.azuread

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val azureADUrl = "https://myprofile.microsoft.com"
    // The OTP URL: "https://login.microsoftonline.com/common/SAS/ProcessAuth"

    @SuppressLint("JavascriptInterface")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the WebView settings instance
        val settings = webView.settings;

        // ### WebView Settings ###

        // Enable java script in web view
        settings.javaScriptEnabled = true
        settings.loadWithOverviewMode = true
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.domStorageEnabled = true
        settings.setSupportMultipleWindows(true)
        settings.loadWithOverviewMode = true
        settings.allowContentAccess = true
        settings.setGeolocationEnabled(true)
        settings.allowFileAccess = true
        webView.fitsSystemWindows = true

        // Load the website
        webView.loadUrl(azureADUrl)

        // Set web view client
        webView.webViewClient = object : WebViewClient() {

            // Keeping for future use, if needed
//            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
//                webView.loadUrl(azureADUrl)
//                return true
//            }

            override fun onPageStarted(view: WebView, azureADUrl: String, favicon: Bitmap?) {
                // Page loading started
                // Do something
            }

            override fun onPageFinished(view: WebView, url: String) {
                // Page loading finished
                println("Entering onPageFinished")
                val email = "USERNAME@COMPANY_NAME.onmicrosoft.com"
                val passwd = ""
                val totp = 123456

                // Enable disable back forward button

                // Email login - Auto fill-in (WORKING)
                // view.loadUrl("javascript:(function() { document.getElementById('i0116').value = '" + email + "'; ;})()")

                // Password - Auto fill-in (NOT WORKING)
                // view.loadUrl("javascript:(function() { document.getElementById('i0118').value = '" + passwd + "'; ;})()")

                // TOTP Field - Auto fill-in (NOT WORKING)
                // view.loadUrl("javascript:(function() { document.getElementById('idTxtBx_SAOTCC_OTC').value = '" + totp + "'; ;})()")
                // view.loadUrl("javascript:(function() { document.getElementByName('otc').value = '" + totp + "'; ;})()")

                // Auto select/submit "next" or "login" buttons in the UI navigation
                // view.loadUrl("javascript:(function() { var z = document.getElementById('submit').click(); })()");
            }
        }

        // Enable app settings
        //setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "This action will retrieve the 6-digit OTP from YubiKey and paste into the OTP field.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
