package com.example.opsc_ice_task_2_goals

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    data class Goal(val title: String, val description: String)

    private lateinit var goalAdapter: GoalAdapter
    private val goalList = mutableListOf<Goal>()
    private lateinit var dbHelper: DBHelper

    companion object {
        const val REQUEST_CODE_GOAL_DETAIL = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DBHelper(this, null)
        loadGoalsFromDatabase()

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        goalAdapter = GoalAdapter(goalList)
        recyclerView.adapter = goalAdapter

        val btnAddGoal: Button = findViewById(R.id.btnAddGoal)
        val txtGoalTitle: EditText = findViewById(R.id.txtGoalTitle)
        val txtGoalDescription: EditText = findViewById(R.id.txtGoalDescription)

        btnAddGoal.setOnClickListener {
            val title = txtGoalTitle.text.toString()
            val description = txtGoalDescription.text.toString()

            if (title.isNotEmpty() && description.isNotEmpty()) {
                dbHelper.addGoal(title, description)
                goalList.add(Goal(title, description))
                goalAdapter.notifyItemInserted(goalList.size - 1)
                txtGoalTitle.text.clear()
                txtGoalDescription.text.clear()
            }
        }

        findViewById<Button>(R.id.btnsettings).setOnClickListener {
            val intent = Intent(this, SettingsPage::class.java)
            startActivity(intent)
        }
    }

    private fun loadGoalsFromDatabase() {
        val goalsFromDb = dbHelper.getAllGoals()
        goalList.clear()
        goalList.addAll(goalsFromDb)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_GOAL_DETAIL && resultCode == RESULT_OK) {
            val position = data?.getIntExtra("goal_position", -1) ?: return
            if (position != -1) {
                goalList.removeAt(position)
                goalAdapter.notifyItemRemoved(position)
            }
        }
    }
}
