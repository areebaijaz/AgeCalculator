package com.example.agecalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.time.Year
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var etBirthDate: EditText
    private lateinit var etBirthMonth: EditText
    private lateinit var etBirthYear: EditText
    private lateinit var ctButton: Button
    private lateinit var tvAge: TextView

    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etBirthDate = findViewById(R.id.etBirthDate)
        etBirthMonth = findViewById(R.id.etBirthMonth)
        etBirthYear = findViewById(R.id.etBirthYear)
        ctButton = findViewById(R.id.ctButton)
        tvAge = findViewById(R.id.tvAge)

        ctButton.setOnClickListener {
            val enteredDate = etBirthDate.text.toString()
            val enteredMonth = etBirthMonth.text.toString()
            val enteredYear = etBirthYear.text.toString()
            if (enteredDate.isNotEmpty() || enteredMonth.isNotEmpty() || enteredYear.isNotEmpty()) {
                val birthDate = enteredDate.toInt()
                val birthMonth = enteredMonth.toInt()
                val birthYear = enteredYear.toInt()
                val currentyear = Calendar.getInstance().get(Calendar.YEAR)
                val currentDate = Calendar.getInstance().get(Calendar.DATE)
                val currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1
                var resultedAgeYear = currentyear - birthYear
                var resultedAgeMonth = currentMonth - birthMonth
                var resultedAgeDate = currentDate - birthDate

                if(resultedAgeMonth <0)
                {
                    resultedAgeYear -= 1
                    resultedAgeMonth += 12

                }
                if(resultedAgeDate < 0){

                    resultedAgeMonth -=1
                    val maxDayOfPrevMonth = Calendar.getInstance().apply {
                        set(currentyear,currentMonth-1,1)
                        set(Calendar.DAY_OF_MONTH,0)
//                        add(currentMonth,1)
                    }.get(Calendar.DAY_OF_MONTH)
                    resultedAgeDate += maxDayOfPrevMonth
                }
                tvAge.text = "Your age is  $resultedAgeYear years $resultedAgeDate days  and $resultedAgeMonth month  "
            } else {
                tvAge.text = getString(R.string.enter_text)
            }

        }
    }

}

