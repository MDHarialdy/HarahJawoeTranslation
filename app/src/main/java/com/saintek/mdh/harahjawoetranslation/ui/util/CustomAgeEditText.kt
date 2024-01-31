package com.saintek.mdh.harahjawoetranslation.ui.util

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class CustomAgeEditText: AppCompatEditText {

    constructor(context: Context): super(context){
        init()
    }

    constructor(context: Context, atrs: AttributeSet): super(context, atrs){
        init()
    }

    constructor(context: Context, atrs: AttributeSet, defStyleAtrs: Int): super(context, atrs, defStyleAtrs){
        init()
    }

    private fun init(){
        addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty() && !s.toString().matches(Regex("\\d*"))) {
                    // Hapus karakter non-angka dan tampilkan peringatan
                    val cleanText = s.toString().replace(Regex("\\D"), "")
                    setText(cleanText)
                    setError("Hanya angka yang diperbolehkan!", null)
                } else {
                    error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {
                //nothing
            }
        })
    }
}