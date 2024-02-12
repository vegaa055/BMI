package edu.arizona.cast.anthonyvega.bmi

import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModel
import java.text.DecimalFormat

class HeartRateViewModel: ViewModel(){

    var lowerTargetRate = 0.0
    var upperTargetRage = 0.0
    var maxHeartRange = 0

    fun calculateHeartRate(heartRateMessage: TextView, maxHeartRateMessage:TextView, age_input: EditText){

        val age = age_input.text.toString().toInt()

        maxHeartRange = 220 - age
        lowerTargetRate = maxHeartRange * 0.50
        upperTargetRage = maxHeartRange * 0.85
        val df: DecimalFormat = DecimalFormat("#.0")

        //println("Heart rate: "+ maxHeartRange.toString())

        var message:String = "Target Range: " + df.format(lowerTargetRate) + "-" + df.format(upperTargetRage)
        var maxRateMessage = "Max Heart Rate: " + maxHeartRange
        println(message)
        heartRateMessage.text = message
        maxHeartRateMessage.text = maxRateMessage
    }

}

