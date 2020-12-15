import java.nio.file.Paths
import kotlin.jvm.Throws


interface FileHandler {

    fun fromFile(fileName: String): List<Shape>

    fun toFile(fileName: String, list: List<Shape>)
}