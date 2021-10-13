package com.manakov.fragmentapp

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class DataFragment : Fragment(R.layout.fragment_data) {

    companion object {
        const val BACK_STACK_TAG = "DataFragmentBackStackTag"

        fun newInstance(dataItem: DataItem, index: Int) = DataFragment().also {
            it.arguments = Bundle().apply {
                putParcelable(ITEM_STRING, dataItem)
                putInt(INDEX_STRING, index)
            }
        }
    }

    private lateinit var fragmentActionListener: FragmentActionListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentActionListener) {
            context.also {
                fragmentActionListener = it
            }
        } else {
            throw IllegalArgumentException("context must implement FragmentActionListener interface")
        }
    }

    private lateinit var nameEditText: EditText
    private lateinit var surnameEditText: EditText
    private lateinit var numberEditText: EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var args = requireArguments()
        var item : DataItem = args.getParcelable(ITEM_STRING)!!

        nameEditText = view.findViewById(R.id.nameEditTextView)
        nameEditText.text = item.name.toEditable()

        surnameEditText = view.findViewById(R.id.surnameEditTextView)
        surnameEditText.text = item.surname.toEditable()

        numberEditText = view.findViewById(R.id.numberEditTextView)
        numberEditText.text = item.number.toEditable()

        view.findViewById<Button>(R.id.saveChangesButton).setOnClickListener {
            fragmentActionListener.onSaveStateAction(
                DataItem(
                    nameEditText.text.toString(),
                    surnameEditText.text.toString(),
                    numberEditText.text.toString()
                ),
                args.getInt(INDEX_STRING, 0)
            )
        }
    }

    private fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)
}