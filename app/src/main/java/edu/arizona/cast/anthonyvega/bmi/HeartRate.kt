package edu.arizona.cast.anthonyvega.bmi

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_heart_rate.*
import java.text.DecimalFormat


class HeartRate : AppCompatActivity() {

    fun ClearFields(heartRateMessage: TextView, maxRateMessage:TextView, age_input: EditText){
        val clearBtn:Button = findViewById(R.id.clear_Button)
        val provider: ViewModelProvider = ViewModelProviders.of(this)
        val heartRateViewModel = provider.get(HeartRateViewModel::class.java)
        clearBtn.setOnClickListener {
            heartRateMessage.text = ""
            maxRateMessage.text = ""
            age_input.text.clear()
            heartRateViewModel.maxHeartRange = 0
        }
    }

    fun SetMessage(heartRateViewModel: HeartRateViewModel, heartRateMessage: TextView, maxHeartRateMessage: TextView){
        val df: DecimalFormat = DecimalFormat("#.0")
        var message:String = "Target Range: " + df.format(heartRateViewModel.lowerTargetRate) + "-" + df.format(heartRateViewModel.upperTargetRage)
        var maxRateMessage = "Max Heart Rate: " + heartRateViewModel.maxHeartRange
        if(heartRateViewModel.maxHeartRange != 0){
            heartRateMessage.text = message
            maxHeartRateMessage.text = maxRateMessage
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heart_rate)

        val provider: ViewModelProvider = ViewModelProviders.of(this)
        val heartRateViewModel = provider.get(HeartRateViewModel::class.java)

        val intent = getIntent()
        val age: Int = intent.getIntExtra("user_age", 0)
        if(age!=0){
            age_input.setText(age.toString())
        }


        val calc_btn: Button = findViewById(R.id.calc_heart_rate_Button)
        val heartRateMessage:TextView = findViewById(R.id.heart_rate_Text)
        val maxHeartRateMessage:TextView = findViewById(R.id.max_heart_rate_Text)
        val age_input = findViewById<EditText>(R.id.age_input)

        SetMessage(heartRateViewModel, heartRateMessage, maxHeartRateMessage)

        calc_btn.setOnClickListener {
            heartRateViewModel.calculateHeartRate(heartRateMessage,maxHeartRateMessage, age_input)
            var thisAge = age_input.text.toString().toInt()
            val newIntent = Intent()
            newIntent.putExtra("user_age_fromChild", thisAge)
            setResult(Activity.RESULT_OK, newIntent)
            
        }
        ClearFields(heartRateMessage, maxHeartRateMessage, age_input)
    }
}