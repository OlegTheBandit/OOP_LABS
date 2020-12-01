import java.nio.file.Paths

interface FileHandler {

    fun fromFile(fileName: String): List<Shape>

    fun toFile(fileName: String, list: List<Shape>)
}