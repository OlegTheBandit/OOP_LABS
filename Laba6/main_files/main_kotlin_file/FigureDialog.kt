package com.example.hellowworld

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatDialogFragment

enum class FiguresType {
    TRIANGLE, RECTANGLE, CIRCLE, SQUARE
}


class FigureDialog(figureTipe: FiguresType): AppCompatDialogFragment() {

    lateinit  var side1: EditText
    lateinit  var side2: EditText
    lateinit  var side3: EditText
    var ftype: FiguresType = figureTipe


    lateinit var listener:FigureDialogListener


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val inflater: LayoutInflater = activity!!.layoutInflater
        when(ftype){
            FiguresType.TRIANGLE -> {
                val view: View = inflater.inflate(R.layout.layout_triangle_dialog, null)
                builder.setView(view)
                    .setTitle("Triangle")
                    .setNegativeButton(
                        "cancel"
                    ) { dialogInterface, i -> }
                    .setPositiveButton(
                        "ok"
                    ) { dialogInterface, i ->
                        val side1: String = side1.getText().toString()
                        val side2: String = side2.getText().toString()
                        val side3: String = side3.getText().toString()
                        listener.applyTriangleTexts(side1,side2,side3)
                    }
                side1 = view.findViewById(R.id.triangle_side1)
                side2 = view.findViewById(R.id.triangle_side2)
                side3 = view.findViewById(R.id.triangle_side3)
                return  builder.create()
            }
            FiguresType.RECTANGLE -> {
                val view: View = inflater.inflate(R.layout.layout_rectangle_dialog, null)

                builder.setView(view)
                    .setTitle("Rectangle")
                    .setNegativeButton(
                        "cancel"
                    ) { dialogInterface, i -> }
                    .setPositiveButton(
                        "ok"
                    ) { dialogInterface, i ->
                        val side1: String = side1.getText().toString()
                        val side2: String = side2.getText().toString()
                        listener.applyRectangleTexts(side1,side2)
                    }

                side1 = view.findViewById(R.id.rectangle_side1)
                side2 = view.findViewById(R.id.rectangle_side2)
            }
            FiguresType.CIRCLE ->{
                val view: View = inflater.inflate(R.layout.layout_circle_square_dialog, null)

                builder.setView(view)
                    .setTitle("Circle")
                    .setNegativeButton(
                        "cancel"
                    ) { dialogInterface, i -> }
                    .setPositiveButton(
                        "ok"
                    ) { dialogInterface, i ->
                        val side1: String = side1.getText().toString()
                        listener.applyCircleTexts(side1)
                    }

                side1 = view.findViewById(R.id.circle_square_side1)
            }
            FiguresType.SQUARE ->{
                val view: View = inflater.inflate(R.layout.layout_circle_square_dialog, null)

                builder.setView(view)
                    .setTitle("Square")
                    .setNegativeButton(
                        "cancel"
                    ) { dialogInterface, i -> }
                    .setPositiveButton(
                        "ok"
                    ) { dialogInterface, i ->
                        val side1: String = side1.getText().toString()
                        listener.applySquareTexts(side1)
                    }

                side1 = view.findViewById(R.id.circle_square_side1)
            }
        }
        return  builder.create()



    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as FigureDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                context.toString() +
                        "must implement ExampleDialogListener"
            )
        }
    }

    interface FigureDialogListener {
        fun applyTriangleTexts(side1: String?, side2: String?, side3: String?)
        fun applyRectangleTexts(side1: String?, side2: String?)
        fun applyCircleTexts(side1: String?)
        fun applySquareTexts(side1: String?)
    }
}


/*class TriangleDialog: AppCompatDialogFragment() {

    lateinit  var side1: EditText
    lateinit  var side2: EditText
    lateinit  var side3: EditText
    lateinit var listener:TriangleDialogListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val inflater: LayoutInflater = activity!!.layoutInflater
        val view: View = inflater.inflate(R.layout.layout_triangle_dialog, null)

        builder.setView(view)
            .setTitle("Triangle")
            .setNegativeButton(
                "cancel"
            ) { dialogInterface, i -> }
            .setPositiveButton(
                "ok"
            ) { dialogInterface, i ->
                val side1: String = side1.getText().toString()
                val side2: String = side2.getText().toString()
                val side3: String = side3.getText().toString()
                listener.applyTriangleTexts(side1,side2,side3)
            }

        side1 = view.findViewById(R.id.triangle_side1)
        side2 = view.findViewById(R.id.triangle_side2)
        side3 = view.findViewById(R.id.triangle_side3)

        return  builder.create()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as TriangleDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                context.toString() +
                        "must implement ExampleDialogListener"
            )
        }
    }

    interface TriangleDialogListener {
        fun applyTriangleTexts(side1: String?, side2: String?, side3: String?)
    }
}


class RectangleDialog: AppCompatDialogFragment() {

    lateinit  var side1: EditText
    lateinit  var side2: EditText
    lateinit var listener:RectangleDialogListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val inflater: LayoutInflater = activity!!.layoutInflater
        val view: View = inflater.inflate(R.layout.layout_rectangle_dialog, null)

        builder.setView(view)
            .setTitle("Rectangle")
            .setNegativeButton(
                "cancel"
            ) { dialogInterface, i -> }
            .setPositiveButton(
                "ok"
            ) { dialogInterface, i ->
                val side1: String = side1.getText().toString()
                val side2: String = side2.getText().toString()
                listener.applyRectangleTexts(side1,side2)
            }

        side1 = view.findViewById(R.id.triangle_side1)
        side2 = view.findViewById(R.id.triangle_side2)

        return  builder.create()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as RectangleDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                context.toString() +
                        "must implement ExampleDialogListener"
            )
        }
    }

    interface RectangleDialogListener {
        fun applyRectangleTexts(side1: String?, side2: String?)
    }
}*/
