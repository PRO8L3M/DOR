package com.mobile.droidsOnRoids.ext

import com.mobile.droidsOnRoids.data.entity.Cell
import retrofit2.Response
import java.lang.Exception
import kotlin.math.sqrt

fun <A: Any> Response<A>.bodyOrException(): A {
    val body = body()
    return if (isSuccessful && body != null) {
        body
    } else throw Exception(message())
}

fun <T: Any> List<T>.throwIfEmpty() = if (isEmpty()) throw Exception("List is empty!") else this

fun List<Cell>.convertToSudokuChain() = joinToString {it.value.toString()}.replace(", ", "").replace('0', '.')

fun String.convertDigitsChainToCells(): List<Cell> {
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
