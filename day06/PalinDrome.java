/*Input a number, judge the number is a palindrome number or not*/

import java.util.Scanner;

public class PalinDrome{
	public static void main(String args[]){
	System.out.println("Please input the test number!");
	Scanner num = new Scanner(System.in);
	int x = num.nextInt();
	System.out.println("Your input number is " + x);

	int judgeNumber = 0;

	if((x < 0) || (x % 10 == 0 && x != 0)){
		System.out.println("It is not a palindrome number!");
	}
	
	while(x > judgeNumber){
		judgeNumber = judgeNumber * 10 + x % 10;
		x /= 10;
	}

	if((x == judgeNumber) || (x == judgeNumber / 10)){
		System.out.println("It is a palindrome number!");
	}else{
		System.out.println("It is not a palindrome number!");
	}
	}
}
