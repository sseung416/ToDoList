package com.example.todolist.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import com.example.todolist.R
import java.lang.StringBuilder
import java.lang.reflect.ParameterizedType
import java.util.*
import javax.inject.Inject

abstract class BaseDialog<B: ViewDataBinding, VM: ViewModel> : DialogFragment() {
    @Inject lateinit var mViewModel: VM

    protected val viewModel: VM get() = mViewModel
    protected lateinit var binding: B

    protected abstract fun init()
    protected abstract fun observerViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
        observerViewModel()
        init()
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return Dialog(requireContext()).apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    override fun dismiss() {
        super.dismiss()
        binding.unbind()
    }

    @LayoutRes
    private fun getLayoutRes(): Int {
        val split = ((Objects.requireNonNull(javaClass.genericSuperclass) as ParameterizedType).actualTypeArguments[0] as Class<*>)
            .simpleName.replace("Binding$".toRegex(), "")
            .split("(?<=.)(?=\\p{Upper})".toRegex())
            .dropLastWhile { it.isEmpty() }.toTypedArray()

        val name = StringBuilder()
        for(i in split.indices) {
            name.append(split[i].lowercase(Locale.ROOT))
            if (i != split.lastIndex) name.append("_")
        }

        return R.layout::class.java.getField(name.toString()).getInt(R.layout::class.java)
    }
}