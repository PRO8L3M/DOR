package com.mobile.droidsOnRoids.data.network

import com.mobile.droidsOnRoids.data.entity.Cell
import okhttp3.ResponseBody
import retrofit2.Converter

class ResponseConverter : Converter<ResponseBody, List<Cell>> {
    override fun convert(value: ResponseBody): List<Cell>? = convertDigitsChainToCells(value.string())

    private fun convertDigitsChainToCells(sudokuChain: String): List<Cell> {
        val digitsChain = sudokuChain.replace(".", "0")
        val digitList = digitsChain.toCharArray().map { charDigit -> charDigit.toString().toInt() }
        return List(digitList.size) { index ->
            val isEditable = when (digitList[index]) {
                0 -> true
                else -> false
            }
            Cell(
                row = index / 9,
                column = index % 9,
                value = digitList[index],
                isEditable = isEditable
            )
        }
    }
}
