import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Farm implements Comparable<Farm> {
	
	int index;
	int distance;
	int post;
	
	public Farm(int index, int distance) {
		super();
		this.index = index;
		this.distance = distance;
	}
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public int getPost() {
		return post;
	}
	public void setPost(int post) {
		this.post = post;
	}
	
	@Override
	public int compareTo(Farm o) {
		// TODO Auto-generated method stub
		if(this.getDistance() > o.getDistance()){
			return 1;
		}
		return -1;
	}
}

public class CowPartySolution {
	
	static int test_case;
	
	static int farm;	//	1<=�냽�옣�쓽 �닔<=1000	
	static int road;	//	1<=�룄濡쒖쓽 �닔<=N*(N-1)
	static int party;	//	1<=�뙆�떚 �뿴由щ뒗 �냽�옣<=�냽�옣�쓽 �닔
	static int [][] roadMap;
	
//	static boolean [] visit;
	static int [][] distance;
	static int max;
	
	static PriorityQueue<Farm> pq;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("cowparty_input.txt"));
//		BufferedReader br = new BufferedReader(new FileReader("dijkstra_input.txt"));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		test_case = Integer.parseInt(br.readLine());
		
		for(int z=1; z<=test_case; z++){
			
			StringTokenizer st;
			
			st = new StringTokenizer(br.readLine());
			
			farm = Integer.parseInt(st.nextToken());
			road = Integer.parseInt(st.nextToken());
			party = Integer.parseInt(st.nextToken());
			
			roadMap = new int[farm+1][farm+1];
			
			int from;
			int to;
			int time;
			
	//		System.out.println("farm : " + farm + ", road : " + road + ", party : " + party);
			
			for(int i=1; i<=road; i++){
				st = new StringTokenizer(br.readLine());
				
				from = Integer.parseInt(st.nextToken());
				to = Integer.parseInt(st.nextToken());
				time = Integer.parseInt(st.nextToken());
				
	//			System.out.println("from : " + from + " to : " + to + " time : " + time);
				
				roadMap[from][to] = time;
			}
			
	//		printMatrix(roadMap);
			
			distance = new int[farm+1][farm+1];
	//		visit = new boolean[farm+1];
			
	//		Arrays.fill(distance, Integer.MAX_VALUE);
			for(int i=1; i<=farm; i++){
				for(int j=1; j<=farm; j++){
					distance[i][j] = Integer.MAX_VALUE;
				}
			}
	//		Arrays.fill(visit, false);
			
			go();
	//		go(2);
			System.out.println("#" + test_case + " "+ max);
		}
		br.close();
	}
	
	static void go(){
		
		for(int x=1; x<=farm; x++){
			
//			if(x == party){
				
				pq = new PriorityQueue<Farm>();
				
				distance[x][x] = 0;
				pq.offer(new Farm(x, distance[x][x]));
				
				while(!pq.isEmpty()){
					
					int cost = pq.peek().getDistance();
					int here = pq.peek().getIndex();
					pq.poll();
//					System.out.println("length of pq : " + pq.size());
					
//					System.out.println("cost : " + cost
//							+ ", distance[" + here + "] : " + distance[here]
//							+ ", here : " + here);
					
					//	湲곗〈�뿉 ���옣�맂 here源뚯��쓽 嫄곕━媛� �뜑 媛�源뚯슱 寃쎌슦
					if(cost > distance[x][here]){
						continue;
					}
					
//					System.out.print(here);
					
					for(int i=1; i<=farm; i++){
						//	�옄�떊怨쇱쓽 嫄곕━媛� �븘�땲硫댁꽌
//						System.out.println("distance[" + i + "] : " + distance[i] + ", distance[" + here + "] : " + distance[here]
//								+ ", roadMap[" + here + "][" + i + "] : " + roadMap[here][i]);
						if( (roadMap[here][i] != 0) &&
								(distance[x][i] > distance[x][here] + roadMap[here][i]) ){
							//	i源뚯��쓽 理쒕떒嫄곕━蹂대떎 here 嫄곗퀜�꽌 i源뚯��쓽 嫄곕━媛� �뜑 媛�源뚯슱 寃쎌슦
							//	理쒕떒嫄곕━ �뾽�뜲�씠�듃
							distance[x][i] = distance[x][here] + roadMap[here][i];
//							printArray(distance);
							pq.offer(new Farm(i, distance[x][i]));
						}
					}
				}
//			}
		}
		
//		System.out.println();
//		for(int i=1; i<=farm; i++){
//			System.out.print(distance[i] + " ");
//		}
//		System.out.println();
//		printMatrix(distance);
		
		max = distance[1][party] + distance[party][1];
		for(int i=2; i<=farm; i++){
			
			if(max < (distance[i][party] + distance[party][i])){
				max = distance[i][party] + distance[party][i];
			}
		}
	}
	
	static void back(int from){
		
	}
	
	static void printArray(int [] array){
		for(int i=1; i<=farm; i++){
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
	
	static void printMatrix(int [][] array){
		System.out.println();
		for(int i=1; i<=farm; i++){
			for(int j=1; j<=farm; j++){
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}