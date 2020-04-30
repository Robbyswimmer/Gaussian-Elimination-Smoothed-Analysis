public class StandardGaussianElimination {
    /**
     * Performs Gaussian Elimination on Matrix A and returns the result
     *@param n represents the size of the matrix (n x n)
     *@param A The matrix that is being transformed into upper triangular form
     *@param b a vector of size n
     * @return returns the matrix A, after Gaussian Elimination has been performed
     */
    public static int[][] gaussianElimination(int n, int[][] A, int[] b) {
        int[][] triangularForm = upperTriangularTransform(n, A, b);
        int[][] finalMatrix = backwardsSubstitution(n, triangularForm, b);
        return finalMatrix;
    }
    /**
     * Puts matrix A into upper triangular form as the first step of
     * Gaussian Elimination
     * @param n represents the size of the matrix (n x n)
     * @param A The matrix that is being transformed into upper triangular form
     * @param b a vector of size n
     * @return returns the newly changed matrix, A, in its upper triangular form
     */
    public static int[][] upperTriangularTransform(int n, int[][] A, int[] b) {
        for (int k = 1; k <= n - 1; k++) {
            for (int i = k + 1; i <= n; i++) {
                int l = A[i][k] / A[k][k];
                for (int j = k + 1; j <= n; j++) {
                    A[i][j] = A[i][j] - l*A[k][j];
                }
                b[i] = b[i] - l*b[k];
            }
        }
        return A;
    }

    /**
     * Performs the back substitution to finish the Gaussian Elimination
     * @param n represents the size of the matrix (n x n)
     * @param A The matrix that is being back substituted
     * @param b The vector of coefficients from system A
     * @return returns the matrix A after completing the Gaussian Elimination
     */
    public static int[][] backwardsSubstitution(int n, int A[][], int[] b) {
        int x[] = new int[b.length];
        for (int row = n; row >=  n; row--) {
            x[row] = b[row];
            for (int col = row + 1; col <= n; col++) {
                x[row] = x[row] - A[row][col]*x[col];
            }
            x[row] = x[row] / A[row][row];
        }
        return A;
    }
}
