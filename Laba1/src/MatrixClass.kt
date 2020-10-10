import java.util.*
import kotlin.math.abs
import kotlin.io.readLine as readLine

class Matrix(_rows: Int, _columns: Int) {
    private var rows: Int
    private var columns: Int
    private var matrix: Array<Array<Double>>

    // Конструктор (явный)
    init {
        rows = _rows
        columns = _columns
        if(rows <= 0 || columns <= 0)
        {
            throw Exception("Заданы неверные размеры матрицы")
        }
        matrix = Array(rows, {Array(columns, {0.0})})
    }

    //Взятие числа строк матрицы
    fun getRows() : Int
    {
        return this.rows
    }

    //Взятие числа колонок матрицы
    fun getColumns() : Int
    {
        return this.columns
    }

    //Взятие элемента матрицы по значению строка+столбец
    fun getNumber( row:Int, column:Int ) : Double
    {
        return this.matrix[row][column]
    }

    //Заполнение матрицы пользователем
    fun fill()
    {
        for(i in (0..this.rows-1)) {
            for(j in 0..(this.columns-1)) {
                print("Элемент ${i+1}-ой строки ${j+1}-го столбца равен: ")
                this.matrix[i][j] =  readLine()!!.toDouble()
            }
        }
    }

    //Заполнение элемента матрицы по стороке/столбцу
    fun setNumber(number:Double, row: Int, column: Int)
    {
            this.matrix[row][column] =  number
    }

    //Сравнение размеров матриц
    fun matrixSizeCompare(matrix1: Matrix,  matrix2: Matrix): Boolean
    {
        if(matrix1.getRows() != matrix2.getRows() || matrix1.getColumns() != matrix2.getColumns())
        {
            return false
        }
        return true
    }

    //Проверка матрицы на квадратность
    fun matrixIsSquare(): Boolean
    {
        if(this.getRows()==this.getColumns())
        {
            return true
        }
        return false
    }


    //Проверка для умножения
    fun multiplyCheck(matrix1: Matrix, matrix2: Matrix): Boolean
    {
        if(matrix1.getRows() == matrix2.getColumns())
        {
            return true
        }
        return false;
    }

    //Сложение матриц
    fun sumMatrix(matrix1: Matrix,  matrix2: Matrix): Matrix
    {
        if(matrixSizeCompare(matrix1, matrix2))
        {
            val newMatrix = Matrix(matrix1.getRows(), matrix2.getColumns())

            for(i in (0..this.rows-1)) {
                for(j in 0..(this.columns-1)) {
                    newMatrix.setNumber(matrix1.getNumber(i,j)+matrix2.getNumber(i,j), i, j)
                }
            }

            return newMatrix
        }
        else
        {
            throw Exception("Размеры матриц не совпадают")
        }
    }

    //Вычитание матриц
    fun subtractionMatrix(matrix1: Matrix,  matrix2: Matrix): Matrix
    {
        if(matrixSizeCompare(matrix1, matrix2))
        {
            val newMatrix = Matrix(matrix1.getRows(), matrix2.getColumns())

            for(i in (0..this.rows-1)) {
                for(j in 0..(this.columns-1)) {
                    newMatrix.setNumber(matrix1.getNumber(i,j)-matrix2.getNumber(i,j), i, j)
                }
            }

            return newMatrix
        }
        else
        {
            throw Exception("Размеры матриц не совпадают")
        }
    }

    //Перемножение матриц
    fun multiplyMatrix(matrix1: Matrix, matrix2: Matrix) : Matrix
    {
        if(multiplyCheck(matrix1,matrix2)){
            val newMatrix = Matrix(matrix1.getRows(), matrix2.getColumns())

            for(i in (0..newMatrix.getRows()-1)) {
                for(j in 0..newMatrix.getColumns()-1) {
                    newMatrix.setNumber(0.0,i,j)

                    for(k in 0..matrix1.getColumns()-1)
                    {
                        newMatrix.setNumber(newMatrix.getNumber(i,j)+matrix1.getNumber(i,k) * matrix2.getNumber(k,j),i,j)
                    }
                }
            }
            return newMatrix
        }
        else
        {
            throw Exception("Матрицы нельзя перемножить")
        }
    }

    //Умножение матрицы на скаляр
    fun multiplyOnSсalar(scalar: Double)
    {
        for(i in 0..this.getRows()-1){
            for(j in 0..this.getColumns()-1) {
                this.setNumber(this.getNumber(i, j) * scalar, i, j)
            }
        }
    }

    fun IsEqual(matrix: Matrix): Boolean
     {
        if(!matrixSizeCompare(this, matrix)){
            return false
        }
        for(i in 0..this.getRows()-1){
            for(j in 0..this.getColumns()-1){
                if(this.getNumber(i,j) != matrix.getNumber(i,j))
                    return false
            }
        }
        return true
    }

    val EPS: Double = 1E-9;

    //Вычисление определителя матрицы
    fun detMatrix(): Double {

        if(matrixIsSquare()) {
            var pivotIndex: Int = -1
            var pivotValue: Double = 0.0
            var determinant: Double = 1.0
            var multiplier: Double
            var ourMatrix: Array<Array<Double>> = this.matrix

            for (i in 0..this.getRows()-1){
                for(j in i..this.getRows()-1){
                    if(abs(ourMatrix[j][i])>pivotValue){
                        pivotIndex = j
                        pivotValue = abs(ourMatrix[j][i]).toDouble()
                    }
                }
                if(pivotValue < EPS){
                    return 0.0
                }
                if(pivotIndex !=i) {
                    ourMatrix[i] = ourMatrix[pivotIndex].also {  ourMatrix[pivotIndex] = ourMatrix[i] }
                    determinant *= -1
                }

                for(j in i+1..this.getRows()-1){
                    if(ourMatrix[j][i]!=0.0){
                        multiplier = (1/ourMatrix[i][i] * ourMatrix[j][i]).toDouble()

                        for(k in i..this.getRows()-1){
                            ourMatrix[j][k] -= ourMatrix[i][k] * multiplier
                        }
                    }
                }

                determinant *= ourMatrix[i][i]

            }

            return determinant
        }
        else{
            throw Exception("Матрицы не квадратная")
        }
    }

    // Вывод матрицы в консоль
    fun printMatrix()
    {
        for(i in (0..this.rows-1)) {
            for(j in 0..(this.columns-1)) {
                print("${matrix[i][j]} ")
            }
            print("\n")
        }
    }





}