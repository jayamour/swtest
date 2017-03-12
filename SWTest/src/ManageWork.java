
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Work implements Comparable<Work>{
	
	public long start;
	public long end;
	public long profit;
	
	Work() {
		
	}
	
	Work(long s, long e, long p){
		this.start = s;
		this.end = e;
		this.profit = p;
	}
	
	@Override
	public int compareTo(Work o) {

		if(this.end >= o.end) {
			return 1;
		}else if(this.end == o.end && this.start >= this.start){
			return 1;
		}else{
			return -1;
		}
	}
}

public class ManageWork {
	
	static int test_case;
	static int N;
	static long [] s;
	static long [] e;
	static long [] c;
	
	static long [] dp;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("work_input.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		test_case = Integer.parseInt(br.readLine());
		
		StringTokenizer st = null;
		
		for(int z=1; z<=test_case; z++){
		
			N = Integer.parseInt(br.readLine());	//	8
			
			s = new long[N+1];
			e = new long[N+1];
			c = new long[N+1];
			
			
			ArrayList<Work> list = new ArrayList<Work>();
			
			for(int i=1; i<=N; i++){
				
				st = new StringTokenizer(br.readLine());
				
				long start = Long.parseLong(st.nextToken());
				long end = Long.parseLong(st.nextToken());
				long work = Long.parseLong(st.nextToken());
				
				list.add(new Work(start, end, work));
			}
			
			Collections.sort(list);
			
			ArrayList<Long> result = new ArrayList<Long>();
			
			long max = 0;
			
			for(int i=0; i<list.size(); i++){
				result.add(list.get(i).profit);
				
				if(max < result.get(i)){
					max = result.get(i);
				}
			}
			
//			for(int i=0; i<result.size(); i++){
//				System.out.println("profit : " + result.get(i));
//			}
//
//			for(int i=0; i<list.size(); i++){
//				
//				System.out.println("s : " + list.get(i).start + ", end : " + list.get(i).end + ", p : " + list.get(i).profit);
//			}
			
			//	i~8
			for(int i=1; i<list.size(); i++){
				
				for(int j=0; j<i; j++){
					
					if(list.get(j).end < list.get(i).start){
						
						long k = result.get(j) + list.get(i).profit;
						
						if(result.get(i) < k){
							result.set(i, k);
							
							if(max < k){
								max = k;
							}
						}
						
					}
				}
				
			}
			
			
			System.out.println("#" + z + " " + max);
		
		}
		
		br.close();
	}
}