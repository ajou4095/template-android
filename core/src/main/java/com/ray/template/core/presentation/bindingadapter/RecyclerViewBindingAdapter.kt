package com.ray.template.core.presentation.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("listAdapterItem")
fun RecyclerView.setListAdapterItem(items: List<Any>?) {
    (adapter as? ListAdapter<Any, *>)?.submitList(items)
}