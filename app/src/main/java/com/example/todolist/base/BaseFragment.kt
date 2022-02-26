package com.example.todolist.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.todolist.BR
import com.example.todolist.R
import java.lang.StringBuilder
import java.lang.reflect.ParameterizedType
import java.util.*
import javax.inject.Inject

abstract class BaseFragment<B: ViewDataBinding, VM: ViewModel> : Fragment() {
    @Inject lateinit var mViewModel: VM

    protected val viewModel get() = mViewModel
    protected lateinit var binding: B

    protected abstract fun init()
    protected abstract fun observeViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        performDataBinding(inflater, container)
        observeViewModel()
        init()

        return binding.root
    }

    override fun onDestroyView() {
        binding.unbind()
        super.onDestroyView()
    }

    private fun performDataBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, getLayoutRes(), container,false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.setVariable(BR.vm, viewModel)
        binding.executePendingBindings()
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