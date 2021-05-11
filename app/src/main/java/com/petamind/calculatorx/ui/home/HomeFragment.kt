package com.petamind.calculatorx.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.notkamui.keval.Keval
import com.petamind.calculatorx.R
import java.lang.StringBuilder

class HomeFragment : Fragment(), View.OnClickListener {

    private var resultText: TextView? = null


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        resultText = root.findViewById(R.id.result_textview)

        val table = root.findViewById<TableLayout>(R.id.table)
        val rowLayoutParams = TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
            0, 1f)
        val cellLayoutParams = TableRow.LayoutParams(0,
            TableRow.LayoutParams.MATCH_PARENT, 1f)
        val labels = resources.getStringArray(R.array.labels)
        for(row in 0..5){
            val tableRow = TableRow(context)
            tableRow.layoutParams = rowLayoutParams
            for(col in 0..3){
                val cell = Button(context)
                cell.layoutParams = cellLayoutParams
                cell.text = "${labels[col + row * 4]}"
                cell.setTextAppearance(R.style.cell)
                cell.setOnClickListener(this)
                tableRow.addView(cell)
            }
            table.addView(tableRow)
        }
        Log.d("keval", Keval.eval("(3+4)(2/8 * 5) % PI").toString())
        return root
    }

    override fun onClick(v: View?) {
        val op = (v as Button).text
        if(op == "="){
            resultText?.text = Keval.eval(resultText?.text.toString()).toString()
            return
        }
        val sb = StringBuilder(resultText?.text)
        sb.append(when(op){
            "0", "1", "3", "2", "5", "4", "6", "7", "8", "9", ".",
            "+"-> (v as Button).text
            else -> ""
        })
        resultText?.text = sb.toString()
    }
}