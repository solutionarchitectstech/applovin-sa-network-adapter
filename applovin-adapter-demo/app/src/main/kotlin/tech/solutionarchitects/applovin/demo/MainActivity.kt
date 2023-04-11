package tech.solutionarchitects.applovin.demo

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxAdViewAdListener
import com.applovin.mediation.MaxError
import com.applovin.mediation.adapters.CloseButtonType
import com.applovin.mediation.adapters.provideSolutionArchitectsBannerParams
import com.applovin.mediation.ads.MaxAdView

class MainActivity : AppCompatActivity(), MaxAdViewAdListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Look this ad unit ids here: https://dash.applovin.com/o/mediation/ad_units/
        createBannerAd("YOUR_ADUNIT", R.id.banner1)
        createBannerAd("YOUR_ADUNIT", R.id.banner2)
    }

    private var adView: MaxAdView? = null

    private fun createBannerAd(adUnit: String, bannerId: Int) {
        adView = MaxAdView(adUnit, this)
        adView?.setListener(this)
        adView?.provideSolutionArchitectsBannerParams(closeButtonType = CloseButtonType.Countdown(30))

        // Stretch to the width of the screen for banners to be fully functional
        val width = resources.getDimensionPixelSize(R.dimen.banner_width)

        // Banner height on phones and tablets is 50 and 90, respectively
        val heightPx = resources.getDimensionPixelSize(R.dimen.banner_height)

        adView?.layoutParams = FrameLayout.LayoutParams(width, heightPx)

        // Set background or background color for banners to be fully functional
        adView?.setBackgroundColor(Color.BLACK)

        val rootView = findViewById<ViewGroup>(bannerId)
        rootView.addView(adView)

        // Load the ad
        adView?.loadAd()
    }

    // MAX Ad Listener
    override fun onAdLoaded(maxAd: MaxAd) {
        Log.e("MainActivity", "Max Ad Listener: onAdLoaded | placementId: ${maxAd.placement}")
    }

    override fun onAdLoadFailed(adUnitId: String?, error: MaxError?) {
        Log.e("MainActivity", "Max Ad Listener: onAdLoadFailed | placementId: $adUnitId | message:${error?.message}")
    }

    override fun onAdDisplayFailed(ad: MaxAd?, error: MaxError?) {
        Log.e("MainActivity", "Max Ad Listener: onAdDisplayFailed | placementId: ${ad?.placement}|  with message:${error?.message}")
    }

    override fun onAdClicked(maxAd: MaxAd) {
        Log.e("MainActivity", "Max Ad Listener: onAdClicked | placementId: ${maxAd.placement}")
    }

    override fun onAdExpanded(maxAd: MaxAd) {
        Log.e("MainActivity", "Max Ad Listener: onAdExpanded | placementId: ${maxAd.placement}")
    }

    override fun onAdCollapsed(maxAd: MaxAd) {
        Log.e("MainActivity", "Max Ad Listener: onAdCollapsed | placementId: ${maxAd.placement}")
    }

    /* use this for impression tracking */
    override fun onAdDisplayed(maxAd: MaxAd) {
        Log.e("MainActivity", "Max Ad Listener: onAdDisplayed | placementId: ${maxAd.placement}")
    }

    /* DO NOT USE - THIS IS RESERVED FOR FULLSCREEN ADS ONLY AND WILL BE REMOVED IN A FUTURE SDK RELEASE */
    override fun onAdHidden(maxAd: MaxAd) {
        Log.e("MainActivity", "Max Ad Listener: onAdHidden | placementId: ${maxAd.placement}")
    }
}