package com.mobile.droidsOnRoids

import com.mobile.droidsOnRoids.data.entity.Cell
import com.mobile.droidsOnRoids.ext.convertDigitChainToCells
import com.mobile.droidsOnRoids.ext.convertToSudokuChain
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.IOException
import javax.crypto.IllegalBlockSizeException
import kotlin.test.assertFailsWith

class SudokuUnitTest {
    private val correctList = listOf(
        Cell(row = 0, column = 0, value = 1, isEditable = false, isSolution = false),
        Cell(row = 0, column = 1, value = 2, isEditable = false, isSolution = false),
        Cell(row = 0, column = 2, value = 3, isEditable = false, isSolution = false),
        Cell(row = 1, column = 0, value = 0, isEditable = true, isSolution = false),
        Cell(row = 1, column = 1, value = 0, isEditable = true, isSolution = false),
        Cell(row = 1, column = 2, value = 0, isEditable = true, isSolution = false),
        Cell(row = 2, column = 0, value = 3, isEditable = false, isSolution = false),
        Cell(row = 2, column = 1, value = 2, isEditable = false, isSolution = false),
        Cell(row = 2, column = 2, value = 1, isEditable = false, isSolution = false)
    )

    private val notSquareRootedList = listOf(
        Cell(row = 0, column = 0, value = 1, isEditable = false, isSolution = false),
        Cell(row = 0, column = 1, value = 2, isEditable = false, isSolution = false),
        Cell(row = 0, column = 2, value = 3, isEditable = false, isSolution = false),
        Cell(row = 1, column = 0, value = 0, isEditable = true, isSolution = false),
        Cell(row = 1, column = 0, value = 0, isEditable = true, isSolution = false)
    )

    private val digitChain = "123...321"

    private val notSquareRootedDigitChain = "123.."

    @Test
    fun `converting from digit chain to cell list is correct`() {
        val result = digitChain.convertDigitChainToCells()
        assertEquals(result, correctList)
    }

    @Test
    fun `converting from cell list to digit chain is correct`() {
        val result = correctList.convertToSudokuChain()
        assertEquals(result, digitChain)
    }

    @Test
    fun `converting from digit chain to cell list while length value is not square rooted should throw exception`() {
        assertFailsWith(IllegalBlockSizeException::class) {
            notSquareRootedDigitChain.convertDigitChainToCells()
        }
    }
}
