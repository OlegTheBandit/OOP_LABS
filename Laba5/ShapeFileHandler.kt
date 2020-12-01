import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileWriter
import java.lang.reflect.Type
import java.nio.file.Files
import java.nio.file.Paths as Paths1


class ShapeFileHandler: FileHandler {

    val shapeListType: Type = object : TypeToken<List<Shape>>() {}.type

    val gson = GsonBuilder()
            .registerTypeAdapter(Shape::class.java, InterfaceAdapter())
            .create()

    override fun toFile(fileName: String ,list: List<Shape>) {

        val shapeListJson = gson.toJson(list, shapeListType)
        val writer = File(fileName).bufferedWriter()
        writer.write(shapeListJson)
        writer.close()

    }

    override fun fromFile(fileName: String): List<Shape> {
        while(true) {
            if (!File(fileName).exists()) {

                println("The file does not exist. \n Enter the correct file name: ")
                var fileName = readLine().toString()
            }
            else
            {
                val shapeListJson: String = File(fileName).readText(Charsets.UTF_8)
                return gson.fromJson(shapeListJson, shapeListType)
            }
        }
    }
}
