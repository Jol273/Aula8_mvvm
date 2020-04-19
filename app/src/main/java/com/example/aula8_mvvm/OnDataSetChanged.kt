package com.example.aula8_mvvm

import com.example.aula8_mvvm.classes.Operation

interface OnDataSetChanged {

    fun onDataSetChanged(value: List<Operation>)
}