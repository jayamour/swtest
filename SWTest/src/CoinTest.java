
public class CoinTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		{
			int n = 3;
			int k = 10;

			int[] coin = new int[n + 1];

			int[] dp = new int[100];

			coin[1] = 1;
			coin[2] = 2;
			coin[3] = 5;

			dp[0] = 1;

			for (int i = 1; i <= 3; i++) {
				for (int j = coin[i]; j <= 10; j++) {
					dp[j] += dp[j - coin[i]];
					//	dp[1] = dp[1] + dp[1 - 1];
					//	dp[2] = dp[2] + dp[2 - 1];
					//	dp[3] = dp[2] + dp[3 - 1];
					
					//	dp[4] = dp[4] + dp[4 - 2];
					if(i==2){
						System.out.println(dp[j]);
					}
				}
			}
			System.out.println(dp[k]);
		}

	}
}