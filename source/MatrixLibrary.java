public class MatrixLibrary {
    // Vector dot product [Tích vô hướng của vector]
    static double dot(double[] x, double[] y) {
        if (x.length != y.length) {
            throw new IllegalArgumentException("Vectors must be of the same length");
        }

        double result = 0.0;
        for (int i = 0; i < x.length; i++) {
            result += x[i] * y[i];
        }
        return result;
    }

    // Matrix-matrix product [Tích ma trận-ma trận]
    static double[][] mult(double[][] a, double[][] b) {
        int m = a.length;      // Rows in a
        int n = a[0].length;   // Columns in a (also rows in b)
        int p = b[0].length;   // Columns in b

        if (n != b.length) {
            throw new IllegalArgumentException("Matrix dimensions do not match for multiplication");
        }

        double[][] result = new double[m][p];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < p; j++) {
                for (int k = 0; k < n; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }

    // Transpose [Chuyển vị]
    static double[][] transpose(double[][] a) {
        int rows = a.length;
        int cols = a[0].length;
        double[][] transposed = new double[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposed[j][i] = a[i][j];
            }
        }
        return transposed;
    }

    // Matrix-vector product [Tích ma trận-vecto]
    static double[] mult(double[][] a, double[] x) {
        int m = a.length;      // Rows in matrix a
        int n = a[0].length;   // Columns in matrix a (also length of vector x)

        if (n != x.length) {
            throw new IllegalArgumentException("Matrix and vector dimensions do not match for multiplication");
        }

        double[] result = new double[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[i] += a[i][j] * x[j];
            }
        }
        return result;
    }

    // Vector-matrix product [Tích vô hướng ma trận]
    static double[] mult(double[] y, double[][] a) {
        int n = y.length;      // Length of vector y
        int m = a.length;      // Rows in matrix a
        int p = a[0].length;   // Columns in matrix a

        if (n != m) {
            throw new IllegalArgumentException("Vector and matrix dimensions do not match for multiplication");
        }

        double[] result = new double[p];
        for (int j = 0; j < p; j++) {
            for (int i = 0; i < n; i++) {
                result[j] += y[i] * a[i][j];
            }
        }
        return result;
    }

}
