import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BiggestSquare {
	
	static int test_case;
	
	static int N, M;
	
	static int [][] map;
	static int [][] dp;
	
	static int answer = 0;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("square_input.txt"));
		
//		test_case = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		
//		for(int z=1; z<=test_case; z++){
			
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new int[N+1][M+1];
			dp = new int[N+1][M+1];
			
			
			for(int i=1; i<=N; i++){
				
				st = new StringTokenizer(br.readLine());
				
				for(int j=1; j<=M; j++){
					map[i][j] = Integer.parseInt(st.nextToken());
					checkSquare(i, j);
				}
			}
			
			
//			printMatrix(map);			
//			printMatrix(dp);
			
			System.out.println((answer+1)*(answer+1));
//			System.out.println("#" + z + " " + (answer+1));
//		}
		
		br.close();

	}
	
	static void checkSquare(int x, int y){
		
		for(int i=1; i<=x; i++){
			
			for(int j=1; j<=y; j++){
				
				//	현 위치 값이 1일 경우, dp 배열의 같은 위치에 1 체크
				if(map[i][j] == 1){
					dp[i][j] = 0;
					continue;
				}else{
					
					if( (i>1 && j>1) ){
						
						if(map[i-1][j-1] == 1){
							dp[i][j] = 0;
							continue;
						}
					}
					
					if( i>1 ){
						
						//	한 칸 위 값 체크
						if(map[i-1][j] == 1){
							dp[i][j] = 0;
							continue;
						}
					}

					if( j>1 ){
						
						//	한 칸 왼쪽 값 체크
						if(map[i][j-1] == 1){
							dp[i][j] = 0;
							continue;
						}
					}
					
					dpCheck(i, j);
				}
			}
		}
	}
	
	static void dpCheck(int i, int j){
		
		int x = 0, y = 0, z = 0;
		
		if( (i>1 && j>1) ){

			x = dp[i-1][j-1];
		}
		
		if( i>1 ){
			y = dp[i-1][j];
		}
		
		if( j>1 ){
			z = dp[i][j-1];
		}
		
		dp[i][j] = findMin(x, y, z);
	}
	
	static int findMin(int x, int y, int z){
		
		int min = Math.min(Math.min(x, y), z) + 1;
		
		if(answer < min){
			answer = min;
		}
		
//		return min+1;
		return min;
	}
	
	static void printMatrix(int [][] array){
		System.out.println("==========");
		for(int i=1; i<=N; i++){
			for(int j=1; j<=M; j++){
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("==========");
	}
}