import com.google.gson.*
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

fun main()
{

    val rectangle = Rectangle(2.0, 2.0)
    val inList: List<Shape> = listOf(
            Rectangle(2.0, 2.0),
            Circle(4.0),
            Triangle(1.0, 1.0, 1.0)
    )

    val shapeListType: Type = object : TypeToken<List<Shape>>() {}.type

    val gson = GsonBuilder()
            .registerTypeAdapter(Shape::class.java, InterfaceAdapter())
            .create()

    val shapeFileHandler = ShapeFileHandler()
    shapeFileHandler.toFile("1.json",inList)
    val outList: List<Shape> = shapeFileHandler.fromFile("1.json")
    println(outList.toString() == inList.toString())
}