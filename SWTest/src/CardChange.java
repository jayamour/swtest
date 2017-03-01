import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Card implements Comparable<Card> {
	int number;
	int location;
	
	Card(int n, int l){
		this.number = n;
		this.location = l;
	}

	@Override
	public int compareTo(Card o) {
		// TODO Auto-generated method stub
		if(this.number > o.number){
			return 1;
		}else{
			return -1;
		}
	}
}

public class CardChange {

	static int test_case;
	static int N; // 1 ≤ N ≤ 1,000,000
	static int M; // 1 ≤ M ≤ N

	static int cnt; // 뒤집은 카드 개수
	static int[] card;
	
	static PriorityQueue<Card> pq = new PriorityQueue<Card>();

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("cardchange_input.txt"));
		// BufferedReader br = new BufferedReader(new
		// FileReader("cardchange_test.txt"));

		test_case = Integer.parseInt(br.readLine());

		StringTokenizer st;

		for (int z = 1; z <= 1; z++) {
//		for (int z = 1; z <= test_case; z++) {

			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			card = new int[N + 1];

			st = new StringTokenizer(br.readLine());

			int sum = 0;

			int divide = N / M;

			if ((N % M) == 0) {
				divide = N / M;
			} else {
				divide = N / M + 1;
			}

			// System.out.println("::: " + divide);

			boolean[] oddStart = new boolean[M + 1];
			boolean[] evenStart = new boolean[M + 1];

			Arrays.fill(evenStart, true);
			Arrays.fill(oddStart, true);

			int[][] toCnt = new int[3][M + 1];

			int[][] startPoint = new int[3][M + 1];
			
			int [] select = new int[M+1];

			for (int i = 1; i <= N; i++) {
				card[i] = Integer.parseInt(st.nextToken());

				if (i % M == 0) {

					// 세번째 값

					if ((card[i] & 1) == 0) {
						toCnt[1][M] = toCnt[1][M] + 1;

						if (oddStart[M]) {
							startPoint[1][M] = i;
							oddStart[M] = false;
						}
					} else {
						toCnt[2][M] = toCnt[2][M] + 1;

						if (evenStart[M]) {
							startPoint[2][M] = i;
							evenStart[M] = false;
						}
					}

				} else {

					// 처음 값이 짝수일 경우

					if ((card[i] & 1) == 0) {
						toCnt[1][i % M] = toCnt[1][i % M] + 1;

						if (oddStart[i % M]) {
							startPoint[1][i % M] = i;
							oddStart[i % M] = false;
						}

					} else {
						toCnt[2][i % M] = toCnt[2][i % M] + 1;

						if (evenStart[i % M]) {
							startPoint[2][i % M] = i;
							evenStart[i % M] = false;
						}
					}
				}
			}
			printArray(card);
			printDoubleArray(toCnt);
			
			System.out.println("--------------------");
			printStartPoint(startPoint);
			System.out.println("====================");
			
			
			
			Card cards;
			
			for(int i=1; i<=M; i++){
				if(toCnt[1][i] < toCnt[2][i]) {
					select[i] = 1;
				} else if(toCnt[1][i] > toCnt[2][i]) {
					select[i] = 2;
				} else {
					cards = new Card(toCnt[1][i], i);
					pq.add(cards);
//					continue;
				}
			}
			
			for(int i=1; i<=M; i++){
				System.out.print(select[i] + " ");
			}
			System.out.println();
			
			Card c1 = new Card(5, 1);
			Card c2 = new Card(10, 2);
			Card c3 = new Card(7, 3);
			
			pq.clear();
			
			pq.add(c1);
			pq.add(c2);
			pq.add(c3);
			
			System.out.println(pq.poll().number);
			System.out.println(pq.poll().location);
		}

		br.close();
	}

	static void printStartPoint(int[][] arr) {
		
		for (int i = 1; i <= 2; i++) {
			for (int j = 1; j <= M; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	static void printDoubleArray(int[][] arr) {
		for (int i = 1; i <= 2; i++) {
			for (int j = 1; j <= M; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	static void sum() {

		int sum = 0;

		for (int i = 1; i <= N; i++) {
			sum = sum + card[i];

			// 1부터 M까지의 합
			if (i == M) {
				System.out.println("sum : " + sum);

				// 2의 배수가 아닐 경우 제일 처음 카드 뒤집기
				if (sum % 2 != 0) {
					card[i] = card[i] + 1;
					cnt++;
				}
			}
		}

		// System.out.println("sum : " + sum);
	}

	static void printArray(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}