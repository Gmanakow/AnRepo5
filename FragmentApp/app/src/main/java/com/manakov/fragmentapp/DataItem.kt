package com.manakov.fragmentapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

const val NAME_STRING = "NAME_STRING"
const val SURNAME_STRING = "SURNAME_STRING"
const val NUMBER_STRING = "NUMBER_STRING"
const val ITEM_STRING = "ITEM_STRING"
const val INDEX_STRING = "INDEX"
const val LIST_STRING = "LIST"

@Parcelize
class DataItem(
    var name : String = "",
    var surname : String = "",
    var number : String = ""
) : Parcelable