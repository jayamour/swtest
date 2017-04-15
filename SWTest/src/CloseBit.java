import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

public class CloseBit {
	
	static int test_case;
	static int N, K;
	static long [][][] dp = new long[100+1][100][2];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("closebit_input.txt"));
		
		test_case = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		
		dp[2][0][0] = 2;
		dp[2][0][1] = 1;
		dp[2][1][0] = 0;
		dp[2][1][1] = 1;
		
//		if()
		
		for(int i=3; i<=100; i++){
			
			for(int j=0; j<i-1; j++){
				
//				int x = i-1;
//				if ( i <= 5){
//					System.out.println("i j 0 : " + (i-1) + ", " + j);
//					System.out.println("i j 1 : " + (i-1) + ", " + j + ", " + (j-1));
//				}

				
//				[5]	[2]	[0]	=	[4]	[1]	[0]	+	[4]	[1]	[1]
//				[5]	[2]	[1]	=	[4]	[1]	[0]	+	[4]	[0]	[1]
				
//				[4]	[1]	[0]	=	[3]	[1]	[0]	+	[3]	[1]	[1]
//				[4]	[1]	[1]	=	[3]	[1]	[0]	+	[3]	[0]	[1]
				
//				[3] [1] [0] =   [2] [1] [0] +   [2] [1] [1]
//				[3] [1] [1] =   [2] [1] [0] +   [2] [0] [1]
				
				if(j == 0){
					dp[i][j][0] = dp[i-1][j][0] + dp[i-1][j][1];
					dp[i][j][1] = dp[i-1][j][0];
				} else {
				
					dp[i][j][0] = dp[i-1][j][0] + dp[i-1][j][1];
					dp[i][j][1] = dp[i-1][j][0] + dp[i-1][j-1][1];
				}
			}
		}
		
		System.out.println(dp[5][2][0] + ", " + dp[5][2][1]);
		
//		for(int z=1; z<=test_case; z++){
//			
//			st = new StringTokenizer(br.readLine());
//			
//			N = Integer.parseInt(st.nextToken());
//			K = Integer.parseInt(st.nextToken());
//			
//			System.out.println("#" + z + " " + dp[N][K][0] + dp[N][K][1]);
//		}
		
		br.close();
	}

}