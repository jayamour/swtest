import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PowerTest {
	
	static int input;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		long a, b, c;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
//		a = Long.parseLong(st.nextToken());
//		b = Long.parseLong(st.nextToken());
//		c = Long.parseLong(st.nextToken());
		
		a = 10;
		b = 11;
		c = 12;
		
		long base = a;
		
		long exp = b;
		
		long answer = 1;
		
		while(exp > 0){
			
			if( (exp & 1) == 1 ){
				answer = answer * base;
				answer = answer % c;
			}
			
			base = base * base;
			base = base % c;
			
			exp = exp/2;
		}
		
		System.out.println(answer);
/*
		input = 5;
		
		int result = 2;
		
		int answer = 1;
		
		while(input > 0){
			
			if( (input & 1) == 1 ){
				answer = answer * result;
				//	answer = 2
				//	answer = 8
			}
			
			result = result * result;
			
			System.out.println(input + ", " + result);
			
			input = input/2;
		}
		
		System.out.println("answer : " + answer);
*/
		
		br.close();
	}
}