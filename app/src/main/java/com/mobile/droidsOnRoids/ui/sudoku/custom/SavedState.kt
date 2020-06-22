package com.mobile.droidsOnRoids.ui.sudoku.custom

import android.os.Parcel
import android.os.Parcelable
import android.util.SparseArray
import android.view.View

internal class SavedState : View.BaseSavedState {

    var childrenStates: SparseArray<Parcelable>? = null
    var currentSelectedRow = 0
    var currentSelectedColumn = 0
    var sudokuSize = 0

    constructor(superState: Parcelable?) : super(superState)

    constructor(source: Parcel) : super(source) {
        with(source) {
            childrenStates = readSparseArray(javaClass.classLoader)
            currentSelectedRow = readInt()
            currentSelectedColumn = readInt()
            sudokuSize = readInt()
        }

    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        with(out) {
            writeSparseArray(childrenStates as SparseArray<Any>?)
            writeInt(currentSelectedRow)
            writeInt(currentSelectedColumn)
            writeInt(sudokuSize)
        }
    }

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<SavedState> {
            override fun createFromParcel(source: Parcel) =
                SavedState(source)

            override fun newArray(size: Int): Array<SavedState?> = arrayOfNulls(size)
        }
    }
}