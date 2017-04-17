
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
		
		//	1���� ���
		for(i=1; i<=N; i++){
			
			//	�湮���� ���� ��� �湮
			if(visit[i] == 0){
//				System.out.println(" i : " + i);
				visit(i);
			}
		}
		
		System.out.println();
	}
	
	static void visit(int i){
		
		//	���� ���߿� �湮�� ������ ���
		System.out.print(" " + i);
		
		int j;
		
		//	i ��� �湮 üũ
		visit[i] = 1;
		
		for(j=1; j<=N; j++){
			
			//	from i to j ����Ǿ� �ְ� j ��带 �湮���� �ʾҴٸ�
			if(array[i][j] == 1 && visit[j] == 0){
				//	j�� �̵�
				visit(j);
			}
		}
	}
}