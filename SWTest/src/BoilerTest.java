import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Boiler implements Comparable<Boiler> {
	
	int index;
	int in;
	int out;
	
	int beforeIdx;
	
	int sum = in + out;
	
	long temp;

	public Boiler(int i, long l) {
		index = i;
		this.temp = l;
	}

	@Override
	public int compareTo(Boiler o) {
//		return (int)(o.temp - this.temp);
		return o.sum - this.sum;
	}

	@Override
	public String toString(){
		return String.valueOf("(" + index + ", " + sum + ")");
	}
}

public class BoilerTest {
	
	static int z;
	
	static int totalHouse;
	static int totalPipe;
	
	static Boiler [] boiler;
	
	static long [] temp;
	
	static int [] indegree;
	
	static long max;
	
//	ArrayList<ArrayList<City>> list = new ArrayList<ArrayList<City>>();
	
	static ArrayList<ArrayList<Integer>> graph;	//	인접 리스트 그래프

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new FileReader("boiler_input.txt"));
		
		int test_case;
		
		test_case = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		
		for(z=1; z<=test_case; z++){
			
			st = new StringTokenizer(br.readLine());
			
			totalHouse = Integer.parseInt(st.nextToken());
			totalPipe = Integer.parseInt(st.nextToken());
			
			temp = new long[totalHouse+1];
			
			boiler = new Boiler[totalHouse+1];
			
			st = new StringTokenizer(br.readLine());
			
			for(int i=1; i<=totalHouse; i++){
				temp[i] = Long.parseLong(st.nextToken());
				boiler[i] = new Boiler(i, temp[i]);
			}
			
			int from, to;
			
			graph = new ArrayList<ArrayList<Integer>>();

			for(int i=1; i<=(totalHouse+1); i++){
				graph.add(new ArrayList<Integer>());
			}
			
			indegree = new int[totalHouse+1];
			
			for(int i=1; i<=totalPipe; i++){
				st = new StringTokenizer(br.readLine());
				
				from = Integer.parseInt(st.nextToken());
				to = Integer.parseInt(st.nextToken());
				
//				if(from > to){
//					
//					int tmp = from;
//					from = to;
//					to = tmp;
//				}
				
				graph.get(from).add(to);
				
				boiler[from].out++;
				boiler[from].sum = boiler[from].in + boiler[from].out;
				
				boiler[to].in++;
				boiler[to].sum = boiler[to].in + boiler[to].out;
				
				boiler[to].beforeIdx = from;
				
				indegree[to]++;
			}
			
//			for(int i=1; i<=totalHouse; i++){
//				System.out.print(boiler[i] + " ");
//			}
//			System.out.println();
			
			max = 0;
			
//			for(int i=1; i<graph.size(); i++){
//				
//				if(graph.get(i).size() != 0){
//					for(int node : graph.get(i)){
//						System.out.print(node + " ");
//					}
//					System.out.println();
//				}
//			}
			
//			if(z==3){
//				printArray(indegree);
//			}
			
			topologicalSort();
			
			System.out.println("#" + z + " " + max);
		}
		
		br.close();
	}
	
	static void topologicalSort(){
		
//		PriorityQueue<Integer> searchQ = new PriorityQueue<Integer>();	//	탐색 큐
//		PriorityQueue<Integer> resultQ = new PriorityQueue<Integer>();	//	결과 큐
		
		PriorityQueue<Boiler> searchQ = new PriorityQueue<Boiler>();	//	탐색 큐
		PriorityQueue<Boiler> inQueue = new PriorityQueue<Boiler>();	//	탐색 큐
		PriorityQueue<Boiler> resultQ = new PriorityQueue<Boiler>();	//	결과 큐
		
//		Queue<Integer> searchQ = new LinkedList<Integer>();	//	탐색 큐
//		Queue<Integer> resultQ = new LinkedList<Integer>();	//	결과 큐

		for(int i=1; i<=totalHouse; i++){
			
			searchQ.offer(boiler[i]);
			
//			//	진입차수 0인 노드 탐색 큐 넣기
//			if(indegree[i] == 0){
//				//	시작점
//				searchQ.offer(i);
//			}
		}
		
		int first = 0;
		
		while(!searchQ.isEmpty()){
			
			//	탐색 큐에 있는 값 꺼내기
//			int from = searchQ.poll();
			
			Boiler from = searchQ.poll();
			
			if(from.in == 0){
				inQueue.add(from);
				continue;
			}
			
//			//	결과 큐에 넣기
//			resultQ.offer(from);
			
			//	결과 큐에 넣기
//			resultQ.offer(boiler[from]);
//			resultQ.offer(from);
			
			//	linkNode = 진입노드가 0인 노드와 연결되어 있던 노드
//			for(int linkNode : graph.get(from)){
//				
//				//	진입차수 감소
//				indegree[linkNode] = indegree[linkNode] - 1;
//				
//				if(indegree[linkNode] == 0){
//					searchQ.offer(linkNode);
//				}
//			}
			
			//	start : 3
			int start = from.index;
	
			long tempMax = 0;
			
			
			//	max 찾기
			for(int linkNode : graph.get(start)){
				
//				System.out.println("linkNode ::: " + linkNode);
				if(tempMax < boiler[linkNode].temp){
					tempMax = boiler[linkNode].temp;
				}
				
				boiler[linkNode].temp = 0;
			}
			
			long y = boiler[start].temp;
			
			if( (y - tempMax) < 0){
				boiler[start].temp = 0;
			}else{
				boiler[start].temp = y - tempMax;
			}
			
			//	이전 노드가 존재할 경우 이전 노드의 온도도 올림
			if(boiler[start].beforeIdx != 0){
				
				long x = boiler[boiler[start].beforeIdx].temp;
				
				if(x > 0){
				
					//	14-17
					if( (x - tempMax) < 0 ){
						boiler[boiler[start].beforeIdx].temp = 0;
					}else{
						boiler[boiler[start].beforeIdx].temp = x - tempMax;
//						searchQ.add(boiler[boiler[start].beforeIdx]);
					}
				}
			}
			
//			System.out.println("tempMax :: " + tempMax);
			max = max + tempMax;
		}

		
		while(!inQueue.isEmpty()){
			
			Boiler from = inQueue.poll();
			
//			System.out.println("inQueue from : " + from);
			
			int start = from.index;
	
			long tempMax = from.temp;
			
			//	max 찾기
			for(int linkNode : graph.get(start)){
				
				if(tempMax < boiler[linkNode].temp){
					tempMax = boiler[linkNode].temp;
				}
				
				boiler[linkNode].temp = 0;
			}
			
			long y = boiler[start].temp;
			
//			System.out.println("<<<< " + tempMax + " >>>>");
			
			if( (y - tempMax) < 0){
				boiler[start].temp = 0;
			}else{
				boiler[start].temp = y - tempMax;
			}
			
			//	진입차수 0인 노드
			max = max + tempMax;
		}
	}
	
	static void printArray(int [] array){
		System.out.println("=== indegree ===");
		for(int i=1; i<array.length; i++){
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
}