
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
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

		if(this.end > o.end) {
			return 1;
		} else {
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
			
			PriorityQueue<Work> pq = new PriorityQueue<Work>();
			
			ArrayList<Long> list; // = new ArrayList<Long>();
			
			long max = 0;
			
			for(int i=1; i<=N; i++){
				
				st = new StringTokenizer(br.readLine());
				
	//			s[i] = Long.parseLong(st.nextToken());
	//			e[i] = Long.parseLong(st.nextToken());
	//			c[i] = Long.parseLong(st.nextToken());
				
				long start = Long.parseLong(st.nextToken());
				long end = Long.parseLong(st.nextToken());
				long work = Long.parseLong(st.nextToken());
				
				if(max < end) {
					max = end;
				}
				
				pq.add(new Work(start, end, work));
			}
			
	//		dp = new long[(int)max+1];
	
			list = new ArrayList<Long>();
			
			//	ArrayList는 0부터 시작하니 +1
			for(int i=1; i<=max+1; i++){
				list.add((long) 0);
			}
			
	//		for(int i=1; i<=max; i++){
	//			
	//		}
			
	//		Arrays.fill(dp, 0);
			
			Work w; // = pq.poll();
			
	//		dp[(int)w.end] = w.profit;	//	dp[3] = 15;
			
			while(!pq.isEmpty()) {
				
				w = pq.poll();	//	1, 4, 20 => dp[4] = dp[1-1] + 20
				
	//			System.out.println("s : " + w.start + ", end : " + w.end + ", p : " + w.profit);
	
	//			long k = dp[(int) w.start - 1] + w.profit;
	//
	//			if (dp[(int) w.end] < k) {
	//				dp[(int) w.end] = k;
	//			}
				
				long k = list.get((int) (w.start - 1)) + w.profit;
				
				if( list.get((int) w.end) < k ) {
					list.set((int) w.end, k);
				}
			}
			
	//		System.out.println("#" + z + " " + dp[(int)max]);
			System.out.println("#" + z + " " + list.get((int) max));
		
		}
		
		br.close();
	}
}