package com.saintek.mdh.harahjawoetranslation.ui.util

import android.content.Context
import android.graphics.drawable.Icon
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class CustomCityEditText: AppCompatEditText {

    constructor(context: Context): super(context){
        init()
    }

    constructor(context: Context, atrs: AttributeSet): super(context, atrs){
        init()
    }

    constructor(context: Context, atrs: AttributeSet, defStyleAtr: Int): super(context, atrs, defStyleAtr){
        init()
    }

    private fun init(){
        addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //Nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                error = if ((s?.length ?: 0) >= 4) {
                    null
                } else {
                    "Minimal 4 Huruf"
                }
            }

            override fun afterTextChanged(s: Editable?) {
                //Nothing
            }
        })
    }
}