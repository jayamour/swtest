
public class GCD_LCMtest {

	public static void main(String[] args) {
		
//		System.out.println(getGCD(12, 16));
//		System.out.println(getGCD2(210, 480));
//		System.out.println(getGCDbyEuclidean(480, 210));
		System.out.println(getGCDbyEuclidean2(480, 210));
		System.out.println(getGCDbyEuclidean2(210, 480));
		System.out.println(getGCDbyEuclidean2(1124681232, 468121612));
		System.out.println(getGCDbyEuclidean2(468121612, 1124681232));
	}
	
	static int getGCDbyEuclidean2(int a, int b){
		//	f(210, 480) = f(480, 210) = f(60, 30) = 30
		
		if(a%b == 0){
			return b;
		}else{
			return getGCDbyEuclidean2(b, a%b);
		}
	}
	
	static int getGCDbyEuclidean(int a, int b){
		
		//	f(480, 210) = f(210, 60) = f(60, 30) = 30
		int mod = a%b;
		//	mod = 60
		
		while(mod>0){
			
			a = b;
			b = mod;
			
			mod = a%b;
		}
				
		return b;
	}
	
	static int getGCD(int a, int b){
		int max_div = 1;
		
		int range = findMin(a, b);
		
		for(int i=1; i<=range; i++){
			
			if(a%i == 0 && b%i == 0){
				max_div = i;
			}
		}
		
		return max_div;
	}
	
	static int getGCD2(int a, int b){
		int max_div = 1;
		
		int range = findMin(a, b);
		
		for(int i=range; i>=1; i--){
			
			if(a%i == 0 && b%i == 0){
				max_div = i;
				break;
			}
		}
		
		return max_div;
	}
	
	static int findMin(int a, int b){
		if(a > b){
			return b;
		}else{
			return a;
		}
	}
}