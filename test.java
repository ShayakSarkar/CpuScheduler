import java.util.LinkedList;
import java.util.Queue;

public class test{
	public static void main(String args[]){
		Queue<Integer> q=new LinkedList<Integer>();
		q.add(1);
		q.add(2);
		q.add(3);
		q.add(4);
		q.add(5);
		q.add(6);
		System.out.println(q);
		q.remove();
		q.remove();
		System.out.println(q);
	}
}


