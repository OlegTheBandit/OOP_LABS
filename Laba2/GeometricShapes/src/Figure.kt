import kotlin.math.sqrt

class Triangle(_a:Double, _b:Double, _c:Double) : Shape {
    private val a:Double
    private val b:Double
    private val c:Double

    init {
        a=_a
        b=_b
        c=_c
        if(a >= b+c || b >= a+c || c >= a+b)
        {
            throw Exception("Такой треугольник не может существовать")
        }

        if(a <= 0 || b <= 0 || c <= 0)
        {
            throw Exception("Сторона не может быть отрицательной или нулевой")
        }

    }

    override fun calcArea(): Double {
        return sqrt((this.a+this.b+this.c)/2 * ((this.a+this.b+this.c)/2 - this.a)
        * ((this.a+this.b+this.c)/2 - this.b) * ((this.a+this.b+this.c)/2 - this.c))
    }

    override fun calcPerimeter(): Double {
        return (this.a+this.b+this.c)
    }

    override fun printFigureInfo() {
        println("Треугольник со сторонами: ${this.a}, ${this.b}, ${this.c}. S = ${this.calcArea()}. P = " +
                "${this.calcPerimeter()}")
    }
}

class Rectangle(private val width:Double,private val height:Double): Shape {

    init {
        if(width <= 0 || height <= 0)
            throw Exception("Сторона не может быть отрицательной или нулевой")
    }

    override fun calcArea(): Double {
        return width * height
    }

    override fun calcPerimeter(): Double {
        return 2 * (width + height)
    }

    override fun printFigureInfo() {
        println("Прямоугольник с высотой: ${this.height}, шириной ${this.width}. S = ${this.calcArea()}. P = " +
                "${this.calcPerimeter()}")
    }
}

class Square(private val side: Double): Shape{

    init {
        if(side <= 0)
            throw Exception("Сторона не может быть отрицательной или нулевой")
    }

    override fun calcArea(): Double {
        return side*side
    }

    override fun calcPerimeter(): Double {
        return side*4
    }

    override fun printFigureInfo() {
        println("Квадрат со стороной: ${this.side}. S = ${this.calcArea()}. P = ${this.calcPerimeter()}")
    }
}

class Circle(private val radius: Double): Shape{

    val pi:Double = 3.14159265

    init {
        if(radius <= 0)
            throw Exception("Радиус не может быть отрицательным или равнятся 0")
    }

    override fun calcArea(): Double {
        return pi*radius*radius
    }

    override fun calcPerimeter(): Double {
        return 2*pi*radius
    }

    override fun printFigureInfo() {
        println("Окружность c радиусом: ${this.radius}. S = ${this.calcArea()}, P = ${this.calcPerimeter()}")
    }

}