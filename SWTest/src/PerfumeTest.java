import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Perfume implements Comparable<Perfume> {

	public int duration;
	public int hour;

	Perfume(int d, int h) {
		this.duration = d;
		this.hour = h;
	}

	public int compareTo(Perfume p) {
		if (this.hour == p.hour) {
			return this.duration - p.duration;
		} else {
			return this.hour - p.hour;
		}
	}

	public String toString() {

		return duration + " " + hour;
	}
}

public class PerfumeTest {

	static int perfumeCnt;

	static Perfume[] p;

	static int[][] dp;
	static int[][] path;

	static int[] hour;
	
	static int[] d;
	
	static int[] max;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("perfume_input.txt"));

		int test_case = Integer.parseInt(br.readLine());

		for (int z = 1; z <= test_case; z++) {

			perfumeCnt = Integer.parseInt(br.readLine());

			p = new Perfume[perfumeCnt + 1];
			p[0] = new Perfume(-1, -1);
			hour = new int[perfumeCnt + 1];
			max = new int[perfumeCnt + 1];
			d = new int[perfumeCnt + 1];

			StringTokenizer st;

			for (int i = 1; i <= perfumeCnt; i++) {
				st = new StringTokenizer(br.readLine());

				p[i] = new Perfume(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			// *****************************
			if (z == 4) {

				Arrays.sort(p);

				for (int i = 0; i <= perfumeCnt; i++) {
					hour[i] = p[i].hour;
				}

				printPerfume(p);

				path = new int[perfumeCnt + 1][perfumeCnt + 1];

				// dp[1][2];

				for (int i = 1; i <= perfumeCnt; i++) {

					for (int j = 1; j <= perfumeCnt; j++) {

						int result = p[i].hour;

						if (i == j) {
							path[i][j] = -1;
						} else {

							if (p[i].hour <= p[j].duration) {
								result = result + p[j].hour;
							}

							path[i][j] = result;
						}
					}
				}

				printArr(hour);
				
				//	2 2 3 3 6 8 8 9 11 16 
				
				for(int i=1; i<=perfumeCnt; i++){
					
					
				}
				
				System.out.println("... " + binarySearch(4));
				
				System.out.println(p[1]);
				
				System.out.println(findMax(p[1].duration));

				// printArray(path);

			}
			// *****************************
		}

		br.close();
	}
	
	static int findMax(int n){
		
		return binarySearch(p[n].duration);
		
//		return 0;
	}

	static int binarySearch(int target) {

		int first = 0;
		int last = hour.length;
		int mid;

		while (first <= last) {

			mid = (first + last) / 2;

			if (target == hour[mid]) {

				for(int i=mid; i>=first; i--){
					
					if(hour[i-1] == target){
						continue;
					}else{
						return i;
					}
				}
			}

			else {

				if (target < hour[mid]) {

					last = mid - 1;
				} else {				

					first = mid + 1;
				}
			}
			// if target is not existed,
			// not occur reversal of the first and last
		}

		return first;
		// when target is not existed
	}

	static void printPerfume(Perfume[] s) {
		for (int i = 1; i < s.length; i++) {
			System.out.println(s[i]);
		}
	}

	static void printArr(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	static void printArray(int[][] arr) {
		for (int i = 1; i < arr.length; i++) {
			for (int j = 1; j < arr.length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}