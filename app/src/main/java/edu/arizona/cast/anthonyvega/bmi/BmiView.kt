package edu.arizona.cast.anthonyvega.bmi

import android.graphics.Color
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat

class BmiView: ViewModel() {

    var height = 0
    var weight = 0.0
    var bmi = 0.0
    val feet: Array<Int> = arrayOf(0,1, 2, 3, 4, 5, 6, 7, 8, 9)
    val inches: Array<Int> = arrayOf(0,1,2,3,4,5,6,7,8,9,10,11)

    fun CalculateBMI(bmiView:BmiView, calc_button:Button, weight_input:EditText, activity: MainActivity){

        calc_button.setOnClickListener {
            bmiView.weight = weight_input.text.toString().toDouble()
            bmiView.bmi = (bmiView.weight*703)/(bmiView.height*bmiView.height)
            DisplayResults(activity, activity.result)
            CheckBMI(activity.bmi_scale)
        }
    }

    fun CheckBMI(msg:TextView){

        if(bmi < 18.5 && bmi > 0){
            msg.text = "Underweight"
            msg.setTextColor(Color.parseColor("#FFFF00"))
        }
        else if(bmi > 18.5 && bmi < 24.9){
            msg.text = "Normal"
            msg.setTextColor(Color.parseColor("#32CD32"))
        }
        else if(bmi > 24.9 && bmi < 30){
            msg.text = "Overweight"
            msg.setTextColor(Color.parseColor("#FFFF00"))
        }
        else if(bmi >= 30){
            msg.text = "Obese"
            msg.setTextColor(Color.parseColor("#FF0000"))
        }else{
            msg.text = ""
        }
    }

    fun DisplayResults(activity: MainActivity, result:TextView){

        val displayResult = activity.findViewById<TextView>(R.id.result)
        val df: DecimalFormat = DecimalFormat("#.0")
        result.text = "Your BMI is: "+ df.format(bmi)
    }
}