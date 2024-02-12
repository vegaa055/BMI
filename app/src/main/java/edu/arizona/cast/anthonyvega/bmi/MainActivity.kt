package edu.arizona.cast.anthonyvega.bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    fun InitializeSpinners(bmiViewModel:BmiView){

        val feet = bmiViewModel.feet
        val inches: Array<Int> = bmiViewModel.inches
        var feetSpinner = findViewById(R.id.feet_dropDown) as Spinner
        var inchSpinner = findViewById(R.id.inches_dropDown) as Spinner

        val feetArrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            feet
        )
        val inchesArrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            inches
        )
        feetSpinner.adapter = feetArrayAdapter
        inchSpinner.adapter = inchesArrayAdapter
        GetSpinnerValue(feetSpinner, inchSpinner, feet, inches, bmiViewModel)
    }

    fun ClearFields(){

        clear_button.setOnClickListener {

            weight_input.text.clear()
            feet_dropDown.setSelection(0)
            inches_dropDown.setSelection(0)
            result.text = ""
            bmi_scale.text = ""
        }
    }

    fun GetSpinnerValue(feet:Spinner, inches:Spinner, feetVal:Array<Int>, inchesValue:Array<Int>, bmiView:BmiView){

        feet.onItemSelectedListener = object :

            AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                bmiView.height = (feetVal[p2]*12)    //feet
            }
        }
        inches.onItemSelectedListener = object:

            AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                bmiView.height += inchesValue[p2]
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val provider: ViewModelProvider = ViewModelProviders.of(this)
        val bmiViewModel = provider.get(BmiView::class.java)
        result.text = ""
        InitializeSpinners(bmiViewModel)

        bmiViewModel.CalculateBMI(bmiViewModel, calc_button, weight_input, this)
        if(bmiViewModel.bmi != 0.0){
            bmiViewModel.DisplayResults(this, result)
        }

        bmiViewModel.CheckBMI(bmi_scale)
        ClearFields()
    }
}