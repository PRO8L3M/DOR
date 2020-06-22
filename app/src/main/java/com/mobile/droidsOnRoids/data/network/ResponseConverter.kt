package com.mobile.droidsOnRoids.data.network

import com.mobile.droidsOnRoids.data.entity.Cell
import com.mobile.droidsOnRoids.ext.convertDigitChainToCells
import okhttp3.ResponseBody
import retrofit2.Converter

class ResponseConverter : Converter<ResponseBody, List<Cell>> {
    override fun convert(value: ResponseBody): List<Cell>? = value.string().convertDigitChainToCells()
}
