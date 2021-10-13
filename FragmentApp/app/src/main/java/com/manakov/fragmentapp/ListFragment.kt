package com.manakov.fragmentapp

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

class ListFragment : Fragment(R.layout.fragment_list) {

    companion object {
        const val LOG_TAG = "ListFragment"
        const val BACK_STACK_TAG = "ListFragmentBackStackTag"
        const val SAVED_STATE_TAG = "ListFragment"

        fun newInstance(list: ArrayList<DataItem>) = ListFragment().also {
            it.arguments = Bundle().apply {
                putParcelableArrayList(LIST_STRING, list)
            }
        }
    }

    private val viewNames = listOf(
        R.id.firstTextView,
        R.id.secondTextView,
        R.id.thirdTextView
    )
    private val views = mutableListOf<TextView>()

    val list by lazy { requireArguments().getParcelableArrayList<DataItem>(LIST_STRING) }

    private lateinit var fragmentActionListener: FragmentActionListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentActionListener) {
            context.also { fragmentActionListener = it }
        } else {
            throw IllegalArgumentException("context must implement FragmentActionListener interface")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        views.clear()
        viewNames.forEachIndexed { index, element ->
            val textView = view.findViewById<TextView>(element)
            textView.text = list!![index].name
            textView.setOnClickListener {
                fragmentActionListener.onSelectItemAction(list!![index], index)
            }
            views.add(textView)
        }
    }

    fun refresh() {
        views.forEachIndexed { index, textView ->
            textView.text = list!![index].name
        }
    }


}