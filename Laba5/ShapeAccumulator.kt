class ShapeAccumulator {
    var shapes = arrayListOf<Shape>()

    fun<T : Shape>  add(figure: T){
        shapes.add(figure)
    }

    fun<T : Shape>  addAll(collection: Collection<T>){
        collection.forEach(){shapes.add(it)}
    }

    fun getMaxAreaShape() : Shape? {
        if(shapes.isEmpty()){
            return null
        }
        return shapes.maxBy(Shape::calcArea)!!
    }

    fun getMinAreaShape() : Shape? {
        if(shapes.isEmpty()){
            return null
        }
        return shapes.minBy(Shape::calcArea)!!
    }

    fun getMaxPerimeterShape() : Shape? {
        if(shapes.isEmpty()){
            return null
        }
        return shapes.maxBy(Shape::calcPerimeter)!!
    }

    fun getMinPerimeterShape() : Shape? {
        if(shapes.isEmpty()){
            return null
        }
        return shapes.minBy(Shape::calcPerimeter)!!
    }

    fun getTotalArea() : Double? {
        if(shapes.isEmpty()){
            return null
        }
        return shapes.sumByDouble { it.calcArea() }
    }

    fun getTotalPerimeter() : Double? {
        if(shapes.isEmpty()){
            return null
        }
        return shapes.sumByDouble { it.calcPerimeter() }
    }
}