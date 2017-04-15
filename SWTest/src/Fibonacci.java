import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Fibonacci {
	
	static int test_case;
	
	static long MOD = 1000000007;
	
	static long [][] v = new long[900001][2];
	
	static long [][] answer = {{1, 0}, {0, 1}};
	static long [][] baseMat = {{1, 1}, {1, 0}};
	static long [][] mat = {{1, 1}, {1, 0}};
//	static long [][] result = new long[2][2];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BigInteger bi;
		
		bi = new BigInteger("1000000000000000000");
		
		long exponent;
		
		exponent = bi.longValue();
		
//		System.out.println("ex : " + exponent);
		
//		long long input = 1000000000000000000;

		//	1, 1, 2, 3, 5, 8, 13, 21, 34, 55
		
		//	1000000 x 1000000
		
		//	array[0] : 1, 2, 3, 4, ... 999998, 999999, 1000000
		//	array[1] : 1000001, 1000002, 1000003, 1000004, ... 1999998, 1999999, 2000000
		//	array[2] : 2000001, 2000002, 2000003, 2000004, ... 2999998, 2999999, 3000000
		//	array[10] : 10000001, 10000002, 10000003, 10000004, ... 10999998, 10999999, 11000000
		//	array[900000] : 900000000001, 900000000002, 900000000003,
		//	10000004, ... 99999999997, 999999999998, 999999999999, 1000000000000
		
		//	f(n+2) = f(n+1) + f(n)
		//	f(n+1) = f(n+1)
		
		/*
		 * 
		 *   n = (1 1)n = f(n+1) f(n)
		 * A 	 (1 0)    f(n)   f(n-1) 
		 * 
		 * 
		 * 
		 * A^n = A^(n/2) x  A^(n/2)
		 * 
		 * if( (n & 1) == 0 ) then
		 * 
		 * (1 1)n/2 x (1 1)n/2
		 * (1 0)      (1 0)
		 * 
		 * f(0) = 0
		 * f(1) = 1
		 * f(2) = 1
		 */
		
//		String n = "999999999998";
		
//		long input = bi.longValue();
		
//		exponent = 10;
		
		//	0, 1, 1, 2, 3, 5
		
		while(exponent > 0) {
			
			//	홀수일 경우
			if( (exponent & 1) == 1 ){
				answer = multiplyMatrix(answer, mat);
			}
			
			mat = multiplyMatrix(mat, mat);
			
			exponent = exponent/2;
		}
		
//		System.out.println();
//		System.out.println("=== answer ===");
//		printMat(answer);
		
		System.out.println(answer[0][1]);
		
		br.close();
	}
	
	/**
	 * 이중배열 곱하기
	 */
	static long [][] multiplyMatrix(long[][] a, long[][] b) {
		
		long [][] result = new long[2][2];
		
//		result[0][0] = (a[0][0] * b[0][0]) % MOD + (a[0][1] * b[1][0]) % MOD; 
//		result[0][1] = (a[0][0] * b[0][1]) % MOD + (a[0][1] * b[1][1]) % MOD;
//		result[1][0] = (a[1][0] * b[0][0]) % MOD + (a[1][1] * b[1][0]) % MOD;
//		result[1][1] = (a[1][0] * b[0][1]) % MOD + (a[1][1] * b[1][1]) % MOD;
		
		result[0][0] = (a[0][0] * b[0][0] + a[0][1] * b[1][0]) % MOD; 
		result[0][1] = (a[0][0] * b[0][1] + a[0][1] * b[1][1]) % MOD;
		result[1][0] = (a[1][0] * b[0][0] + a[1][1] * b[1][0]) % MOD;
		result[1][1] = (a[1][0] * b[0][1] + a[1][1] * b[1][1]) % MOD;
		
		return result;
	}
	
	/**
	 * 이중배열 출력
	 * 
	 */
	static void printMat(long [][] x){
		for(int i=0; i<2; i++){
			for(int j=0; j<2; j++){
				System.out.print(x[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	static double fibonacci(int n){
		
		double i = Math.sqrt(5.0);
		
		return (double) ( (Math.pow((1+i)/2, n) + Math.pow((1-i)/2, n))/i );
	}
}