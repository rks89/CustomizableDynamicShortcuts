package com.rajnish.customizabledynamicshortcuts

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity : AppCompatActivity() {

    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.N_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val shortcutManager = getSystemService(ShortcutManager::class.java)


        val shortcut = ShortcutInfo.Builder(this, "pinned-shortcut")
            .setShortLabel("Android Auth333")
            .setLongLabel("Launch Android Authority")
            .setIcon(Icon.createWithResource(this, android.R.mipmap.sym_def_app_icon))
            .setIntent(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.androidauthority.com/")
                )
            )
            .build()

        shortcutManager.dynamicShortcuts = Arrays.asList(shortcut)
        if (shortcutManager.isRequestPinShortcutSupported())
        {
            val pinShortcutInfo =
                ShortcutInfo.Builder(this@MainActivity, "pinned-shortcut")
                    .build()
            val pinnedShortcutCallbackIntent =
                shortcutManager.createShortcutResultIntent(pinShortcutInfo)
            val successCallback = PendingIntent.getBroadcast(
                this@MainActivity, 0,
                pinnedShortcutCallbackIntent, 0
            )
            shortcutManager.requestPinShortcut(pinShortcutInfo, successCallback.intentSender)
        }

    }
}
