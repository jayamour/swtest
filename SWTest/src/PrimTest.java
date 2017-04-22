
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
	
	int index;
	int cost;
	
	Edge (int i, int c) {
		this.index = i;
		this.cost = c;
	}

	@Override
	public int compareTo(Edge e) {
		// TODO Auto-generated method stub
		return this.cost = e.cost;
	}
}

public class PrimTest {
	
	static int z;
	
	static int cityCnt;
	
	static int roadCnt;
	
	static int K;
	
	static ArrayList<ArrayList<Edge>> remove;
	
	static ArrayList<ArrayList<Edge>> build;
	
	static PriorityQueue<Edge> pq;
	
	static int [] distance;
	static boolean [] visited;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new FileReader("prim_input.txt"));
		
		int test_case = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		
		for(z=1; z<=test_case; z++){
			
			st = new StringTokenizer(br.readLine());
			
			cityCnt = Integer.parseInt(st.nextToken());
			
			roadCnt = Integer.parseInt(st.nextToken());
			
			K = Integer.parseInt(st.nextToken());
			
			remove = new ArrayList<ArrayList<Edge>>();
			
			for(int i=1; i<=(cityCnt+1); i++){
				remove.add(new ArrayList<Edge>());
			}
			
			build = new ArrayList<ArrayList<Edge>>();
			
			for(int i=1; i<=(cityCnt+1); i++){
				build.add(new ArrayList<Edge>());
			}
			
			distance = new int[cityCnt+1];
			visited = new boolean[cityCnt+1];
			
			int from, to, cost;
			
			for(int i=1; i<=roadCnt; i++){
				st = new StringTokenizer(br.readLine());

				from = Integer.parseInt(st.nextToken());
				to = Integer.parseInt(st.nextToken());
				cost = Integer.parseInt(st.nextToken());
				
				remove.get(from).add(new Edge(to, cost));
				remove.get(to).add(new Edge(from, cost));
			}
			
			for(int i=1; i<=K; i++){
				st = new StringTokenizer(br.readLine());

				from = Integer.parseInt(st.nextToken());
				to = Integer.parseInt(st.nextToken());
				cost = Integer.parseInt(st.nextToken());
				
				build.get(from).add(new Edge(to, cost));
				build.get(to).add(new Edge(from, cost));
			}
			
			prim(1);
		}
		
		br.close();
	}
	
	static void prim(int start){
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		Arrays.fill(visited, false);
		
		pq = new PriorityQueue<Edge>();
		
		distance[start] = 0;
		
		pq.offer(new Edge(start, 0));
		
		while(!pq.isEmpty()){
			
			Edge e = pq.poll();
			
			int here = e.index;
			
			if(visited[here] == true){
				continue;
			}
			
			visited[here] = true;
			
			int cost = e.cost;
			
			for(Edge list : remove.get(here)){
				
				
			}
			
		}
	}
}