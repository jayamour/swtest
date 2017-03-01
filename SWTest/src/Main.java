import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int T;
	static int N;
	static int [] coin;
	static int W;
	static int [][] dp;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
//		T = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
//		for(int z=1; z<=T; z++){
			
//			N = Integer.parseInt(br.readLine());
		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
			
			coin = new int[N+1];
			
			for(int z=1; z<=N; z++){
				coin[z] = Integer.parseInt(br.readLine());
			}
			
//			for(int i=1; i<=N; i++){
//				coin[i] = Integer.parseInt(st.nextToken());
//			}
			
//			System.out.println("hello");
			
			Arrays.sort(coin);
			
//			W = Integer.parseInt(br.readLine());
			
			dp = new int[N+1][W+1];
			
			int min = Integer.MAX_VALUE;
			
			for(int i=1; i<=N; i++){
				
				for(int j=1; j<=W; j++){
					if(i == 1){
//						System.out.println("j : " + j + ", coin[" + i + "] : " + coin[i]);
						dp[i][j] = j/coin[i];
					} else {
//						System.out.println("j : " + j + ", coin[" + i + "] : " + coin[i]);
						if( j < coin[i] ) {
							dp[i][j] = dp[i-1][j];
						} else {
							dp[i][j] = j/coin[i] + dp[i-1][j%coin[i]];
						}
					}
				}
				if(min > dp[i][W]){
					min = dp[i][W];
				}
			}
			
//			System.out.println("min : " + min);
			
//			for(int i=1; i<=W; i++){
//				
//				if(i < coin[2]){
//					dp[2][i] = dp[1][i];
//				}else{
//					dp[2][i] = i/coin[2] + dp[1][i%coin[2]];
//				}
//			}
//			
//			for(int i=1; i<=W; i++){
//				
//				if(i < coin[3]){
//					dp[3][i] = dp[2][i];
//				}else{
//					dp[3][i] = i/coin[3] + dp[2][i%coin[3]];
//				}
//			}
			
//			printArray(coin);
//			printDoubleArray(dp);
			
//			System.out.println("#" + z + " " + min);
			System.out.println(min);
//		}
		
		br.close();
	}
	
	static void printArray(int [] arr){
		for(int i=1; i<arr.length; i++){
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
	static void printDoubleArray(int [][] arr){
		for(int i=1; i<=N; i++){
			for(int j=1; j<=W; j++){
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
}