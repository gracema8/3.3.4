public class UnitTest {

    public static void main(String[] args){
        int[][] arr1 = { {0,-1},{1,0}};
        Matrix mat1 = new Matrix(arr1);
        Matrix mat2 = new Matrix(arr1);

        Matrix matrix3 = Matrix.matrixMultiply(mat1, mat2);
        matrix3.print();

        int[] arr2 = {0,90};
        Vector testVect = new Vector(arr2);
        Vector resultVect = Matrix.matrixMultiply(testVect, mat1);
        resultVect.print();

    }
}
