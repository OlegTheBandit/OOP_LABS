import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import java.util.*
import kotlin.math.abs
import kotlin.io.readLine as readLine

class Matrix(private var matrix: Array<Array<Double>>) {
    private var rows: Int = matrix.size
    private var columns: Int = matrix[0].size

    init{
        for(i in 0..matrix.size-1){
            if(matrix[0].size != matrix[i].size)
                throw IllegalStateException("This is not matrix.")
        }
    }
    //Взятие числа строк матрицы
    fun getRows() : Int {
        return this.rows
    }

    //Взятие числа колонок матрицы
    fun getColumns() : Int {
        return this.columns
    }

    //Взятие элемента матрицы по значению строка+столбец
    fun getNumber( row:Int, column:Int ) : Double {
        return this.matrix[row][column]
    }

    //Заполнение элемента матрицы по стороке/столбцу
    fun setNumber(number:Double, row: Int, column: Int) {
            this.matrix[row][column] =  number
    }

    //Сравнение размеров матриц
    fun matrixSizeCompare(matrix1: Matrix,  matrix2: Matrix): Boolean {
            return !(matrix1.getRows() != matrix2.getRows() || matrix1.getColumns() != matrix2.getColumns())
    }

    //Проверка матрицы на квадратность
    fun matrixIsSquare(): Boolean {
            return this.getRows()==this.getColumns()
    }


    //Проверка для умножения
    fun multiplyCheck(matrix1: Matrix, matrix2: Matrix): Boolean {
            return matrix1.getColumns() == matrix2.getRows()
    }

    //Сложение матриц
    fun sumMatrix(matrix1: Matrix,  matrix2: Matrix): Matrix {
        if(matrixSizeCompare(matrix1, matrix2)) {
            val newMatrix = Matrix(Array(matrix1.getRows(),{ Array(matrix1.getColumns(),{0.0})}))
            for(i in (0..this.rows-1)) {
                for(j in 0..(this.columns-1)) {
                    newMatrix.setNumber(matrix1.getNumber(i,j)+matrix2.getNumber(i,j), i, j)
                }
            }

            return newMatrix
        }
        else
        {
            throw IllegalStateException("Matrix sizes are not equal")
        }
    }

    //Вычитание матриц
    fun subtractionMatrix(matrix1: Matrix,  matrix2: Matrix): Matrix {
        if(matrixSizeCompare(matrix1, matrix2))
        {
            val newMatrix = Matrix(Array(matrix1.getRows(),{ Array(matrix1.getColumns(), {0.0}) }))

            for(i in (0..this.rows-1)) {
                for(j in 0..(this.columns-1)) {
                    newMatrix.setNumber(matrix1.getNumber(i,j)-matrix2.getNumber(i,j), i, j)
                }
            }

            return newMatrix
        }
        else
        {
            throw IllegalStateException("Matrix sizes are not equal")
        }
    }

    //Перемножение матриц
    fun multiplyMatrix(matrix1: Matrix, matrix2: Matrix) : Matrix {
        if(multiplyCheck(matrix1,matrix2)){
            val newMatrix = Matrix(Array(matrix1.getRows(),{ Array(matrix2.getColumns(), {0.0}) }))

            for(i in (0..newMatrix.getRows()-1)) {
                for(j in 0..newMatrix.getColumns()-1) {
                    newMatrix.setNumber(0.0,i,j)

                    for(k in 0..matrix1.getColumns()-1) {
                        newMatrix.setNumber(newMatrix.getNumber(i,j)+matrix1.getNumber(i,k) * matrix2.getNumber(k,j),i,j)
                    }
                }
            }
            return newMatrix
        }
        else
        {
            throw IllegalStateException("Matrix sizes are not equal")
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



    override fun equals(other: Any?): Boolean {
        if(this === other)
            return true
        if(other !is Matrix)
            return false
        else{
            if(this.getRows() != other.getRows() || this.getColumns() != other.getColumns())
                return false
            for(i in 0..this.getRows()-1)
                for(j in 0..this.getColumns()-1)
                    if(this.getNumber(i,j) != other.getNumber(i,j))
                        return false
            return false
        }
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
            throw IllegalArgumentException("Matrix is not square")
        }
    }

    override fun hashCode(): Int {
        var result = matrix.contentDeepHashCode()
        result = 31 * result + rows
        result = 31 * result + columns
        result = 31 * result + EPS.hashCode()
        return result
    }

    override fun toString(): String {
        var outString: String = ""

        for (i in (0..this.rows - 1)) {
            for (j in 0..(this.columns - 1)) {
                outString += "${matrix[i][j]} "
            }
            outString += "\n"
        }
        return outString
    }


}