package com.example.todolist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.DialogFragment
import com.example.todolist.R

class CalendarFragmentDialog : DialogFragment() {

    private lateinit var backBtn: ImageButton
    private lateinit var checkBtn: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.setCancelable(false);

        val rootView = inflater.inflate(R.layout.fragment_calendar_dialog, container, false)

        backBtn = rootView.findViewById(R.id.ib_back_calendar)
        checkBtn = rootView.findViewById(R.id.ib_check_calendar)

        backBtn.setOnClickListener {
            dismiss()
        }
        checkBtn.setOnClickListener {
            dismiss()
        }

        return rootView
    }

}