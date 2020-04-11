package com.example.aula8_mvvm.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aula8_mvvm.*
import kotlinx.android.synthetic.main.fragment_history.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HistoryFragment : Fragment() {

    private val storage = ListStorage()
    private var operations = listOf<Operation>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)

        CoroutineScope(Dispatchers.IO).launch {
            operations = storage.getAll()
            view.list_history.adapter = HistoryAdapter(activity as Context, R.layout.item_expression, operations)
            view.list_history.layoutManager = LinearLayoutManager(activity as Context)
        }


        return view
    }


}
