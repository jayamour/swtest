import java.util.ArrayList;

public class getPrimeTest {
	
	static ArrayList<Integer> prime = new ArrayList<Integer>();
	static int [] array;

	public static void main(String[] args) {
		
		int x = 500001;
//		int x = 101;
//		int x = 1000000001;
		
		long start = System.currentTimeMillis();
		
		array = new int[x];
		
		for(int i=1; i<=(x-1); i++){
			//	initialize of array
			array[i] = i;
		}
		
//		getPrimebySieve(x-1);
//		getPrimebySieve2(x-1);
		
		System.out.println(isPrime(1124681231));
		
		System.out.println(1124681231/10169);
		
		System.out.println(110599 * 10169);
		
//		System.out.println(isPrime(7));
//		getPrimeNumber(50000);
//		getPrimeNumberList(1000000);
		long end = System.currentTimeMillis();
		System.out.println("time : " + (end-start));
	}
	
	static boolean isPrime(int n){
		
		if(n<=1){
			return false;
		}
		
		//	0010 & 0001 => 짝수
		//	0011 & 0001	=> 홀수
		if((n&1) == 0){
			return (n == 2);
		}
		
		int range = (int) Math.sqrt(n);
		
//		for(int i=3; i<=range; i=i+2){
//			if(n%i == 0){
//				System.out.println(i);
//				return false;
//			}
//		}
		
		for(int i=range; i>=1; i=i-1){
			if(n%i == 0){
				System.out.println(i);
//				return false;
			}
		}
		
		return true;
	}
	
	static void getPrimebySieve2(int n){
		
		int k = (int) Math.sqrt(n);
		
		for(int i=2; i<=k; i++){
			
			if(array[i] == 0){
				continue;
			}
			
			//	array[4, 6, 8, ...]
			//	array[6, 9, 12, ...]
			for(int j=i*2; j<=n; j=j+i){
				array[j] = 0;
			}
		}
		
		for(int i=2; i<=n; i++){
			if(array[i] != 0){
				System.out.println(array[i]);
			}
		}
	}
	
	static void getPrimebySieve(int n){
		
		for(int i=2; i<=n; i++){
			for(int j=2; j<=n; j++){
				
				if(array[j] == 0){
					continue;
				}
				
				//	i의 배수를 0으로 치환
				if( array[j] != i && array[j]%i == 0){
					array[j] = 0;
				}
			}
		}
		
		for(int i=2; i<=n; i++){
			if(array[i] != 0){
				System.out.println(array[i]);
			}
		}
	}
	
	static void getPrimeNumberList(int n){
		int i = 2;
		prime.add(i);
		
		while(i<=n){
			
			for(int j=0; j<prime.size(); j++){
				if(i%prime.get(j) == 0){
					break;
				}
				
				//	ArrayList 모두 체크 완료
				if( (j+1) == prime.size() ){
					prime.add(i);
				}
			}
			
			i++;
		}
		
//		for(Integer num : prime){
//			System.out.println(num);
//		}
	}

	static void getPrimeNumber(int n){
		
		int i = 2;
		boolean isPrime;
		
		while(i<=n){
			
			isPrime = true;
			
			for(int j=2; j<i; j++){
				if(i%j == 0){
					isPrime = false;
					break;
				}
				continue;
			}
			
			if(isPrime){
				System.out.println(i);
			}
			
			i++;
		}
	}
}
