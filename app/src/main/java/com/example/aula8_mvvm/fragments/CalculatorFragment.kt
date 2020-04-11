package com.example.aula8_mvvm.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import com.example.aula8_mvvm.*
import kotlinx.android.synthetic.main.fragment_calculator.*
import java.text.SimpleDateFormat
import java.util.*


class CalculatorFragment : Fragment(), OnDisplayChanged {

    private val TAG = MainActivity::class.java.simpleName

    var text = SimpleDateFormat("HH:mm:ss").format(Date())
    private val duration = Toast.LENGTH_SHORT
    var lastExpression = ""

    private lateinit var viewModel: CalculatorViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_calculator, container, false)
        viewModel = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)
        //viewModel.display.let { view.text_visor.text = it }
        ButterKnife.bind(this,view)
        return view
    }

    override fun onStart() {
        viewModel.registerListener(this)
        super.onStart()
    }

    override fun onDisplayChanged(value: String?) {
        value?.let {text_visor.text = it}
    }

    override fun onDestroy() {
        viewModel.unregisterListener()
        super.onDestroy()
    }
/*
    @OnClick(R.id.button_last)
    fun onClickLast(view: View){
        Log.i(TAG,"Click no botão Ultima Conta")
        text_visor.text = lastExpression
    }

    @OnClick(R.id.button_clear)
    fun onClickClear(view: View){
        Log.i(TAG,"Click no botão C")
        text_visor.text = "0"
        /*val toast = Toast.makeText(this, "$text button_clear", duration)
        toast.show()*/
    }

    @OnClick(R.id.button_backspace)
    fun onClickBackSpace(view: View){
        Log.i(TAG,"Click no botão >")
        var str = text_visor.text
        if(str.length > 1) {
            str = str.substring(0,str.length - 1)
            text_visor.text = str
        } else if(str.length <= 1){
            text_visor.text = "0"
        }
        /*val toast = Toast.makeText(this, "$text button_backspace", duration)
        toast.show()*/
    }

    @Optional
    @OnClick(R.id.button_0,R.id.button_00,R.id.button_1,R.id.button_2,R.id.button_3,R.id.button_4,R.id.button_5,R.id.button_6,R.id.button_7,R.id.button_8,R.id.button_9,R.id.button_dot,R.id.button_adition,R.id.button_subtraction,R.id.button_multiplication,R.id.button_division)
    fun onClickSymbol(view: View){
        text_visor.text = viewModel.onClickSymbol(view.tag.toString())
    }

    @OnClick(R.id.button_equals)
    fun onClickEquals(view : View){
        viewModel.onClickEquals()
        Log.i(TAG, "O resultado da expressão é ${text_visor.text}")
        /*val toast = Toast.makeText(this, "$text button_equals", duration)
        toast.show()*/
    }

 */


}
