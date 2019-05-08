package samkazmi.example.splash.ui

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import samkazmi.example.base.BaseActivity
import samkazmi.example.base.Navigator
import samkazmi.example.splash.R
import samkazmi.example.splash.callback.SplashCallback
import samkazmi.example.splash.databinding.ActivitySplashBinding

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class SplashActivity : BaseActivity(), SplashCallback {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_splash
        )

        supportFragmentManager.beginTransaction().add(R.id.flSplash, SplashFragment.newInstance(), "SplashFragment")
            .commit()
    }

    override fun onStartActivity(module: Navigator.Modules) {
        navigator.startModule(this, module)
        finish()
    }

}
