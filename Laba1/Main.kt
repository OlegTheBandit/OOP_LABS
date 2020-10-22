fun main()
{
    val matrix1 = Matrix(arrayOf(arrayOf(2.0, -1.0), arrayOf(5.0, 3.0)))
    val matrix2 = Matrix(arrayOf(arrayOf(3.0, 1.0, 0.0), arrayOf(2.0, -1.0, 5.0)))

    val matrix3=matrix1.multiplyMatrix(matrix1,matrix2)
    println(matrix3.toString())

    val matrix4 = Matrix(arrayOf(arrayOf(1.0, -2.0), arrayOf(3.0, 1.0)))
    println(matrix4.detMatrix())
}