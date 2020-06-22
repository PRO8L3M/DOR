package com.mobile.droidsOnRoids.ext

import com.mobile.droidsOnRoids.data.entity.Cell
import retrofit2.Response
import java.io.IOException
import java.lang.IllegalStateException
import javax.crypto.IllegalBlockSizeException
import kotlin.math.floor
import kotlin.math.sqrt

fun <A : Any> Response<A>.bodyOrException(): A {
    val body = body()
    return if (isSuccessful && body != null) {
        body
    } else throw Exception(message())
}

fun <T : Any> List<T>.throwIfEmpty() =
    if (isEmpty()) throw NoSuchElementException("List is empty!") else this

fun List<Cell>.convertToSudokuChain() =
    joinToString { it.value.toString() }.replace(", ", "").replace('0', '.')

fun String.convertDigitChainToCells(): List<Cell> {
    val squareValue = sqrt(length.toDouble())
    if ((squareValue - floor(squareValue)) != 0.0 ) throw IllegalBlockSizeException("Provided list size is not square rooted!")
    val sudokuSize = sqrt(length.toDouble()).toInt()
    val isSolution = !contains(".")
    val digitsChain = replace(".", "0")
    val digitList = digitsChain.toCharArray().map { charDigit -> charDigit.toString().toInt() }
    return List(digitList.size) { index ->
        val isEditable = digitList[index] == 0
        Cell(
            row = index / sudokuSize,
            column = index % sudokuSize,
            value = digitList[index],
            isEditable = isEditable,
            isSolution = isSolution
        )
    }
}
