package com.example.todolist.view.dialog

import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.todolist.R
import com.example.todolist.base.BaseDialog
import com.example.todolist.databinding.DialogCreateGoalBinding
import com.example.todolist.model.data.Goal
import com.example.todolist.viewmodel.dialog.CreateGoalViewModel
import com.example.todolist.widget.recyclerview.adapter.ColorAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateGoalDialog : BaseDialog<DialogCreateGoalBinding, CreateGoalViewModel>() {
    override val viewModel: CreateGoalViewModel by activityViewModels()
    private val colorAdapter = ColorAdapter()

    override fun init() {
        binding.rvColor.adapter = colorAdapter

        binding.btnClose.setOnClickListener {
            dismiss()
        }

        binding.btnConfirm.setOnClickListener {
            val goal = Goal(goal = binding.etGoal.text.toString(), color = colorAdapter.getSelectedColor())
            viewModel.insertGoal(goal)
            dismiss()
        }
    }

    override fun observerViewModel() {}

    override fun onResume() {
        super.onResume()
        val width = resources.getDimensionPixelSize(R.dimen.dialog_create_goal_width)
        dialog?.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    init { isCancelable = false }

    companion object { const val TAG = "CreateGoalDialog" }
}