package com.jalexy.meme.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.jalexy.meme.base.utils.BindingUtils
import kotlin.reflect.KClass

abstract class BindingFragment<out VB : ViewBinding>(
    private val bindingClass: KClass<VB>
) : Fragment() {

    private var _binding: VB? = null
    val binding: VB
        get() {
            return _binding ?: throw IllegalStateException(
                "Fragment $this: ViewBinding wasn't initialize"
            )
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return BindingUtils.getBinding<VB>(bindingClass, inflater, container, false).also {
            _binding = it
        }.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}