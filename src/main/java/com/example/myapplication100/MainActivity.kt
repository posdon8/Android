package com.example.myapplication100

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var editTextFromAmount: EditText
    private lateinit var editTextToAmount: EditText
    private lateinit var spinnerFromCurrency: Spinner
    private lateinit var spinnerToCurrency: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextFromAmount = findViewById(R.id.editTextFromAmount)
        editTextToAmount = findViewById(R.id.editTextToAmount)
        spinnerFromCurrency = findViewById(R.id.spinnerFromCurrency)
        spinnerToCurrency = findViewById(R.id.spinnerToCurrency)

        // Thiết lập dữ liệu cho Spinner
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.currencies_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFromCurrency.adapter = adapter
        spinnerToCurrency.adapter = adapter

        // Lắng nghe sự thay đổi trong EditText số tiền nguồn
        editTextFromAmount.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                convertCurrency()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun afterTextChanged(s: Editable?) {}
        })

        // Lắng nghe sự thay đổi của Spinner đồng tiền nguồn
        spinnerFromCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                convertCurrency()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // Lắng nghe sự thay đổi của Spinner đồng tiền đích
        spinnerToCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                convertCurrency()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // Lắng nghe nhấp vào EditText số tiền nguồn
        editTextFromAmount.setOnClickListener {
            // Chọn Spinner đồng tiền nguồn
            spinnerFromCurrency.requestFocus()
            spinnerFromCurrency.setSelection(spinnerFromCurrency.selectedItemPosition)
        }

        // Lắng nghe nhấp vào EditText số tiền đích
        editTextToAmount.setOnClickListener {
            // Chọn Spinner đồng tiền đích
            spinnerToCurrency.requestFocus()
            spinnerToCurrency.setSelection(spinnerToCurrency.selectedItemPosition)
        }
    }

    private fun convertCurrency() {
        val fromAmountStr = editTextFromAmount.text.toString()
        if (fromAmountStr.isNotEmpty()) {
            val fromAmount = fromAmountStr.toDouble()
            val fromCurrency = spinnerFromCurrency.selectedItem.toString()
            val toCurrency = spinnerToCurrency.selectedItem.toString()

            // Thực hiện chuyển đổi (giả sử tỷ giá là 1:1 cho ví dụ này)
            val convertedAmount = performConversion(fromAmount, fromCurrency, toCurrency)

            // Cập nhật số tiền đích
            editTextToAmount.setText(convertedAmount.toString())
        } else {
            editTextToAmount.setText("")
        }
    }

    private fun performConversion(amount: Double, fromCurrency: String, toCurrency: String): Double {
      val conversionRates =mapOf(
          "USD" to mapOf("CNY" to 6.4, "EUR" to 0.85, "JPY" to 110.0, "GBP" to 0.75, "AUD" to 1.3, "INR" to 85.0),
          "CNY" to mapOf("USD" to 0.16, "EUR" to 0.13, "JPY" to 17.2, "GBP" to 0.12, "AUD" to 0.20, "INR" to 11.0),
          "EUR" to mapOf("USD" to 1.18, "CNY" to 7.5, "JPY" to 130.0, "GBP" to 0.88, "AUD" to 1.5, "INR" to 100.0),
          "JPY" to mapOf("USD" to 0.0091, "CNY" to 0.058, "EUR" to 0.0077, "GBP" to 0.0067, "AUD" to 0.0115, "INR" to 0.77),
          "GBP" to mapOf("USD" to 1.33, "CNY" to 8.3, "EUR" to 1.14, "JPY" to 150.0, "AUD" to 1.7, "INR" to 115.0),
          "AUD" to mapOf("USD" to 0.77, "CNY" to 5.0, "EUR" to 0.67, "JPY" to 110.0, "GBP" to 0.59, "INR" to 54.0),
          "INR" to mapOf("USD" to 0.012, "CNY" to 0.090, "EUR" to 0.010, "JPY" to 1.3, "GBP" to 0.0087, "AUD" to 0.018)
      )

        // Kiểm tra nếu có tỷ lệ chuyển đổi
        return if (conversionRates.containsKey(fromCurrency) && conversionRates[fromCurrency]!!.containsKey(toCurrency)) {
            val rate = conversionRates[fromCurrency]!![toCurrency]!!
            amount * rate // Trả về số tiền đã chuyển đổi
        } else {
            amount // Nếu không có tỷ lệ chuyển đổi, trả về số tiền gốc
        }

        return amount // Ví dụ: giữ nguyên giá trị
    }
}
