import com.google.gson.*
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import kotlin.jvm.Throws

class InterfaceAdapter: JsonSerializer<Shape>, JsonDeserializer<Shape> {
    companion object {
        const val CLASSNAME = "CLASSNAME"
        const val DATA = "DATA"
    }


    @Throws(JsonParseException::class)
    override fun deserialize(jsonElement: JsonElement, type: Type,
                             jsonDeserializationContext: JsonDeserializationContext): Shape {
        val jsonObject = jsonElement.asJsonObject
        val prim = jsonObject.get(CLASSNAME) as JsonPrimitive
        val className = prim.asString
        val objectClass = getObjectClass(className)
        return jsonDeserializationContext.deserialize(jsonObject.get(DATA), objectClass)
    }

    private fun getObjectClass(className: String): Class<*> {
        return try {
            Class.forName(className)
        } catch (e: ClassNotFoundException) {
            //e.printStackTrace();
            throw JsonParseException(e.message)
        }
    }

    override fun serialize(jsonElement: Shape, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        val jsonObject = JsonObject()
        jsonObject.addProperty(CLASSNAME, jsonElement.javaClass.name)
        jsonObject.add(DATA, context.serialize(jsonElement))
        return jsonObject
    }
}

