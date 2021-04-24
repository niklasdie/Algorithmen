package Uebungen.u04;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;

public class ArrayListTest {

	public static void main(String[] args) {
		List<Integer> x = new ArrayList<>();
		long start, end;
		start = System.currentTimeMillis();

		for (int i = 0; i < 1000000; i++) {
//			if (i%10000==0) {
//				System.out.println(i);
//			}
			x.add(x.size()/2, i);
		}

		end = System.currentTimeMillis();
		end -= start;
		System.out.println(end + " milisec");
	}

}
