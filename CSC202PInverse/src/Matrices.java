/* Write a program to read two 3x3 matrices, compute the sum, the product of the two matrices.
 * Then, compute the transpose, cofacter matrix and the determinant of the resultant matrix.
 * Then find the inverse of the first matrix then multiply it by the first column of the second matrix to get the third matrix.
 * Also tested using the matrices given in class. Also the diagonal element of the first matrix and the second matrix compute the standard of deviation of the six
 * elements.
 * In this program write a different method to perform the operations and use proper notation to pass the arguments and to access the elements.
 * Use files for input and output. Make sure to use class and objects. Use constructor and overload constructor to initialize the object.
 */

import java.io.*;
import java.util.*;


public class Matrices {
	private static int[][] a = new int[3][3]; 			   //Matrix from matrix.txt
	private static int[][] b = new int[3][3]; 			  //Matrix from matrixB.txt
	private static int[][] c = new int[3][3];			 //Sum of matrix a and b
	private static int[][] e = new int[3][3]; 			//Multiplication of matrix a and b
	private static int[][] temp = new int[3][3];		 //Temp matrix used to calculate transpose matrix
	private static int[][] coFactor = new int[3][3]; 	//cofactor matrix
	private static int det;                            //determinant
	private static int[][] testMatrix = {{1,2,0},{3,0,1},{-2,5,10}};  //Testing matrix to get a non zero determinant
	private static double[][] inverse = new double[3][3];
	private static double[][] transpose = new double [3][3];
	
	private static BufferedReader br;
	private static FileReader fr;
	private static String fileMatrixA = "matrix";
	private static String fileMatrixB = "matrixB";

	public Matrices(int[][]a) throws IOException {
		this.a = a;
	}
	
	public Matrices(int[][]a, int[][]b) throws IOException {
		this.a = a;
		this.b = b;
	}
	public static void readFileMatrix(String inLine, int[][]a) throws IOException {
		int i = 0;
		BufferedReader br = new BufferedReader(new FileReader(inLine));
		
		while((inLine = br.readLine()) !=null) {
			String[] array = inLine.split(" ");  //splits each element using delimiter of a space	
				for (int j = 0; j <3; j++) {
					int n = Integer.parseInt(array[j]);
						a[i][j] = n;
			}
			i++;
		}
	}
	//Method that reads the 2d matrix and displays
	public static void displayMatrix(int a[][]) {
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[i].length; j++) {
				System.out.print(a[i][j] + "\t");
			}
			System.out.println();
		}
	}
	public static void displayMatrix(double a[][]) {
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[i].length; j++) {
				System.out.print(a[i][j] + "\t");
			}
			System.out.println();
		}
	}

	//Method to calculate the sum of 2 3x3 matrices
	public static void matrixSum(int[][] a, int[][] b, int[][] c) {
		 for(int i=0; i<3; i++)
	       {
	           for(int j=0; j<3; j++)
	           {
	               c[i][j] = a[i][j] + b[i][j];
	           }
	       }
	}
	//method to multiply 2 3x3 matrices and stores to matrix e
	public static void multiplyMatrix(int[][] a, int[][] b, int[][] e) {
		int sum = 0, c, d, k;
		for( c=0; c<3; c++)
         {
            for( d=0; d<3; d++)
            {   
               for( k=0; k<3; k++)
               {
                   sum = sum + a[c][k] * b[k][d];
               }
 
               e[c][d] = sum;
               sum = 0;
            }
         }
	}
	//Method that transposes a 3x3 matrix
	public static void transposeMatrix(int[][] a, int[][] temp) {
		  
	        for (int i = 0; i < 3; i++){
	            for (int j = 0; j < 3; j++){
	                temp[j][i] = a[i][j];
	            }
	        }
	}
	//Method that calculates the cofactor matrix
	public static void coFactor(int[][] a, int[][]cofactor) {
		int row = 0, col=0;
		for (row = 0; row < 3; row++) {
			for (col = 0; col < 3; col++) {
	            	cofactor[row][col] = a[(row + 1) % 3][(col + 1) % 3] * a[(row + 2) % 3][(col + 2) % 3] 
	            			- a[(row + 1) % 3][(col + 2) % 3] * a[(row + 2) % 3][(col + 1) % 3];
	            }
	        }
	}
	public static void determinant(int[][] a) {
		det = a[0][0]*(a[1][1]*a[2][2] - a[2][1]*a[1][2])
			- a[0][1]*(a[1][0]*a[2][2] - a[1][2]*a[2][0])
			+ a[0][2]*(a[1][0]*a[2][1] - a[1][1]*a[2][0]);
	}
	public static void inverseMatrix(double[][] inverse, int det, int[][]temp, double[][]transpose) {
	
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++){
				inverse[i][j] = (double)((1/det)* (double)(temp[i][j]));
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		
		Matrices matricesA = new Matrices(a);
		Matrices matricesB = new Matrices(a, b);
	
		
		System.out.println("MatrixA");
		matricesA.readFileMatrix(fileMatrixA, a);
		matricesA.displayMatrix(a);
		
		System.out.println("MatrixB");
		matricesB.readFileMatrix(fileMatrixB, b);
		matricesB.displayMatrix(b);
		
		System.out.println("Sum of MatrixA and MatrixB" + "\n");
		matricesA.matrixSum(a, b, c);
		matricesA.displayMatrix(c);
		
		System.out.println("Product of Matrix A and Matrix B" + "\n");
		multiplyMatrix(a, b, e);
		displayMatrix(e);
		
		System.out.println("Determinant of the Sum of Matrix A and B");
		determinant(c);
		System.out.println(det);
		
		System.out.println("The Cofactor matrix of the Sum Matrix");
		coFactor(c, coFactor);
		displayMatrix(coFactor);
		
		System.out.println("The tranpose matrix of the Sum Matrix");
		transposeMatrix(coFactor, temp);
		displayMatrix(temp);
		
		System.out.println("The inverse Matrix");
		inverseMatrix(inverse, det, temp, transpose);
		displayMatrix(inverse);

		
	}

}
