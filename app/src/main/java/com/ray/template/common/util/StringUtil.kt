package com.ray.template.common.util

val CharSequence?.isNotEmpty: Boolean
    get() = !isNullOrEmpty()
