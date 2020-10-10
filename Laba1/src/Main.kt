fun main()
{
    var matrix1 = Matrix(2,3);
    matrix1.fill()
    matrix1.printMatrix()

    print('\n')

    var matrix2 = Matrix(2, 3);
    matrix2.fill()
    matrix2.printMatrix()

    print('\n')

    matrix1 = matrix1.sumMatrix(matrix1, matrix2)
    matrix1.printMatrix()

    print('\n')

    var matrix3 = Matrix(3, 2)
    matrix3.fill()
    matrix3.printMatrix()

    matrix3= matrix3.multiplyMatrix(matrix1, matrix3)
    println("")
    matrix3.printMatrix()

    println("")

    var matrix4 = Matrix(3, 3)
    matrix4.fill()
    matrix4.printMatrix()
    println("${matrix4.detMatrix()}")

    var matrix5: Matrix = matrix4
    println("${matrix4.IsEqual(matrix5)}")
    println("${matrix4.IsEqual(matrix3)}")


}