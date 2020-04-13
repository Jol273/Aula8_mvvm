package com.example.aula8_mvvm

import androidx.lifecycle.ViewModel
import butterknife.OnClick
import butterknife.Optional

class CalculatorViewModel : ViewModel() {

    private val calculatorLogic = CalculatorLogic()
    var display: String = "0"

    private var listener: OnDisplayChanged? = null

    private fun notifyOnDisplayChanged(){
        listener?.onDisplayChanged(display)
    }

    fun registerListener(listener: OnDisplayChanged){
        this.listener = listener
        listener.onDisplayChanged(display)
    }

    fun unregisterListener(){ listener = null }

    fun onClickSymbol(symbol: String) {
        display = calculatorLogic.insertSymbol(display,symbol)
        notifyOnDisplayChanged()
    }

    fun onClickEquals(){
        display = calculatorLogic.performOperation(display).toString()
        notifyOnDisplayChanged()
    }

    fun onClickBackspace(){
        display = calculatorLogic.performBackspace(display)
        notifyOnDisplayChanged()
    }

    fun onClickClear(){
        display = calculatorLogic.performOperationClear()
        notifyOnDisplayChanged()
    }
    
    fun onClickLastExpression(){
        display = calculatorLogic.getLastExpression()
        notifyOnDisplayChanged()
    }

}