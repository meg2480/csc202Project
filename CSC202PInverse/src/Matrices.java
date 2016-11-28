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
	private static int sod;
	
	private static double[][] inverse = new double[3][3];   //Matrix for inverse
	private static double[][] transpose = new double [3][3];//Matrix for the transpose
	private static int[][] inverseProduct = {{1}, {4}, {7}}; //First column of second Matrix
	
	
	private static PrintWriter pw;
	private static BufferedReader br;

	private static String fileMatrixA = "matrix";           //first matrix
	private static String fileMatrixB = "matrixB";			//second matrix
	private static String fileMatrixSum = "matrixSum";      //sum of matrix A and B
	private static String fileMatrixProduct = "matrixProduct";  //product of matrix A and B
	private static String fileMatrixCoFactorOfSumMatrix = "matrixCoFactorOfSumMatrix"; //Cofactor of sum of matrix A and B
	private static String fileMatrixTransposeOfSumMatrix = "matrixTransposeOfSumMatrix"; //Transpose of the cofactorSum matrix
	private static String fileMatrixCoFactor = "matrixCo";              //Cofactor of first matrix
	private static String fileMatrixTranspose = "matrixTranspose";      //transpose of first matrix cofactor
	private static String fileMatrixInverse = "matrixInverse";           //Inverse of first matrix
	private static String fileInverseMatrixProduct = "inverseMatrixProduct"; //the product of first matrix to first column of matrix b
	private static String fileSOD = "StandardOfDeviation";            //SoD of matrix A and B
	//Constructor
	public Matrices(int[][]a) {
		this.a = a;
	}
	//Overloaded Constructor
	public Matrices(int[][]a, int[][]b)  {
		this.a = a;
		this.b = b;
	}
	//Read method to read from file into an int matrix
	public static void readFile(String inLine, int[][]a) throws IOException {
		int i = 0;
		br = new BufferedReader(new FileReader(inLine));
		while((inLine = br.readLine()) !=null) {
			String[] array = inLine.split(" ");  //splits each element using delimiter of a space	
				for (int j = 0; j < 3; j++) {
					int n = Integer.parseInt(array[j]);
						a[i][j] = n;
			}
			i++;
		}
		br.close();
	}
	//overloaded method to read into a double matrix
	public static void readFile(String inLine, double[][]a) throws IOException {
		int i = 0;
		br = new BufferedReader(new FileReader(inLine));
		while((inLine = br.readLine()) !=null) {
			String[] array = inLine.split(" ");  //splits each element using delimiter of a space	
				for (int j = 0; j <3; j++) {
					double n = Double.parseDouble(array[j]);
						a[i][j] = n;
			}
			i++;
		}
		br.close();
	}
	//Write method to write matrix to file with formatting
	public static void writeFile(String file, int[][]a) throws IOException {
		pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a.length; j++) {
				if(i == 0 && j == 2 || i == 1 && j ==2) {
					pw.print(a[i][j] + " ");
					pw.println("");
				}
				else 
				pw.print(a[i][j] + " ");
			}
		}
		pw.close();
	}
	//Write overloaded method to write matrix to file with formatting
	public static void writeFile(String file, double[][]a) throws IOException {
		pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a.length; j++) {
				if(i == 0 && j == 2 || i == 1 && j ==2) {
					pw.print(a[i][j] + " ");
					pw.println("");
				}
				else 
				pw.print(a[i][j] + " ");
			}
		}
		pw.close();
	}
	//overloaded method to write an int to file
	public static void writeFile(String file, int a) throws IOException {
		pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
					pw.print(a);
					pw.close();
	}
	//Method that reads the 2d matrix and prints to console
	public static void displayMatrix(int a[][]) {
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[i].length; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
	}
	//overloaded method to read a double 2d matrix and prints to console
	public static void displayMatrix(double a[][]) {
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[i].length; j++) {
				System.out.print(a[i][j] + " ");
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
	public static void matrixProduct(int[][] a, int[][] b, int[][] e) {
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
	//Method that transposes a 3x3 matrix to temp
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
	//Method that calculates the determinant
	public static void determinant(int[][] a) {
		det = a[0][0]*(a[1][1]*a[2][2] - a[2][1]*a[1][2])
			- a[0][1]*(a[1][0]*a[2][2] - a[1][2]*a[2][0])
			+ a[0][2]*(a[1][0]*a[2][1] - a[1][1]*a[2][0]);
	}
	
	//method that calculates the inverse matrix
	public static void inverseMatrix(double[][] inverse, int det, double[][]transpose) {
	
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++){
				inverse[i][j] = (((transpose[i][j])/det));
			}
		}
	}
	//method that calculates the Standard of deviation
	public static void CalculateDiagonal(int[][]a, int[][]b) {
		sod = ((a[0][0] + a[1][1] + a [2][2] + b[0][0] + b[1][1] + b[2][2])/6);
	}
	//method that saves the first column of b to another matrix
	public static void setFirstColumn(int[][]a, int[][]b) {

				inverseProduct[0][0] = b[0][0];
				inverseProduct[1][0] = b[1][0];
				inverseProduct[2][0] = b[2][0];
	}
	//method that calculates the product of 3x3 matrix and 3x1 matrix
	public static void InverseProduct(int[][]a, int[][]b, int[][]e) {
		int sum = 0;
		for (int c = 0; c < b[0].length; c++) {
	        for (int k = 0; k < a.length; k++) {
	            sum = 0;
	            for (int f = 0; f < b.length; f++) {
	                sum = sum + a[k][f] * b[f][c];
               }
               e[k][c] = sum;
            }
         }
	}
	//main
	public static void main(String[] args) throws IOException{
		//create objects
		Matrices matricesA = new Matrices(a);
		Matrices findInverseOfFirstMatrix = new Matrices(a, b);
		//reads from 2 files matrix.txt and matrixB.txt and displays
		System.out.println("MatrixA");
		matricesA.readFile(fileMatrixA, a);
		matricesA.displayMatrix(a);
		
		System.out.println("MatrixB");
		matricesA.readFile(fileMatrixB, b);
		matricesA.displayMatrix(b);
		
		//Calls to sum method, display, and write
		System.out.println("Sum of MatrixA and MatrixB" + "\n");
		matricesA.matrixSum(a, b, c);
		matricesA.displayMatrix(c);
		matricesA.writeFile(fileMatrixSum, c);
		
		//calls to product method, displays, and writes
		System.out.println("Product of Matrix A and Matrix B" + "\n");
		matricesA.matrixProduct(a, b, e);
		matricesA.displayMatrix(e);
		matricesA.writeFile(fileMatrixProduct, e);
		
		//calls to determinant method and prints
		System.out.println("Determinant of the Sum of Matrix A and B");
		matricesA.determinant(c);
		System.out.println(det);
		
		//calls to cofactor method, displays, and writes
		System.out.println("The Cofactor matrix of the Sum Matrix");
		matricesA.coFactor(c, coFactor);
		matricesA.displayMatrix(coFactor);
		matricesA.writeFile(fileMatrixCoFactorOfSumMatrix, coFactor);
		
		//calls to tranpose matrix, writes to file, then prints
		System.out.println("The tranpose matrix of the Sum Matrix");
		matricesA.transposeMatrix(coFactor, temp);
		matricesA.writeFile(fileMatrixTransposeOfSumMatrix, temp);
		matricesA.displayMatrix(temp);
		
		//Find the inverse of the first Matrix
		//calls to determinant method to calculate the first matrix's determinant
		System.out.println("Determinant of the Sum of Matrix A and B");
		findInverseOfFirstMatrix.determinant(a);
		System.out.println(det);
		
		//calls to cofactor matrix, displays, and writes
		System.out.println("The Cofactor matrix of the Sum Matrix");
		findInverseOfFirstMatrix.coFactor(c, coFactor);
		findInverseOfFirstMatrix.displayMatrix(coFactor);
		findInverseOfFirstMatrix.writeFile(fileMatrixCoFactor, coFactor);
		
		//calls to tranpose matrix, writes to file as int, then reads back into double, then prints
		//Reason for this is to convert the int[][] to an double[][] to calculate the inverse
		//Although casting to double within the formula in the inverseMatrix method works as well
		System.out.println("The tranpose matrix of the Sum Matrix");
		findInverseOfFirstMatrix.transposeMatrix(coFactor, temp);
		findInverseOfFirstMatrix.displayMatrix(temp);
		findInverseOfFirstMatrix.writeFile(fileMatrixTranspose, temp);
		findInverseOfFirstMatrix.readFile(fileMatrixTranspose, transpose); 
		findInverseOfFirstMatrix.displayMatrix(transpose);
		
		//Calls to the inverseMatrix method, displays the inverse, and prints
		System.out.println("The inverse Matrix of the Sum Matrix");
		findInverseOfFirstMatrix.inverseMatrix(inverse, det, transpose);
		findInverseOfFirstMatrix.displayMatrix(inverse);
		findInverseOfFirstMatrix.writeFile(fileMatrixInverse, inverse);
		
		//Calls to the method that calculates the standard of deviation, prints it, and writes to file
		System.out.println("The standard of Deviation");
		findInverseOfFirstMatrix.CalculateDiagonal(a, b);
		System.out.println(sod);
		findInverseOfFirstMatrix.writeFile(fileSOD, sod);
		
		//calls to the method that calculates the product of first matrix and the first column of matrix B
		findInverseOfFirstMatrix.InverseProduct(b, inverseProduct ,e);
		findInverseOfFirstMatrix.displayMatrix(e);
		findInverseOfFirstMatrix.writeFile(fileInverseMatrixProduct, e);
	} 

}
