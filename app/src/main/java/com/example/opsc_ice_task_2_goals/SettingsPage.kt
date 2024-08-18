package com.example.opsc_ice_task_2_goals

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.CompoundButton
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class SettingsPage : AppCompatActivity() {
    private lateinit var darkModeSwitch: Switch
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_page)

        findViewById<Button>(R.id.btnback).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        sharedPreferences = getSharedPreferences("SettingsPref", Context.MODE_PRIVATE)
        darkModeSwitch = findViewById(R.id.switch1)

        // Load and set the switch state based on saved preference
        darkModeSwitch.isChecked = sharedPreferences.getBoolean("dark_mode", false)

        darkModeSwitch.setOnCheckedChangeListener { _: CompoundButton, isChecked: Boolean ->
            // Save the dark mode preference
            with(sharedPreferences.edit()) {
                putBoolean("dark_mode", isChecked)
                apply()
            }

            // Apply the selected theme
            AppCompatDelegate.setDefaultNightMode(
                if (isChecked) AppCompatDelegate.MODE_NIGHT_YES
                else AppCompatDelegate.MODE_NIGHT_NO


            )
        }
    }

    override fun onResume() {
        super.onResume()
        // Apply the saved theme when the activity resumes
        val darkModeEnabled = sharedPreferences.getBoolean("dark_mode", false)
        AppCompatDelegate.setDefaultNightMode(
            if (darkModeEnabled) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )
    }
}
