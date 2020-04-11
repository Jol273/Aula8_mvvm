package com.example.aula8_mvvm

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.aula8_mvvm.fragments.CalculatorFragment
import com.example.aula8_mvvm.fragments.HistoryFragment

abstract class NavigationManager{

    companion object {

        private fun placeFragment(fm: FragmentManager, fragment: Fragment) {
            val transition = fm.beginTransaction()
            transition.replace(R.id.frame,fragment)
            transition.addToBackStack(null)
            transition.commit()
        }

        fun goToCalculatorFragment(fm: FragmentManager){
            placeFragment(fm,
                CalculatorFragment()
            )
        }

        fun goToHistoryFragment(fm: FragmentManager){
            placeFragment(fm, HistoryFragment())
        }
    }
}