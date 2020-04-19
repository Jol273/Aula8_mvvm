package com.example.aula8_mvvm.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aula8_mvvm.*
import com.example.aula8_mvvm.classes.Operation
import com.example.aula8_mvvm.storage.ListStorage
import kotlinx.android.synthetic.main.fragment_history.*


class HistoryFragment : Fragment(), OnDataSetChanged {

    private var operations = ListStorage.getInstance().getAll()

    private var dataSetChanged: OnDataSetChanged?= null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
        /*CoroutineScope(Dispatchers.Main).launch {
        }*/
    }

    override fun onStart() {
        list_history.layoutManager = LinearLayoutManager(activity as Context)
        list_history.adapter = HistoryAdapter(activity as Context, R.layout.item_expression, operations)
        super.onStart()
    }

    override fun onDataSetChanged(value: List<Operation>) {
        value.let {operations = it}
    }

}

