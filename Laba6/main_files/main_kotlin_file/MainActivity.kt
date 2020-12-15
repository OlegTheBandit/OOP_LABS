package com.example.hellowworld

import Circle
import InterfaceAdapter
import Rectangle
import Shape
import ShapeFileHandler
import Square
import Triangle
import android.content.Context
import android.os.Bundle
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.*
import java.lang.reflect.Type


val rectangle = Rectangle(2.0, 2.0)
val inList: List<Shape> = listOf(
        Rectangle(2.0, 2.0),
        Circle(4.0),
        Triangle(1.0, 1.0, 1.0),
    Rectangle(2.0, 2.0),
    Circle(4.0),
    Triangle(1.0, 1.0, 1.0),
    Rectangle(2.0, 2.0),
    Circle(4.0),
    Triangle(1.0, 1.0, 1.0),
    Rectangle(2.0, 2.0),
    Circle(4.0),
    Triangle(1.0, 1.0, 1.0),
    Rectangle(2.0, 2.0),
    Circle(4.0),
    Triangle(1.0, 1.0, 1.0)
)

val shapeListType: Type = object : TypeToken<List<Shape>>() {}.type

val gson = GsonBuilder()
        .registerTypeAdapter(Shape::class.java, InterfaceAdapter())
        .create()
val shapeFileHandler = ShapeFileHandler()


class MainActivity : AppCompatActivity(), RecyclerViewAdapter.OnItemClickListener, FigureDialog.FigureDialogListener{

    lateinit var outList: ArrayList<Shape>
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var string: String? = ""
        if(!fileExist("1.json")){
            try {
                val inputStream: InputStream = assets.open("1.json")
                val size: Int = inputStream.available()
                val buffer = ByteArray(size)
                    inputStream.read(buffer)
                    string = String(buffer)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        else{
           string = load("1.json")
        }
        outList = gson.fromJson(string, shapeListType)
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = RecyclerViewAdapter(outList, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        var itemTouchHelper = ItemTouchHelper(MoveItemAdapter(recyclerView.adapter as RecyclerViewAdapter, outList))
        itemTouchHelper.attachToRecyclerView(recyclerView)
        var fab_add: FloatingActionButton = findViewById(R.id.fab_add)
        fab_add.setOnClickListener(){
            val popupMenu = PopupMenu(this, it)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId){
                    R.id.triangle ->{
                        openFigureDialog(FiguresType.TRIANGLE);
                    }
                    R.id.rectangle ->{
                        openFigureDialog(FiguresType.RECTANGLE);
                    }
                    R.id.circle ->{
                        openFigureDialog(FiguresType.CIRCLE);
                    }
                    R.id.square ->{
                        openFigureDialog(FiguresType.SQUARE);
                    }
                    else -> false
                }
            }
            popupMenu.inflate(R.menu.popup_menu)
            popupMenu.show()
        }

    }

    fun fileExist(fname: String?): Boolean {
        val file = baseContext.getFileStreamPath(fname)
        return file.exists()
    }

    override fun onPause() {
        super.onPause()
        if(isFinishing()){
            val str = gson.toJson(outList, shapeListType)
            save("1.json",str)
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        val str = gson.toJson(outList, shapeListType)
        save("1.json",str)
    }

    override fun onDestroy() {
        super.onDestroy()
        val str = gson.toJson(outList, shapeListType)
        save("1.json",str)
    }

    fun save(fileName: String,fileData: String) {
        val file:String = fileName
        val data:String = fileData
        val fileOutputStream:FileOutputStream
        try {
            fileOutputStream = openFileOutput(file, Context.MODE_PRIVATE)
            fileOutputStream.write(data.toByteArray())
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun load(filename: String): String {
        var fileInputStream: FileInputStream? = null
        fileInputStream = openFileInput(filename)
        var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
        val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
        val stringBuilder: StringBuilder = StringBuilder()
        var text: String? = ""
        text = bufferedReader.readLine();
        return text.toString()
    }

    override fun onItemClick(position: Int) {
        /*Toast.makeText(this, "Figure ${outList[position].javaClass.toString()} clicked", Toast.LENGTH_SHORT).show()*/
        val clickedFigure = outList[position]
        recyclerView.adapter?.notifyItemChanged(position)

    }

    fun openFigureDialog(figureType: FiguresType): Boolean {
        val exampleDialog = FigureDialog(figureType)
        exampleDialog.show(supportFragmentManager, figureType.toString() +" dialog")
        return true
    }

    override fun applyTriangleTexts(side1: String?, side2: String?, side3: String?) {
        try {
            val newTriangle = Triangle(side1!!.toDouble(), side2!!.toDouble(), side3!!.toDouble())
            outList.add(0,newTriangle)
            recyclerView.adapter?.notifyItemInserted(0)
        }
        catch(e: Exception) {
            Toast.makeText(this, "You can't create this figure, try again?", Toast.LENGTH_SHORT).show()
        }
    }

    override fun applyRectangleTexts(side1: String?, side2: String?) {
        try {
            val newRectangle = Rectangle(side1!!.toDouble(), side2!!.toDouble())
            outList.add(0,newRectangle)
            recyclerView.adapter?.notifyItemInserted(0)
        }
        catch(e: Exception) {
            Toast.makeText(this, "You can't create this figure, try again?", Toast.LENGTH_SHORT).show()
        }
    }

    override fun applyCircleTexts(side1: String?) {
        try {
            val newCircle = Circle(side1!!.toDouble())
            outList.add(0,newCircle)
            recyclerView.adapter?.notifyItemInserted(0)
        }
        catch(e: Exception) {
            Toast.makeText(this, "You can't create this figure, try again?", Toast.LENGTH_SHORT).show()
        }
    }

    override fun applySquareTexts(side1: String?) {
        try {
            val newSquare = Square(side1!!.toDouble())
            outList.add(0,newSquare)
            recyclerView.adapter?.notifyItemInserted(0)
        }
        catch(e: Exception) {
            Toast.makeText(this, "You can't create this figure, try again?", Toast.LENGTH_SHORT).show()
        }
    }
}