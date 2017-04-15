import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class City { //implements Comparable<City>{
	
	public int to;
	public int time;
	
	City() {
		
	}
	
	City(int to, int time){
		this.to = to;
		this.time = time;
	}
	
	public String toString(){
		return this.to + ", " + this.time;
	}
}

public class ShortestPath {
	
	static int test_case;
	
	static int N, M;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new FileReader("shortestpath_input.txt"));
		
		test_case = Integer.parseInt(br.readLine());
		
		for(int z=1; z<=test_case; z++){
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			ArrayList<ArrayList<City>> list = new ArrayList<ArrayList<City>>();
			
			for(int i=1; i<=(N+1); i++){
				list.add(new ArrayList<City>());
			}
			
			int from, to, time;
			
			for(int i=1; i<=M; i++){
			
				st = new StringTokenizer(br.readLine());
				
				from = Integer.parseInt(st.nextToken());
				to = Integer.parseInt(st.nextToken());
				time = Integer.parseInt(st.nextToken());
				
				System.out.println(from + ", " + to + ", " + time);
				
				list.get(from).add(new City(to, time));
			}
			
			for(int i=0; i<list.size(); i++){
				
				for(int j=0; j<list.get(i).size(); j++){
					System.out.println(i + " : " + list.get(i).get(j));
				}
			}
			
		}
		
		br.close();
	}
}