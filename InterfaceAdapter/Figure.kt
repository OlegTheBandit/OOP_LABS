import kotlin.math.sqrt

class Triangle(inpA:Double, inpB:Double, inpC:Double) : Shape {
    private val a:Double
    private val b:Double
    private val c:Double
    val type = "Triangle"

    init {
        a=inpA
        b=inpB
        c=inpC
        if(a >= b+c || b >= a+c || c >= a+b)
        {
            throw IllegalArgumentException("Such a triangle cannot exist")
        }

        if(a <= 0 || b <= 0 || c <= 0)
        {
            throw IllegalArgumentException("The side cannot be negative or zero")
        }

    }

    override fun calcArea(): Double {
        return sqrt((this.a+this.b+this.c)/2 * ((this.a+this.b+this.c)/2 - this.a)
        * ((this.a+this.b+this.c)/2 - this.b) * ((this.a+this.b+this.c)/2 - this.c))
    }

    override fun calcPerimeter(): Double {
        return (this.a+this.b+this.c)
    }

    override fun toString(): String {
        return "Triangle with sides: ${this.a}, ${this.b}, ${this.c}. S = ${this.calcArea()}. P = " +
                "${this.calcPerimeter()}"
    }
}

class Rectangle(private val width:Double,private val height:Double): Shape {

    val type = "Rectangle"
    init {
        if(width <= 0 || height <= 0)
            throw IllegalArgumentException("The side cannot be negative or zero")
    }

    override fun calcArea(): Double {
        return width * height
    }

    override fun calcPerimeter(): Double {
        return 2 * (width + height)
    }

    override fun toString(): String {
        return "A rectangle with a height: ${this.height}, width ${this.width}. S = ${this.calcArea()}. P = " +
                "${this.calcPerimeter()}"
    }
}

class Square(private val side: Double): Shape{

    val classType: String = "Square"

    init {
        if(side <= 0)
            throw IllegalArgumentException("The side cannot be negative or zero")
    }

    override fun calcArea(): Double {
        return side*side
    }

    override fun calcPerimeter(): Double {
        return side*4
    }

    override fun toString(): String {
        return "A square with a side: ${this.side}. S = ${this.calcArea()}. P = ${this.calcPerimeter()}"
    }
}

class Circle(private val radius: Double): Shape{

    val classType: String = "Circle"
    val pi:Double = 3.14159265

    init {
        if(radius <= 0)
            throw java.lang.IllegalArgumentException("The radius cannot be negative or equal to 0")
    }

    override fun calcArea(): Double {
        return pi*radius*radius
    }

    override fun calcPerimeter(): Double {
        return 2*pi*radius
    }

    override fun toString(): String {
        return "Circle with radius: ${this.radius}. S = ${this.calcArea()}, P = ${this.calcPerimeter()}"
    }

}