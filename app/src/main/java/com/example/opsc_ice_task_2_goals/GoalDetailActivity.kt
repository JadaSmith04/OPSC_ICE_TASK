package com.example.opsc_ice_task_2_goals

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GoalDetailActivity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal_detail)

        dbHelper = DBHelper(this, null)

        val title = intent.getStringExtra("goal_title")
        val description = intent.getStringExtra("goal_description")
        val position = intent.getIntExtra("goal_position", -1)

        val txtTitle: TextView = findViewById(R.id.txtGoalTitle)
        val txtDescription: TextView = findViewById(R.id.txtGoalDescription)
        val btnMarkCompleted: Button = findViewById(R.id.btnMarkCompleted)

        txtTitle.text = title
        txtDescription.text = description

        btnMarkCompleted.setOnClickListener {
            if (position != -1) {
                // Get the goal ID from the database based on title and description (assuming titles are unique)
                val goalId = dbHelper.getGoalIdByTitle(title ?: "")
                if (goalId != -1) {
                    dbHelper.deleteGoal(goalId)
                    val resultIntent = Intent()
                    resultIntent.putExtra("goal_position", position)
                    setResult(RESULT_OK, resultIntent)
                    finish()
                }
            }
        }

        findViewById<Button>(R.id.btnbacking).setOnClickListener {
            finish()  // Close GoalDetailActivity and return to MainActivity
        }
    }
}
