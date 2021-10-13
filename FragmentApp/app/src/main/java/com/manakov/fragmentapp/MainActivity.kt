package com.manakov.fragmentapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), FragmentActionListener {

    private lateinit var listFragment: ListFragment
    private lateinit var dataFragment: DataFragment

    private lateinit var list: ArrayList<DataItem>

    private val isTablet: Boolean by lazy { resources.getBoolean(R.bool.isTablet) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        list = if (savedInstanceState != null) {
            savedInstanceState.getParcelableArrayList(LIST_STRING)!!
        } else {
            arrayListOf(
                DataItem(
                    "name1", "surname1", "number1"
                ),
                DataItem(
                    "name2", "surname2", "number2"
                ),
                DataItem(
                    "banana", "banana", "banana"
                )
            )
        }

        supportFragmentManager.beginTransaction().run {
            listFragment = ListFragment.newInstance(list)
            add(listFragment, ListFragment.BACK_STACK_TAG)
            replace(R.id.listFragmentFrameLayout, listFragment)
            commit()
        }
    }

    override fun onSelectItemAction(dataItem: DataItem, index: Int) {
        supportFragmentManager.beginTransaction().run {
            dataFragment = DataFragment.newInstance(dataItem, index)
            add(dataFragment, DataFragment.BACK_STACK_TAG)
            addToBackStack(DataFragment.BACK_STACK_TAG)
            if (!isTablet) {
                replace(R.id.listFragmentFrameLayout, dataFragment)
            } else {
                replace(R.id.dataFragmentFrameLayout, dataFragment)
            }
            commit()
        }
    }

    override fun onSaveStateAction(dataItem: DataItem, index: Int) {
        supportFragmentManager.beginTransaction().run {
            listFragment.list!![index] = dataItem
            listFragment.refresh()
            onBackPressed()
            if (!isTablet) {
                replace(R.id.listFragmentFrameLayout, listFragment)
            } else {
                detach(dataFragment)
            }
            commit()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(LIST_STRING, list)
    }
}