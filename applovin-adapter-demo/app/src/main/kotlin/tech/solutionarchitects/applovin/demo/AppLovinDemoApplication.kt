package tech.solutionarchitects.applovin.demo

import android.app.Application
import com.applovin.mediation.adapters.provideSolutionArchitectsInitConfig
import com.applovin.sdk.AppLovinSdk
import com.applovin.sdk.AppLovinSdk.initializeSdk

class AppLovinDemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        AppLovinSdk.getInstance(applicationContext).apply {
            mediationProvider = "max"
            settings.setVerboseLogging(true) // Optional

            provideSolutionArchitectsInitConfig(
                storeUrl = "YOUR_STORE_URL",
                uid = "YOUR_UID",
                debugMode = true,
                headers = mapOf("Authorization" to { "Bearer YOUR_BEARER_TOKEN" }),
                bannerUrl = "https://YOUR_BANNER_ENDPOINT"
            )
            initializeSdk(applicationContext)
        }
    }
}