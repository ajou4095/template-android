package com.ray.template.android.common.util.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

data class Zip2Result<T1, T2>(
    val result1: T1,
    val result2: T2
)

suspend fun <T1, T2> zip(
    block1: suspend () -> Result<T1>,
    block2: suspend () -> Result<T2>
): Result<Zip2Result<T1, T2>> = withContext(Dispatchers.IO) {
    val deferred1 = async { block1() }
    val deferred2 = async { block2() }

    return@withContext runCatching {
        Zip2Result(
            result1 = deferred1.await().getOrThrow(),
            result2 = deferred2.await().getOrThrow()
        )
    }
}

data class Zip3Result<T1, T2, T3>(
    val result1: T1,
    val result2: T2,
    val result3: T3
)

suspend fun <T1, T2, T3> zip(
    block1: suspend () -> Result<T1>,
    block2: suspend () -> Result<T2>,
    block3: suspend () -> Result<T3>
): Result<Zip3Result<T1, T2, T3>> = withContext(Dispatchers.IO) {
    val deferred1 = async { block1() }
    val deferred2 = async { block2() }
    val deferred3 = async { block3() }

    return@withContext runCatching {
        Zip3Result(
            result1 = deferred1.await().getOrThrow(),
            result2 = deferred2.await().getOrThrow(),
            result3 = deferred3.await().getOrThrow()
        )
    }
}

data class Zip4Result<T1, T2, T3, T4>(
    val result1: T1,
    val result2: T2,
    val result3: T3,
    val result4: T4
)

suspend fun <T1, T2, T3, T4> zip(
    block1: suspend () -> Result<T1>,
    block2: suspend () -> Result<T2>,
    block3: suspend () -> Result<T3>,
    block4: suspend () -> Result<T4>
): Result<Zip4Result<T1, T2, T3, T4>> = withContext(Dispatchers.IO) {
    val deferred1 = async { block1() }
    val deferred2 = async { block2() }
    val deferred3 = async { block3() }
    val deferred4 = async { block4() }

    return@withContext runCatching {
        Zip4Result(
            result1 = deferred1.await().getOrThrow(),
            result2 = deferred2.await().getOrThrow(),
            result3 = deferred3.await().getOrThrow(),
            result4 = deferred4.await().getOrThrow()
        )
    }
}

data class Zip5Result<T1, T2, T3, T4, T5>(
    val result1: T1,
    val result2: T2,
    val result3: T3,
    val result4: T4,
    val result5: T5
)

suspend fun <T1, T2, T3, T4, T5> zip(
    block1: suspend () -> Result<T1>,
    block2: suspend () -> Result<T2>,
    block3: suspend () -> Result<T3>,
    block4: suspend () -> Result<T4>,
    block5: suspend () -> Result<T5>
): Result<Zip5Result<T1, T2, T3, T4, T5>> = withContext(Dispatchers.IO) {
    val deferred1 = async { block1() }
    val deferred2 = async { block2() }
    val deferred3 = async { block3() }
    val deferred4 = async { block4() }
    val deferred5 = async { block5() }

    return@withContext runCatching {
        Zip5Result(
            result1 = deferred1.await().getOrThrow(),
            result2 = deferred2.await().getOrThrow(),
            result3 = deferred3.await().getOrThrow(),
            result4 = deferred4.await().getOrThrow(),
            result5 = deferred5.await().getOrThrow()
        )
    }
}

data class Zip6Result<T1, T2, T3, T4, T5, T6>(
    val result1: T1,
    val result2: T2,
    val result3: T3,
    val result4: T4,
    val result5: T5,
    val result6: T6
)

suspend fun <T1, T2, T3, T4, T5, T6> zip(
    block1: suspend () -> Result<T1>,
    block2: suspend () -> Result<T2>,
    block3: suspend () -> Result<T3>,
    block4: suspend () -> Result<T4>,
    block5: suspend () -> Result<T5>,
    block6: suspend () -> Result<T6>
): Result<Zip6Result<T1, T2, T3, T4, T5, T6>> = withContext(Dispatchers.IO) {
    val deferred1 = async { block1() }
    val deferred2 = async { block2() }
    val deferred3 = async { block3() }
    val deferred4 = async { block4() }
    val deferred5 = async { block5() }
    val deferred6 = async { block6() }

    return@withContext runCatching {
        Zip6Result(
            result1 = deferred1.await().getOrThrow(),
            result2 = deferred2.await().getOrThrow(),
            result3 = deferred3.await().getOrThrow(),
            result4 = deferred4.await().getOrThrow(),
            result5 = deferred5.await().getOrThrow(),
            result6 = deferred6.await().getOrThrow()
        )
    }
}
