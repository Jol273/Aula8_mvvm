package com.example.aula8_mvvm.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Optional
import com.example.aula8_mvvm.*
import com.example.aula8_mvvm.CalculatorViewModel
import com.example.aula8_mvvm.activities.MainActivity
import com.example.aula8_mvvm.classes.Operation
import com.example.aula8_mvvm.storage.ListStorage
import kotlinx.android.synthetic.main.fragment_calculator.*
import kotlinx.android.synthetic.main.fragment_calculator.view.*


class CalculatorFragment : Fragment(), OnDisplayChanged, OnDataSetChanged {

    private val TAG = MainActivity::class.java.simpleName

    private lateinit var viewModel: CalculatorViewModel
    private var operations = ListStorage.getInstance().getAll()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_calculator, container, false)

        viewModel = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)
        //viewModel.display.let { view.text_visor.text = it }
        ButterKnife.bind(this,view)
        return view
    }

    private fun makeHistory(){
        list_history?.layoutManager = LinearLayoutManager(activity as Context)
        list_history?.adapter = HistoryAdapter(activity as Context,R.layout.item_expression, operations)
    }

    override fun onStart() {
        viewModel.registerDisplayListener(this)
        viewModel.registerDataSetListener(this)
        makeHistory()
        super.onStart()
    }

    override fun onDisplayChanged(value: String?) {
        value?.let {text_visor.text = it}
    }

    @Optional
    override fun onDataSetChanged(value: List<Operation>) {
        value.let { operations = it}
    }

    override fun onDestroy() {
        viewModel.unregisterDisplayListener()
        viewModel.unregisterDataSetListener()
        super.onDestroy()
    }

    @Optional
    @OnClick(R.id.button_last)
    fun onClickLast(view: View){
        viewModel.onClickLastExpression()
        Log.i(TAG,"Click no botão Ultima Conta")

    }

    @OnClick(R.id.button_clear)
    fun onClickClear(view: View){
        Log.i(TAG,"Click no botão C")
        viewModel.onClickClear()
        /*val toast = Toast.makeText(this, "$text button_clear", duration)
        toast.show()*/
    }

    @OnClick(R.id.button_backspace)
    fun onClickBackSpace(view: View){
        Log.i(TAG,"Click no botão >")
        viewModel.onClickBackspace()
        /*val toast = Toast.makeText(this, "$text button_backspace", duration)
        toast.show()*/
    }

    @Optional
    @OnClick(R.id.button_0,R.id.button_00,R.id.button_1,R.id.button_2,R.id.button_3,R.id.button_4,R.id.button_5,R.id.button_6,R.id.button_7,R.id.button_8,R.id.button_9,R.id.button_dot,R.id.button_adition,R.id.button_subtraction,R.id.button_multiplication,R.id.button_division)
    fun onClickSymbol(view: View){
        viewModel.onClickSymbol(view.tag.toString())
    }

    @OnClick(R.id.button_equals)
    fun onClickEquals(view : View){
        viewModel.onClickEquals()
        Log.i(TAG, "O resultado da expressão é ${text_visor.text}")
        /*val toast = Toast.makeText(this, "$text button_equals", duration)
        toast.show()*/
    }

}
