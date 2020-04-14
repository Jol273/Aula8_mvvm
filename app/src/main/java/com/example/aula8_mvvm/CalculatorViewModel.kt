package com.example.aula8_mvvm

import androidx.lifecycle.ViewModel
import com.example.aula8_mvvm.CalculatorLogic
import com.example.aula8_mvvm.ListStorage
import com.example.aula8_mvvm.OnDataSetChanged
import com.example.aula8_mvvm.OnDisplayChanged

class CalculatorViewModel : ViewModel() {

    private val calculatorLogic = CalculatorLogic()
    var display: String = "0"
    private var storage = ListStorage.getInstance().getAll()

    private var displayListener: OnDisplayChanged? = null
    private var dataSetListener: OnDataSetChanged? = null

    private fun notifyOnDisplayChanged(){ displayListener?.onDisplayChanged(display) }

    private fun notifyOnDataSetChanged(){ dataSetListener?.onDataSetChanged(storage) }

    fun registerDataSetListener(dataSetListener: OnDataSetChanged){
        this.dataSetListener = dataSetListener
        dataSetListener.onDataSetChanged(storage)
    }

    fun registerDisplayListener(displayListener: OnDisplayChanged){
        this.displayListener = displayListener
        displayListener.onDisplayChanged(display)
    }

    fun unregiserDataSetListener(){ dataSetListener = null}

    fun unregisterDisplayListener(){ displayListener = null }

    fun onClickSymbol(symbol: String) {
        display = calculatorLogic.insertSymbol(display,symbol)
        notifyOnDisplayChanged()
    }

    fun onClickEquals(){
        display = calculatorLogic.performOperation(display).toString()
        notifyOnDisplayChanged()
        notifyOnDataSetChanged()
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