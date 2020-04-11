package com.example.aula8_mvvm

import androidx.lifecycle.ViewModel
import butterknife.OnClick
import butterknife.Optional

class CalculatorViewModel : ViewModel() {

    private val calculatorLogic = CalculatorLogic()
    var display: String = ""

    private var listener: OnDisplayChanged? = null

    private fun notifyOnDisplayChanged(){
        listener?.onDisplayChanged(display)
    }

    fun registerListener(listener: OnDisplayChanged){
        this.listener = listener
        listener.onDisplayChanged(display)
    }

    fun unregisterListener(){ listener = null }


    /*@Optional
    @OnClick(R.id.button_0,R.id.button_00,R.id.button_1,R.id.button_2,R.id.button_3,R.id.button_4,R.id.button_5,R.id.button_6,R.id.button_7,R.id.button_8,R.id.button_9,R.id.button_dot,R.id.button_adition,R.id.button_subtraction,R.id.button_multiplication,R.id.button_division)
    */fun onClickSymbol(symbol: String) : String {
        display = calculatorLogic.insertSymbol(display,symbol)
        return display
    }

    fun onClickEquals(): String{
        val result = calculatorLogic.performOperation(display)
        display = result.toString()
        return display
    }
}