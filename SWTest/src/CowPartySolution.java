import java.io.BufferedReader;
import java.io.FileReader;
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
	
	static int farm;	//	1<=농장의 수<=1000	
	static int road;	//	1<=도로의 수<=N*(N-1)
	static int party;	//	1<=파티 열리는 농장<=농장의 수
	static int [][] roadMap;
	
	static boolean [] visit;
	static int [] distance;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("cowparty_input.txt"));
//		BufferedReader br = new BufferedReader(new FileReader("dijkstra_input.txt"));
		
		test_case = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		farm = Integer.parseInt(st.nextToken());
		road = Integer.parseInt(st.nextToken());
		party = Integer.parseInt(st.nextToken());
		
		roadMap = new int[farm+1][farm+1];
		
		int from;
		int to;
		int time;
		
		System.out.println("farm : " + farm + ", road : " + road + ", party : " + party);
		
		for(int i=1; i<=road; i++){
			st = new StringTokenizer(br.readLine());
			
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			time = Integer.parseInt(st.nextToken());
			
//			System.out.println("from : " + from + " to : " + to + " time : " + time);
			
			roadMap[from][to] = time;
		}
		
		printMatrix(roadMap);
		
		distance = new int[farm+1];
		visit = new boolean[farm+1];
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		Arrays.fill(visit, false);
		
		go(1);
//		go(2);
		
		br.close();
	}
	
	static void go(int from){
		PriorityQueue<Farm> pq = new PriorityQueue<Farm>();
		
		distance[from] = 0;
		pq.offer(new Farm(from, distance[from]));
		
		while(!pq.isEmpty()){
			
			int cost = pq.peek().getDistance();
			int here = pq.peek().getIndex();
			pq.poll();
			
//			System.out.println("cost : " + cost
//					+ ", distance[" + here + "] : " + distance[here]
//					+ ", here : " + here);
			
			//	기존에 저장된 here까지의 거리가 더 가까울 경우
			if(cost > distance[here]){
				continue;
			}
			
//			System.out.print(here);
			
			for(int i=1; i<=farm; i++){
				//	자신과의 거리가 아니면서
//				System.out.println("distance[" + i + "] : " + distance[i] + ", distance[" + here + "] : " + distance[here]
//						+ ", roadMap[" + here + "][" + i + "] : " + roadMap[here][i]);
				if( (roadMap[here][i] != 0) &&
						(distance[i] > distance[here] + roadMap[here][i]) ){
					//	i까지의 최단거리보다 here 거쳐서 i까지의 거리가 더 가까울 경우
					//	최단거리 업데이트
					distance[i] = distance[here] + roadMap[here][i];
//					printArray(distance);
					pq.offer(new Farm(i, distance[i]));
					System.out.println("length of pq : " + pq.size());
				}
			}
		}
		
		System.out.println();
		for(int i=1; i<=farm; i++){
			System.out.print(distance[i] + " ");
		}
		System.out.println();
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