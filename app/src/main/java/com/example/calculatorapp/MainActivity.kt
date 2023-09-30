package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var tvInput: TextView? = null
    private var lastNumeric: Boolean = false
    private var lastDot: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput = findViewById(R.id.tvInput)
    }

    fun onDigit(view: View) {
        tvInput?.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }

    fun onClear(view: View) {
        tvInput?.text = ""
        lastNumeric = false
        lastDot = false
    }

    fun onDecimalPoint(view: View) {
        if (lastNumeric && !lastDot) {
            tvInput?.append((view as Button).text)
            lastDot = true
            lastNumeric = false
        }
    }
    fun onOperator(view:View) {
        tvInput?.text?.let {
            if (lastNumeric && !onOperatorAdded(it.toString())) {
                tvInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }

    fun onEqual(view: View) {
        if (lastNumeric) {
            var tvValue = tvInput?.text.toString()
            var prefix = ""

            if (tvValue.startsWith("-")) {
                tvValue = tvValue.substring(1)
                prefix = "-"
            }
            if (tvValue.contains("-")) {
                val splitValue = tvValue.split('-')
                var one = splitValue[0]
                val two = splitValue[1]
                if (prefix == "-") {
                    one = prefix + one
                }
                val result = (one.toDouble() - two.toDouble()).toString()
                tvInput?.text = result
            } else if((tvValue.contains("+"))){
                    val splitValue = tvValue.split('+')
                    var one = splitValue[0]
                    val two = splitValue[1]
                    if (prefix == "-") {
                        one = prefix + one
                    }
                    val result = (one.toDouble() + two.toDouble()).toString()
                    tvInput?.text = result
            } else if((tvValue.contains("*"))){
                val splitValue = tvValue.split('*')
                var one = splitValue[0]
                val two = splitValue[1]
                if (prefix == "-") {
                    one = prefix + one
                }
                val result = (one.toDouble() * two.toDouble()).toString()
                tvInput?.text = result
            } else if((tvValue.contains("/"))){
                val splitValue = tvValue.split('/')
                var one = splitValue[0]
                val two = splitValue[1]
                if (prefix == "-") {
                    one = prefix + one
                }
                val result = (one.toDouble() / two.toDouble()).toString()
                tvInput?.text = result
            }
        }
    }

    fun onMinus(view:View) {
        if(!onOperatorAdded(tvInput?.text.toString())) {
            tvInput?.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }
    private fun onOperatorAdded(value : String) : Boolean {
        return if(value.startsWith("-")) {
            false
        } else {
            value.contains("+") ||
            value.contains("-") ||
            value.contains("/") ||
            value.contains("*")
        }
    }
}