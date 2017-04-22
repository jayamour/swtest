import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
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
	
	ArrayList<Integer> beforeList;	//	이전 리스트
	
	ArrayList<Integer> nextList;	//	다음 리스트
	
	int sum;
	
	long temp;

	public Boiler(int i, long l) {
		index = i;
		this.temp = l;
		beforeList = new ArrayList<Integer>();
		nextList = new ArrayList<Integer>();
	}

	@Override
	public int compareTo(Boiler o) {
//		return o.sum - this.sum;
//		return o.out - this.out;
		return this.out - o.out;
	}

	@Override
	public String toString(){
		return String.valueOf("(" + index + ", " + temp + ")");
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
//		BufferedReader br = new BufferedReader(new FileReader("boiler_sample_input.txt"));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
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
				
				graph.get(from).add(to);
				
				boiler[to].beforeIdx = from;
				
				boiler[from].nextList.add(to);
				boiler[from].out++;
				
				boiler[to].beforeList.add(from);
				boiler[to].in++;
			}
			
			//	진입+진출차수 계산하여 sum에 저장
//			for(int i=1; i<=totalHouse; i++){
//				boiler[i].sum = boiler[i].in + boiler[i].out;
//			}
			
			max = 0;
			
			topologicalSort();
			
			System.out.println("#" + z + " " + max);
			
//			for(int i=1; i<=totalHouse; i++){
//				if(boiler[i].temp != 0){
//					System.out.print("i : " + i);
//				}
//			}
		}
		
		br.close();
	}
	
	static void topologicalSort(){
		
		PriorityQueue<Boiler> searchQ = new PriorityQueue<Boiler>();	//	탐색 큐
		PriorityQueue<Boiler> inQueue = new PriorityQueue<Boiler>();	//	탐색 큐
		
		Queue<Boiler> queue = new LinkedList<Boiler>();
		
//		for(int i=1; i<=totalHouse; i++){
//			System.out.println(boiler[i]);
//		}
		
		
		for(int i=1; i<=totalHouse; i++){
			
			if( boiler[i].in == 0 ){
				inQueue.offer(boiler[i]);
//			} else {
//				searchQ.offer(boiler[i]);
			}
			
			//	진출차수 0인 노드(리프노드) 큐 저장
			if( boiler[i].out == 0){
//				System.out.println("i : " + i);
				queue.add(boiler[i]);
			}
		}
		
		ArrayList<Integer> nextList;
		
		while(!queue.isEmpty()){
			
			Boiler from = queue.poll();
			
			//	빼야할 온도
			long temp = from.temp;
			
			if(z==8){
				System.out.println("start >>> " + from.index + ", temp : " + temp);
			}
			
			//	부모 노드로 이동
			int bef = from.beforeIdx;
						
			//	부모 노드에서 시작한 노드의 온도 빼기
			long befTemp = boiler[bef].temp;
			
			if( (befTemp - temp) < 0){
				boiler[bef].temp = 0;
			} else {
				boiler[bef].temp = boiler[bef].temp - temp;
			}
			
			//	부모 노드의 자식 노드에서 시작한 노드의 온도 빼기
			
			nextList = boiler[bef].nextList;
			
			for(int nextNode : nextList){
				
				long t = boiler[nextNode].temp;
				
				if(t == 0){
					continue;
				} else {
					
					if( (t-temp) < 0 ){
						boiler[nextNode].temp = 0;
					} else {
						boiler[nextNode].temp = t - temp;
					}
				}
			}
			
			if(z==8){
//				for(int i=1; i<=totalHouse; i++){
//					System.out.print(boiler[i] + ", ");
//				}
//				System.out.println();
			}
			
			//	부모 노드의 부모 노드 온도 올리기
			int befStart = boiler[bef].beforeIdx;
			
			if(befStart != 0){
				
				//	부모 노드의 부모 노드 온도
				long parentTemp = boiler[befStart].temp;
				
				if(parentTemp != 0) {
					
					//	시작한 노드의 온도 빼기
					if( (parentTemp - temp) < 0 ){
						boiler[befStart].temp = 0;
					} else {
						boiler[befStart].temp = parentTemp - temp;
					}
				}
			}
			
			max += temp;
				
			if(z==8){
				for(int i=1; i<=totalHouse; i++){
					System.out.print(boiler[i] + " ");
				}
				System.out.println();
			}
		}
		
		while(!searchQ.isEmpty()){
			
			Boiler from = searchQ.poll();
			
			//	start : 3
			int start = from.index;
			
			
			//	빼야할 온도
			long temp = from.temp;
			
			//	부모 노드로 이동
			int bef = from.beforeIdx;
						
			//	부모 노드에서 시작한 노드의 온도 빼기
			long befTemp = boiler[bef].temp;
			
			if( (befTemp - temp) < 0){
				boiler[bef].temp = 0;
			} else {
				boiler[bef].temp = boiler[bef].temp - temp;
			}
			
			//	부모 노드의 자식 노드에서 시작한 노드의 온도 빼기
			
			nextList = boiler[bef].nextList;
			
			for(int nextNode : nextList){
				
				long t = boiler[nextNode].temp;
				
				if(t == 0){
					continue;
				} else {
					
					if( (t-temp) < 0 ){
						boiler[nextNode].temp = 0;
					} else {
						boiler[nextNode].temp = t - temp;
					}
				}
			}
			
			if(z==4){
//				for(int i=1; i<=totalHouse; i++){
//					System.out.print(boiler[i] + ", ");
//				}
//				System.out.println();
			}
			
			//	부모 노드의 부모 노드 온도 올리기
			int befStart = boiler[bef].beforeIdx;
			
			if(befStart != 0){
				
				//	부모 노드의 부모 노드 온도
				long parentTemp = boiler[befStart].temp;
				
				if(parentTemp != 0) {
					
					//	시작한 노드의 온도 빼기
					if( (parentTemp - temp) < 0 ){
						boiler[befStart].temp = 0;
					} else {
						boiler[befStart].temp = parentTemp - temp;
					}
				}
			}
			
			max += temp;
				
				if(z==3){
//					System.out.println("tempMin ::: " + tempMin);
//					for(int i=1; i<=totalHouse; i++){
//						System.out.print(boiler[i] + " ");
//					}
//					System.out.println();
				}
//			}
		}

		
		while(!inQueue.isEmpty()){
			
			Boiler from = inQueue.poll();
			
			if(z==8){
				System.out.println(from.temp);
			}
	
			long tempMax = from.temp;
			
			for(int next : from.nextList){
				
				if( boiler[next].temp == 0){
					continue;
				}
				
				if(tempMax < boiler[next].temp){
					tempMax = boiler[next].temp;
				}
				
				boiler[next].temp = 0;
			}
			
			from.temp = 0;
			
			if(z==3){
//				System.out.println("tempMax ::: " + tempMax);
//				for(int i=1; i<=totalHouse; i++){
//					System.out.print(boiler[i] + " ");
//				}
//				System.out.println();
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