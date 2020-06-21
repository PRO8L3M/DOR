package com.mobile.droidsOnRoids.common.customs

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Parcelable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import com.mobile.droidsOnRoids.R
import com.mobile.droidsOnRoids.data.entity.Cell
import kotlin.math.min

class SudokuView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    init {
        isSaveEnabled = true
    }

    private var cellSizePixels = 0f
    private var selectedRow =
        ROW_STARTING_POSITION
    private var selectedColumn =
        COLUMN_STARTING_POSITION
    private var cells: List<Cell>? = null
    private val selectedCellColor = ContextCompat.getColor(context, R.color.weakYellow)

    private val linesPaint = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.BLACK
        strokeWidth = 4F
    }

    private val digitsPaint = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        color = Color.BLACK
    }

    private val selectedCellPaint = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        color = selectedCellColor
    }

    override fun onDraw(canvas: Canvas) {
        cellSizePixels = (width / BOARD_SIZE).toFloat()
        digitsPaint.textSize =
            TEXT_SIZE
        drawLines(canvas)
        drawText(canvas)
        fillCell(canvas, selectedRow, selectedColumn, selectedCellPaint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val sizePixels = min(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(sizePixels, sizePixels)
    }

    private fun drawLines(canvas: Canvas) = with(canvas) {
        drawRect(0f, 0f, width.toFloat(), height.toFloat(), linesPaint)

        for (i in 1 until BOARD_SIZE) {
            drawLine(i * cellSizePixels, 0f, i * cellSizePixels, height.toFloat(), linesPaint)
            drawLine(0f, i * cellSizePixels, width.toFloat(), i * cellSizePixels, linesPaint)
        }
    }

    private fun fillCell(canvas: Canvas, row: Int, column: Int, paint: Paint) = with(canvas) {
        drawRect(
            column * cellSizePixels,
            row * cellSizePixels,
            (column + 1) * cellSizePixels,
            (row + 1) * cellSizePixels,
            paint
        )
    }

    fun fillCells(cells: List<Cell>) {
        this.cells = cells
        invalidate()
    }

    private fun updateCurrentSelectedCell(row: Int, column: Int) {
        selectedRow = row
        selectedColumn = column
        invalidate()
    }

    fun getCurrentSelectedCell() =
        cells?.find { cell -> (cell.row == selectedRow) and (cell.column == selectedColumn) }

    private fun drawText(canvas: Canvas) = with(canvas) {
        cells?.forEach { cell ->
            val row = cell.row
            val column = cell.column
            val value = cell.value.toString()

            val textBounds = Rect()
            digitsPaint.getTextBounds(value, 0, value.length, textBounds)
            val textWidth = digitsPaint.measureText(value)
            val textHeight = textBounds.height()

            val valueString = when (cell.value) {
                0 -> ""
                else -> cell.value.toString()
            }


            drawText(
                valueString,
                (column * cellSizePixels) + cellSizePixels / 2 - textWidth / 2,
                (row * cellSizePixels) + cellSizePixels / 2 + textHeight / 2,
                digitsPaint
            )
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent) =
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                handleClick(event.x, event.y)
                true
            }
            else -> false
        }

    private fun handleClick(x: Float, y: Float) {
        val newSelectedRow = (y / cellSizePixels).toInt()
        val newSelectedColumn = (x / cellSizePixels).toInt()
        updateCurrentSelectedCell(newSelectedRow, newSelectedColumn)
    }

    override fun onSaveInstanceState(): Parcelable? {
        return SavedState(super.onSaveInstanceState()).apply {
            currentSelectedRow = selectedRow
            currentSelectedColumn = selectedColumn
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) = when (state) {
        is SavedState -> {
            with(state) {
                super.onRestoreInstanceState(superState)
                selectedRow = currentSelectedRow
                selectedColumn = currentSelectedColumn
            }
        }
        else -> super.onRestoreInstanceState(state)
    }

    companion object {
        private const val BOARD_SIZE = 9
        private const val TEXT_SIZE = 80f
        private const val ROW_STARTING_POSITION = 4
        private const val COLUMN_STARTING_POSITION = 4
    }
}