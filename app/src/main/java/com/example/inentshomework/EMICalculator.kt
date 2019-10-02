package com.example.inentshomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_emicalculator.*
import kotlin.math.pow
import kotlin.math.truncate

class EMICalculator : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emicalculator)

        sbLoanTerm.setOnSeekBarChangeListener(this)
        sbLoanAmount.setOnSeekBarChangeListener(this)
        sbInterestRate.setOnSeekBarChangeListener(this)

        val loanTerm = sbLoanTerm.getProgress().toString().toInt()
        val loanAmount = sbLoanAmount.getProgress().toString().toInt()
        val rateProgress = sbInterestRate.getProgress().toString().toInt()
        val rate = interestRate(rateProgress, sbInterestRate.min, sbInterestRate.max)
        tvInterestRateValue.text = rate.toString()
        tvLoanTermValue.text = sbLoanTerm.getProgress().toString()
        tvLoanAmountValue.text = sbLoanAmount.getProgress().toString()
        tvEMIValue.text = getEMI(loanTerm, loanAmount, rate).toString()

    }

    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        when (seekBar.id) {
            R.id.sbInterestRate -> updateInterestRate(seekBar, progress)
            R.id.sbLoanAmount -> updateLoanAmount(progress)
            R.id.sbLoanTerm -> updateLoanTerm(progress)
        }
    }

    override fun onStartTrackingTouch(seekBar: SeekBar) {
        Log.d("SEEK_BAR", "tracking ${seekBar.id}")

    }

    override fun onStopTrackingTouch(seekBar: SeekBar) {
        Log.d("SEEK_BAR", "end tracking ${seekBar.id}")
    }

    fun updateInterestRate(seekBar: SeekBar, progress: Int) {
        var min: Int = seekBar.getMin()
        var max: Int = seekBar.getMax()
        tvInterestRateValue.text = interestRate(progress, min, max).toString()
        tvEMIValue.text = getEMI(
            tvLoanTermValue.text.toString().toInt(),
            tvLoanAmountValue.text.toString().toInt(),
            tvInterestRateValue.text.toString().toDouble()
        ).toString()
    }

    fun updateLoanTerm(progress: Int) {
        tvLoanTermValue.text = progress.toString()
        tvEMIValue.text = getEMI(
            tvLoanTermValue.text.toString().toInt(),
            tvLoanAmountValue.text.toString().toInt(),
            tvInterestRateValue.text.toString().toDouble()
        ).toString()
    }
    fun updateLoanAmount(progress: Int) {
        tvLoanAmountValue.text = progress.toString()
        tvEMIValue.text = getEMI(
            tvLoanTermValue.text.toString().toInt(),
            tvLoanAmountValue.text.toString().toInt(),
            tvInterestRateValue.text.toString().toDouble()
        ).toString()
    }
}

const val MIN_RATE = 5.0
const val MAX_RATE = 15.0

fun interestRate(progress: Int, min: Int, max: Int): Double {
    val progressPercent: Double = progress / (max - min + 1.0)

    val rate = progressPercent * (MAX_RATE - MIN_RATE) + MIN_RATE
    Log.d("INTEREST_RATE_CALC", "Min: $min Max: $max Progress: $progress Percent: $progressPercent Rate: $rate")
    return numDecimalPlaces(rate)
}

const val BASE = 10.0
fun numDecimalPlaces(value: Double, numDecimals: Int = 2): Double {
    val base: Double = BASE.pow(numDecimals)
    return truncate(value * base) / base
}

//P is the principal amount borrowed
// I is the annual interest rate
// r is the periodic monthly interest rate
// n is the total number of monthly payments
// t is the number of months in a year

//(P x I) x ((1 + r)^n)/ (t x ((1 + r)^n)- 1)

fun getEMI(monthlyInstallments: Int, amount: Int, rate: Double): Double {
    val r = rate / (12.0 * 100.0)
    val n = monthlyInstallments
    val t = 12.0
    val I = rate / 100.0
    val P = amount
    return numDecimalPlaces((P * I) * ((1 + r).pow(n)) / (t * ((1 + r).pow(n) - 1)))
}

