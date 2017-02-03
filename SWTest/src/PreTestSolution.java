import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PreTestSolution {

	static long [][] dp;
	static long [][] array;
	static int [][] loc;
	
	static int test_case;
	
	static int day;
	static int injection;
	
	static long [] fatigue;
	static long [] health;
	static long [] painToday;
	static long [] painAcc;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
//		BufferedReader br = new BufferedReader(new FileReader("sample_input.txt"));
//		BufferedReader br = new BufferedReader(new FileReader("sample_test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		
		test_case = Integer.parseInt(br.readLine());
		
		StringTokenizer st = null;
		
		for(int x=1; x<=test_case; x++){
//		for(int x=1; x<=1; x++){
			
			st = new StringTokenizer(br.readLine());
			
			day = Integer.parseInt(st.nextToken());
			injection = Integer.parseInt(st.nextToken());
			
			dp = new long [injection+1][day+1];
			array = new long [day+1][day+1];
			loc = new int [injection+1][day+1];
			
			fatigue = new long[day+1];
			health = new long[day+1];
			painToday = new long[day+1];
			painAcc = new long[day+1];
			
			st = new StringTokenizer(br.readLine());
			
			for(int i=1; i<=day; i++){
				health[i] = Integer.parseInt(st.nextToken());
			}
			
//			System.out.println(day + " " + injection);
//			printArray(health);
			
			makeNoInjection();
			
			makeDP();
			
//			printArray(fatigue);
//			printArray(painToday);
//			printArray(painAcc);
			
//			printDoubleArr(array);

//			printDp(dp);

//			if(x == 1){
//				printDp(loc);
//				printLongDp(array);
//			}
			
			if(injection != day){
				System.out.print("#" + x + " " + dp[injection][day] + " ");
				printLoc(injection, day);
				System.out.println();
			}else{
				System.out.print("#" + x + " " + 0 + " ");
				for(int i=1; i<=day; i++){
					System.out.print(i + " ");
				}
				System.out.println();
			}
		}
		
		br.close();

	}
	
	static void printLoc(int i, int d){

		if(i >= 1){
			printLoc(i-1, loc[i][d]);	//	printLoc(1, 6);
			//	printLoc(0, 3);
		}

		if(d != day){
			System.out.print(d + " ");
		}
	}
	
	static void makeDP(){
		
		for(int i=1; i<=injection; i++){
			
			for(int j=i+1; j<=day; j++){
				
				long min = dp[i-1][i] + array[i+1][j];
				//	dp[0][1] + array[2][2];
				//	dp[0][1] + array[2][3];
				//	dp[0][1] + array[2][4];
				//	dp[1][2] + array[3][3];
//				System.out.println("dp["+ i + "]["+ j + "] : dp[" + (i-1) + "][" + i + "] + array[" + (i+1) + "][" + j +"] : " + min);				
				//	dp[2][4] : dp[1][2] + array[3][4] : 468

				loc[i][j] = i;
				
				if((i+1) == j){
					//	dp[1][2]
//					System.out.println("dp["+ i + "]["+ j + "] : dp[" + (i-1) + "][" + i + "] + array[" + (i+1) + "][" + j +"] : " + min);
					dp[i][j] = min;

				}else{
					//	i=2, j=4, k=3;
					for(int k=i+1; k<j; k++){

//						System.out.println("zzdp["+ i + "]["+ j + "] : dp[" + (i-1) + "][" + k + "] + array[" + (k+1) + "][" + j +"] : " + (dp[i-1][k]) + " + " + (array[k+1][j]) + ", min : " + min);
						//	min > dp[0][2] + array[3][3]
						//	min	> dp[0][2] + array[3][4]
						//	min	> dp[0][3] + array[4][4]
						//	min	> dp[1][3] + array[4][4]
						if(min > dp[i-1][k] + array[k+1][j]){
							loc[i][j] = k;
							min = dp[i-1][k] + array[k+1][j];
						}
					}


//					System.out.println("dp["+ i + "]["+ j + "] : " + min);
					dp[i][j] = min;
				}
			}
			
		}
	}
	
	static void makeNoInjection(){
		
		fatigue[1] = 0;
		painAcc[1] = 0;
		
		for(int i=1; i<day; i++){
			
			fatigue[i+1] = fatigue[i] + health[i];
			
			painToday[i] = fatigue[i] * health[i];
			
			if(i>1){
				painAcc[i] = painToday[i] + painAcc[i-1];
				dp[0][i] = painAcc[i];
			}
			
			if(i==(day-1)){
				painToday[day] = fatigue[day] * health[day];
				painAcc[day] = painToday[day] + painAcc[day-1];
				
				dp[0][day] = painAcc[day];
			}
		}
		
		for(int from=2; from<=day; from++){
			for(int to=from; to<=day; to++){
				
				if(from == to){
					array[from][to] = 0;
				}else if( (from+1) == to ){
					array[from][to] = health[from] * health[to]; 
				}else{
					//	array[2][4] = 63 + (22-6)*11
					//	array[2][4] = array[2][3] + (fatigue[4]-fatigue[2])*health[4]
					array[from][to] = array[from][to-1] + (fatigue[to]-fatigue[from]) * health[to];
				}
			}
		}
	}
	
	static void printLongDp(long [][] arr){
		for(int i=0; i<=injection; i++){
			for(int j=1; j<=day; j++){
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	static void printDp(int [][] arr){
		for(int i=0; i<=injection; i++){
			for(int j=1; j<=day; j++){
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	static void printDoubleArr(int [][] arr){
		for(int i=1; i<=day; i++){
			for(int j=1; j<=day; j++){
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	static void printArray(int [] array){
		for(int i=1; i<array.length; i++){
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
}