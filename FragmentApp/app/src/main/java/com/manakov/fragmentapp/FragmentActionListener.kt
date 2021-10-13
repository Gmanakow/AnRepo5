package com.manakov.fragmentapp

interface FragmentActionListener {
    fun onSelectItemAction(dataItem: DataItem, index : Int)
    fun onSaveStateAction(dataItem: DataItem, index : Int)
}