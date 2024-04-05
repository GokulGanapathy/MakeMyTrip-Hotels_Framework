package Tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class listTestTry {

	public static void main(String[] args) {
		//[19080, 7948, 32200, 20418, 18832, 9416, 17600, 3163, 41424, 13391]
		List<Integer> prices = new ArrayList<>();
		int arr[]= {0,8,0,8,2,6,0,3,4,1};
		for(int i=0;i<arr.length;++i) {
			prices.add(arr[i]);
		}
		
		Collections.sort(prices);
		System.out.println(prices);
		int k=3;
		int score=0;
		for(int i=0;i<k;++i) {
			int a=Collections.max(prices);
			prices.remove(a);
			int b=Collections.max(prices);
			prices.remove(b);
			score = score +(a&b);
			System.out.println(score);
			System.out.println(prices);
			
		}
		System.out.println(score);
	}

}
