import java.util.*

fun main()
{


    var a = Triangle(5.0, 3.0, 4.0)
    var b = Triangle(5.0, 3.0, 4.0)

    var list1 = arrayListOf<Shape>(a, b)
    var list2 = Stack<Triangle>()
    var list3: MutableList<Circle> = arrayListOf(Circle(4.0), Circle(2.0))


    list2.push(a)
    list2.push(b)

    var ourClass = ShapeAccumulator()

    ourClass.addAll(list1)
    ourClass.addAll(list2)
    ourClass.addAll(list3)

    ourClass.shapes.forEach { println(it.toString())}

    println("Total area: ${ourClass.getTotalArea()}")
    /*val trliangle1: Shape = Triangle(5.0, 3.0, 4.0)
    val triangle2: Shape = Triangle(6.0, 6.0, 6.0)
    val triangle3: Shape = Triangle(7.0, 4.0, 4.0)
    val rectangle1: Shape = Rectangle( 2.0, 9.0)
    val rectangle2: Shape = Rectangle( 5.0, 5.0)
    val rectangle3: Shape = Rectangle( 3.0, 7.0)
    val square1: Shape = Square( 10.0)
    val square2: Shape = Square( 7.999)
    val square3: Shape = Square( 8.999)
    val circle1: Shape = Circle( 9.5)
    val circle2: Shape = Circle ( 5.3)
    val circle3: Shape = Circle (13.13131313)
    val shapes = listOf(trliangle1, triangle2, triangle3, rectangle1,
    rectangle2, rectangle3, square1, square2, square3, circle1, circle2, circle3)
    var sumArea = 0.0
    shapes.forEach{sumArea+=it.calcArea()}
    println("Суммарная площадь всех фигур равн: $sumArea")
    print("Фигура с максимальнйо площадью - ")
    println(shapes.maxBy (Shape::calcArea).toString())
    print("Фигура с минимальной площадью - ")
    println(shapes.minBy (Shape::calcArea).toString())
    print("Фигура с максимальным периметром - ")
    println(shapes.maxBy (Shape::calcPerimeter).toString())
    print("Фигура с минимальным периметром - ")
    println(shapes.minBy (Shape::calcPerimeter).toString())*/

}