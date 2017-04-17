
public class TopologialTest{
	
	static int N = 8;
	
	static int [][] array = {
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,1,0,0,0,0,0},
			{0,1,0,0,0,1,0,0,0},
			{0,0,0,0,1,0,0,1,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,1,0,1,0,0},
			{0,0,0,0,0,0,0,1,1},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,1,0}
	};
	
	static int[] visit;
	
	public static void main(String[] args){
		
		visit = new int[N+1];
		
		int i;
		
		//	1부터 출발
		for(i=1; i<=N; i++){
			
			//	방문하지 않은 노드 방문
			if(visit[i] == 0){
//				System.out.println(" i : " + i);
				visit(i);
			}
		}
		
		System.out.println();
	}
	
	static void visit(int i){
		
		//	가장 나중에 방문한 노드부터 출력
		System.out.print(" " + i);
		
		int j;
		
		//	i 노드 방문 체크
		visit[i] = 1;
		
		for(j=1; j<=N; j++){
			
			//	from i to j 연결되어 있고 j 노드를 방문하지 않았다면
			if(array[i][j] == 1 && visit[j] == 0){
				//	j로 이동
				visit(j);
			}
		}
	}
}