package com.example.bai1

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.bai1.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Sự kiện chọn ngày sinh
        binding.btnSelectDate.setOnClickListener {
            showDatePickerDialog()
        }

        // Sự kiện nút Submit
        binding.btnSubmit.setOnClickListener {
            validateForm()
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            val dateString = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            binding.tvBirthday.text = dateString
        }, year, month, day)

        datePickerDialog.show()
    }

    private fun validateForm() {
        var isValid = true

        // Kiểm tra MSSV
        if (binding.edtMSSV.text.isNullOrBlank()) {
            binding.edtMSSV.error = "Vui lòng nhập MSSV"
            isValid = false
        }

        // Kiểm tra Họ tên
        if (binding.edtFullName.text.isNullOrBlank()) {
            binding.edtFullName.error = "Vui lòng nhập Họ tên"
            isValid = false
        }

        // Kiểm tra Giới tính
        if (binding.rgGender.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Vui lòng chọn Giới tính", Toast.LENGTH_SHORT).show()
            isValid = false
        }

        // Kiểm tra Email
        if (binding.edtEmail.text.isNullOrBlank()) {
            binding.edtEmail.error = "Vui lòng nhập Email"
            isValid = false
        }

        // Kiểm tra Số điện thoại
        if (binding.edtPhone.text.isNullOrBlank()) {
            binding.edtPhone.error = "Vui lòng nhập Số điện thoại"
            isValid = false
        }

        // Kiểm tra Ngày sinh
        if (binding.tvBirthday.text.isNullOrBlank() || binding.tvBirthday.text == "Ngày sinh") {
            Toast.makeText(this, "Vui lòng chọn Ngày sinh", Toast.LENGTH_SHORT).show()
            isValid = false
        }

        // Kiểm tra nơi ở hiện tại (Tỉnh, Huyện, Xã)
        if (binding.spnProvince.selectedItem == null) {
            Toast.makeText(this, "Vui lòng chọn Tỉnh/Thành", Toast.LENGTH_SHORT).show()
            isValid = false
        }
        if (binding.spnDistrict.selectedItem == null) {
            Toast.makeText(this, "Vui lòng chọn Quận/Huyện", Toast.LENGTH_SHORT).show()
            isValid = false
        }
        if (binding.spnWard.selectedItem == null) {
            Toast.makeText(this, "Vui lòng chọn Phường/Xã", Toast.LENGTH_SHORT).show()
            isValid = false
        }

        // Kiểm tra đồng ý điều khoản
        if (!binding.cbTerms.isChecked) {
            Toast.makeText(this, "Vui lòng đồng ý với các điều khoản", Toast.LENGTH_SHORT).show()
            isValid = false
        }

        if (isValid) {
            Toast.makeText(this, "Form hợp lệ, đăng ký thành công!", Toast.LENGTH_SHORT).show()
        }
    }
}
