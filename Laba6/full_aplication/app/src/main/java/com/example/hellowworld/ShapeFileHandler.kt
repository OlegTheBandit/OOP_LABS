import com.google.gson.GsonBuilder
import com.google.gson.JsonParseException
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileNotFoundException
import java.io.FileWriter
import java.lang.reflect.Type
import java.nio.file.Files
import kotlin.jvm.Throws
import java.nio.file.Paths as Paths1


class ShapeFileHandler: FileHandler {

    val shapeListType: Type = object : TypeToken<List<Shape>>() {}.type

    val gson = GsonBuilder()
            .registerTypeAdapter(Shape::class.java, InterfaceAdapter())
            .create()


    override fun toFile(fileName: String ,list: List<Shape>) {

        val shapeListJson = gson.toJson(list, shapeListType)
        val writer = File(fileName).bufferedWriter()
        writer.use{ it.write(shapeListJson) }
    }

    @Throws
    override fun fromFile(fileName: String): List<Shape> {
            if (!File(fileName).exists()) {

                throw  FileNotFoundException("File not found")
            }
            else
            {
                val shapeListJson: String = File(fileName).readText(Charsets.UTF_8)
                return gson.fromJson(shapeListJson, shapeListType)
            }
    }
}
