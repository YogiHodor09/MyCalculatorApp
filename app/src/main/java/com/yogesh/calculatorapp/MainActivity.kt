package com.yogesh.calculatorapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var tvResult: TextView? = null

    var lastNumeric: Boolean = false
    var lastDot: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tvResult)
    }

    fun onDigit(view: View) {
        tvResult?.append((view as Button).text)
        lastNumeric = true
        lastDot = false


    }

    fun onClear(view: View) {
        tvResult?.text = ""
    }

    fun onDecimalPoint(view: View) {
        if (lastNumeric && !lastDot) {
            tvResult?.append(".")
            lastNumeric = false
            lastDot = true
        }

    }

    fun onOperator(view: View) {
        tvResult?.text?.let {

            if (lastNumeric && !isOperatorAdded(it.toString())) {
                tvResult?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }

    }

    fun onEqual(view: View) {
        if (lastNumeric) {
            var tvValue = tvResult?.text.toString()
            var prefix = ""
            try {
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one // 99-33-3 = 63
                    }

                    tvResult?.text =
                        removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())

                } else if (tvValue.contains("+")) {
                    val splitValue = tvValue.split("+")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one // 99-33-3 = 63
                    }

                    tvResult?.text =
                        removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())

                } else if (tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one // 99-33-3 = 63
                    }

                    tvResult?.text =
                        removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())

                } else if (tvValue.contains("*")) {
                    val splitValue = tvValue.split("*")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one // 99-33-3 = 63
                    }

                    tvResult?.text =
                        removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }

            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    // remove 'decimal point values and returns Int - 9.0 = 9'
    private fun removeZeroAfterDot(result: String): String {
        var value = result
        if (result.contains(".0"))
            value = result.substring(0, result.length - 2) // eg :- 99.0 = 99
        return value

    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") ||
                    value.contains("*") ||
                    value.contains("+") ||
                    value.contains("-")
        }
    }
}