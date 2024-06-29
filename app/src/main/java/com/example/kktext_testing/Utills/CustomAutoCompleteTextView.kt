package com.example.kktext_testing.Utills

import android.content.Context
import android.util.AttributeSet
import android.widget.AutoCompleteTextView
import androidx.appcompat.widget.AppCompatAutoCompleteTextView


class CustomAutoCompleteTextView : AppCompatAutoCompleteTextView  {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun showDropDown() {
        if (adapter != null) {
            val dropdownHeight = calculateDropdownHeight()
            val verticalOffset = -dropdownHeight - height
            setDropDownVerticalOffset(verticalOffset)
        }
        super.showDropDown()
    }
    private fun calculateDropdownHeight(): Int {
        val itemHeight = 60 // Approximate item height
        val itemCount = adapter?.count ?: 0
        val maxVisibleItems = 5 // Maximum visible items
        return Math.min(itemCount, maxVisibleItems) * itemHeight
    }

}