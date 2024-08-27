package java;

/**
 * Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
 * You must do it in place.
 *
 * @see https://leetcode.com/problems/set-matrix-zeroes/description/
 */
public class SetMatrixZeroes {
    public void setZeroes(int[][] matrix) {
        boolean firstRowHasZero = false, firstColHasZero = false;
        
        for (int i=0;i<matrix[0].length;i++) {
            if (matrix[0][i] == 0) firstRowHasZero = true;
        }

        for (int i=0;i<matrix.length;i++) {
            if (matrix[i][0] == 0) firstColHasZero = true;
        }

        for (int i=1;i<matrix.length;i++) {
            for (int j=1;j<matrix[i].length;j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        for (int i=1;i<matrix.length;i++) {
            for (int j=1;j<matrix[i].length;j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (firstRowHasZero) {
            for (int i=0;i<matrix[0].length;i++) {
                matrix[0][i] = 0;
            }
        }
        if (firstColHasZero) {
            for (int i=0;i<matrix.length;i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
