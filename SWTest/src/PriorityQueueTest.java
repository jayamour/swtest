import java.util.PriorityQueue;

class Person implements Comparable<Person> {
	
	int index;
	int age;
	
	Person(int index, int age){
		this.index = index;
		this.age = age;
	}
	
	public int compareTo(Person p){
		return p.age - this.age;
	}
	
	public String toString(){
		return "["+index+"] :: " + age; 
	}
}

public class PriorityQueueTest {
	
	static Person [] person;
	
	static int N;

	public static void main(String[] args) {
		
		N = 6;
		
		person = new Person[N+1];
		
		for(int i=1; i<=N; i++){
			person[i] = new Person(i, i * 10);
		}
		
		for(int i=1; i<=N; i++){
//			System.out.println(person[i]);
		}
		
		System.out.println("==========");
		
		PriorityQueue<Person> pq = new PriorityQueue<Person>();
		
		for(int i=1; i<=N; i++){
			pq.offer(person[i]);
		}
		
		person[3].age = 1000;
		
//		System.out.println(person[3]);
		
		while(!pq.isEmpty()){
			
			Person p = pq.poll();
			System.out.println(p);
		}
	}
	
	
}