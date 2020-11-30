import com.google.gson.*
import com.google.gson.JsonParseException
import java.lang.reflect.Type


class InterfaceAdapter: JsonSerializer<Shape>, JsonDeserializer<Shape>{
    private val CLASSNAME = "CLASSNAME"
    private val DATA = "DATA"

    @Throws(JsonParseException::class)
    override fun deserialize(jsonElement: JsonElement, type: Type?,
                             jsonDeserializationContext: JsonDeserializationContext): Shape? {
        val jsonObject = jsonElement.asJsonObject
        val prim = jsonObject[CLASSNAME] as JsonPrimitive
        val className = prim.asString
        val klass = getObjectClass(className)
        return jsonDeserializationContext.deserialize(jsonObject[DATA], klass)
    }

    private fun getObjectClass(className: String?): Class<*>? {
        return try {
            Class.forName(className)
        } catch (e: ClassNotFoundException) {
            //e.printStackTrace();
            throw JsonParseException(e.message)
        }
    }

    override fun serialize(src: Shape?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        val jsonObject = JsonObject()
        jsonObject.addProperty(CLASSNAME, src!!::class.java.name)
        jsonObject.add(DATA, context!!.serialize(src))
        return jsonObject
    }

}


fun main()
{


    var t = Triangle(1.0, 1.0, 1.0)
    var r = Rectangle(2.0, 2.0)
    var inList: List<Shape> = listOf(t, r)

    //Create our gson instance
    val builder = GsonBuilder()
    builder.registerTypeAdapter(Shape::class.java, InterfaceAdapter())
    val gson = builder.create()

    println(gson.toJson(inList))

   /* println()
    var jsonString: String = Gson().toJson(inList)
    val deserializer = ShapeDeserializer("type")
    deserializer.registerBarnType("Triangle", Triangle::class.java)
    deserializer.registerBarnType("Rectangle", Rectangle::class.java)
    val gson = GsonBuilder().registerTypeAdapter(Shape::class.java, deserializer).create()
    val outList: List<Shape> = gson.fromJson(jsonString, object : TypeToken<List<Shape?>?>() {}.type)
    println(outList)
    var file: BufferedWriter = File("text.json").bufferedWriter()
    file.write(jsonString)
    file.close()*/
}