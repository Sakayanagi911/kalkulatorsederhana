package com.example.kalkulator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private var currentInput: String = ""
    private var operator: String? = null
    private var firstNumber: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.tvDisplay)

        val buttons = listOf(
            Pair(R.id.btn0, "0"), Pair(R.id.btn1, "1"), Pair(R.id.btn2, "2"),
            Pair(R.id.btn3, "3"), Pair(R.id.btn4, "4"), Pair(R.id.btn5, "5"),
            Pair(R.id.btn6, "6"), Pair(R.id.btn7, "7"), Pair(R.id.btn8, "8"),
            Pair(R.id.btn9, "9"), Pair(R.id.btnPlus, "+"), Pair(R.id.btnMinus, "-"),
            Pair(R.id.btnMultiply, "*"), Pair(R.id.btnDivide, "/")
        )

        buttons.forEach { (id, value) ->
            findViewById<Button>(id).setOnClickListener { handleInput(value) }
        }

        findViewById<Button>(R.id.btnClear).setOnClickListener {
            clear()
        }

        findViewById<Button>(R.id.btnEquals).setOnClickListener {
            calculate()
        }
    }

    private fun handleInput(input: String) {
        if (input in listOf("+", "-", "*", "/")) {
            if (currentInput.isNotEmpty()) {
                firstNumber = currentInput.toDouble()
                operator = input
                currentInput = ""
            }
        } else {
            currentInput += input
        }
        display.text = currentInput
    }

    private fun calculate() {
        if (operator != null && currentInput.isNotEmpty()) {
            val secondNumber = currentInput.toDouble()
            val result = when (operator) {
                "+" -> firstNumber + secondNumber
                "-" -> firstNumber - secondNumber
                "*" -> firstNumber * secondNumber
                "/" -> firstNumber / secondNumber
                else -> 0.0
            }
            display.text = result.toString()
            currentInput = result.toString()
            operator = null
        }
    }

    private fun clear() {
        currentInput = ""
        firstNumber = 0.0
        operator = null
        display.text = "0"
    }
}
