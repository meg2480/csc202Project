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
	private static int[][] a = new int[3][3];
	private static int[][] b = new int[3][3];
	private static int[][] c = new int[3][3];
	private static int[][] e = new int[3][3];
	private static int[][] temp = new int[3][3];
	private static int[][] coFactor = new int[3][3];
	private static int det;
	private static int[][] inverse = {{1,2,3},{0,4,5},{1,0,-6}};
	
	private static String fileMatrixA = "matrix";
	private static String fileMatrixB = "matrixB";
	
	public static void main(String[] args) throws IOException{
		
		readFileMatrixA(fileMatrixA);
		displayMatrixA(a);
		
		System.out.println("");
		readFileMatrixB(fileMatrixB);
		displayMatrixA(b);
		
		System.out.println("");
		matrixSum(a,b,c);
		displayMatrixA(c);
		
		System.out.println("");
		multiplyMatrix(a,b,e);
		displayMatrixA(e);
		
		System.out.println("");
		transposeMatrix(c, temp);
		displayMatrixA(temp);
		
		System.out.println("");
		transposeMatrix(e, temp);
		displayMatrixA(temp);
		
		System.out.println("");
		determinant(c,coFactor, det);
		System.out.println(det);
		
		System.out.println("");
		determinant(a, coFactor, det);
		System.out.println(det);
		
		System.out.println("");
		
		coFactor(a,coFactor, det);
		displayMatrixA(coFactor);
		
		
	}
	
	public static void readFileMatrixA(String fileMatrixA) throws IOException {
		int i = 0;
		String inLine;
		BufferedReader br = new BufferedReader(new FileReader(fileMatrixA));
	
		while((inLine = br.readLine()) !=null) {
			String[] array = inLine.split(" ");
			
			for (int j = 0; j <3; j++) {
				int n = Integer.parseInt(array[j]);
				a[i][j] = n;
				
			}
			i++;
		}
	}
	
	public static void readFileMatrixB(String filematrixB) throws IOException {
		int i = 0;
		String inLine;
		BufferedReader br1 = new BufferedReader(new FileReader(fileMatrixB));
	
		while((inLine = br1.readLine()) !=null) {
			String[] array = inLine.split(" ");
			
			for (int j = 0; j <3; j++) {
				int n = Integer.parseInt(array[j]);
				b[i][j] = n;
				
			}
			i++;
		}
	}
	
	public static void displayMatrixA(int a[][]) {
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[i].length; j++) {
				System.out.print(a[i][j] + "\t");
			}
			System.out.println();
		}
	}

	
	public static int[][] matrixSum(int [][]a, int[][]b, int [][]c) {
		 for(int i=0; i<3; i++)
	       {
	           for(int j=0; j<3; j++)
	           {
	               c[i][j] = a[i][j] + b[i][j];
	           }
	       }
		 return c;
	}
	
	public static int[][] multiplyMatrix(int[][] a, int[][] b, int[][] e) {
		int m=3, p=3, q=3, sum = 0, c, d, k;
		for( c=0; c<m; c++)
         {
            for( d=0; d<q; d++)
            {   
               for( k=0; k<p; k++)
               {
                   sum = sum + a[c][k] * b[k][d];
               }
 
               e[c][d] = sum;
               sum = 0;
            }
         }
		return e;
	}
	public static int[][] transposeMatrix(int[][] c, int[][] temp) {
		  
	        for (int i = 0; i < 3; i++){
	            for (int j = 0; j < 3; j++){
	                temp[j][i] = c[i][j];
	            }
	        }
	        return temp;   
	}
	
	/*public static int determinantMatrix(int[][]a, int det)
	{
		det = ((a[0][0] *(a[1][1]*a[2][2] - a[1][2]*a[2][1])) 
			- (a[1][0] * (a[0][1]*a[2][2] - a[0][2]*a[2][1])) 
			+ (a[2][0] * (a[0][1]*a[1][2] - a[0][2]*a[1][1])));
		return det;
	} */
	
	public static int[][] coFactor(int[][] a, int[][]cofactor, int det) {
		int row = 0, col=0;
		for (row = 0; row < 3; row++) {
			for (col = 0; col < 3; col++) {
	            	cofactor[row][col] = a[(row + 1) % 3][(col + 1) % 3] * a[(row + 2) % 3][(col + 2) % 3] 
	            			- a[(row + 1) % 3][(col + 2) % 3] * a[(row + 2) % 3][(col + 1) % 3];
	            	if (row == 0)
	        			det += a[0][col] * cofactor[row][col];
	            		
	            }
			
	        }
		return cofactor;
	}
	
	public static void determinant(int[][] a, int[][]cofactor, int det) {
		int row = 0, col=0;
		
		for (row = 0; row < 3; row++) {
			for (col = 0; col < 3; col++) {
	            	cofactor[row][col] = a[(row + 1) % 3][(col + 1) % 3] * a[(row + 2) % 3][(col + 2) % 3] 
	            			- a[(row + 1) % 3][(col + 2) % 3] * a[(row + 2) % 3][(col + 1) % 3];
	            	
	            	if (row == 0)
	        			det += a[0][col] * cofactor[row][col];
	         }
		}
	}
	

}
