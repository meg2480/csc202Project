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
	private static int[][] inverse = new int[3][3];
	
	private static String fileMatrixA = "matrix";
	private static String fileMatrixB = "matrixB";

	public Matrices(int[][]a) throws IOException {
		a = readFileMatrixA(fileMatrixA);
	}
	
	public Matrices(String inLine) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(inLine));
		readFileMatrixA(inLine);
	}
	public Matrices(String inLine, int[][]c) throws IOException {
		readFileMatrixA(inLine);
		readFileMatrixA(fileMatrixA);
		c = c;
	}
	
	public static int[][] readFileMatrixA(String inLine) throws IOException {
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
		return a;
	}
	//Method that reads the 2d matrix and displays
	public static void displayMatrixA(int a[][]) {
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[i].length; j++) {
				System.out.print(a[i][j] + "\t");
			}
			System.out.println();
		}
	}

	//Method to calculate the sum of 2 3x3 matrices
	public static void matrixSum(int [][] a, int[][] b, int[][] c) {
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
	public static void coFactor(int[][] a, int[][]cofactor, int det) {
		int row = 0, col=0;
		for (row = 0; row < 3; row++) {
			for (col = 0; col < 3; col++) {
	            	cofactor[row][col] = a[(row + 1) % 3][(col + 1) % 3] * a[(row + 2) % 3][(col + 2) % 3] 
	            			- a[(row + 1) % 3][(col + 2) % 3] * a[(row + 2) % 3][(col + 1) % 3];
	            }
	        }
	}
	//not working
	public static void determinant(int[][] a) {
		det = a[0][0]*(a[1][1]*a[2][2] - a[2][1]*a[1][2])
			- a[0][1]*(a[1][0]*a[2][2] - a[1][2]*a[2][0])
			+ a[0][2]*(a[1][0]*a[2][1] - a[1][1]*a[2][0]);
	}
	
	public static void inverseMatrix(int[][] a) {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++){
				inverse[i][j] = (int) ((1.0/det)*temp[i][j]);
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		
		Matrices matricesA = new Matrices();
		Matrices matricesB = new Matrices(inLine);
		Matrices matricesC = new Matrices(fileMatrixB, c);
		
		matricesA.readFileMatrixA(fileMatrixA);
		matricesA.displayMatrixA(a);
		
		System.out.println("");
		matricesB.readFileMatrixA(fileMatrixB);
		matricesB.displayMatrixA(a);
		
		
		System.out.println("");
		matricesC.matrixSum(matricesC.a, matricesC.b, matricesC.c);
		matricesC.displayMatrixA(c);
		;
		

		/*
		//call sum method to calculate sum of a and b to store to c
		System.out.println("");
		matrixSum(a,b,c);
		displayMatrixA(c);
		//call multiply method and store to e
		System.out.println("");
		multiplyMatrix(a,b,e);
		displayMatrixA(e);
		// call to transpose method using metrix c
		System.out.println("");
		transposeMatrix(c, temp);
		displayMatrixA(temp);
		// call to transpose method using matrix e
		System.out.println("");
		transposeMatrix(e, temp);
		displayMatrixA(temp);
		
		//call to determinant method
		System.out.println("");
		determinant(inverse); // able to calculate determinant however unable to print or store it outside of method...
		System.out.println(det); //det prints out 0 when infact it is not true
		//call to cofactor method
		System.out.println("");
		coFactor(a,coFactor, det); //calculates cofactor but doesn't take into account negative signs right...
		displayMatrixA(coFactor);
		
		System.out.println("");
		inverseMatrix(temp);
		displayMatrixA(temp);  */
	}

}
